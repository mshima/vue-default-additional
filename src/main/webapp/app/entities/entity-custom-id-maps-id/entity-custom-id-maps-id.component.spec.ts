import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import EntityCustomIdMapsId from './entity-custom-id-maps-id.vue';
import EntityCustomIdMapsIdService from './entity-custom-id-maps-id.service';
import AlertService from '@/shared/alert/alert.service';

type EntityCustomIdMapsIdComponentType = InstanceType<typeof EntityCustomIdMapsId>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('EntityCustomIdMapsId Management Component', () => {
    let entityCustomIdMapsIdServiceStub: SinonStubbedInstance<EntityCustomIdMapsIdService>;
    let mountOptions: MountingOptions<EntityCustomIdMapsIdComponentType>['global'];

    beforeEach(() => {
      entityCustomIdMapsIdServiceStub = sinon.createStubInstance<EntityCustomIdMapsIdService>(EntityCustomIdMapsIdService);
      entityCustomIdMapsIdServiceStub.retrieve.resolves({ headers: {} });

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
          entityCustomIdMapsIdService: () => entityCustomIdMapsIdServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        entityCustomIdMapsIdServiceStub.retrieve.resolves({ headers: {}, data: [{ customId: 123 }] });

        // WHEN
        const wrapper = shallowMount(EntityCustomIdMapsId, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(entityCustomIdMapsIdServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.entityCustomIdMapsIds[0]).toEqual(expect.objectContaining({ customId: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: EntityCustomIdMapsIdComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(EntityCustomIdMapsId, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        entityCustomIdMapsIdServiceStub.retrieve.reset();
        entityCustomIdMapsIdServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        entityCustomIdMapsIdServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ customId: 123 });

        comp.removeEntityCustomIdMapsId();
        await comp.$nextTick(); // clear components

        // THEN
        expect(entityCustomIdMapsIdServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(entityCustomIdMapsIdServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
