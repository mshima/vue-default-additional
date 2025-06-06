import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import EntityCustomIdRequiredDTOMapsId from './entity-custom-id-required-dto-maps-id.vue';
import EntityCustomIdRequiredDTOMapsIdService from './entity-custom-id-required-dto-maps-id.service';
import AlertService from '@/shared/alert/alert.service';

type EntityCustomIdRequiredDTOMapsIdComponentType = InstanceType<typeof EntityCustomIdRequiredDTOMapsId>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('EntityCustomIdRequiredDTOMapsId Management Component', () => {
    let entityCustomIdRequiredDTOMapsIdServiceStub: SinonStubbedInstance<EntityCustomIdRequiredDTOMapsIdService>;
    let mountOptions: MountingOptions<EntityCustomIdRequiredDTOMapsIdComponentType>['global'];

    beforeEach(() => {
      entityCustomIdRequiredDTOMapsIdServiceStub = sinon.createStubInstance<EntityCustomIdRequiredDTOMapsIdService>(
        EntityCustomIdRequiredDTOMapsIdService,
      );
      entityCustomIdRequiredDTOMapsIdServiceStub.retrieve.resolves({ headers: {} });

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
          entityCustomIdRequiredDTOMapsIdService: () => entityCustomIdRequiredDTOMapsIdServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        entityCustomIdRequiredDTOMapsIdServiceStub.retrieve.resolves({ headers: {}, data: [{ customId: 123 }] });

        // WHEN
        const wrapper = shallowMount(EntityCustomIdRequiredDTOMapsId, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(entityCustomIdRequiredDTOMapsIdServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.entityCustomIdRequiredDTOMapsIds[0]).toEqual(expect.objectContaining({ customId: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: EntityCustomIdRequiredDTOMapsIdComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(EntityCustomIdRequiredDTOMapsId, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        entityCustomIdRequiredDTOMapsIdServiceStub.retrieve.reset();
        entityCustomIdRequiredDTOMapsIdServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        entityCustomIdRequiredDTOMapsIdServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ customId: 123 });

        comp.removeEntityCustomIdRequiredDTOMapsId();
        await comp.$nextTick(); // clear components

        // THEN
        expect(entityCustomIdRequiredDTOMapsIdServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(entityCustomIdRequiredDTOMapsIdServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
