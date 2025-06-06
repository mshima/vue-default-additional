import { type Ref, defineComponent, inject, onMounted, ref } from 'vue';
import { useI18n } from 'vue-i18n';

import EntityCustomSequenceService from './entity-custom-sequence.service';
import { type IEntityCustomSequence } from '@/shared/model/entity-custom-sequence.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  name: 'EntityCustomSequence',
  setup() {
    const { t: t$ } = useI18n();
    const entityCustomSequenceService = inject('entityCustomSequenceService', () => new EntityCustomSequenceService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const entityCustomSequences: Ref<IEntityCustomSequence[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveEntityCustomSequences = async () => {
      isFetching.value = true;
      try {
        const res = await entityCustomSequenceService().retrieve();
        entityCustomSequences.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveEntityCustomSequences();
    };

    onMounted(async () => {
      await retrieveEntityCustomSequences();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IEntityCustomSequence) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeEntityCustomSequence = async () => {
      try {
        await entityCustomSequenceService().delete(removeId.value);
        const message = t$('jhipsterVueApp.entityCustomSequence.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveEntityCustomSequences();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      entityCustomSequences,
      handleSyncList,
      isFetching,
      retrieveEntityCustomSequences,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeEntityCustomSequence,
      t$,
    };
  },
});
