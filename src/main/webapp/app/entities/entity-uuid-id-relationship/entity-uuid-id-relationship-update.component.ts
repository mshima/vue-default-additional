import { type Ref, computed, defineComponent, inject, ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import EntityUuidIdRelationshipService from './entity-uuid-id-relationship.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import EntityUuidIdService from '@/entities/entity-uuid-id/entity-uuid-id.service';
import { type IEntityUuidId } from '@/shared/model/entity-uuid-id.model';
import EntityUuidIdMapsIdService from '@/entities/entity-uuid-id-maps-id/entity-uuid-id-maps-id.service';
import { type IEntityUuidIdMapsId } from '@/shared/model/entity-uuid-id-maps-id.model';
import { EntityUuidIdRelationship, type IEntityUuidIdRelationship } from '@/shared/model/entity-uuid-id-relationship.model';

export default defineComponent({
  name: 'EntityUuidIdRelationshipUpdate',
  setup() {
    const entityUuidIdRelationshipService = inject('entityUuidIdRelationshipService', () => new EntityUuidIdRelationshipService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const entityUuidIdRelationship: Ref<IEntityUuidIdRelationship> = ref(new EntityUuidIdRelationship());

    const entityUuidIdService = inject('entityUuidIdService', () => new EntityUuidIdService());

    const entityUuidIds: Ref<IEntityUuidId[]> = ref([]);

    const entityUuidIdMapsIdService = inject('entityUuidIdMapsIdService', () => new EntityUuidIdMapsIdService());

    const entityUuidIdMapsIds: Ref<IEntityUuidIdMapsId[]> = ref([]);
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'en'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveEntityUuidIdRelationship = async entityUuidIdRelationshipId => {
      try {
        const res = await entityUuidIdRelationshipService().find(entityUuidIdRelationshipId);
        entityUuidIdRelationship.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.entityUuidIdRelationshipId) {
      retrieveEntityUuidIdRelationship(route.params.entityUuidIdRelationshipId);
    }

    const initRelationships = () => {
      entityUuidIdService()
        .retrieve()
        .then(res => {
          entityUuidIds.value = res.data;
        });
      entityUuidIdMapsIdService()
        .retrieve()
        .then(res => {
          entityUuidIdMapsIds.value = res.data;
        });
    };

    initRelationships();

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      oneToOne: {},
      oneToOneMapsId: {},
      manyToOne: {},
      manyToOneMapsId: {},
      manyToManies: {},
      manyToManyMapsIds: {},
    };
    const v$ = useVuelidate(validationRules, entityUuidIdRelationship as any);
    v$.value.$validate();

    return {
      entityUuidIdRelationshipService,
      alertService,
      entityUuidIdRelationship,
      previousState,
      isSaving,
      currentLanguage,
      entityUuidIds,
      entityUuidIdMapsIds,
      v$,
      t$,
    };
  },
  created(): void {
    this.entityUuidIdRelationship.manyToManies = [];
    this.entityUuidIdRelationship.manyToManyMapsIds = [];
  },
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.entityUuidIdRelationship.id) {
        this.entityUuidIdRelationshipService()
          .update(this.entityUuidIdRelationship)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jhipsterVueApp.entityUuidIdRelationship.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.entityUuidIdRelationshipService()
          .create(this.entityUuidIdRelationship)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jhipsterVueApp.entityUuidIdRelationship.created', { param: param.id }).toString());
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
