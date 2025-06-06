import { type Ref, defineComponent, inject, onMounted, ref } from 'vue';
import { useI18n } from 'vue-i18n';

import EntityUuidIdDTORelService from './entity-uuid-id-dto-rel.service';
import { type IEntityUuidIdDTORel } from '@/shared/model/entity-uuid-id-dto-rel.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  name: 'EntityUuidIdDTORel',
  setup() {
    const { t: t$ } = useI18n();
    const entityUuidIdDTORelService = inject('entityUuidIdDTORelService', () => new EntityUuidIdDTORelService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const entityUuidIdDTORels: Ref<IEntityUuidIdDTORel[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveEntityUuidIdDTORels = async () => {
      isFetching.value = true;
      try {
        const res = await entityUuidIdDTORelService().retrieve();
        entityUuidIdDTORels.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveEntityUuidIdDTORels();
    };

    onMounted(async () => {
      await retrieveEntityUuidIdDTORels();
    });

    const removeId: Ref<string> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IEntityUuidIdDTORel) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeEntityUuidIdDTORel = async () => {
      try {
        await entityUuidIdDTORelService().delete(removeId.value);
        const message = t$('jhipsterVueApp.entityUuidIdDTORel.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveEntityUuidIdDTORels();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      entityUuidIdDTORels,
      handleSyncList,
      isFetching,
      retrieveEntityUuidIdDTORels,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeEntityUuidIdDTORel,
      t$,
    };
  },
});
