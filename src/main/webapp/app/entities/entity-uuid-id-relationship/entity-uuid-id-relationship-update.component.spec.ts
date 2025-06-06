import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import EntityUuidIdRelationshipUpdate from './entity-uuid-id-relationship-update.vue';
import EntityUuidIdRelationshipService from './entity-uuid-id-relationship.service';
import AlertService from '@/shared/alert/alert.service';

import EntityUuidIdService from '@/entities/entity-uuid-id/entity-uuid-id.service';
import EntityUuidIdMapsIdService from '@/entities/entity-uuid-id-maps-id/entity-uuid-id-maps-id.service';

type EntityUuidIdRelationshipUpdateComponentType = InstanceType<typeof EntityUuidIdRelationshipUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const entityUuidIdRelationshipSample = { id: '9fec3727-3421-4967-b213-ba36557ca194' };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<EntityUuidIdRelationshipUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('EntityUuidIdRelationship Management Update Component', () => {
    let comp: EntityUuidIdRelationshipUpdateComponentType;
    let entityUuidIdRelationshipServiceStub: SinonStubbedInstance<EntityUuidIdRelationshipService>;

    beforeEach(() => {
      route = {};
      entityUuidIdRelationshipServiceStub = sinon.createStubInstance<EntityUuidIdRelationshipService>(EntityUuidIdRelationshipService);
      entityUuidIdRelationshipServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          entityUuidIdRelationshipService: () => entityUuidIdRelationshipServiceStub,
          entityUuidIdService: () =>
            sinon.createStubInstance<EntityUuidIdService>(EntityUuidIdService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          entityUuidIdMapsIdService: () =>
            sinon.createStubInstance<EntityUuidIdMapsIdService>(EntityUuidIdMapsIdService, {
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
        const wrapper = shallowMount(EntityUuidIdRelationshipUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.entityUuidIdRelationship = entityUuidIdRelationshipSample;
        entityUuidIdRelationshipServiceStub.update.resolves(entityUuidIdRelationshipSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(entityUuidIdRelationshipServiceStub.update.calledWith(entityUuidIdRelationshipSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        entityUuidIdRelationshipServiceStub.create.resolves(entity);
        const wrapper = shallowMount(EntityUuidIdRelationshipUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.entityUuidIdRelationship = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(entityUuidIdRelationshipServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        entityUuidIdRelationshipServiceStub.find.resolves(entityUuidIdRelationshipSample);
        entityUuidIdRelationshipServiceStub.retrieve.resolves([entityUuidIdRelationshipSample]);

        // WHEN
        route = {
          params: {
            entityUuidIdRelationshipId: `${entityUuidIdRelationshipSample.id}`,
          },
        };
        const wrapper = shallowMount(EntityUuidIdRelationshipUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.entityUuidIdRelationship).toMatchObject(entityUuidIdRelationshipSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        entityUuidIdRelationshipServiceStub.find.resolves(entityUuidIdRelationshipSample);
        const wrapper = shallowMount(EntityUuidIdRelationshipUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
