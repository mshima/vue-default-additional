import { type Ref, defineComponent, inject, onMounted, ref } from 'vue';
import { useI18n } from 'vue-i18n';

import EntityCustomIdRequiredDTOMapsIdService from './entity-custom-id-required-dto-maps-id.service';
import { type IEntityCustomIdRequiredDTOMapsId } from '@/shared/model/entity-custom-id-required-dto-maps-id.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  name: 'EntityCustomIdRequiredDTOMapsId',
  setup() {
    const { t: t$ } = useI18n();
    const entityCustomIdRequiredDTOMapsIdService = inject(
      'entityCustomIdRequiredDTOMapsIdService',
      () => new EntityCustomIdRequiredDTOMapsIdService(),
    );
    const alertService = inject('alertService', () => useAlertService(), true);

    const entityCustomIdRequiredDTOMapsIds: Ref<IEntityCustomIdRequiredDTOMapsId[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveEntityCustomIdRequiredDTOMapsIds = async () => {
      isFetching.value = true;
      try {
        const res = await entityCustomIdRequiredDTOMapsIdService().retrieve();
        entityCustomIdRequiredDTOMapsIds.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveEntityCustomIdRequiredDTOMapsIds();
    };

    onMounted(async () => {
      await retrieveEntityCustomIdRequiredDTOMapsIds();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IEntityCustomIdRequiredDTOMapsId) => {
      removeId.value = instance.customId;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeEntityCustomIdRequiredDTOMapsId = async () => {
      try {
        await entityCustomIdRequiredDTOMapsIdService().delete(removeId.value);
        const message = t$('jhipsterVueApp.entityCustomIdRequiredDTOMapsId.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveEntityCustomIdRequiredDTOMapsIds();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      entityCustomIdRequiredDTOMapsIds,
      handleSyncList,
      isFetching,
      retrieveEntityCustomIdRequiredDTOMapsIds,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeEntityCustomIdRequiredDTOMapsId,
      t$,
    };
  },
});
