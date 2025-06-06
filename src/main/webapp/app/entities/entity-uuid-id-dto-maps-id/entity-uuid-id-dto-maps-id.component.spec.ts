import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import EntityUuidIdDTOMapsId from './entity-uuid-id-dto-maps-id.vue';
import EntityUuidIdDTOMapsIdService from './entity-uuid-id-dto-maps-id.service';
import AlertService from '@/shared/alert/alert.service';

type EntityUuidIdDTOMapsIdComponentType = InstanceType<typeof EntityUuidIdDTOMapsId>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('EntityUuidIdDTOMapsId Management Component', () => {
    let entityUuidIdDTOMapsIdServiceStub: SinonStubbedInstance<EntityUuidIdDTOMapsIdService>;
    let mountOptions: MountingOptions<EntityUuidIdDTOMapsIdComponentType>['global'];

    beforeEach(() => {
      entityUuidIdDTOMapsIdServiceStub = sinon.createStubInstance<EntityUuidIdDTOMapsIdService>(EntityUuidIdDTOMapsIdService);
      entityUuidIdDTOMapsIdServiceStub.retrieve.resolves({ headers: {} });

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
          entityUuidIdDTOMapsIdService: () => entityUuidIdDTOMapsIdServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        entityUuidIdDTOMapsIdServiceStub.retrieve.resolves({ headers: {}, data: [{ id: '9fec3727-3421-4967-b213-ba36557ca194' }] });

        // WHEN
        const wrapper = shallowMount(EntityUuidIdDTOMapsId, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(entityUuidIdDTOMapsIdServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.entityUuidIdDTOMapsIds[0]).toEqual(expect.objectContaining({ id: '9fec3727-3421-4967-b213-ba36557ca194' }));
      });
    });
    describe('Handles', () => {
      let comp: EntityUuidIdDTOMapsIdComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(EntityUuidIdDTOMapsId, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        entityUuidIdDTOMapsIdServiceStub.retrieve.reset();
        entityUuidIdDTOMapsIdServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        entityUuidIdDTOMapsIdServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: '9fec3727-3421-4967-b213-ba36557ca194' });

        comp.removeEntityUuidIdDTOMapsId();
        await comp.$nextTick(); // clear components

        // THEN
        expect(entityUuidIdDTOMapsIdServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(entityUuidIdDTOMapsIdServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
