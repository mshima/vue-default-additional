import { type Ref, defineComponent, inject, onMounted, ref } from 'vue';
import { useI18n } from 'vue-i18n';

import EntityUuidIdService from './entity-uuid-id.service';
import { type IEntityUuidId } from '@/shared/model/entity-uuid-id.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  name: 'EntityUuidId',
  setup() {
    const { t: t$ } = useI18n();
    const entityUuidIdService = inject('entityUuidIdService', () => new EntityUuidIdService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const entityUuidIds: Ref<IEntityUuidId[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveEntityUuidIds = async () => {
      isFetching.value = true;
      try {
        const res = await entityUuidIdService().retrieve();
        entityUuidIds.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveEntityUuidIds();
    };

    onMounted(async () => {
      await retrieveEntityUuidIds();
    });

    const removeId: Ref<string> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IEntityUuidId) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeEntityUuidId = async () => {
      try {
        await entityUuidIdService().delete(removeId.value);
        const message = t$('jhipsterVueApp.entityUuidId.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveEntityUuidIds();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      entityUuidIds,
      handleSyncList,
      isFetching,
      retrieveEntityUuidIds,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeEntityUuidId,
      t$,
    };
  },
});
