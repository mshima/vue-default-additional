import { type Ref, computed, defineComponent, inject, ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import UuidIdFilteringRelationshipService from './uuid-id-filtering-relationship.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import UuidIdFilteringService from '@/entities/uuid-id-filtering/uuid-id-filtering.service';
import { type IUuidIdFiltering } from '@/shared/model/uuid-id-filtering.model';
import UuidIdFilteringMapsIdService from '@/entities/uuid-id-filtering-maps-id/uuid-id-filtering-maps-id.service';
import { type IUuidIdFilteringMapsId } from '@/shared/model/uuid-id-filtering-maps-id.model';
import { type IUuidIdFilteringRelationship, UuidIdFilteringRelationship } from '@/shared/model/uuid-id-filtering-relationship.model';

export default defineComponent({
  name: 'UuidIdFilteringRelationshipUpdate',
  setup() {
    const uuidIdFilteringRelationshipService = inject('uuidIdFilteringRelationshipService', () => new UuidIdFilteringRelationshipService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const uuidIdFilteringRelationship: Ref<IUuidIdFilteringRelationship> = ref(new UuidIdFilteringRelationship());

    const uuidIdFilteringService = inject('uuidIdFilteringService', () => new UuidIdFilteringService());

    const uuidIdFilterings: Ref<IUuidIdFiltering[]> = ref([]);

    const uuidIdFilteringMapsIdService = inject('uuidIdFilteringMapsIdService', () => new UuidIdFilteringMapsIdService());

    const uuidIdFilteringMapsIds: Ref<IUuidIdFilteringMapsId[]> = ref([]);
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'en'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveUuidIdFilteringRelationship = async uuidIdFilteringRelationshipRelatedId => {
      try {
        const res = await uuidIdFilteringRelationshipService().find(uuidIdFilteringRelationshipRelatedId);
        uuidIdFilteringRelationship.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.uuidIdFilteringRelationshipId) {
      retrieveUuidIdFilteringRelationship(route.params.uuidIdFilteringRelationshipId);
    }

    const initRelationships = () => {
      uuidIdFilteringService()
        .retrieve()
        .then(res => {
          uuidIdFilterings.value = res.data;
        });
      uuidIdFilteringMapsIdService()
        .retrieve()
        .then(res => {
          uuidIdFilteringMapsIds.value = res.data;
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
    const v$ = useVuelidate(validationRules, uuidIdFilteringRelationship as any);
    v$.value.$validate();

    return {
      uuidIdFilteringRelationshipService,
      alertService,
      uuidIdFilteringRelationship,
      previousState,
      isSaving,
      currentLanguage,
      uuidIdFilterings,
      uuidIdFilteringMapsIds,
      v$,
      t$,
    };
  },
  created(): void {
    this.uuidIdFilteringRelationship.manyToManies = [];
    this.uuidIdFilteringRelationship.manyToManyMapsIds = [];
  },
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.uuidIdFilteringRelationship.relatedId) {
        this.uuidIdFilteringRelationshipService()
          .update(this.uuidIdFilteringRelationship)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jhipsterVueApp.uuidIdFilteringRelationship.updated', { param: param.relatedId }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.uuidIdFilteringRelationshipService()
          .create(this.uuidIdFilteringRelationship)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(
              this.t$('jhipsterVueApp.uuidIdFilteringRelationship.created', { param: param.relatedId }).toString(),
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
