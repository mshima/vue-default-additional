import { type Ref, defineComponent, inject, ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import EntityCustomSequenceService from './entity-custom-sequence.service';
import { type IEntityCustomSequence } from '@/shared/model/entity-custom-sequence.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  name: 'EntityCustomSequenceDetails',
  setup() {
    const entityCustomSequenceService = inject('entityCustomSequenceService', () => new EntityCustomSequenceService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const entityCustomSequence: Ref<IEntityCustomSequence> = ref({});

    const retrieveEntityCustomSequence = async entityCustomSequenceId => {
      try {
        const res = await entityCustomSequenceService().find(entityCustomSequenceId);
        entityCustomSequence.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.entityCustomSequenceId) {
      retrieveEntityCustomSequence(route.params.entityCustomSequenceId);
    }

    return {
      alertService,
      entityCustomSequence,

      previousState,
      t$: useI18n().t,
    };
  },
});
