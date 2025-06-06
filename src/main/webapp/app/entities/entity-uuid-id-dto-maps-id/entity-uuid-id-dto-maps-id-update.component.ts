import { type Ref, computed, defineComponent, inject, ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import EntityUuidIdDTOMapsIdService from './entity-uuid-id-dto-maps-id.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import EntityUuidIdDTOService from '@/entities/entity-uuid-id-dto/entity-uuid-id-dto.service';
import { type IEntityUuidIdDTO } from '@/shared/model/entity-uuid-id-dto.model';
import EntityUuidIdDTORelService from '@/entities/entity-uuid-id-dto-rel/entity-uuid-id-dto-rel.service';
import { type IEntityUuidIdDTORel } from '@/shared/model/entity-uuid-id-dto-rel.model';
import { EntityUuidIdDTOMapsId, type IEntityUuidIdDTOMapsId } from '@/shared/model/entity-uuid-id-dto-maps-id.model';

export default defineComponent({
  name: 'EntityUuidIdDTOMapsIdUpdate',
  setup() {
    const entityUuidIdDTOMapsIdService = inject('entityUuidIdDTOMapsIdService', () => new EntityUuidIdDTOMapsIdService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const entityUuidIdDTOMapsId: Ref<IEntityUuidIdDTOMapsId> = ref(new EntityUuidIdDTOMapsId());

    const entityUuidIdDTOService = inject('entityUuidIdDTOService', () => new EntityUuidIdDTOService());

    const entityUuidIdDTOS: Ref<IEntityUuidIdDTO[]> = ref([]);

    const entityUuidIdDTORelService = inject('entityUuidIdDTORelService', () => new EntityUuidIdDTORelService());

    const entityUuidIdDTORels: Ref<IEntityUuidIdDTORel[]> = ref([]);
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'en'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveEntityUuidIdDTOMapsId = async entityUuidIdDTOMapsIdId => {
      try {
        const res = await entityUuidIdDTOMapsIdService().find(entityUuidIdDTOMapsIdId);
        entityUuidIdDTOMapsId.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.entityUuidIdDTOMapsIdId) {
      retrieveEntityUuidIdDTOMapsId(route.params.entityUuidIdDTOMapsIdId);
    }

    const initRelationships = () => {
      entityUuidIdDTOService()
        .retrieve()
        .then(res => {
          entityUuidIdDTOS.value = res.data;
        });
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
      entityUuidIdDTO: {},
      oneToOneMapsIdBack: {},
      manyToOneMapsIdBacks: {},
      manyToManyMapsIdBacks: {},
    };
    const v$ = useVuelidate(validationRules, entityUuidIdDTOMapsId as any);
    v$.value.$validate();

    return {
      entityUuidIdDTOMapsIdService,
      alertService,
      entityUuidIdDTOMapsId,
      previousState,
      isSaving,
      currentLanguage,
      entityUuidIdDTOS,
      entityUuidIdDTORels,
      v$,
      t$,
    };
  },
  created(): void {
    this.entityUuidIdDTOMapsId.manyToManyMapsIdBacks = [];
  },
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.entityUuidIdDTOMapsId.id) {
        this.entityUuidIdDTOMapsIdService()
          .update(this.entityUuidIdDTOMapsId)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jhipsterVueApp.entityUuidIdDTOMapsId.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.entityUuidIdDTOMapsIdService()
          .create(this.entityUuidIdDTOMapsId)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jhipsterVueApp.entityUuidIdDTOMapsId.created', { param: param.id }).toString());
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
