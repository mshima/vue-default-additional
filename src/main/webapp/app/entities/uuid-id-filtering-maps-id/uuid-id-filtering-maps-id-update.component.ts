import { type Ref, computed, defineComponent, inject, ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import UuidIdFilteringMapsIdService from './uuid-id-filtering-maps-id.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import UuidIdFilteringService from '@/entities/uuid-id-filtering/uuid-id-filtering.service';
import { type IUuidIdFiltering } from '@/shared/model/uuid-id-filtering.model';
import UuidIdFilteringRelationshipService from '@/entities/uuid-id-filtering-relationship/uuid-id-filtering-relationship.service';
import { type IUuidIdFilteringRelationship } from '@/shared/model/uuid-id-filtering-relationship.model';
import { type IUuidIdFilteringMapsId, UuidIdFilteringMapsId } from '@/shared/model/uuid-id-filtering-maps-id.model';

export default defineComponent({
  name: 'UuidIdFilteringMapsIdUpdate',
  setup() {
    const uuidIdFilteringMapsIdService = inject('uuidIdFilteringMapsIdService', () => new UuidIdFilteringMapsIdService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const uuidIdFilteringMapsId: Ref<IUuidIdFilteringMapsId> = ref(new UuidIdFilteringMapsId());

    const uuidIdFilteringService = inject('uuidIdFilteringService', () => new UuidIdFilteringService());

    const uuidIdFilterings: Ref<IUuidIdFiltering[]> = ref([]);

    const uuidIdFilteringRelationshipService = inject('uuidIdFilteringRelationshipService', () => new UuidIdFilteringRelationshipService());

    const uuidIdFilteringRelationships: Ref<IUuidIdFilteringRelationship[]> = ref([]);
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'en'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveUuidIdFilteringMapsId = async uuidIdFilteringMapsIdCustomId => {
      try {
        const res = await uuidIdFilteringMapsIdService().find(uuidIdFilteringMapsIdCustomId);
        uuidIdFilteringMapsId.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.uuidIdFilteringMapsIdId) {
      retrieveUuidIdFilteringMapsId(route.params.uuidIdFilteringMapsIdId);
    }

    const initRelationships = () => {
      uuidIdFilteringService()
        .retrieve()
        .then(res => {
          uuidIdFilterings.value = res.data;
        });
      uuidIdFilteringRelationshipService()
        .retrieve()
        .then(res => {
          uuidIdFilteringRelationships.value = res.data;
        });
    };

    initRelationships();

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      uuidIdFiltering: {},
      oneToOneMapsIdBack: {},
      manyToOneMapsIdBacks: {},
      manyToManyMapsIdBacks: {},
    };
    const v$ = useVuelidate(validationRules, uuidIdFilteringMapsId as any);
    v$.value.$validate();

    return {
      uuidIdFilteringMapsIdService,
      alertService,
      uuidIdFilteringMapsId,
      previousState,
      isSaving,
      currentLanguage,
      uuidIdFilterings,
      uuidIdFilteringRelationships,
      v$,
      t$,
    };
  },
  created(): void {
    this.uuidIdFilteringMapsId.manyToManyMapsIdBacks = [];
  },
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.uuidIdFilteringMapsId.customId) {
        this.uuidIdFilteringMapsIdService()
          .update(this.uuidIdFilteringMapsId)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jhipsterVueApp.uuidIdFilteringMapsId.updated', { param: param.customId }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.uuidIdFilteringMapsIdService()
          .create(this.uuidIdFilteringMapsId)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jhipsterVueApp.uuidIdFilteringMapsId.created', { param: param.customId }).toString());
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
