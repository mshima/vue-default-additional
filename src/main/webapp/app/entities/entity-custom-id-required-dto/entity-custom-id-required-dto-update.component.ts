import { type Ref, computed, defineComponent, inject, ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import EntityCustomIdRequiredDTOService from './entity-custom-id-required-dto.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import EntityCustomIdRequiredDTORelService from '@/entities/entity-custom-id-required-dto-rel/entity-custom-id-required-dto-rel.service';
import { type IEntityCustomIdRequiredDTORel } from '@/shared/model/entity-custom-id-required-dto-rel.model';
import { EntityCustomIdRequiredDTO, type IEntityCustomIdRequiredDTO } from '@/shared/model/entity-custom-id-required-dto.model';

export default defineComponent({
  name: 'EntityCustomIdRequiredDTOUpdate',
  setup() {
    const entityCustomIdRequiredDTOService = inject('entityCustomIdRequiredDTOService', () => new EntityCustomIdRequiredDTOService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const entityCustomIdRequiredDTO: Ref<IEntityCustomIdRequiredDTO> = ref(new EntityCustomIdRequiredDTO());

    const entityCustomIdRequiredDTORelService = inject(
      'entityCustomIdRequiredDTORelService',
      () => new EntityCustomIdRequiredDTORelService(),
    );

    const entityCustomIdRequiredDTORels: Ref<IEntityCustomIdRequiredDTORel[]> = ref([]);
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'en'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveEntityCustomIdRequiredDTO = async entityCustomIdRequiredDTOCustomId => {
      try {
        const res = await entityCustomIdRequiredDTOService().find(entityCustomIdRequiredDTOCustomId);
        entityCustomIdRequiredDTO.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.entityCustomIdRequiredDTOId) {
      retrieveEntityCustomIdRequiredDTO(route.params.entityCustomIdRequiredDTOId);
    }

    const initRelationships = () => {
      entityCustomIdRequiredDTORelService()
        .retrieve()
        .then(res => {
          entityCustomIdRequiredDTORels.value = res.data;
        });
    };

    initRelationships();

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      entityCustomIdRequiredDTOMapsId: {},
      oneToOneBack: {},
      manyToOneBacks: {},
      manyToManyBacks: {},
    };
    const v$ = useVuelidate(validationRules, entityCustomIdRequiredDTO as any);
    v$.value.$validate();

    return {
      entityCustomIdRequiredDTOService,
      alertService,
      entityCustomIdRequiredDTO,
      previousState,
      isSaving,
      currentLanguage,
      entityCustomIdRequiredDTORels,
      v$,
      t$,
    };
  },
  created(): void {
    this.entityCustomIdRequiredDTO.manyToManyBacks = [];
  },
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.entityCustomIdRequiredDTO.customId) {
        this.entityCustomIdRequiredDTOService()
          .update(this.entityCustomIdRequiredDTO)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jhipsterVueApp.entityCustomIdRequiredDTO.updated', { param: param.customId }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.entityCustomIdRequiredDTOService()
          .create(this.entityCustomIdRequiredDTO)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(
              this.t$('jhipsterVueApp.entityCustomIdRequiredDTO.created', { param: param.customId }).toString(),
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
