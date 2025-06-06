import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import UuidIdFiltering from './uuid-id-filtering.vue';
import UuidIdFilteringService from './uuid-id-filtering.service';
import AlertService from '@/shared/alert/alert.service';

type UuidIdFilteringComponentType = InstanceType<typeof UuidIdFiltering>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('UuidIdFiltering Management Component', () => {
    let uuidIdFilteringServiceStub: SinonStubbedInstance<UuidIdFilteringService>;
    let mountOptions: MountingOptions<UuidIdFilteringComponentType>['global'];

    beforeEach(() => {
      uuidIdFilteringServiceStub = sinon.createStubInstance<UuidIdFilteringService>(UuidIdFilteringService);
      uuidIdFilteringServiceStub.retrieve.resolves({ headers: {} });

      alertService = new AlertService({
        i18n: { t: vitest.fn() } as any,
        toastController: {
          show: vitest.fn(),
        } as any,
      });

      mountOptions = {
        stubs: {
          bModal: bModalStub as any,
          'font-awesome-icon': true,
          'b-badge': true,
          'b-button': true,
          'router-link': true,
        },
        directives: {
          'b-modal': {},
        },
        provide: {
          alertService,
          uuidIdFilteringService: () => uuidIdFilteringServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        uuidIdFilteringServiceStub.retrieve.resolves({ headers: {}, data: [{ customId: '9fec3727-3421-4967-b213-ba36557ca194' }] });

        // WHEN
        const wrapper = shallowMount(UuidIdFiltering, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(uuidIdFilteringServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.uuidIdFilterings[0]).toEqual(expect.objectContaining({ customId: '9fec3727-3421-4967-b213-ba36557ca194' }));
      });
    });
    describe('Handles', () => {
      let comp: UuidIdFilteringComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(UuidIdFiltering, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        uuidIdFilteringServiceStub.retrieve.reset();
        uuidIdFilteringServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        uuidIdFilteringServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ customId: '9fec3727-3421-4967-b213-ba36557ca194' });

        comp.removeUuidIdFiltering();
        await comp.$nextTick(); // clear components

        // THEN
        expect(uuidIdFilteringServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(uuidIdFilteringServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
