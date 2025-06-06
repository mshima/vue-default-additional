import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import EntityCustomIdRequiredDTO from './entity-custom-id-required-dto.vue';
import EntityCustomIdRequiredDTOService from './entity-custom-id-required-dto.service';
import AlertService from '@/shared/alert/alert.service';

type EntityCustomIdRequiredDTOComponentType = InstanceType<typeof EntityCustomIdRequiredDTO>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('EntityCustomIdRequiredDTO Management Component', () => {
    let entityCustomIdRequiredDTOServiceStub: SinonStubbedInstance<EntityCustomIdRequiredDTOService>;
    let mountOptions: MountingOptions<EntityCustomIdRequiredDTOComponentType>['global'];

    beforeEach(() => {
      entityCustomIdRequiredDTOServiceStub = sinon.createStubInstance<EntityCustomIdRequiredDTOService>(EntityCustomIdRequiredDTOService);
      entityCustomIdRequiredDTOServiceStub.retrieve.resolves({ headers: {} });

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
          entityCustomIdRequiredDTOService: () => entityCustomIdRequiredDTOServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        entityCustomIdRequiredDTOServiceStub.retrieve.resolves({ headers: {}, data: [{ customId: 123 }] });

        // WHEN
        const wrapper = shallowMount(EntityCustomIdRequiredDTO, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(entityCustomIdRequiredDTOServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.entityCustomIdRequiredDTOS[0]).toEqual(expect.objectContaining({ customId: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: EntityCustomIdRequiredDTOComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(EntityCustomIdRequiredDTO, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        entityCustomIdRequiredDTOServiceStub.retrieve.reset();
        entityCustomIdRequiredDTOServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        entityCustomIdRequiredDTOServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ customId: 123 });

        comp.removeEntityCustomIdRequiredDTO();
        await comp.$nextTick(); // clear components

        // THEN
        expect(entityCustomIdRequiredDTOServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(entityCustomIdRequiredDTOServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
