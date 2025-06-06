import { type Ref, computed, defineComponent, inject, ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import EntityCustomIdDTOMapsIdService from './entity-custom-id-dto-maps-id.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import EntityCustomIdDTOService from '@/entities/entity-custom-id-dto/entity-custom-id-dto.service';
import { type IEntityCustomIdDTO } from '@/shared/model/entity-custom-id-dto.model';
import EntityCustomIdDTORelService from '@/entities/entity-custom-id-dto-rel/entity-custom-id-dto-rel.service';
import { type IEntityCustomIdDTORel } from '@/shared/model/entity-custom-id-dto-rel.model';
import { EntityCustomIdDTOMapsId, type IEntityCustomIdDTOMapsId } from '@/shared/model/entity-custom-id-dto-maps-id.model';

export default defineComponent({
  name: 'EntityCustomIdDTOMapsIdUpdate',
  setup() {
    const entityCustomIdDTOMapsIdService = inject('entityCustomIdDTOMapsIdService', () => new EntityCustomIdDTOMapsIdService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const entityCustomIdDTOMapsId: Ref<IEntityCustomIdDTOMapsId> = ref(new EntityCustomIdDTOMapsId());

    const entityCustomIdDTOService = inject('entityCustomIdDTOService', () => new EntityCustomIdDTOService());

    const entityCustomIdDTOS: Ref<IEntityCustomIdDTO[]> = ref([]);

    const entityCustomIdDTORelService = inject('entityCustomIdDTORelService', () => new EntityCustomIdDTORelService());

    const entityCustomIdDTORels: Ref<IEntityCustomIdDTORel[]> = ref([]);
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'en'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveEntityCustomIdDTOMapsId = async entityCustomIdDTOMapsIdCustomId => {
      try {
        const res = await entityCustomIdDTOMapsIdService().find(entityCustomIdDTOMapsIdCustomId);
        entityCustomIdDTOMapsId.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.entityCustomIdDTOMapsIdId) {
      retrieveEntityCustomIdDTOMapsId(route.params.entityCustomIdDTOMapsIdId);
    }

    const initRelationships = () => {
      entityCustomIdDTOService()
        .retrieve()
        .then(res => {
          entityCustomIdDTOS.value = res.data;
        });
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
      entityCustomIdDTO: {},
      oneToOneMapsIdBack: {},
      manyToOneMapsIdBacks: {},
      manyToManyMapsIdBacks: {},
    };
    const v$ = useVuelidate(validationRules, entityCustomIdDTOMapsId as any);
    v$.value.$validate();

    return {
      entityCustomIdDTOMapsIdService,
      alertService,
      entityCustomIdDTOMapsId,
      previousState,
      isSaving,
      currentLanguage,
      entityCustomIdDTOS,
      entityCustomIdDTORels,
      v$,
      t$,
    };
  },
  created(): void {
    this.entityCustomIdDTOMapsId.manyToManyMapsIdBacks = [];
  },
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.entityCustomIdDTOMapsId.customId) {
        this.entityCustomIdDTOMapsIdService()
          .update(this.entityCustomIdDTOMapsId)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jhipsterVueApp.entityCustomIdDTOMapsId.updated', { param: param.customId }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.entityCustomIdDTOMapsIdService()
          .create(this.entityCustomIdDTOMapsId)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jhipsterVueApp.entityCustomIdDTOMapsId.created', { param: param.customId }).toString());
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
