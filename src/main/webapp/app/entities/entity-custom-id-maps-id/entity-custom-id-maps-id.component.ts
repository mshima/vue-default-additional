import { type Ref, defineComponent, inject, onMounted, ref } from 'vue';
import { useI18n } from 'vue-i18n';

import EntityCustomIdMapsIdService from './entity-custom-id-maps-id.service';
import { type IEntityCustomIdMapsId } from '@/shared/model/entity-custom-id-maps-id.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  name: 'EntityCustomIdMapsId',
  setup() {
    const { t: t$ } = useI18n();
    const entityCustomIdMapsIdService = inject('entityCustomIdMapsIdService', () => new EntityCustomIdMapsIdService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const entityCustomIdMapsIds: Ref<IEntityCustomIdMapsId[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveEntityCustomIdMapsIds = async () => {
      isFetching.value = true;
      try {
        const res = await entityCustomIdMapsIdService().retrieve();
        entityCustomIdMapsIds.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveEntityCustomIdMapsIds();
    };

    onMounted(async () => {
      await retrieveEntityCustomIdMapsIds();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IEntityCustomIdMapsId) => {
      removeId.value = instance.customId;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeEntityCustomIdMapsId = async () => {
      try {
        await entityCustomIdMapsIdService().delete(removeId.value);
        const message = t$('jhipsterVueApp.entityCustomIdMapsId.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveEntityCustomIdMapsIds();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      entityCustomIdMapsIds,
      handleSyncList,
      isFetching,
      retrieveEntityCustomIdMapsIds,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeEntityCustomIdMapsId,
      t$,
    };
  },
});
