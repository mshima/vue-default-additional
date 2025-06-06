import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import EntityCustomIdDTORel from './entity-custom-id-dto-rel.vue';
import EntityCustomIdDTORelService from './entity-custom-id-dto-rel.service';
import AlertService from '@/shared/alert/alert.service';

type EntityCustomIdDTORelComponentType = InstanceType<typeof EntityCustomIdDTORel>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('EntityCustomIdDTORel Management Component', () => {
    let entityCustomIdDTORelServiceStub: SinonStubbedInstance<EntityCustomIdDTORelService>;
    let mountOptions: MountingOptions<EntityCustomIdDTORelComponentType>['global'];

    beforeEach(() => {
      entityCustomIdDTORelServiceStub = sinon.createStubInstance<EntityCustomIdDTORelService>(EntityCustomIdDTORelService);
      entityCustomIdDTORelServiceStub.retrieve.resolves({ headers: {} });

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
          entityCustomIdDTORelService: () => entityCustomIdDTORelServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        entityCustomIdDTORelServiceStub.retrieve.resolves({ headers: {}, data: [{ relatedId: 123 }] });

        // WHEN
        const wrapper = shallowMount(EntityCustomIdDTORel, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(entityCustomIdDTORelServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.entityCustomIdDTORels[0]).toEqual(expect.objectContaining({ relatedId: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: EntityCustomIdDTORelComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(EntityCustomIdDTORel, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        entityCustomIdDTORelServiceStub.retrieve.reset();
        entityCustomIdDTORelServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        entityCustomIdDTORelServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ relatedId: 123 });

        comp.removeEntityCustomIdDTORel();
        await comp.$nextTick(); // clear components

        // THEN
        expect(entityCustomIdDTORelServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(entityCustomIdDTORelServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
