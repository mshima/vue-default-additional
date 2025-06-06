import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import EntityCustomId from './entity-custom-id.vue';
import EntityCustomIdService from './entity-custom-id.service';
import AlertService from '@/shared/alert/alert.service';

type EntityCustomIdComponentType = InstanceType<typeof EntityCustomId>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('EntityCustomId Management Component', () => {
    let entityCustomIdServiceStub: SinonStubbedInstance<EntityCustomIdService>;
    let mountOptions: MountingOptions<EntityCustomIdComponentType>['global'];

    beforeEach(() => {
      entityCustomIdServiceStub = sinon.createStubInstance<EntityCustomIdService>(EntityCustomIdService);
      entityCustomIdServiceStub.retrieve.resolves({ headers: {} });

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
          entityCustomIdService: () => entityCustomIdServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        entityCustomIdServiceStub.retrieve.resolves({ headers: {}, data: [{ customId: 123 }] });

        // WHEN
        const wrapper = shallowMount(EntityCustomId, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(entityCustomIdServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.entityCustomIds[0]).toEqual(expect.objectContaining({ customId: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: EntityCustomIdComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(EntityCustomId, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        entityCustomIdServiceStub.retrieve.reset();
        entityCustomIdServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        entityCustomIdServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ customId: 123 });

        comp.removeEntityCustomId();
        await comp.$nextTick(); // clear components

        // THEN
        expect(entityCustomIdServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(entityCustomIdServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
