import { type Ref, computed, defineComponent, inject, ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import EntityCustomSequenceService from './entity-custom-sequence.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import { EntityCustomSequence, type IEntityCustomSequence } from '@/shared/model/entity-custom-sequence.model';

export default defineComponent({
  name: 'EntityCustomSequenceUpdate',
  setup() {
    const entityCustomSequenceService = inject('entityCustomSequenceService', () => new EntityCustomSequenceService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const entityCustomSequence: Ref<IEntityCustomSequence> = ref(new EntityCustomSequence());
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'en'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

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

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      name: {},
    };
    const v$ = useVuelidate(validationRules, entityCustomSequence as any);
    v$.value.$validate();

    return {
      entityCustomSequenceService,
      alertService,
      entityCustomSequence,
      previousState,
      isSaving,
      currentLanguage,
      v$,
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.entityCustomSequence.id) {
        this.entityCustomSequenceService()
          .update(this.entityCustomSequence)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jhipsterVueApp.entityCustomSequence.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.entityCustomSequenceService()
          .create(this.entityCustomSequence)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jhipsterVueApp.entityCustomSequence.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
