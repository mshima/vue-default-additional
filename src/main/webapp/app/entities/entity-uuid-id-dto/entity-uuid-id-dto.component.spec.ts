import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import EntityUuidIdDTO from './entity-uuid-id-dto.vue';
import EntityUuidIdDTOService from './entity-uuid-id-dto.service';
import AlertService from '@/shared/alert/alert.service';

type EntityUuidIdDTOComponentType = InstanceType<typeof EntityUuidIdDTO>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('EntityUuidIdDTO Management Component', () => {
    let entityUuidIdDTOServiceStub: SinonStubbedInstance<EntityUuidIdDTOService>;
    let mountOptions: MountingOptions<EntityUuidIdDTOComponentType>['global'];

    beforeEach(() => {
      entityUuidIdDTOServiceStub = sinon.createStubInstance<EntityUuidIdDTOService>(EntityUuidIdDTOService);
      entityUuidIdDTOServiceStub.retrieve.resolves({ headers: {} });

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
          entityUuidIdDTOService: () => entityUuidIdDTOServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        entityUuidIdDTOServiceStub.retrieve.resolves({ headers: {}, data: [{ id: '9fec3727-3421-4967-b213-ba36557ca194' }] });

        // WHEN
        const wrapper = shallowMount(EntityUuidIdDTO, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(entityUuidIdDTOServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.entityUuidIdDTOS[0]).toEqual(expect.objectContaining({ id: '9fec3727-3421-4967-b213-ba36557ca194' }));
      });
    });
    describe('Handles', () => {
      let comp: EntityUuidIdDTOComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(EntityUuidIdDTO, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        entityUuidIdDTOServiceStub.retrieve.reset();
        entityUuidIdDTOServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        entityUuidIdDTOServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: '9fec3727-3421-4967-b213-ba36557ca194' });

        comp.removeEntityUuidIdDTO();
        await comp.$nextTick(); // clear components

        // THEN
        expect(entityUuidIdDTOServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(entityUuidIdDTOServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
