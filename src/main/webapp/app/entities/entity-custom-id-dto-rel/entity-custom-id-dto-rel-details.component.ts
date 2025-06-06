import { type Ref, defineComponent, inject, ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import EntityCustomIdDTORelService from './entity-custom-id-dto-rel.service';
import { type IEntityCustomIdDTORel } from '@/shared/model/entity-custom-id-dto-rel.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  name: 'EntityCustomIdDTORelDetails',
  setup() {
    const entityCustomIdDTORelService = inject('entityCustomIdDTORelService', () => new EntityCustomIdDTORelService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const entityCustomIdDTORel: Ref<IEntityCustomIdDTORel> = ref({});

    const retrieveEntityCustomIdDTORel = async entityCustomIdDTORelRelatedId => {
      try {
        const res = await entityCustomIdDTORelService().find(entityCustomIdDTORelRelatedId);
        entityCustomIdDTORel.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.entityCustomIdDTORelId) {
      retrieveEntityCustomIdDTORel(route.params.entityCustomIdDTORelId);
    }

    return {
      alertService,
      entityCustomIdDTORel,

      previousState,
      t$: useI18n().t,
    };
  },
});
