import { type Ref, defineComponent, inject, ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import EntityCustomIdRequiredDTOService from './entity-custom-id-required-dto.service';
import { type IEntityCustomIdRequiredDTO } from '@/shared/model/entity-custom-id-required-dto.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  name: 'EntityCustomIdRequiredDTODetails',
  setup() {
    const entityCustomIdRequiredDTOService = inject('entityCustomIdRequiredDTOService', () => new EntityCustomIdRequiredDTOService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const entityCustomIdRequiredDTO: Ref<IEntityCustomIdRequiredDTO> = ref({});

    const retrieveEntityCustomIdRequiredDTO = async entityCustomIdRequiredDTOCustomId => {
      try {
        const res = await entityCustomIdRequiredDTOService().find(entityCustomIdRequiredDTOCustomId);
        entityCustomIdRequiredDTO.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.entityCustomIdRequiredDTOId) {
      retrieveEntityCustomIdRequiredDTO(route.params.entityCustomIdRequiredDTOId);
    }

    return {
      alertService,
      entityCustomIdRequiredDTO,

      previousState,
      t$: useI18n().t,
    };
  },
});
