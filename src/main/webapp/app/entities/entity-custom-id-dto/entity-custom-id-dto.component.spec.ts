import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import EntityCustomIdDTO from './entity-custom-id-dto.vue';
import EntityCustomIdDTOService from './entity-custom-id-dto.service';
import AlertService from '@/shared/alert/alert.service';

type EntityCustomIdDTOComponentType = InstanceType<typeof EntityCustomIdDTO>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('EntityCustomIdDTO Management Component', () => {
    let entityCustomIdDTOServiceStub: SinonStubbedInstance<EntityCustomIdDTOService>;
    let mountOptions: MountingOptions<EntityCustomIdDTOComponentType>['global'];

    beforeEach(() => {
      entityCustomIdDTOServiceStub = sinon.createStubInstance<EntityCustomIdDTOService>(EntityCustomIdDTOService);
      entityCustomIdDTOServiceStub.retrieve.resolves({ headers: {} });

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
          entityCustomIdDTOService: () => entityCustomIdDTOServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        entityCustomIdDTOServiceStub.retrieve.resolves({ headers: {}, data: [{ customId: 123 }] });

        // WHEN
        const wrapper = shallowMount(EntityCustomIdDTO, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(entityCustomIdDTOServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.entityCustomIdDTOS[0]).toEqual(expect.objectContaining({ customId: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: EntityCustomIdDTOComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(EntityCustomIdDTO, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        entityCustomIdDTOServiceStub.retrieve.reset();
        entityCustomIdDTOServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        entityCustomIdDTOServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ customId: 123 });

        comp.removeEntityCustomIdDTO();
        await comp.$nextTick(); // clear components

        // THEN
        expect(entityCustomIdDTOServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(entityCustomIdDTOServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
