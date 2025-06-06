import { type Ref, defineComponent, inject, ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import EntityUuidIdRelationshipService from './entity-uuid-id-relationship.service';
import { type IEntityUuidIdRelationship } from '@/shared/model/entity-uuid-id-relationship.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  name: 'EntityUuidIdRelationshipDetails',
  setup() {
    const entityUuidIdRelationshipService = inject('entityUuidIdRelationshipService', () => new EntityUuidIdRelationshipService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const entityUuidIdRelationship: Ref<IEntityUuidIdRelationship> = ref({});

    const retrieveEntityUuidIdRelationship = async entityUuidIdRelationshipId => {
      try {
        const res = await entityUuidIdRelationshipService().find(entityUuidIdRelationshipId);
        entityUuidIdRelationship.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.entityUuidIdRelationshipId) {
      retrieveEntityUuidIdRelationship(route.params.entityUuidIdRelationshipId);
    }

    return {
      alertService,
      entityUuidIdRelationship,

      previousState,
      t$: useI18n().t,
    };
  },
});
