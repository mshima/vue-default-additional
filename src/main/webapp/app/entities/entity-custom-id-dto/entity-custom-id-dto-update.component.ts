import { type Ref, computed, defineComponent, inject, ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import EntityCustomIdDTOService from './entity-custom-id-dto.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import EntityCustomIdDTORelService from '@/entities/entity-custom-id-dto-rel/entity-custom-id-dto-rel.service';
import { type IEntityCustomIdDTORel } from '@/shared/model/entity-custom-id-dto-rel.model';
import { EntityCustomIdDTO, type IEntityCustomIdDTO } from '@/shared/model/entity-custom-id-dto.model';

export default defineComponent({
  name: 'EntityCustomIdDTOUpdate',
  setup() {
    const entityCustomIdDTOService = inject('entityCustomIdDTOService', () => new EntityCustomIdDTOService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const entityCustomIdDTO: Ref<IEntityCustomIdDTO> = ref(new EntityCustomIdDTO());

    const entityCustomIdDTORelService = inject('entityCustomIdDTORelService', () => new EntityCustomIdDTORelService());

    const entityCustomIdDTORels: Ref<IEntityCustomIdDTORel[]> = ref([]);
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'en'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveEntityCustomIdDTO = async entityCustomIdDTOCustomId => {
      try {
        const res = await entityCustomIdDTOService().find(entityCustomIdDTOCustomId);
        entityCustomIdDTO.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.entityCustomIdDTOId) {
      retrieveEntityCustomIdDTO(route.params.entityCustomIdDTOId);
    }

    const initRelationships = () => {
      entityCustomIdDTORelService()
        .retrieve()
        .then(res => {
          entityCustomIdDTORels.value = res.data;
        });
    };

    initRelationships();

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      entityCustomIdDTOMapsId: {},
      oneToOneBack: {},
      manyToOneBacks: {},
      manyToManyBacks: {},
    };
    const v$ = useVuelidate(validationRules, entityCustomIdDTO as any);
    v$.value.$validate();

    return {
      entityCustomIdDTOService,
      alertService,
      entityCustomIdDTO,
      previousState,
      isSaving,
      currentLanguage,
      entityCustomIdDTORels,
      v$,
      t$,
    };
  },
  created(): void {
    this.entityCustomIdDTO.manyToManyBacks = [];
  },
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.entityCustomIdDTO.customId) {
        this.entityCustomIdDTOService()
          .update(this.entityCustomIdDTO)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jhipsterVueApp.entityCustomIdDTO.updated', { param: param.customId }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.entityCustomIdDTOService()
          .create(this.entityCustomIdDTO)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jhipsterVueApp.entityCustomIdDTO.created', { param: param.customId }).toString());
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
