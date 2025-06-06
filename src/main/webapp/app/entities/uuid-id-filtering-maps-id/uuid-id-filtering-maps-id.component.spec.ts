import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import UuidIdFilteringMapsId from './uuid-id-filtering-maps-id.vue';
import UuidIdFilteringMapsIdService from './uuid-id-filtering-maps-id.service';
import AlertService from '@/shared/alert/alert.service';

type UuidIdFilteringMapsIdComponentType = InstanceType<typeof UuidIdFilteringMapsId>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('UuidIdFilteringMapsId Management Component', () => {
    let uuidIdFilteringMapsIdServiceStub: SinonStubbedInstance<UuidIdFilteringMapsIdService>;
    let mountOptions: MountingOptions<UuidIdFilteringMapsIdComponentType>['global'];

    beforeEach(() => {
      uuidIdFilteringMapsIdServiceStub = sinon.createStubInstance<UuidIdFilteringMapsIdService>(UuidIdFilteringMapsIdService);
      uuidIdFilteringMapsIdServiceStub.retrieve.resolves({ headers: {} });

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
          uuidIdFilteringMapsIdService: () => uuidIdFilteringMapsIdServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        uuidIdFilteringMapsIdServiceStub.retrieve.resolves({ headers: {}, data: [{ customId: '9fec3727-3421-4967-b213-ba36557ca194' }] });

        // WHEN
        const wrapper = shallowMount(UuidIdFilteringMapsId, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(uuidIdFilteringMapsIdServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.uuidIdFilteringMapsIds[0]).toEqual(expect.objectContaining({ customId: '9fec3727-3421-4967-b213-ba36557ca194' }));
      });
    });
    describe('Handles', () => {
      let comp: UuidIdFilteringMapsIdComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(UuidIdFilteringMapsId, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        uuidIdFilteringMapsIdServiceStub.retrieve.reset();
        uuidIdFilteringMapsIdServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        uuidIdFilteringMapsIdServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ customId: '9fec3727-3421-4967-b213-ba36557ca194' });

        comp.removeUuidIdFilteringMapsId();
        await comp.$nextTick(); // clear components

        // THEN
        expect(uuidIdFilteringMapsIdServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(uuidIdFilteringMapsIdServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
