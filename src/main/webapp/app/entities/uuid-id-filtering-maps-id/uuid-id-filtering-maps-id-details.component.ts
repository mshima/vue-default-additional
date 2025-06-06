import { type Ref, defineComponent, inject, ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import UuidIdFilteringMapsIdService from './uuid-id-filtering-maps-id.service';
import { type IUuidIdFilteringMapsId } from '@/shared/model/uuid-id-filtering-maps-id.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  name: 'UuidIdFilteringMapsIdDetails',
  setup() {
    const uuidIdFilteringMapsIdService = inject('uuidIdFilteringMapsIdService', () => new UuidIdFilteringMapsIdService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const uuidIdFilteringMapsId: Ref<IUuidIdFilteringMapsId> = ref({});

    const retrieveUuidIdFilteringMapsId = async uuidIdFilteringMapsIdCustomId => {
      try {
        const res = await uuidIdFilteringMapsIdService().find(uuidIdFilteringMapsIdCustomId);
        uuidIdFilteringMapsId.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.uuidIdFilteringMapsIdId) {
      retrieveUuidIdFilteringMapsId(route.params.uuidIdFilteringMapsIdId);
    }

    return {
      alertService,
      uuidIdFilteringMapsId,

      previousState,
      t$: useI18n().t,
    };
  },
});
