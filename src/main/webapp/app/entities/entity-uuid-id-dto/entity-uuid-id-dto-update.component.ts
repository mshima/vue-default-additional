import { type Ref, computed, defineComponent, inject, ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import EntityUuidIdDTOService from './entity-uuid-id-dto.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import EntityUuidIdDTORelService from '@/entities/entity-uuid-id-dto-rel/entity-uuid-id-dto-rel.service';
import { type IEntityUuidIdDTORel } from '@/shared/model/entity-uuid-id-dto-rel.model';
import { EntityUuidIdDTO, type IEntityUuidIdDTO } from '@/shared/model/entity-uuid-id-dto.model';

export default defineComponent({
  name: 'EntityUuidIdDTOUpdate',
  setup() {
    const entityUuidIdDTOService = inject('entityUuidIdDTOService', () => new EntityUuidIdDTOService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const entityUuidIdDTO: Ref<IEntityUuidIdDTO> = ref(new EntityUuidIdDTO());

    const entityUuidIdDTORelService = inject('entityUuidIdDTORelService', () => new EntityUuidIdDTORelService());

    const entityUuidIdDTORels: Ref<IEntityUuidIdDTORel[]> = ref([]);
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'en'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveEntityUuidIdDTO = async entityUuidIdDTOId => {
      try {
        const res = await entityUuidIdDTOService().find(entityUuidIdDTOId);
        entityUuidIdDTO.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.entityUuidIdDTOId) {
      retrieveEntityUuidIdDTO(route.params.entityUuidIdDTOId);
    }

    const initRelationships = () => {
      entityUuidIdDTORelService()
        .retrieve()
        .then(res => {
          entityUuidIdDTORels.value = res.data;
        });
    };

    initRelationships();

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      entityUuidIdDTOMapsId: {},
      oneToOneBack: {},
      manyToOneBacks: {},
      manyToManyBacks: {},
    };
    const v$ = useVuelidate(validationRules, entityUuidIdDTO as any);
    v$.value.$validate();

    return {
      entityUuidIdDTOService,
      alertService,
      entityUuidIdDTO,
      previousState,
      isSaving,
      currentLanguage,
      entityUuidIdDTORels,
      v$,
      t$,
    };
  },
  created(): void {
    this.entityUuidIdDTO.manyToManyBacks = [];
  },
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.entityUuidIdDTO.id) {
        this.entityUuidIdDTOService()
          .update(this.entityUuidIdDTO)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jhipsterVueApp.entityUuidIdDTO.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.entityUuidIdDTOService()
          .create(this.entityUuidIdDTO)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jhipsterVueApp.entityUuidIdDTO.created', { param: param.id }).toString());
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
