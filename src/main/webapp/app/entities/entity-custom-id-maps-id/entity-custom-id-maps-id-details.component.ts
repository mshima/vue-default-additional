import { type Ref, defineComponent, inject, ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import EntityCustomIdMapsIdService from './entity-custom-id-maps-id.service';
import { type IEntityCustomIdMapsId } from '@/shared/model/entity-custom-id-maps-id.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  name: 'EntityCustomIdMapsIdDetails',
  setup() {
    const entityCustomIdMapsIdService = inject('entityCustomIdMapsIdService', () => new EntityCustomIdMapsIdService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const entityCustomIdMapsId: Ref<IEntityCustomIdMapsId> = ref({});

    const retrieveEntityCustomIdMapsId = async entityCustomIdMapsIdCustomId => {
      try {
        const res = await entityCustomIdMapsIdService().find(entityCustomIdMapsIdCustomId);
        entityCustomIdMapsId.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.entityCustomIdMapsIdId) {
      retrieveEntityCustomIdMapsId(route.params.entityCustomIdMapsIdId);
    }

    return {
      alertService,
      entityCustomIdMapsId,

      previousState,
      t$: useI18n().t,
    };
  },
});
