import { type Ref, defineComponent, inject, ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import EntityUuidIdDTOService from './entity-uuid-id-dto.service';
import { type IEntityUuidIdDTO } from '@/shared/model/entity-uuid-id-dto.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  name: 'EntityUuidIdDTODetails',
  setup() {
    const entityUuidIdDTOService = inject('entityUuidIdDTOService', () => new EntityUuidIdDTOService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const entityUuidIdDTO: Ref<IEntityUuidIdDTO> = ref({});

    const retrieveEntityUuidIdDTO = async entityUuidIdDTOId => {
      try {
        const res = await entityUuidIdDTOService().find(entityUuidIdDTOId);
        entityUuidIdDTO.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.entityUuidIdDTOId) {
      retrieveEntityUuidIdDTO(route.params.entityUuidIdDTOId);
    }

    return {
      alertService,
      entityUuidIdDTO,

      previousState,
      t$: useI18n().t,
    };
  },
});
