import { type Ref, defineComponent, inject, onMounted, ref } from 'vue';
import { useI18n } from 'vue-i18n';

import EntityCustomIdDTOService from './entity-custom-id-dto.service';
import { type IEntityCustomIdDTO } from '@/shared/model/entity-custom-id-dto.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  name: 'EntityCustomIdDTO',
  setup() {
    const { t: t$ } = useI18n();
    const entityCustomIdDTOService = inject('entityCustomIdDTOService', () => new EntityCustomIdDTOService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const entityCustomIdDTOS: Ref<IEntityCustomIdDTO[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveEntityCustomIdDTOs = async () => {
      isFetching.value = true;
      try {
        const res = await entityCustomIdDTOService().retrieve();
        entityCustomIdDTOS.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveEntityCustomIdDTOs();
    };

    onMounted(async () => {
      await retrieveEntityCustomIdDTOs();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IEntityCustomIdDTO) => {
      removeId.value = instance.customId;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeEntityCustomIdDTO = async () => {
      try {
        await entityCustomIdDTOService().delete(removeId.value);
        const message = t$('jhipsterVueApp.entityCustomIdDTO.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveEntityCustomIdDTOs();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      entityCustomIdDTOS,
      handleSyncList,
      isFetching,
      retrieveEntityCustomIdDTOs,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeEntityCustomIdDTO,
      t$,
    };
  },
});
