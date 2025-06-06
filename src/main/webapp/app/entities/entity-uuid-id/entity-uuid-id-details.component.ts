import { type Ref, defineComponent, inject, ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import EntityUuidIdService from './entity-uuid-id.service';
import { type IEntityUuidId } from '@/shared/model/entity-uuid-id.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  name: 'EntityUuidIdDetails',
  setup() {
    const entityUuidIdService = inject('entityUuidIdService', () => new EntityUuidIdService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const entityUuidId: Ref<IEntityUuidId> = ref({});

    const retrieveEntityUuidId = async entityUuidIdId => {
      try {
        const res = await entityUuidIdService().find(entityUuidIdId);
        entityUuidId.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.entityUuidIdId) {
      retrieveEntityUuidId(route.params.entityUuidIdId);
    }

    return {
      alertService,
      entityUuidId,

      previousState,
      t$: useI18n().t,
    };
  },
});
