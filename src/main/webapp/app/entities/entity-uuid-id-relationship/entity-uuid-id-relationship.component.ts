import { type Ref, defineComponent, inject, onMounted, ref } from 'vue';
import { useI18n } from 'vue-i18n';

import EntityUuidIdRelationshipService from './entity-uuid-id-relationship.service';
import { type IEntityUuidIdRelationship } from '@/shared/model/entity-uuid-id-relationship.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  name: 'EntityUuidIdRelationship',
  setup() {
    const { t: t$ } = useI18n();
    const entityUuidIdRelationshipService = inject('entityUuidIdRelationshipService', () => new EntityUuidIdRelationshipService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const entityUuidIdRelationships: Ref<IEntityUuidIdRelationship[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveEntityUuidIdRelationships = async () => {
      isFetching.value = true;
      try {
        const res = await entityUuidIdRelationshipService().retrieve();
        entityUuidIdRelationships.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveEntityUuidIdRelationships();
    };

    onMounted(async () => {
      await retrieveEntityUuidIdRelationships();
    });

    const removeId: Ref<string> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IEntityUuidIdRelationship) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeEntityUuidIdRelationship = async () => {
      try {
        await entityUuidIdRelationshipService().delete(removeId.value);
        const message = t$('jhipsterVueApp.entityUuidIdRelationship.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveEntityUuidIdRelationships();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      entityUuidIdRelationships,
      handleSyncList,
      isFetching,
      retrieveEntityUuidIdRelationships,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeEntityUuidIdRelationship,
      t$,
    };
  },
});
