import { type Ref, defineComponent, inject, ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import UuidIdFilteringRelationshipService from './uuid-id-filtering-relationship.service';
import { type IUuidIdFilteringRelationship } from '@/shared/model/uuid-id-filtering-relationship.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  name: 'UuidIdFilteringRelationshipDetails',
  setup() {
    const uuidIdFilteringRelationshipService = inject('uuidIdFilteringRelationshipService', () => new UuidIdFilteringRelationshipService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const uuidIdFilteringRelationship: Ref<IUuidIdFilteringRelationship> = ref({});

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

    return {
      alertService,
      uuidIdFilteringRelationship,

      previousState,
      t$: useI18n().t,
    };
  },
});
