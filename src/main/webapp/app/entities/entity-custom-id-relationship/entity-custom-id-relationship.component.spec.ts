import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import EntityCustomIdRelationship from './entity-custom-id-relationship.vue';
import EntityCustomIdRelationshipService from './entity-custom-id-relationship.service';
import AlertService from '@/shared/alert/alert.service';

type EntityCustomIdRelationshipComponentType = InstanceType<typeof EntityCustomIdRelationship>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('EntityCustomIdRelationship Management Component', () => {
    let entityCustomIdRelationshipServiceStub: SinonStubbedInstance<EntityCustomIdRelationshipService>;
    let mountOptions: MountingOptions<EntityCustomIdRelationshipComponentType>['global'];

    beforeEach(() => {
      entityCustomIdRelationshipServiceStub =
        sinon.createStubInstance<EntityCustomIdRelationshipService>(EntityCustomIdRelationshipService);
      entityCustomIdRelationshipServiceStub.retrieve.resolves({ headers: {} });

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
          entityCustomIdRelationshipService: () => entityCustomIdRelationshipServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        entityCustomIdRelationshipServiceStub.retrieve.resolves({ headers: {}, data: [{ relatedId: 123 }] });

        // WHEN
        const wrapper = shallowMount(EntityCustomIdRelationship, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(entityCustomIdRelationshipServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.entityCustomIdRelationships[0]).toEqual(expect.objectContaining({ relatedId: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: EntityCustomIdRelationshipComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(EntityCustomIdRelationship, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        entityCustomIdRelationshipServiceStub.retrieve.reset();
        entityCustomIdRelationshipServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        entityCustomIdRelationshipServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ relatedId: 123 });

        comp.removeEntityCustomIdRelationship();
        await comp.$nextTick(); // clear components

        // THEN
        expect(entityCustomIdRelationshipServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(entityCustomIdRelationshipServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
