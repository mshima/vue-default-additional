import { type Ref, defineComponent, inject, ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import EntityUuidIdDTOMapsIdService from './entity-uuid-id-dto-maps-id.service';
import { type IEntityUuidIdDTOMapsId } from '@/shared/model/entity-uuid-id-dto-maps-id.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  name: 'EntityUuidIdDTOMapsIdDetails',
  setup() {
    const entityUuidIdDTOMapsIdService = inject('entityUuidIdDTOMapsIdService', () => new EntityUuidIdDTOMapsIdService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const entityUuidIdDTOMapsId: Ref<IEntityUuidIdDTOMapsId> = ref({});

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

    return {
      alertService,
      entityUuidIdDTOMapsId,

      previousState,
      t$: useI18n().t,
    };
  },
});
