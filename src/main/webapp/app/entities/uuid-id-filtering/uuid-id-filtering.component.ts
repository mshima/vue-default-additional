import { type Ref, defineComponent, inject, onMounted, ref } from 'vue';
import { useI18n } from 'vue-i18n';

import UuidIdFilteringService from './uuid-id-filtering.service';
import { type IUuidIdFiltering } from '@/shared/model/uuid-id-filtering.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  name: 'UuidIdFiltering',
  setup() {
    const { t: t$ } = useI18n();
    const uuidIdFilteringService = inject('uuidIdFilteringService', () => new UuidIdFilteringService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const uuidIdFilterings: Ref<IUuidIdFiltering[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveUuidIdFilterings = async () => {
      isFetching.value = true;
      try {
        const res = await uuidIdFilteringService().retrieve();
        uuidIdFilterings.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveUuidIdFilterings();
    };

    onMounted(async () => {
      await retrieveUuidIdFilterings();
    });

    const removeId: Ref<string> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IUuidIdFiltering) => {
      removeId.value = instance.customId;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeUuidIdFiltering = async () => {
      try {
        await uuidIdFilteringService().delete(removeId.value);
        const message = t$('jhipsterVueApp.uuidIdFiltering.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveUuidIdFilterings();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      uuidIdFilterings,
      handleSyncList,
      isFetching,
      retrieveUuidIdFilterings,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeUuidIdFiltering,
      t$,
    };
  },
});
