import { type Ref, defineComponent, inject, onMounted, ref } from 'vue';
import { useI18n } from 'vue-i18n';

import EntityIntegerIdService from './entity-integer-id.service';
import { type IEntityIntegerId } from '@/shared/model/entity-integer-id.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  name: 'EntityIntegerId',
  setup() {
    const { t: t$ } = useI18n();
    const entityIntegerIdService = inject('entityIntegerIdService', () => new EntityIntegerIdService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const entityIntegerIds: Ref<IEntityIntegerId[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveEntityIntegerIds = async () => {
      isFetching.value = true;
      try {
        const res = await entityIntegerIdService().retrieve();
        entityIntegerIds.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveEntityIntegerIds();
    };

    onMounted(async () => {
      await retrieveEntityIntegerIds();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IEntityIntegerId) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeEntityIntegerId = async () => {
      try {
        await entityIntegerIdService().delete(removeId.value);
        const message = t$('jhipsterVueApp.entityIntegerId.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveEntityIntegerIds();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      entityIntegerIds,
      handleSyncList,
      isFetching,
      retrieveEntityIntegerIds,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeEntityIntegerId,
      t$,
    };
  },
});
