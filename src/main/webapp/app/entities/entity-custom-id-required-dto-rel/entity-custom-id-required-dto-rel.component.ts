import { type Ref, defineComponent, inject, onMounted, ref } from 'vue';
import { useI18n } from 'vue-i18n';

import EntityCustomIdRequiredDTORelService from './entity-custom-id-required-dto-rel.service';
import { type IEntityCustomIdRequiredDTORel } from '@/shared/model/entity-custom-id-required-dto-rel.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  name: 'EntityCustomIdRequiredDTORel',
  setup() {
    const { t: t$ } = useI18n();
    const entityCustomIdRequiredDTORelService = inject(
      'entityCustomIdRequiredDTORelService',
      () => new EntityCustomIdRequiredDTORelService(),
    );
    const alertService = inject('alertService', () => useAlertService(), true);

    const entityCustomIdRequiredDTORels: Ref<IEntityCustomIdRequiredDTORel[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveEntityCustomIdRequiredDTORels = async () => {
      isFetching.value = true;
      try {
        const res = await entityCustomIdRequiredDTORelService().retrieve();
        entityCustomIdRequiredDTORels.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveEntityCustomIdRequiredDTORels();
    };

    onMounted(async () => {
      await retrieveEntityCustomIdRequiredDTORels();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IEntityCustomIdRequiredDTORel) => {
      removeId.value = instance.relatedId;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeEntityCustomIdRequiredDTORel = async () => {
      try {
        await entityCustomIdRequiredDTORelService().delete(removeId.value);
        const message = t$('jhipsterVueApp.entityCustomIdRequiredDTORel.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveEntityCustomIdRequiredDTORels();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      entityCustomIdRequiredDTORels,
      handleSyncList,
      isFetching,
      retrieveEntityCustomIdRequiredDTORels,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeEntityCustomIdRequiredDTORel,
      t$,
    };
  },
});
