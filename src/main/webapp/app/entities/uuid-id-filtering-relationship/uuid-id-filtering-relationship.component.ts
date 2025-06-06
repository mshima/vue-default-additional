import { type Ref, defineComponent, inject, onMounted, ref } from 'vue';
import { useI18n } from 'vue-i18n';

import UuidIdFilteringRelationshipService from './uuid-id-filtering-relationship.service';
import { type IUuidIdFilteringRelationship } from '@/shared/model/uuid-id-filtering-relationship.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  name: 'UuidIdFilteringRelationship',
  setup() {
    const { t: t$ } = useI18n();
    const uuidIdFilteringRelationshipService = inject('uuidIdFilteringRelationshipService', () => new UuidIdFilteringRelationshipService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const uuidIdFilteringRelationships: Ref<IUuidIdFilteringRelationship[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveUuidIdFilteringRelationships = async () => {
      isFetching.value = true;
      try {
        const res = await uuidIdFilteringRelationshipService().retrieve();
        uuidIdFilteringRelationships.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveUuidIdFilteringRelationships();
    };

    onMounted(async () => {
      await retrieveUuidIdFilteringRelationships();
    });

    const removeId: Ref<string> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IUuidIdFilteringRelationship) => {
      removeId.value = instance.relatedId;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeUuidIdFilteringRelationship = async () => {
      try {
        await uuidIdFilteringRelationshipService().delete(removeId.value);
        const message = t$('jhipsterVueApp.uuidIdFilteringRelationship.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveUuidIdFilteringRelationships();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      uuidIdFilteringRelationships,
      handleSyncList,
      isFetching,
      retrieveUuidIdFilteringRelationships,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeUuidIdFilteringRelationship,
      t$,
    };
  },
});
