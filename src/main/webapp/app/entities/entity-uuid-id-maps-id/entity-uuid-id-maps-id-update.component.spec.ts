import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import EntityUuidIdMapsIdUpdate from './entity-uuid-id-maps-id-update.vue';
import EntityUuidIdMapsIdService from './entity-uuid-id-maps-id.service';
import AlertService from '@/shared/alert/alert.service';

import EntityUuidIdService from '@/entities/entity-uuid-id/entity-uuid-id.service';
import EntityUuidIdRelationshipService from '@/entities/entity-uuid-id-relationship/entity-uuid-id-relationship.service';

type EntityUuidIdMapsIdUpdateComponentType = InstanceType<typeof EntityUuidIdMapsIdUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const entityUuidIdMapsIdSample = { id: '9fec3727-3421-4967-b213-ba36557ca194' };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<EntityUuidIdMapsIdUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('EntityUuidIdMapsId Management Update Component', () => {
    let comp: EntityUuidIdMapsIdUpdateComponentType;
    let entityUuidIdMapsIdServiceStub: SinonStubbedInstance<EntityUuidIdMapsIdService>;

    beforeEach(() => {
      route = {};
      entityUuidIdMapsIdServiceStub = sinon.createStubInstance<EntityUuidIdMapsIdService>(EntityUuidIdMapsIdService);
      entityUuidIdMapsIdServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          entityUuidIdMapsIdService: () => entityUuidIdMapsIdServiceStub,
          entityUuidIdService: () =>
            sinon.createStubInstance<EntityUuidIdService>(EntityUuidIdService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          entityUuidIdRelationshipService: () =>
            sinon.createStubInstance<EntityUuidIdRelationshipService>(EntityUuidIdRelationshipService, {
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
        const wrapper = shallowMount(EntityUuidIdMapsIdUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.entityUuidIdMapsId = entityUuidIdMapsIdSample;
        entityUuidIdMapsIdServiceStub.update.resolves(entityUuidIdMapsIdSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(entityUuidIdMapsIdServiceStub.update.calledWith(entityUuidIdMapsIdSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        entityUuidIdMapsIdServiceStub.create.resolves(entity);
        const wrapper = shallowMount(EntityUuidIdMapsIdUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.entityUuidIdMapsId = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(entityUuidIdMapsIdServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        entityUuidIdMapsIdServiceStub.find.resolves(entityUuidIdMapsIdSample);
        entityUuidIdMapsIdServiceStub.retrieve.resolves([entityUuidIdMapsIdSample]);

        // WHEN
        route = {
          params: {
            entityUuidIdMapsIdId: `${entityUuidIdMapsIdSample.id}`,
          },
        };
        const wrapper = shallowMount(EntityUuidIdMapsIdUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.entityUuidIdMapsId).toMatchObject(entityUuidIdMapsIdSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        entityUuidIdMapsIdServiceStub.find.resolves(entityUuidIdMapsIdSample);
        const wrapper = shallowMount(EntityUuidIdMapsIdUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
