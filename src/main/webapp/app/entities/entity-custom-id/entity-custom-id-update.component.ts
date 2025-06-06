import { type Ref, computed, defineComponent, inject, ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import EntityCustomIdService from './entity-custom-id.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import EntityCustomIdRelationshipService from '@/entities/entity-custom-id-relationship/entity-custom-id-relationship.service';
import { type IEntityCustomIdRelationship } from '@/shared/model/entity-custom-id-relationship.model';
import { EntityCustomId, type IEntityCustomId } from '@/shared/model/entity-custom-id.model';

export default defineComponent({
  name: 'EntityCustomIdUpdate',
  setup() {
    const entityCustomIdService = inject('entityCustomIdService', () => new EntityCustomIdService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const entityCustomId: Ref<IEntityCustomId> = ref(new EntityCustomId());

    const entityCustomIdRelationshipService = inject('entityCustomIdRelationshipService', () => new EntityCustomIdRelationshipService());

    const entityCustomIdRelationships: Ref<IEntityCustomIdRelationship[]> = ref([]);
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'en'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveEntityCustomId = async entityCustomIdCustomId => {
      try {
        const res = await entityCustomIdService().find(entityCustomIdCustomId);
        entityCustomId.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.entityCustomIdId) {
      retrieveEntityCustomId(route.params.entityCustomIdId);
    }

    const initRelationships = () => {
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
      entityCustomIdMapsId: {},
      oneToOneBack: {},
      manyToOneBacks: {},
      manyToManyBacks: {},
    };
    const v$ = useVuelidate(validationRules, entityCustomId as any);
    v$.value.$validate();

    return {
      entityCustomIdService,
      alertService,
      entityCustomId,
      previousState,
      isSaving,
      currentLanguage,
      entityCustomIdRelationships,
      v$,
      t$,
    };
  },
  created(): void {
    this.entityCustomId.manyToManyBacks = [];
  },
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.entityCustomId.customId) {
        this.entityCustomIdService()
          .update(this.entityCustomId)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jhipsterVueApp.entityCustomId.updated', { param: param.customId }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.entityCustomIdService()
          .create(this.entityCustomId)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jhipsterVueApp.entityCustomId.created', { param: param.customId }).toString());
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
