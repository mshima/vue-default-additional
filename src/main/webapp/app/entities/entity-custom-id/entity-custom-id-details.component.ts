import { type Ref, defineComponent, inject, ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import EntityCustomIdService from './entity-custom-id.service';
import { type IEntityCustomId } from '@/shared/model/entity-custom-id.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  name: 'EntityCustomIdDetails',
  setup() {
    const entityCustomIdService = inject('entityCustomIdService', () => new EntityCustomIdService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const entityCustomId: Ref<IEntityCustomId> = ref({});

    const retrieveEntityCustomId = async entityCustomIdCustomId => {
      try {
        const res = await entityCustomIdService().find(entityCustomIdCustomId);
        entityCustomId.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.entityCustomIdId) {
      retrieveEntityCustomId(route.params.entityCustomIdId);
    }

    return {
      alertService,
      entityCustomId,

      previousState,
      t$: useI18n().t,
    };
  },
});
