import { type Ref, defineComponent, inject, ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import EntityCustomIdDTOMapsIdService from './entity-custom-id-dto-maps-id.service';
import { type IEntityCustomIdDTOMapsId } from '@/shared/model/entity-custom-id-dto-maps-id.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  name: 'EntityCustomIdDTOMapsIdDetails',
  setup() {
    const entityCustomIdDTOMapsIdService = inject('entityCustomIdDTOMapsIdService', () => new EntityCustomIdDTOMapsIdService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const entityCustomIdDTOMapsId: Ref<IEntityCustomIdDTOMapsId> = ref({});

    const retrieveEntityCustomIdDTOMapsId = async entityCustomIdDTOMapsIdCustomId => {
      try {
        const res = await entityCustomIdDTOMapsIdService().find(entityCustomIdDTOMapsIdCustomId);
        entityCustomIdDTOMapsId.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.entityCustomIdDTOMapsIdId) {
      retrieveEntityCustomIdDTOMapsId(route.params.entityCustomIdDTOMapsIdId);
    }

    return {
      alertService,
      entityCustomIdDTOMapsId,

      previousState,
      t$: useI18n().t,
    };
  },
});
