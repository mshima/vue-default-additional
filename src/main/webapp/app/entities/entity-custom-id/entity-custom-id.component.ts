import { type Ref, defineComponent, inject, onMounted, ref } from 'vue';
import { useI18n } from 'vue-i18n';

import EntityCustomIdService from './entity-custom-id.service';
import { type IEntityCustomId } from '@/shared/model/entity-custom-id.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  name: 'EntityCustomId',
  setup() {
    const { t: t$ } = useI18n();
    const entityCustomIdService = inject('entityCustomIdService', () => new EntityCustomIdService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const entityCustomIds: Ref<IEntityCustomId[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveEntityCustomIds = async () => {
      isFetching.value = true;
      try {
        const res = await entityCustomIdService().retrieve();
        entityCustomIds.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveEntityCustomIds();
    };

    onMounted(async () => {
      await retrieveEntityCustomIds();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IEntityCustomId) => {
      removeId.value = instance.customId;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeEntityCustomId = async () => {
      try {
        await entityCustomIdService().delete(removeId.value);
        const message = t$('jhipsterVueApp.entityCustomId.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveEntityCustomIds();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      entityCustomIds,
      handleSyncList,
      isFetching,
      retrieveEntityCustomIds,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeEntityCustomId,
      t$,
    };
  },
});
