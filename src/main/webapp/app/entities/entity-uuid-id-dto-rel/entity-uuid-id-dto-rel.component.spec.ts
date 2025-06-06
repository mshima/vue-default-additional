import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import EntityUuidIdDTORel from './entity-uuid-id-dto-rel.vue';
import EntityUuidIdDTORelService from './entity-uuid-id-dto-rel.service';
import AlertService from '@/shared/alert/alert.service';

type EntityUuidIdDTORelComponentType = InstanceType<typeof EntityUuidIdDTORel>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('EntityUuidIdDTORel Management Component', () => {
    let entityUuidIdDTORelServiceStub: SinonStubbedInstance<EntityUuidIdDTORelService>;
    let mountOptions: MountingOptions<EntityUuidIdDTORelComponentType>['global'];

    beforeEach(() => {
      entityUuidIdDTORelServiceStub = sinon.createStubInstance<EntityUuidIdDTORelService>(EntityUuidIdDTORelService);
      entityUuidIdDTORelServiceStub.retrieve.resolves({ headers: {} });

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
          entityUuidIdDTORelService: () => entityUuidIdDTORelServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        entityUuidIdDTORelServiceStub.retrieve.resolves({ headers: {}, data: [{ id: '9fec3727-3421-4967-b213-ba36557ca194' }] });

        // WHEN
        const wrapper = shallowMount(EntityUuidIdDTORel, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(entityUuidIdDTORelServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.entityUuidIdDTORels[0]).toEqual(expect.objectContaining({ id: '9fec3727-3421-4967-b213-ba36557ca194' }));
      });
    });
    describe('Handles', () => {
      let comp: EntityUuidIdDTORelComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(EntityUuidIdDTORel, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        entityUuidIdDTORelServiceStub.retrieve.reset();
        entityUuidIdDTORelServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        entityUuidIdDTORelServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: '9fec3727-3421-4967-b213-ba36557ca194' });

        comp.removeEntityUuidIdDTORel();
        await comp.$nextTick(); // clear components

        // THEN
        expect(entityUuidIdDTORelServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(entityUuidIdDTORelServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
