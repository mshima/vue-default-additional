import { type Ref, computed, defineComponent, inject, ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import EntityIntegerIdService from './entity-integer-id.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import { EntityIntegerId, type IEntityIntegerId } from '@/shared/model/entity-integer-id.model';

export default defineComponent({
  name: 'EntityIntegerIdUpdate',
  setup() {
    const entityIntegerIdService = inject('entityIntegerIdService', () => new EntityIntegerIdService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const entityIntegerId: Ref<IEntityIntegerId> = ref(new EntityIntegerId());
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'en'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveEntityIntegerId = async entityIntegerIdId => {
      try {
        const res = await entityIntegerIdService().find(entityIntegerIdId);
        entityIntegerId.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.entityIntegerIdId) {
      retrieveEntityIntegerId(route.params.entityIntegerIdId);
    }

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      name: {},
    };
    const v$ = useVuelidate(validationRules, entityIntegerId as any);
    v$.value.$validate();

    return {
      entityIntegerIdService,
      alertService,
      entityIntegerId,
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
      if (this.entityIntegerId.id) {
        this.entityIntegerIdService()
          .update(this.entityIntegerId)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jhipsterVueApp.entityIntegerId.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.entityIntegerIdService()
          .create(this.entityIntegerId)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jhipsterVueApp.entityIntegerId.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
