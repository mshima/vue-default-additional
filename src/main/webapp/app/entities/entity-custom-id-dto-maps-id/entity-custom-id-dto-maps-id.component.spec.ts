import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import EntityCustomIdDTOMapsId from './entity-custom-id-dto-maps-id.vue';
import EntityCustomIdDTOMapsIdService from './entity-custom-id-dto-maps-id.service';
import AlertService from '@/shared/alert/alert.service';

type EntityCustomIdDTOMapsIdComponentType = InstanceType<typeof EntityCustomIdDTOMapsId>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('EntityCustomIdDTOMapsId Management Component', () => {
    let entityCustomIdDTOMapsIdServiceStub: SinonStubbedInstance<EntityCustomIdDTOMapsIdService>;
    let mountOptions: MountingOptions<EntityCustomIdDTOMapsIdComponentType>['global'];

    beforeEach(() => {
      entityCustomIdDTOMapsIdServiceStub = sinon.createStubInstance<EntityCustomIdDTOMapsIdService>(EntityCustomIdDTOMapsIdService);
      entityCustomIdDTOMapsIdServiceStub.retrieve.resolves({ headers: {} });

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
          entityCustomIdDTOMapsIdService: () => entityCustomIdDTOMapsIdServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        entityCustomIdDTOMapsIdServiceStub.retrieve.resolves({ headers: {}, data: [{ customId: 123 }] });

        // WHEN
        const wrapper = shallowMount(EntityCustomIdDTOMapsId, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(entityCustomIdDTOMapsIdServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.entityCustomIdDTOMapsIds[0]).toEqual(expect.objectContaining({ customId: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: EntityCustomIdDTOMapsIdComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(EntityCustomIdDTOMapsId, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        entityCustomIdDTOMapsIdServiceStub.retrieve.reset();
        entityCustomIdDTOMapsIdServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        entityCustomIdDTOMapsIdServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ customId: 123 });

        comp.removeEntityCustomIdDTOMapsId();
        await comp.$nextTick(); // clear components

        // THEN
        expect(entityCustomIdDTOMapsIdServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(entityCustomIdDTOMapsIdServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
