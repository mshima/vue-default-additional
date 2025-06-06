import { type Ref, defineComponent, inject, onMounted, ref } from 'vue';
import { useI18n } from 'vue-i18n';

import UuidIdFilteringMapsIdService from './uuid-id-filtering-maps-id.service';
import { type IUuidIdFilteringMapsId } from '@/shared/model/uuid-id-filtering-maps-id.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  name: 'UuidIdFilteringMapsId',
  setup() {
    const { t: t$ } = useI18n();
    const uuidIdFilteringMapsIdService = inject('uuidIdFilteringMapsIdService', () => new UuidIdFilteringMapsIdService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const uuidIdFilteringMapsIds: Ref<IUuidIdFilteringMapsId[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveUuidIdFilteringMapsIds = async () => {
      isFetching.value = true;
      try {
        const res = await uuidIdFilteringMapsIdService().retrieve();
        uuidIdFilteringMapsIds.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveUuidIdFilteringMapsIds();
    };

    onMounted(async () => {
      await retrieveUuidIdFilteringMapsIds();
    });

    const removeId: Ref<string> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IUuidIdFilteringMapsId) => {
      removeId.value = instance.customId;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeUuidIdFilteringMapsId = async () => {
      try {
        await uuidIdFilteringMapsIdService().delete(removeId.value);
        const message = t$('jhipsterVueApp.uuidIdFilteringMapsId.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveUuidIdFilteringMapsIds();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      uuidIdFilteringMapsIds,
      handleSyncList,
      isFetching,
      retrieveUuidIdFilteringMapsIds,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeUuidIdFilteringMapsId,
      t$,
    };
  },
});
