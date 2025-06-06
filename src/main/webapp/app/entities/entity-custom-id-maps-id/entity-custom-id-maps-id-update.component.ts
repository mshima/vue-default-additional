import { type Ref, computed, defineComponent, inject, ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import EntityCustomIdMapsIdService from './entity-custom-id-maps-id.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import EntityCustomIdService from '@/entities/entity-custom-id/entity-custom-id.service';
import { type IEntityCustomId } from '@/shared/model/entity-custom-id.model';
import EntityCustomIdRelationshipService from '@/entities/entity-custom-id-relationship/entity-custom-id-relationship.service';
import { type IEntityCustomIdRelationship } from '@/shared/model/entity-custom-id-relationship.model';
import { EntityCustomIdMapsId, type IEntityCustomIdMapsId } from '@/shared/model/entity-custom-id-maps-id.model';

export default defineComponent({
  name: 'EntityCustomIdMapsIdUpdate',
  setup() {
    const entityCustomIdMapsIdService = inject('entityCustomIdMapsIdService', () => new EntityCustomIdMapsIdService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const entityCustomIdMapsId: Ref<IEntityCustomIdMapsId> = ref(new EntityCustomIdMapsId());

    const entityCustomIdService = inject('entityCustomIdService', () => new EntityCustomIdService());

    const entityCustomIds: Ref<IEntityCustomId[]> = ref([]);

    const entityCustomIdRelationshipService = inject('entityCustomIdRelationshipService', () => new EntityCustomIdRelationshipService());

    const entityCustomIdRelationships: Ref<IEntityCustomIdRelationship[]> = ref([]);
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'en'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveEntityCustomIdMapsId = async entityCustomIdMapsIdCustomId => {
      try {
        const res = await entityCustomIdMapsIdService().find(entityCustomIdMapsIdCustomId);
        entityCustomIdMapsId.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.entityCustomIdMapsIdId) {
      retrieveEntityCustomIdMapsId(route.params.entityCustomIdMapsIdId);
    }

    const initRelationships = () => {
      entityCustomIdService()
        .retrieve()
        .then(res => {
          entityCustomIds.value = res.data;
        });
      entityCustomIdRelationshipService()
        .retrieve()
        .then(res => {
          entityCustomIdRelationships.value = res.data;
        });
    };

    initRelationships();

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      entityCustomId: {},
      oneToOneMapsIdBack: {},
      manyToOneMapsIdBacks: {},
      manyToManyMapsIdBacks: {},
    };
    const v$ = useVuelidate(validationRules, entityCustomIdMapsId as any);
    v$.value.$validate();

    return {
      entityCustomIdMapsIdService,
      alertService,
      entityCustomIdMapsId,
      previousState,
      isSaving,
      currentLanguage,
      entityCustomIds,
      entityCustomIdRelationships,
      v$,
      t$,
    };
  },
  created(): void {
    this.entityCustomIdMapsId.manyToManyMapsIdBacks = [];
  },
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.entityCustomIdMapsId.customId) {
        this.entityCustomIdMapsIdService()
          .update(this.entityCustomIdMapsId)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jhipsterVueApp.entityCustomIdMapsId.updated', { param: param.customId }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.entityCustomIdMapsIdService()
          .create(this.entityCustomIdMapsId)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jhipsterVueApp.entityCustomIdMapsId.created', { param: param.customId }).toString());
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
