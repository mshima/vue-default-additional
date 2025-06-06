import { defineComponent, provide } from 'vue';
import { useI18n } from 'vue-i18n';
import { storeToRefs } from 'pinia';
import { BToastOrchestrator } from 'bootstrap-vue-next';

import { useLoginModal } from '@/account/login-modal';
import LoginForm from '@/account/login-form/login-form.vue';
import Ribbon from '@/core/ribbon/ribbon.vue';
import JhiFooter from '@/core/jhi-footer/jhi-footer.vue';
import JhiNavbar from '@/core/jhi-navbar/jhi-navbar.vue';
import { useAlertService } from '@/shared/alert/alert.service';
import '@/shared/config/dayjs';

export default defineComponent({
  name: 'App',
  components: {
    BToastOrchestrator,
    Ribbon,
    JhiNavbar,
    LoginForm,
    JhiFooter,
  },
  setup() {
    provide('alertService', useAlertService());
    const { loginModalOpen } = storeToRefs(useLoginModal());

    return {
      loginModalOpen,
      t$: useI18n().t,
    };
  },
});
