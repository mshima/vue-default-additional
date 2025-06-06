import { type Ref, defineComponent, inject, onMounted, ref } from 'vue';
import { useI18n } from 'vue-i18n';

import EntityUuidIdDTOService from './entity-uuid-id-dto.service';
import { type IEntityUuidIdDTO } from '@/shared/model/entity-uuid-id-dto.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  name: 'EntityUuidIdDTO',
  setup() {
    const { t: t$ } = useI18n();
    const entityUuidIdDTOService = inject('entityUuidIdDTOService', () => new EntityUuidIdDTOService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const entityUuidIdDTOS: Ref<IEntityUuidIdDTO[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveEntityUuidIdDTOs = async () => {
      isFetching.value = true;
      try {
        const res = await entityUuidIdDTOService().retrieve();
        entityUuidIdDTOS.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveEntityUuidIdDTOs();
    };

    onMounted(async () => {
      await retrieveEntityUuidIdDTOs();
    });

    const removeId: Ref<string> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IEntityUuidIdDTO) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeEntityUuidIdDTO = async () => {
      try {
        await entityUuidIdDTOService().delete(removeId.value);
        const message = t$('jhipsterVueApp.entityUuidIdDTO.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveEntityUuidIdDTOs();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      entityUuidIdDTOS,
      handleSyncList,
      isFetching,
      retrieveEntityUuidIdDTOs,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeEntityUuidIdDTO,
      t$,
    };
  },
});
