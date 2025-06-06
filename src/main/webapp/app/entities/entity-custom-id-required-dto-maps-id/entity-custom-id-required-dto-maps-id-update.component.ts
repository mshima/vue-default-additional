import { type Ref, computed, defineComponent, inject, ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import EntityCustomIdRequiredDTOMapsIdService from './entity-custom-id-required-dto-maps-id.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import EntityCustomIdRequiredDTOService from '@/entities/entity-custom-id-required-dto/entity-custom-id-required-dto.service';
import { type IEntityCustomIdRequiredDTO } from '@/shared/model/entity-custom-id-required-dto.model';
import EntityCustomIdRequiredDTORelService from '@/entities/entity-custom-id-required-dto-rel/entity-custom-id-required-dto-rel.service';
import { type IEntityCustomIdRequiredDTORel } from '@/shared/model/entity-custom-id-required-dto-rel.model';
import {
  EntityCustomIdRequiredDTOMapsId,
  type IEntityCustomIdRequiredDTOMapsId,
} from '@/shared/model/entity-custom-id-required-dto-maps-id.model';

export default defineComponent({
  name: 'EntityCustomIdRequiredDTOMapsIdUpdate',
  setup() {
    const entityCustomIdRequiredDTOMapsIdService = inject(
      'entityCustomIdRequiredDTOMapsIdService',
      () => new EntityCustomIdRequiredDTOMapsIdService(),
    );
    const alertService = inject('alertService', () => useAlertService(), true);

    const entityCustomIdRequiredDTOMapsId: Ref<IEntityCustomIdRequiredDTOMapsId> = ref(new EntityCustomIdRequiredDTOMapsId());

    const entityCustomIdRequiredDTOService = inject('entityCustomIdRequiredDTOService', () => new EntityCustomIdRequiredDTOService());

    const entityCustomIdRequiredDTOS: Ref<IEntityCustomIdRequiredDTO[]> = ref([]);

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

    const retrieveEntityCustomIdRequiredDTOMapsId = async entityCustomIdRequiredDTOMapsIdCustomId => {
      try {
        const res = await entityCustomIdRequiredDTOMapsIdService().find(entityCustomIdRequiredDTOMapsIdCustomId);
        entityCustomIdRequiredDTOMapsId.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.entityCustomIdRequiredDTOMapsIdId) {
      retrieveEntityCustomIdRequiredDTOMapsId(route.params.entityCustomIdRequiredDTOMapsIdId);
    }

    const initRelationships = () => {
      entityCustomIdRequiredDTOService()
        .retrieve()
        .then(res => {
          entityCustomIdRequiredDTOS.value = res.data;
        });
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
      entityCustomIdRequiredDTO: {},
      oneToOneMapsIdBack: {},
      manyToOneMapsIdBacks: {},
      manyToManyMapsIdBacks: {},
    };
    const v$ = useVuelidate(validationRules, entityCustomIdRequiredDTOMapsId as any);
    v$.value.$validate();

    return {
      entityCustomIdRequiredDTOMapsIdService,
      alertService,
      entityCustomIdRequiredDTOMapsId,
      previousState,
      isSaving,
      currentLanguage,
      entityCustomIdRequiredDTOS,
      entityCustomIdRequiredDTORels,
      v$,
      t$,
    };
  },
  created(): void {
    this.entityCustomIdRequiredDTOMapsId.manyToManyMapsIdBacks = [];
  },
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.entityCustomIdRequiredDTOMapsId.customId) {
        this.entityCustomIdRequiredDTOMapsIdService()
          .update(this.entityCustomIdRequiredDTOMapsId)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jhipsterVueApp.entityCustomIdRequiredDTOMapsId.updated', { param: param.customId }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.entityCustomIdRequiredDTOMapsIdService()
          .create(this.entityCustomIdRequiredDTOMapsId)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(
              this.t$('jhipsterVueApp.entityCustomIdRequiredDTOMapsId.created', { param: param.customId }).toString(),
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
