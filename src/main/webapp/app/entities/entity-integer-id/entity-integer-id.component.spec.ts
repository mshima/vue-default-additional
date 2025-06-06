import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import EntityIntegerId from './entity-integer-id.vue';
import EntityIntegerIdService from './entity-integer-id.service';
import AlertService from '@/shared/alert/alert.service';

type EntityIntegerIdComponentType = InstanceType<typeof EntityIntegerId>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('EntityIntegerId Management Component', () => {
    let entityIntegerIdServiceStub: SinonStubbedInstance<EntityIntegerIdService>;
    let mountOptions: MountingOptions<EntityIntegerIdComponentType>['global'];

    beforeEach(() => {
      entityIntegerIdServiceStub = sinon.createStubInstance<EntityIntegerIdService>(EntityIntegerIdService);
      entityIntegerIdServiceStub.retrieve.resolves({ headers: {} });

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
          entityIntegerIdService: () => entityIntegerIdServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        entityIntegerIdServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(EntityIntegerId, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(entityIntegerIdServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.entityIntegerIds[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: EntityIntegerIdComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(EntityIntegerId, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        entityIntegerIdServiceStub.retrieve.reset();
        entityIntegerIdServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        entityIntegerIdServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeEntityIntegerId();
        await comp.$nextTick(); // clear components

        // THEN
        expect(entityIntegerIdServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(entityIntegerIdServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
