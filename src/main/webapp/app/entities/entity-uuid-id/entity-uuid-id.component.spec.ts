import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import EntityUuidId from './entity-uuid-id.vue';
import EntityUuidIdService from './entity-uuid-id.service';
import AlertService from '@/shared/alert/alert.service';

type EntityUuidIdComponentType = InstanceType<typeof EntityUuidId>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('EntityUuidId Management Component', () => {
    let entityUuidIdServiceStub: SinonStubbedInstance<EntityUuidIdService>;
    let mountOptions: MountingOptions<EntityUuidIdComponentType>['global'];

    beforeEach(() => {
      entityUuidIdServiceStub = sinon.createStubInstance<EntityUuidIdService>(EntityUuidIdService);
      entityUuidIdServiceStub.retrieve.resolves({ headers: {} });

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
          entityUuidIdService: () => entityUuidIdServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        entityUuidIdServiceStub.retrieve.resolves({ headers: {}, data: [{ id: '9fec3727-3421-4967-b213-ba36557ca194' }] });

        // WHEN
        const wrapper = shallowMount(EntityUuidId, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(entityUuidIdServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.entityUuidIds[0]).toEqual(expect.objectContaining({ id: '9fec3727-3421-4967-b213-ba36557ca194' }));
      });
    });
    describe('Handles', () => {
      let comp: EntityUuidIdComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(EntityUuidId, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        entityUuidIdServiceStub.retrieve.reset();
        entityUuidIdServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        entityUuidIdServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: '9fec3727-3421-4967-b213-ba36557ca194' });

        comp.removeEntityUuidId();
        await comp.$nextTick(); // clear components

        // THEN
        expect(entityUuidIdServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(entityUuidIdServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
