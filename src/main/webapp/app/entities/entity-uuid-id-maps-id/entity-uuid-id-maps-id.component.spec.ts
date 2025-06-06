import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import EntityUuidIdMapsId from './entity-uuid-id-maps-id.vue';
import EntityUuidIdMapsIdService from './entity-uuid-id-maps-id.service';
import AlertService from '@/shared/alert/alert.service';

type EntityUuidIdMapsIdComponentType = InstanceType<typeof EntityUuidIdMapsId>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('EntityUuidIdMapsId Management Component', () => {
    let entityUuidIdMapsIdServiceStub: SinonStubbedInstance<EntityUuidIdMapsIdService>;
    let mountOptions: MountingOptions<EntityUuidIdMapsIdComponentType>['global'];

    beforeEach(() => {
      entityUuidIdMapsIdServiceStub = sinon.createStubInstance<EntityUuidIdMapsIdService>(EntityUuidIdMapsIdService);
      entityUuidIdMapsIdServiceStub.retrieve.resolves({ headers: {} });

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
          entityUuidIdMapsIdService: () => entityUuidIdMapsIdServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        entityUuidIdMapsIdServiceStub.retrieve.resolves({ headers: {}, data: [{ id: '9fec3727-3421-4967-b213-ba36557ca194' }] });

        // WHEN
        const wrapper = shallowMount(EntityUuidIdMapsId, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(entityUuidIdMapsIdServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.entityUuidIdMapsIds[0]).toEqual(expect.objectContaining({ id: '9fec3727-3421-4967-b213-ba36557ca194' }));
      });
    });
    describe('Handles', () => {
      let comp: EntityUuidIdMapsIdComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(EntityUuidIdMapsId, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        entityUuidIdMapsIdServiceStub.retrieve.reset();
        entityUuidIdMapsIdServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        entityUuidIdMapsIdServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: '9fec3727-3421-4967-b213-ba36557ca194' });

        comp.removeEntityUuidIdMapsId();
        await comp.$nextTick(); // clear components

        // THEN
        expect(entityUuidIdMapsIdServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(entityUuidIdMapsIdServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
