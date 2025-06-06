import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import EntityCustomIdRequiredDTORel from './entity-custom-id-required-dto-rel.vue';
import EntityCustomIdRequiredDTORelService from './entity-custom-id-required-dto-rel.service';
import AlertService from '@/shared/alert/alert.service';

type EntityCustomIdRequiredDTORelComponentType = InstanceType<typeof EntityCustomIdRequiredDTORel>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('EntityCustomIdRequiredDTORel Management Component', () => {
    let entityCustomIdRequiredDTORelServiceStub: SinonStubbedInstance<EntityCustomIdRequiredDTORelService>;
    let mountOptions: MountingOptions<EntityCustomIdRequiredDTORelComponentType>['global'];

    beforeEach(() => {
      entityCustomIdRequiredDTORelServiceStub =
        sinon.createStubInstance<EntityCustomIdRequiredDTORelService>(EntityCustomIdRequiredDTORelService);
      entityCustomIdRequiredDTORelServiceStub.retrieve.resolves({ headers: {} });

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
          entityCustomIdRequiredDTORelService: () => entityCustomIdRequiredDTORelServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        entityCustomIdRequiredDTORelServiceStub.retrieve.resolves({ headers: {}, data: [{ relatedId: 123 }] });

        // WHEN
        const wrapper = shallowMount(EntityCustomIdRequiredDTORel, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(entityCustomIdRequiredDTORelServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.entityCustomIdRequiredDTORels[0]).toEqual(expect.objectContaining({ relatedId: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: EntityCustomIdRequiredDTORelComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(EntityCustomIdRequiredDTORel, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        entityCustomIdRequiredDTORelServiceStub.retrieve.reset();
        entityCustomIdRequiredDTORelServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        entityCustomIdRequiredDTORelServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ relatedId: 123 });

        comp.removeEntityCustomIdRequiredDTORel();
        await comp.$nextTick(); // clear components

        // THEN
        expect(entityCustomIdRequiredDTORelServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(entityCustomIdRequiredDTORelServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
