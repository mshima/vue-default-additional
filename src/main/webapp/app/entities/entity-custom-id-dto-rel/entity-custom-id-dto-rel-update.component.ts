import { type Ref, computed, defineComponent, inject, ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import EntityCustomIdDTORelService from './entity-custom-id-dto-rel.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import EntityCustomIdDTOService from '@/entities/entity-custom-id-dto/entity-custom-id-dto.service';
import { type IEntityCustomIdDTO } from '@/shared/model/entity-custom-id-dto.model';
import EntityCustomIdDTOMapsIdService from '@/entities/entity-custom-id-dto-maps-id/entity-custom-id-dto-maps-id.service';
import { type IEntityCustomIdDTOMapsId } from '@/shared/model/entity-custom-id-dto-maps-id.model';
import { EntityCustomIdDTORel, type IEntityCustomIdDTORel } from '@/shared/model/entity-custom-id-dto-rel.model';

export default defineComponent({
  name: 'EntityCustomIdDTORelUpdate',
  setup() {
    const entityCustomIdDTORelService = inject('entityCustomIdDTORelService', () => new EntityCustomIdDTORelService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const entityCustomIdDTORel: Ref<IEntityCustomIdDTORel> = ref(new EntityCustomIdDTORel());

    const entityCustomIdDTOService = inject('entityCustomIdDTOService', () => new EntityCustomIdDTOService());

    const entityCustomIdDTOS: Ref<IEntityCustomIdDTO[]> = ref([]);

    const entityCustomIdDTOMapsIdService = inject('entityCustomIdDTOMapsIdService', () => new EntityCustomIdDTOMapsIdService());

    const entityCustomIdDTOMapsIds: Ref<IEntityCustomIdDTOMapsId[]> = ref([]);
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'en'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveEntityCustomIdDTORel = async entityCustomIdDTORelRelatedId => {
      try {
        const res = await entityCustomIdDTORelService().find(entityCustomIdDTORelRelatedId);
        entityCustomIdDTORel.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.entityCustomIdDTORelId) {
      retrieveEntityCustomIdDTORel(route.params.entityCustomIdDTORelId);
    }

    const initRelationships = () => {
      entityCustomIdDTOService()
        .retrieve()
        .then(res => {
          entityCustomIdDTOS.value = res.data;
        });
      entityCustomIdDTOMapsIdService()
        .retrieve()
        .then(res => {
          entityCustomIdDTOMapsIds.value = res.data;
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
    const v$ = useVuelidate(validationRules, entityCustomIdDTORel as any);
    v$.value.$validate();

    return {
      entityCustomIdDTORelService,
      alertService,
      entityCustomIdDTORel,
      previousState,
      isSaving,
      currentLanguage,
      entityCustomIdDTOS,
      entityCustomIdDTOMapsIds,
      v$,
      t$,
    };
  },
  created(): void {
    this.entityCustomIdDTORel.manyToManies = [];
    this.entityCustomIdDTORel.manyToManyMapsIds = [];
  },
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.entityCustomIdDTORel.relatedId) {
        this.entityCustomIdDTORelService()
          .update(this.entityCustomIdDTORel)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jhipsterVueApp.entityCustomIdDTORel.updated', { param: param.relatedId }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.entityCustomIdDTORelService()
          .create(this.entityCustomIdDTORel)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jhipsterVueApp.entityCustomIdDTORel.created', { param: param.relatedId }).toString());
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
