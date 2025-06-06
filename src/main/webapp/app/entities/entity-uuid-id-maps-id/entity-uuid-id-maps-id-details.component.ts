import { type Ref, defineComponent, inject, ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import EntityUuidIdMapsIdService from './entity-uuid-id-maps-id.service';
import { type IEntityUuidIdMapsId } from '@/shared/model/entity-uuid-id-maps-id.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  name: 'EntityUuidIdMapsIdDetails',
  setup() {
    const entityUuidIdMapsIdService = inject('entityUuidIdMapsIdService', () => new EntityUuidIdMapsIdService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const entityUuidIdMapsId: Ref<IEntityUuidIdMapsId> = ref({});

    const retrieveEntityUuidIdMapsId = async entityUuidIdMapsIdId => {
      try {
        const res = await entityUuidIdMapsIdService().find(entityUuidIdMapsIdId);
        entityUuidIdMapsId.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.entityUuidIdMapsIdId) {
      retrieveEntityUuidIdMapsId(route.params.entityUuidIdMapsIdId);
    }

    return {
      alertService,
      entityUuidIdMapsId,

      previousState,
      t$: useI18n().t,
    };
  },
});
