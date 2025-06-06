import { type Ref, computed, defineComponent, inject, ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import EntityUuidIdService from './entity-uuid-id.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import EntityUuidIdRelationshipService from '@/entities/entity-uuid-id-relationship/entity-uuid-id-relationship.service';
import { type IEntityUuidIdRelationship } from '@/shared/model/entity-uuid-id-relationship.model';
import { EntityUuidId, type IEntityUuidId } from '@/shared/model/entity-uuid-id.model';

export default defineComponent({
  name: 'EntityUuidIdUpdate',
  setup() {
    const entityUuidIdService = inject('entityUuidIdService', () => new EntityUuidIdService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const entityUuidId: Ref<IEntityUuidId> = ref(new EntityUuidId());

    const entityUuidIdRelationshipService = inject('entityUuidIdRelationshipService', () => new EntityUuidIdRelationshipService());

    const entityUuidIdRelationships: Ref<IEntityUuidIdRelationship[]> = ref([]);
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'en'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveEntityUuidId = async entityUuidIdId => {
      try {
        const res = await entityUuidIdService().find(entityUuidIdId);
        entityUuidId.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.entityUuidIdId) {
      retrieveEntityUuidId(route.params.entityUuidIdId);
    }

    const initRelationships = () => {
      entityUuidIdRelationshipService()
        .retrieve()
        .then(res => {
          entityUuidIdRelationships.value = res.data;
        });
    };

    initRelationships();

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      entityUuidIdMapsId: {},
      oneToOneBack: {},
      manyToOneBacks: {},
      manyToManyBacks: {},
    };
    const v$ = useVuelidate(validationRules, entityUuidId as any);
    v$.value.$validate();

    return {
      entityUuidIdService,
      alertService,
      entityUuidId,
      previousState,
      isSaving,
      currentLanguage,
      entityUuidIdRelationships,
      v$,
      t$,
    };
  },
  created(): void {
    this.entityUuidId.manyToManyBacks = [];
  },
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.entityUuidId.id) {
        this.entityUuidIdService()
          .update(this.entityUuidId)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jhipsterVueApp.entityUuidId.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.entityUuidIdService()
          .create(this.entityUuidId)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jhipsterVueApp.entityUuidId.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },

    getSelected(selectedVals, option, pkField = 'id'): any {
      if (selectedVals) {
        return selectedVals.find(value => option[pkField] === value[pkField]) ?? option;
      }
      return option;
    },
  },
});
