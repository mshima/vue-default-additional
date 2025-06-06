import { type Ref, defineComponent, inject, ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import EntityCustomIdRequiredDTOMapsIdService from './entity-custom-id-required-dto-maps-id.service';
import { type IEntityCustomIdRequiredDTOMapsId } from '@/shared/model/entity-custom-id-required-dto-maps-id.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  name: 'EntityCustomIdRequiredDTOMapsIdDetails',
  setup() {
    const entityCustomIdRequiredDTOMapsIdService = inject(
      'entityCustomIdRequiredDTOMapsIdService',
      () => new EntityCustomIdRequiredDTOMapsIdService(),
    );
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const entityCustomIdRequiredDTOMapsId: Ref<IEntityCustomIdRequiredDTOMapsId> = ref({});

    const retrieveEntityCustomIdRequiredDTOMapsId = async entityCustomIdRequiredDTOMapsIdCustomId => {
      try {
        const res = await entityCustomIdRequiredDTOMapsIdService().find(entityCustomIdRequiredDTOMapsIdCustomId);
        entityCustomIdRequiredDTOMapsId.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.entityCustomIdRequiredDTOMapsIdId) {
      retrieveEntityCustomIdRequiredDTOMapsId(route.params.entityCustomIdRequiredDTOMapsIdId);
    }

    return {
      alertService,
      entityCustomIdRequiredDTOMapsId,

      previousState,
      t$: useI18n().t,
    };
  },
});
