import { type Ref, defineComponent, inject, ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import EntityUuidIdDTORelService from './entity-uuid-id-dto-rel.service';
import { type IEntityUuidIdDTORel } from '@/shared/model/entity-uuid-id-dto-rel.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  name: 'EntityUuidIdDTORelDetails',
  setup() {
    const entityUuidIdDTORelService = inject('entityUuidIdDTORelService', () => new EntityUuidIdDTORelService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const entityUuidIdDTORel: Ref<IEntityUuidIdDTORel> = ref({});

    const retrieveEntityUuidIdDTORel = async entityUuidIdDTORelId => {
      try {
        const res = await entityUuidIdDTORelService().find(entityUuidIdDTORelId);
        entityUuidIdDTORel.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.entityUuidIdDTORelId) {
      retrieveEntityUuidIdDTORel(route.params.entityUuidIdDTORelId);
    }

    return {
      alertService,
      entityUuidIdDTORel,

      previousState,
      t$: useI18n().t,
    };
  },
});
