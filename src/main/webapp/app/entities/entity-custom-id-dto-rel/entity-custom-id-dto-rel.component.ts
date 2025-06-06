import { type Ref, defineComponent, inject, onMounted, ref } from 'vue';
import { useI18n } from 'vue-i18n';

import EntityCustomIdDTORelService from './entity-custom-id-dto-rel.service';
import { type IEntityCustomIdDTORel } from '@/shared/model/entity-custom-id-dto-rel.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  name: 'EntityCustomIdDTORel',
  setup() {
    const { t: t$ } = useI18n();
    const entityCustomIdDTORelService = inject('entityCustomIdDTORelService', () => new EntityCustomIdDTORelService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const entityCustomIdDTORels: Ref<IEntityCustomIdDTORel[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveEntityCustomIdDTORels = async () => {
      isFetching.value = true;
      try {
        const res = await entityCustomIdDTORelService().retrieve();
        entityCustomIdDTORels.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveEntityCustomIdDTORels();
    };

    onMounted(async () => {
      await retrieveEntityCustomIdDTORels();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IEntityCustomIdDTORel) => {
      removeId.value = instance.relatedId;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeEntityCustomIdDTORel = async () => {
      try {
        await entityCustomIdDTORelService().delete(removeId.value);
        const message = t$('jhipsterVueApp.entityCustomIdDTORel.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveEntityCustomIdDTORels();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      entityCustomIdDTORels,
      handleSyncList,
      isFetching,
      retrieveEntityCustomIdDTORels,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeEntityCustomIdDTORel,
      t$,
    };
  },
});
