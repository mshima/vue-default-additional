import { type Ref, defineComponent, inject, ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import EntityIntegerIdService from './entity-integer-id.service';
import { type IEntityIntegerId } from '@/shared/model/entity-integer-id.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  name: 'EntityIntegerIdDetails',
  setup() {
    const entityIntegerIdService = inject('entityIntegerIdService', () => new EntityIntegerIdService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const entityIntegerId: Ref<IEntityIntegerId> = ref({});

    const retrieveEntityIntegerId = async entityIntegerIdId => {
      try {
        const res = await entityIntegerIdService().find(entityIntegerIdId);
        entityIntegerId.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.entityIntegerIdId) {
      retrieveEntityIntegerId(route.params.entityIntegerIdId);
    }

    return {
      alertService,
      entityIntegerId,

      previousState,
      t$: useI18n().t,
    };
  },
});
