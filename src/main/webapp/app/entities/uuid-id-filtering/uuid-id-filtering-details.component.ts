import { type Ref, defineComponent, inject, ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import UuidIdFilteringService from './uuid-id-filtering.service';
import { type IUuidIdFiltering } from '@/shared/model/uuid-id-filtering.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  name: 'UuidIdFilteringDetails',
  setup() {
    const uuidIdFilteringService = inject('uuidIdFilteringService', () => new UuidIdFilteringService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const uuidIdFiltering: Ref<IUuidIdFiltering> = ref({});

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

    return {
      alertService,
      uuidIdFiltering,

      previousState,
      t$: useI18n().t,
    };
  },
});
