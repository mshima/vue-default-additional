import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import EntityUuidIdUpdate from './entity-uuid-id-update.vue';
import EntityUuidIdService from './entity-uuid-id.service';
import AlertService from '@/shared/alert/alert.service';

import EntityUuidIdRelationshipService from '@/entities/entity-uuid-id-relationship/entity-uuid-id-relationship.service';

type EntityUuidIdUpdateComponentType = InstanceType<typeof EntityUuidIdUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const entityUuidIdSample = { id: '9fec3727-3421-4967-b213-ba36557ca194' };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<EntityUuidIdUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('EntityUuidId Management Update Component', () => {
    let comp: EntityUuidIdUpdateComponentType;
    let entityUuidIdServiceStub: SinonStubbedInstance<EntityUuidIdService>;

    beforeEach(() => {
      route = {};
      entityUuidIdServiceStub = sinon.createStubInstance<EntityUuidIdService>(EntityUuidIdService);
      entityUuidIdServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          entityUuidIdService: () => entityUuidIdServiceStub,
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
        const wrapper = shallowMount(EntityUuidIdUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.entityUuidId = entityUuidIdSample;
        entityUuidIdServiceStub.update.resolves(entityUuidIdSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(entityUuidIdServiceStub.update.calledWith(entityUuidIdSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        entityUuidIdServiceStub.create.resolves(entity);
        const wrapper = shallowMount(EntityUuidIdUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.entityUuidId = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(entityUuidIdServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        entityUuidIdServiceStub.find.resolves(entityUuidIdSample);
        entityUuidIdServiceStub.retrieve.resolves([entityUuidIdSample]);

        // WHEN
        route = {
          params: {
            entityUuidIdId: `${entityUuidIdSample.id}`,
          },
        };
        const wrapper = shallowMount(EntityUuidIdUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.entityUuidId).toMatchObject(entityUuidIdSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        entityUuidIdServiceStub.find.resolves(entityUuidIdSample);
        const wrapper = shallowMount(EntityUuidIdUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
