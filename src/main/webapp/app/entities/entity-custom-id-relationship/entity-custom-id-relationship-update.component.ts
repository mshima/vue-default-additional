import { type Ref, computed, defineComponent, inject, ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import EntityCustomIdRelationshipService from './entity-custom-id-relationship.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import EntityCustomIdService from '@/entities/entity-custom-id/entity-custom-id.service';
import { type IEntityCustomId } from '@/shared/model/entity-custom-id.model';
import EntityCustomIdMapsIdService from '@/entities/entity-custom-id-maps-id/entity-custom-id-maps-id.service';
import { type IEntityCustomIdMapsId } from '@/shared/model/entity-custom-id-maps-id.model';
import { EntityCustomIdRelationship, type IEntityCustomIdRelationship } from '@/shared/model/entity-custom-id-relationship.model';

export default defineComponent({
  name: 'EntityCustomIdRelationshipUpdate',
  setup() {
    const entityCustomIdRelationshipService = inject('entityCustomIdRelationshipService', () => new EntityCustomIdRelationshipService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const entityCustomIdRelationship: Ref<IEntityCustomIdRelationship> = ref(new EntityCustomIdRelationship());

    const entityCustomIdService = inject('entityCustomIdService', () => new EntityCustomIdService());

    const entityCustomIds: Ref<IEntityCustomId[]> = ref([]);

    const entityCustomIdMapsIdService = inject('entityCustomIdMapsIdService', () => new EntityCustomIdMapsIdService());

    const entityCustomIdMapsIds: Ref<IEntityCustomIdMapsId[]> = ref([]);
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'en'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveEntityCustomIdRelationship = async entityCustomIdRelationshipRelatedId => {
      try {
        const res = await entityCustomIdRelationshipService().find(entityCustomIdRelationshipRelatedId);
        entityCustomIdRelationship.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.entityCustomIdRelationshipId) {
      retrieveEntityCustomIdRelationship(route.params.entityCustomIdRelationshipId);
    }

    const initRelationships = () => {
      entityCustomIdService()
        .retrieve()
        .then(res => {
          entityCustomIds.value = res.data;
        });
      entityCustomIdMapsIdService()
        .retrieve()
        .then(res => {
          entityCustomIdMapsIds.value = res.data;
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
    const v$ = useVuelidate(validationRules, entityCustomIdRelationship as any);
    v$.value.$validate();

    return {
      entityCustomIdRelationshipService,
      alertService,
      entityCustomIdRelationship,
      previousState,
      isSaving,
      currentLanguage,
      entityCustomIds,
      entityCustomIdMapsIds,
      v$,
      t$,
    };
  },
  created(): void {
    this.entityCustomIdRelationship.manyToManies = [];
    this.entityCustomIdRelationship.manyToManyMapsIds = [];
  },
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.entityCustomIdRelationship.relatedId) {
        this.entityCustomIdRelationshipService()
          .update(this.entityCustomIdRelationship)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jhipsterVueApp.entityCustomIdRelationship.updated', { param: param.relatedId }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.entityCustomIdRelationshipService()
          .create(this.entityCustomIdRelationship)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(
              this.t$('jhipsterVueApp.entityCustomIdRelationship.created', { param: param.relatedId }).toString(),
            );
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
