import { computed } from 'vue';
import { shallowMount } from '@vue/test-utils';
import axios from 'axios';
import sinon from 'sinon';

import { createTestingPinia } from '@pinia/testing';
import ChangePassword from './change-password.vue';

type ChangePasswordComponentType = InstanceType<typeof ChangePassword>;

const pinia = createTestingPinia();

const axiosStub = {
  get: sinon.stub(axios, 'get'),
  post: sinon.stub(axios, 'post'),
};

describe('ChangePassword Component', () => {
  let changePassword: ChangePasswordComponentType;

  beforeEach(() => {
    axiosStub.get.resolves({});
    axiosStub.post.reset();

    const wrapper = shallowMount(ChangePassword, {
      global: {
        plugins: [pinia],
        provide: {
          currentUsername: computed(() => 'username'),
        },
      },
    });
    changePassword = wrapper.vm;
  });

  it('should show error if passwords do not match', () => {
    // GIVEN
    changePassword.resetPassword = { newPassword: 'password1', confirmPassword: 'password2' };
    // WHEN
    changePassword.changePassword();
    // THEN
    expect(changePassword.doNotMatch).toBe('ERROR');
    expect(changePassword.error).toBeNull();
    expect(changePassword.success).toBeNull();
  });

  it('should call Auth.changePassword when passwords match and set success to OK upon success', async () => {
    // GIVEN
    changePassword.resetPassword = { currentPassword: 'password1', newPassword: 'password1', confirmPassword: 'password1' };
    axiosStub.post.resolves({});

    // WHEN
    changePassword.changePassword();
    await changePassword.$nextTick();

    // THEN
    expect(
      axiosStub.post.calledWith('api/account/change-password', {
        currentPassword: 'password1',
        newPassword: 'password1',
      }),
    ).toBeTruthy();

    expect(changePassword.doNotMatch).toBeNull();
    expect(changePassword.error).toBeNull();
    expect(changePassword.success).toBe('OK');
  });

  it('should notify of error if change password fails', async () => {
    // GIVEN
    changePassword.resetPassword = { currentPassword: 'password1', newPassword: 'password1', confirmPassword: 'password1' };
    axiosStub.post.rejects({});

    // WHEN
    changePassword.changePassword();
    await changePassword.$nextTick();

    // THEN
    expect(changePassword.doNotMatch).toBeNull();
    expect(changePassword.success).toBeNull();
    await changePassword.$nextTick();
    expect(changePassword.error).toBe('ERROR');
  });
});
