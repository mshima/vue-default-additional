import { type Ref, defineComponent, inject, onMounted, ref } from 'vue';
import { useI18n } from 'vue-i18n';

import EntityUuidIdDTOMapsIdService from './entity-uuid-id-dto-maps-id.service';
import { type IEntityUuidIdDTOMapsId } from '@/shared/model/entity-uuid-id-dto-maps-id.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  name: 'EntityUuidIdDTOMapsId',
  setup() {
    const { t: t$ } = useI18n();
    const entityUuidIdDTOMapsIdService = inject('entityUuidIdDTOMapsIdService', () => new EntityUuidIdDTOMapsIdService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const entityUuidIdDTOMapsIds: Ref<IEntityUuidIdDTOMapsId[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveEntityUuidIdDTOMapsIds = async () => {
      isFetching.value = true;
      try {
        const res = await entityUuidIdDTOMapsIdService().retrieve();
        entityUuidIdDTOMapsIds.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveEntityUuidIdDTOMapsIds();
    };

    onMounted(async () => {
      await retrieveEntityUuidIdDTOMapsIds();
    });

    const removeId: Ref<string> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IEntityUuidIdDTOMapsId) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeEntityUuidIdDTOMapsId = async () => {
      try {
        await entityUuidIdDTOMapsIdService().delete(removeId.value);
        const message = t$('jhipsterVueApp.entityUuidIdDTOMapsId.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveEntityUuidIdDTOMapsIds();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      entityUuidIdDTOMapsIds,
      handleSyncList,
      isFetching,
      retrieveEntityUuidIdDTOMapsIds,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeEntityUuidIdDTOMapsId,
      t$,
    };
  },
});
