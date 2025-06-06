import { type Ref, defineComponent, inject, onMounted, ref } from 'vue';
import { useI18n } from 'vue-i18n';

import EntityCustomIdRelationshipService from './entity-custom-id-relationship.service';
import { type IEntityCustomIdRelationship } from '@/shared/model/entity-custom-id-relationship.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  name: 'EntityCustomIdRelationship',
  setup() {
    const { t: t$ } = useI18n();
    const entityCustomIdRelationshipService = inject('entityCustomIdRelationshipService', () => new EntityCustomIdRelationshipService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const entityCustomIdRelationships: Ref<IEntityCustomIdRelationship[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveEntityCustomIdRelationships = async () => {
      isFetching.value = true;
      try {
        const res = await entityCustomIdRelationshipService().retrieve();
        entityCustomIdRelationships.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveEntityCustomIdRelationships();
    };

    onMounted(async () => {
      await retrieveEntityCustomIdRelationships();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IEntityCustomIdRelationship) => {
      removeId.value = instance.relatedId;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeEntityCustomIdRelationship = async () => {
      try {
        await entityCustomIdRelationshipService().delete(removeId.value);
        const message = t$('jhipsterVueApp.entityCustomIdRelationship.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveEntityCustomIdRelationships();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      entityCustomIdRelationships,
      handleSyncList,
      isFetching,
      retrieveEntityCustomIdRelationships,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeEntityCustomIdRelationship,
      t$,
    };
  },
});
