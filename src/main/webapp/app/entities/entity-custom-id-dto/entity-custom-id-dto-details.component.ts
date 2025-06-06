import { type Ref, defineComponent, inject, ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import EntityCustomIdDTOService from './entity-custom-id-dto.service';
import { type IEntityCustomIdDTO } from '@/shared/model/entity-custom-id-dto.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  name: 'EntityCustomIdDTODetails',
  setup() {
    const entityCustomIdDTOService = inject('entityCustomIdDTOService', () => new EntityCustomIdDTOService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const entityCustomIdDTO: Ref<IEntityCustomIdDTO> = ref({});

    const retrieveEntityCustomIdDTO = async entityCustomIdDTOCustomId => {
      try {
        const res = await entityCustomIdDTOService().find(entityCustomIdDTOCustomId);
        entityCustomIdDTO.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.entityCustomIdDTOId) {
      retrieveEntityCustomIdDTO(route.params.entityCustomIdDTOId);
    }

    return {
      alertService,
      entityCustomIdDTO,

      previousState,
      t$: useI18n().t,
    };
  },
});
