import { type Ref, defineComponent, inject, ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import EntityCustomIdRequiredDTORelService from './entity-custom-id-required-dto-rel.service';
import { type IEntityCustomIdRequiredDTORel } from '@/shared/model/entity-custom-id-required-dto-rel.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  name: 'EntityCustomIdRequiredDTORelDetails',
  setup() {
    const entityCustomIdRequiredDTORelService = inject(
      'entityCustomIdRequiredDTORelService',
      () => new EntityCustomIdRequiredDTORelService(),
    );
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const entityCustomIdRequiredDTORel: Ref<IEntityCustomIdRequiredDTORel> = ref({});

    const retrieveEntityCustomIdRequiredDTORel = async entityCustomIdRequiredDTORelRelatedId => {
      try {
        const res = await entityCustomIdRequiredDTORelService().find(entityCustomIdRequiredDTORelRelatedId);
        entityCustomIdRequiredDTORel.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.entityCustomIdRequiredDTORelId) {
      retrieveEntityCustomIdRequiredDTORel(route.params.entityCustomIdRequiredDTORelId);
    }

    return {
      alertService,
      entityCustomIdRequiredDTORel,

      previousState,
      t$: useI18n().t,
    };
  },
});
