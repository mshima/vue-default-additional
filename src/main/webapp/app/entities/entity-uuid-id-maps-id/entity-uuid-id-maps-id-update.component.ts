import { type Ref, computed, defineComponent, inject, ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import EntityUuidIdMapsIdService from './entity-uuid-id-maps-id.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import EntityUuidIdService from '@/entities/entity-uuid-id/entity-uuid-id.service';
import { type IEntityUuidId } from '@/shared/model/entity-uuid-id.model';
import EntityUuidIdRelationshipService from '@/entities/entity-uuid-id-relationship/entity-uuid-id-relationship.service';
import { type IEntityUuidIdRelationship } from '@/shared/model/entity-uuid-id-relationship.model';
import { EntityUuidIdMapsId, type IEntityUuidIdMapsId } from '@/shared/model/entity-uuid-id-maps-id.model';

export default defineComponent({
  name: 'EntityUuidIdMapsIdUpdate',
  setup() {
    const entityUuidIdMapsIdService = inject('entityUuidIdMapsIdService', () => new EntityUuidIdMapsIdService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const entityUuidIdMapsId: Ref<IEntityUuidIdMapsId> = ref(new EntityUuidIdMapsId());

    const entityUuidIdService = inject('entityUuidIdService', () => new EntityUuidIdService());

    const entityUuidIds: Ref<IEntityUuidId[]> = ref([]);

    const entityUuidIdRelationshipService = inject('entityUuidIdRelationshipService', () => new EntityUuidIdRelationshipService());

    const entityUuidIdRelationships: Ref<IEntityUuidIdRelationship[]> = ref([]);
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'en'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveEntityUuidIdMapsId = async entityUuidIdMapsIdId => {
      try {
        const res = await entityUuidIdMapsIdService().find(entityUuidIdMapsIdId);
        entityUuidIdMapsId.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.entityUuidIdMapsIdId) {
      retrieveEntityUuidIdMapsId(route.params.entityUuidIdMapsIdId);
    }

    const initRelationships = () => {
      entityUuidIdService()
        .retrieve()
        .then(res => {
          entityUuidIds.value = res.data;
        });
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
      entityUuidId: {},
      oneToOneMapsIdBack: {},
      manyToOneMapsIdBacks: {},
      manyToManyMapsIdBacks: {},
    };
    const v$ = useVuelidate(validationRules, entityUuidIdMapsId as any);
    v$.value.$validate();

    return {
      entityUuidIdMapsIdService,
      alertService,
      entityUuidIdMapsId,
      previousState,
      isSaving,
      currentLanguage,
      entityUuidIds,
      entityUuidIdRelationships,
      v$,
      t$,
    };
  },
  created(): void {
    this.entityUuidIdMapsId.manyToManyMapsIdBacks = [];
  },
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.entityUuidIdMapsId.id) {
        this.entityUuidIdMapsIdService()
          .update(this.entityUuidIdMapsId)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jhipsterVueApp.entityUuidIdMapsId.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.entityUuidIdMapsIdService()
          .create(this.entityUuidIdMapsId)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jhipsterVueApp.entityUuidIdMapsId.created', { param: param.id }).toString());
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
