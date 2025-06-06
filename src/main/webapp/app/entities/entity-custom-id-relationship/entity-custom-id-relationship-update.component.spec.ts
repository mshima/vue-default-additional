import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import EntityCustomIdRelationshipUpdate from './entity-custom-id-relationship-update.vue';
import EntityCustomIdRelationshipService from './entity-custom-id-relationship.service';
import AlertService from '@/shared/alert/alert.service';

import EntityCustomIdService from '@/entities/entity-custom-id/entity-custom-id.service';
import EntityCustomIdMapsIdService from '@/entities/entity-custom-id-maps-id/entity-custom-id-maps-id.service';

type EntityCustomIdRelationshipUpdateComponentType = InstanceType<typeof EntityCustomIdRelationshipUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const entityCustomIdRelationshipSample = { relatedId: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<EntityCustomIdRelationshipUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('EntityCustomIdRelationship Management Update Component', () => {
    let comp: EntityCustomIdRelationshipUpdateComponentType;
    let entityCustomIdRelationshipServiceStub: SinonStubbedInstance<EntityCustomIdRelationshipService>;

    beforeEach(() => {
      route = {};
      entityCustomIdRelationshipServiceStub =
        sinon.createStubInstance<EntityCustomIdRelationshipService>(EntityCustomIdRelationshipService);
      entityCustomIdRelationshipServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

      alertService = new AlertService({
        i18n: { t: vitest.fn() } as any,
        toastController: {
          show: vitest.fn(),
        } as any,
      });

      mountOptions = {
        stubs: {
          'font-awesome-icon': true,
          'b-input-group': true,
          'b-input-group-prepend': true,
          'b-form-datepicker': true,
          'b-form-input': true,
        },
        provide: {
          alertService,
          entityCustomIdRelationshipService: () => entityCustomIdRelationshipServiceStub,
          entityCustomIdService: () =>
            sinon.createStubInstance<EntityCustomIdService>(EntityCustomIdService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          entityCustomIdMapsIdService: () =>
            sinon.createStubInstance<EntityCustomIdMapsIdService>(EntityCustomIdMapsIdService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
        },
      };
    });

    afterEach(() => {
      vitest.resetAllMocks();
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const wrapper = shallowMount(EntityCustomIdRelationshipUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.entityCustomIdRelationship = entityCustomIdRelationshipSample;
        entityCustomIdRelationshipServiceStub.update.resolves(entityCustomIdRelationshipSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(entityCustomIdRelationshipServiceStub.update.calledWith(entityCustomIdRelationshipSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        entityCustomIdRelationshipServiceStub.create.resolves(entity);
        const wrapper = shallowMount(EntityCustomIdRelationshipUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.entityCustomIdRelationship = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(entityCustomIdRelationshipServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        entityCustomIdRelationshipServiceStub.find.resolves(entityCustomIdRelationshipSample);
        entityCustomIdRelationshipServiceStub.retrieve.resolves([entityCustomIdRelationshipSample]);

        // WHEN
        route = {
          params: {
            entityCustomIdRelationshipId: `${entityCustomIdRelationshipSample.relatedId}`,
          },
        };
        const wrapper = shallowMount(EntityCustomIdRelationshipUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.entityCustomIdRelationship).toMatchObject(entityCustomIdRelationshipSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        entityCustomIdRelationshipServiceStub.find.resolves(entityCustomIdRelationshipSample);
        const wrapper = shallowMount(EntityCustomIdRelationshipUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
