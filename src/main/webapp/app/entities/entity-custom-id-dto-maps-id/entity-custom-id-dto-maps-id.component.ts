import { type Ref, defineComponent, inject, onMounted, ref } from 'vue';
import { useI18n } from 'vue-i18n';

import EntityCustomIdDTOMapsIdService from './entity-custom-id-dto-maps-id.service';
import { type IEntityCustomIdDTOMapsId } from '@/shared/model/entity-custom-id-dto-maps-id.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  name: 'EntityCustomIdDTOMapsId',
  setup() {
    const { t: t$ } = useI18n();
    const entityCustomIdDTOMapsIdService = inject('entityCustomIdDTOMapsIdService', () => new EntityCustomIdDTOMapsIdService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const entityCustomIdDTOMapsIds: Ref<IEntityCustomIdDTOMapsId[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveEntityCustomIdDTOMapsIds = async () => {
      isFetching.value = true;
      try {
        const res = await entityCustomIdDTOMapsIdService().retrieve();
        entityCustomIdDTOMapsIds.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveEntityCustomIdDTOMapsIds();
    };

    onMounted(async () => {
      await retrieveEntityCustomIdDTOMapsIds();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IEntityCustomIdDTOMapsId) => {
      removeId.value = instance.customId;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeEntityCustomIdDTOMapsId = async () => {
      try {
        await entityCustomIdDTOMapsIdService().delete(removeId.value);
        const message = t$('jhipsterVueApp.entityCustomIdDTOMapsId.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveEntityCustomIdDTOMapsIds();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      entityCustomIdDTOMapsIds,
      handleSyncList,
      isFetching,
      retrieveEntityCustomIdDTOMapsIds,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeEntityCustomIdDTOMapsId,
      t$,
    };
  },
});
