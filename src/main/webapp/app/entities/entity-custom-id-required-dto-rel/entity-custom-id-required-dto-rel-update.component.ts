import { type Ref, computed, defineComponent, inject, ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import EntityCustomIdRequiredDTORelService from './entity-custom-id-required-dto-rel.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import EntityCustomIdRequiredDTOService from '@/entities/entity-custom-id-required-dto/entity-custom-id-required-dto.service';
import { type IEntityCustomIdRequiredDTO } from '@/shared/model/entity-custom-id-required-dto.model';
import EntityCustomIdRequiredDTOMapsIdService from '@/entities/entity-custom-id-required-dto-maps-id/entity-custom-id-required-dto-maps-id.service';
import { type IEntityCustomIdRequiredDTOMapsId } from '@/shared/model/entity-custom-id-required-dto-maps-id.model';
import { EntityCustomIdRequiredDTORel, type IEntityCustomIdRequiredDTORel } from '@/shared/model/entity-custom-id-required-dto-rel.model';

export default defineComponent({
  name: 'EntityCustomIdRequiredDTORelUpdate',
  setup() {
    const entityCustomIdRequiredDTORelService = inject(
      'entityCustomIdRequiredDTORelService',
      () => new EntityCustomIdRequiredDTORelService(),
    );
    const alertService = inject('alertService', () => useAlertService(), true);

    const entityCustomIdRequiredDTORel: Ref<IEntityCustomIdRequiredDTORel> = ref(new EntityCustomIdRequiredDTORel());

    const entityCustomIdRequiredDTOService = inject('entityCustomIdRequiredDTOService', () => new EntityCustomIdRequiredDTOService());

    const entityCustomIdRequiredDTOS: Ref<IEntityCustomIdRequiredDTO[]> = ref([]);

    const entityCustomIdRequiredDTOMapsIdService = inject(
      'entityCustomIdRequiredDTOMapsIdService',
      () => new EntityCustomIdRequiredDTOMapsIdService(),
    );

    const entityCustomIdRequiredDTOMapsIds: Ref<IEntityCustomIdRequiredDTOMapsId[]> = ref([]);
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'en'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveEntityCustomIdRequiredDTORel = async entityCustomIdRequiredDTORelRelatedId => {
      try {
        const res = await entityCustomIdRequiredDTORelService().find(entityCustomIdRequiredDTORelRelatedId);
        entityCustomIdRequiredDTORel.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.entityCustomIdRequiredDTORelId) {
      retrieveEntityCustomIdRequiredDTORel(route.params.entityCustomIdRequiredDTORelId);
    }

    const initRelationships = () => {
      entityCustomIdRequiredDTOService()
        .retrieve()
        .then(res => {
          entityCustomIdRequiredDTOS.value = res.data;
        });
      entityCustomIdRequiredDTOMapsIdService()
        .retrieve()
        .then(res => {
          entityCustomIdRequiredDTOMapsIds.value = res.data;
        });
    };

    initRelationships();

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      oneToOne: {
        required: validations.required(t$('entity.validation.required').toString()),
      },
      oneToOneMapsId: {
        required: validations.required(t$('entity.validation.required').toString()),
      },
      manyToOne: {
        required: validations.required(t$('entity.validation.required').toString()),
      },
      manyToOneMapsId: {
        required: validations.required(t$('entity.validation.required').toString()),
      },
      manyToManies: {
        required: validations.required(t$('entity.validation.required').toString()),
      },
      manyToManyMapsIds: {
        required: validations.required(t$('entity.validation.required').toString()),
      },
    };
    const v$ = useVuelidate(validationRules, entityCustomIdRequiredDTORel as any);
    v$.value.$validate();

    return {
      entityCustomIdRequiredDTORelService,
      alertService,
      entityCustomIdRequiredDTORel,
      previousState,
      isSaving,
      currentLanguage,
      entityCustomIdRequiredDTOS,
      entityCustomIdRequiredDTOMapsIds,
      v$,
      t$,
    };
  },
  created(): void {
    this.entityCustomIdRequiredDTORel.manyToManies = [];
    this.entityCustomIdRequiredDTORel.manyToManyMapsIds = [];
  },
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.entityCustomIdRequiredDTORel.relatedId) {
        this.entityCustomIdRequiredDTORelService()
          .update(this.entityCustomIdRequiredDTORel)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jhipsterVueApp.entityCustomIdRequiredDTORel.updated', { param: param.relatedId }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.entityCustomIdRequiredDTORelService()
          .create(this.entityCustomIdRequiredDTORel)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(
              this.t$('jhipsterVueApp.entityCustomIdRequiredDTORel.created', { param: param.relatedId }).toString(),
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
