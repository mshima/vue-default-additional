import { type Ref, defineComponent, inject, ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import EntityCustomIdRelationshipService from './entity-custom-id-relationship.service';
import { type IEntityCustomIdRelationship } from '@/shared/model/entity-custom-id-relationship.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  name: 'EntityCustomIdRelationshipDetails',
  setup() {
    const entityCustomIdRelationshipService = inject('entityCustomIdRelationshipService', () => new EntityCustomIdRelationshipService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const entityCustomIdRelationship: Ref<IEntityCustomIdRelationship> = ref({});

    const retrieveEntityCustomIdRelationship = async entityCustomIdRelationshipRelatedId => {
      try {
        const res = await entityCustomIdRelationshipService().find(entityCustomIdRelationshipRelatedId);
        entityCustomIdRelationship.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.entityCustomIdRelationshipId) {
      retrieveEntityCustomIdRelationship(route.params.entityCustomIdRelationshipId);
    }

    return {
      alertService,
      entityCustomIdRelationship,

      previousState,
      t$: useI18n().t,
    };
  },
});
