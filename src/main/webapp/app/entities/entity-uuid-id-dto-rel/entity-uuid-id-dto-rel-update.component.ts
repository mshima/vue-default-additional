import { type Ref, computed, defineComponent, inject, ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import EntityUuidIdDTORelService from './entity-uuid-id-dto-rel.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import EntityUuidIdDTOService from '@/entities/entity-uuid-id-dto/entity-uuid-id-dto.service';
import { type IEntityUuidIdDTO } from '@/shared/model/entity-uuid-id-dto.model';
import EntityUuidIdDTOMapsIdService from '@/entities/entity-uuid-id-dto-maps-id/entity-uuid-id-dto-maps-id.service';
import { type IEntityUuidIdDTOMapsId } from '@/shared/model/entity-uuid-id-dto-maps-id.model';
import { EntityUuidIdDTORel, type IEntityUuidIdDTORel } from '@/shared/model/entity-uuid-id-dto-rel.model';

export default defineComponent({
  name: 'EntityUuidIdDTORelUpdate',
  setup() {
    const entityUuidIdDTORelService = inject('entityUuidIdDTORelService', () => new EntityUuidIdDTORelService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const entityUuidIdDTORel: Ref<IEntityUuidIdDTORel> = ref(new EntityUuidIdDTORel());

    const entityUuidIdDTOService = inject('entityUuidIdDTOService', () => new EntityUuidIdDTOService());

    const entityUuidIdDTOS: Ref<IEntityUuidIdDTO[]> = ref([]);

    const entityUuidIdDTOMapsIdService = inject('entityUuidIdDTOMapsIdService', () => new EntityUuidIdDTOMapsIdService());

    const entityUuidIdDTOMapsIds: Ref<IEntityUuidIdDTOMapsId[]> = ref([]);
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'en'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveEntityUuidIdDTORel = async entityUuidIdDTORelId => {
      try {
        const res = await entityUuidIdDTORelService().find(entityUuidIdDTORelId);
        entityUuidIdDTORel.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.entityUuidIdDTORelId) {
      retrieveEntityUuidIdDTORel(route.params.entityUuidIdDTORelId);
    }

    const initRelationships = () => {
      entityUuidIdDTOService()
        .retrieve()
        .then(res => {
          entityUuidIdDTOS.value = res.data;
        });
      entityUuidIdDTOMapsIdService()
        .retrieve()
        .then(res => {
          entityUuidIdDTOMapsIds.value = res.data;
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
    const v$ = useVuelidate(validationRules, entityUuidIdDTORel as any);
    v$.value.$validate();

    return {
      entityUuidIdDTORelService,
      alertService,
      entityUuidIdDTORel,
      previousState,
      isSaving,
      currentLanguage,
      entityUuidIdDTOS,
      entityUuidIdDTOMapsIds,
      v$,
      t$,
    };
  },
  created(): void {
    this.entityUuidIdDTORel.manyToManies = [];
    this.entityUuidIdDTORel.manyToManyMapsIds = [];
  },
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.entityUuidIdDTORel.id) {
        this.entityUuidIdDTORelService()
          .update(this.entityUuidIdDTORel)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jhipsterVueApp.entityUuidIdDTORel.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.entityUuidIdDTORelService()
          .create(this.entityUuidIdDTORel)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jhipsterVueApp.entityUuidIdDTORel.created', { param: param.id }).toString());
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
