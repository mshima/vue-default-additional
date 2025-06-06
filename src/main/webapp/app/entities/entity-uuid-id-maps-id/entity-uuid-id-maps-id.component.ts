import { type Ref, defineComponent, inject, onMounted, ref } from 'vue';
import { useI18n } from 'vue-i18n';

import EntityUuidIdMapsIdService from './entity-uuid-id-maps-id.service';
import { type IEntityUuidIdMapsId } from '@/shared/model/entity-uuid-id-maps-id.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  name: 'EntityUuidIdMapsId',
  setup() {
    const { t: t$ } = useI18n();
    const entityUuidIdMapsIdService = inject('entityUuidIdMapsIdService', () => new EntityUuidIdMapsIdService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const entityUuidIdMapsIds: Ref<IEntityUuidIdMapsId[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveEntityUuidIdMapsIds = async () => {
      isFetching.value = true;
      try {
        const res = await entityUuidIdMapsIdService().retrieve();
        entityUuidIdMapsIds.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveEntityUuidIdMapsIds();
    };

    onMounted(async () => {
      await retrieveEntityUuidIdMapsIds();
    });

    const removeId: Ref<string> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IEntityUuidIdMapsId) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeEntityUuidIdMapsId = async () => {
      try {
        await entityUuidIdMapsIdService().delete(removeId.value);
        const message = t$('jhipsterVueApp.entityUuidIdMapsId.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveEntityUuidIdMapsIds();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      entityUuidIdMapsIds,
      handleSyncList,
      isFetching,
      retrieveEntityUuidIdMapsIds,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeEntityUuidIdMapsId,
      t$,
    };
  },
});
