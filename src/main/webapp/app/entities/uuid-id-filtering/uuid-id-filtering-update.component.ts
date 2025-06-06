import { type Ref, computed, defineComponent, inject, ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import UuidIdFilteringService from './uuid-id-filtering.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import UuidIdFilteringRelationshipService from '@/entities/uuid-id-filtering-relationship/uuid-id-filtering-relationship.service';
import { type IUuidIdFilteringRelationship } from '@/shared/model/uuid-id-filtering-relationship.model';
import { type IUuidIdFiltering, UuidIdFiltering } from '@/shared/model/uuid-id-filtering.model';

export default defineComponent({
  name: 'UuidIdFilteringUpdate',
  setup() {
    const uuidIdFilteringService = inject('uuidIdFilteringService', () => new UuidIdFilteringService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const uuidIdFiltering: Ref<IUuidIdFiltering> = ref(new UuidIdFiltering());

    const uuidIdFilteringRelationshipService = inject('uuidIdFilteringRelationshipService', () => new UuidIdFilteringRelationshipService());

    const uuidIdFilteringRelationships: Ref<IUuidIdFilteringRelationship[]> = ref([]);
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'en'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveUuidIdFiltering = async uuidIdFilteringCustomId => {
      try {
        const res = await uuidIdFilteringService().find(uuidIdFilteringCustomId);
        uuidIdFiltering.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.uuidIdFilteringId) {
      retrieveUuidIdFiltering(route.params.uuidIdFilteringId);
    }

    const initRelationships = () => {
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
      uuidIdFilteringMapsId: {},
      oneToOneBack: {},
      manyToOneBacks: {},
      manyToManyBacks: {},
    };
    const v$ = useVuelidate(validationRules, uuidIdFiltering as any);
    v$.value.$validate();

    return {
      uuidIdFilteringService,
      alertService,
      uuidIdFiltering,
      previousState,
      isSaving,
      currentLanguage,
      uuidIdFilteringRelationships,
      v$,
      t$,
    };
  },
  created(): void {
    this.uuidIdFiltering.manyToManyBacks = [];
  },
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.uuidIdFiltering.customId) {
        this.uuidIdFilteringService()
          .update(this.uuidIdFiltering)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jhipsterVueApp.uuidIdFiltering.updated', { param: param.customId }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.uuidIdFilteringService()
          .create(this.uuidIdFiltering)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jhipsterVueApp.uuidIdFiltering.created', { param: param.customId }).toString());
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
