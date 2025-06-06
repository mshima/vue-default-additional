import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import EntityUuidIdDTORelUpdate from './entity-uuid-id-dto-rel-update.vue';
import EntityUuidIdDTORelService from './entity-uuid-id-dto-rel.service';
import AlertService from '@/shared/alert/alert.service';

import EntityUuidIdDTOService from '@/entities/entity-uuid-id-dto/entity-uuid-id-dto.service';
import EntityUuidIdDTOMapsIdService from '@/entities/entity-uuid-id-dto-maps-id/entity-uuid-id-dto-maps-id.service';

type EntityUuidIdDTORelUpdateComponentType = InstanceType<typeof EntityUuidIdDTORelUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const entityUuidIdDTORelSample = { id: '9fec3727-3421-4967-b213-ba36557ca194' };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<EntityUuidIdDTORelUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('EntityUuidIdDTORel Management Update Component', () => {
    let comp: EntityUuidIdDTORelUpdateComponentType;
    let entityUuidIdDTORelServiceStub: SinonStubbedInstance<EntityUuidIdDTORelService>;

    beforeEach(() => {
      route = {};
      entityUuidIdDTORelServiceStub = sinon.createStubInstance<EntityUuidIdDTORelService>(EntityUuidIdDTORelService);
      entityUuidIdDTORelServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          entityUuidIdDTORelService: () => entityUuidIdDTORelServiceStub,
          entityUuidIdDTOService: () =>
            sinon.createStubInstance<EntityUuidIdDTOService>(EntityUuidIdDTOService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          entityUuidIdDTOMapsIdService: () =>
            sinon.createStubInstance<EntityUuidIdDTOMapsIdService>(EntityUuidIdDTOMapsIdService, {
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
        const wrapper = shallowMount(EntityUuidIdDTORelUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.entityUuidIdDTORel = entityUuidIdDTORelSample;
        entityUuidIdDTORelServiceStub.update.resolves(entityUuidIdDTORelSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(entityUuidIdDTORelServiceStub.update.calledWith(entityUuidIdDTORelSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        entityUuidIdDTORelServiceStub.create.resolves(entity);
        const wrapper = shallowMount(EntityUuidIdDTORelUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.entityUuidIdDTORel = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(entityUuidIdDTORelServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        entityUuidIdDTORelServiceStub.find.resolves(entityUuidIdDTORelSample);
        entityUuidIdDTORelServiceStub.retrieve.resolves([entityUuidIdDTORelSample]);

        // WHEN
        route = {
          params: {
            entityUuidIdDTORelId: `${entityUuidIdDTORelSample.id}`,
          },
        };
        const wrapper = shallowMount(EntityUuidIdDTORelUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.entityUuidIdDTORel).toMatchObject(entityUuidIdDTORelSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        entityUuidIdDTORelServiceStub.find.resolves(entityUuidIdDTORelSample);
        const wrapper = shallowMount(EntityUuidIdDTORelUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
