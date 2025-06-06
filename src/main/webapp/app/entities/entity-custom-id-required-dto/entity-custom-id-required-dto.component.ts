import { type Ref, defineComponent, inject, onMounted, ref } from 'vue';
import { useI18n } from 'vue-i18n';

import EntityCustomIdRequiredDTOService from './entity-custom-id-required-dto.service';
import { type IEntityCustomIdRequiredDTO } from '@/shared/model/entity-custom-id-required-dto.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  name: 'EntityCustomIdRequiredDTO',
  setup() {
    const { t: t$ } = useI18n();
    const entityCustomIdRequiredDTOService = inject('entityCustomIdRequiredDTOService', () => new EntityCustomIdRequiredDTOService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const entityCustomIdRequiredDTOS: Ref<IEntityCustomIdRequiredDTO[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveEntityCustomIdRequiredDTOs = async () => {
      isFetching.value = true;
      try {
        const res = await entityCustomIdRequiredDTOService().retrieve();
        entityCustomIdRequiredDTOS.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveEntityCustomIdRequiredDTOs();
    };

    onMounted(async () => {
      await retrieveEntityCustomIdRequiredDTOs();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IEntityCustomIdRequiredDTO) => {
      removeId.value = instance.customId;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeEntityCustomIdRequiredDTO = async () => {
      try {
        await entityCustomIdRequiredDTOService().delete(removeId.value);
        const message = t$('jhipsterVueApp.entityCustomIdRequiredDTO.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveEntityCustomIdRequiredDTOs();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      entityCustomIdRequiredDTOS,
      handleSyncList,
      isFetching,
      retrieveEntityCustomIdRequiredDTOs,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeEntityCustomIdRequiredDTO,
      t$,
    };
  },
});
