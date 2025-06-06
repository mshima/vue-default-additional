import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import EntityUuidIdDTOMapsIdUpdate from './entity-uuid-id-dto-maps-id-update.vue';
import EntityUuidIdDTOMapsIdService from './entity-uuid-id-dto-maps-id.service';
import AlertService from '@/shared/alert/alert.service';

import EntityUuidIdDTOService from '@/entities/entity-uuid-id-dto/entity-uuid-id-dto.service';
import EntityUuidIdDTORelService from '@/entities/entity-uuid-id-dto-rel/entity-uuid-id-dto-rel.service';

type EntityUuidIdDTOMapsIdUpdateComponentType = InstanceType<typeof EntityUuidIdDTOMapsIdUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const entityUuidIdDTOMapsIdSample = { id: '9fec3727-3421-4967-b213-ba36557ca194' };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<EntityUuidIdDTOMapsIdUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('EntityUuidIdDTOMapsId Management Update Component', () => {
    let comp: EntityUuidIdDTOMapsIdUpdateComponentType;
    let entityUuidIdDTOMapsIdServiceStub: SinonStubbedInstance<EntityUuidIdDTOMapsIdService>;

    beforeEach(() => {
      route = {};
      entityUuidIdDTOMapsIdServiceStub = sinon.createStubInstance<EntityUuidIdDTOMapsIdService>(EntityUuidIdDTOMapsIdService);
      entityUuidIdDTOMapsIdServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          entityUuidIdDTOMapsIdService: () => entityUuidIdDTOMapsIdServiceStub,
          entityUuidIdDTOService: () =>
            sinon.createStubInstance<EntityUuidIdDTOService>(EntityUuidIdDTOService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          entityUuidIdDTORelService: () =>
            sinon.createStubInstance<EntityUuidIdDTORelService>(EntityUuidIdDTORelService, {
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
        const wrapper = shallowMount(EntityUuidIdDTOMapsIdUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.entityUuidIdDTOMapsId = entityUuidIdDTOMapsIdSample;
        entityUuidIdDTOMapsIdServiceStub.update.resolves(entityUuidIdDTOMapsIdSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(entityUuidIdDTOMapsIdServiceStub.update.calledWith(entityUuidIdDTOMapsIdSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        entityUuidIdDTOMapsIdServiceStub.create.resolves(entity);
        const wrapper = shallowMount(EntityUuidIdDTOMapsIdUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.entityUuidIdDTOMapsId = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(entityUuidIdDTOMapsIdServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        entityUuidIdDTOMapsIdServiceStub.find.resolves(entityUuidIdDTOMapsIdSample);
        entityUuidIdDTOMapsIdServiceStub.retrieve.resolves([entityUuidIdDTOMapsIdSample]);

        // WHEN
        route = {
          params: {
            entityUuidIdDTOMapsIdId: `${entityUuidIdDTOMapsIdSample.id}`,
          },
        };
        const wrapper = shallowMount(EntityUuidIdDTOMapsIdUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.entityUuidIdDTOMapsId).toMatchObject(entityUuidIdDTOMapsIdSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        entityUuidIdDTOMapsIdServiceStub.find.resolves(entityUuidIdDTOMapsIdSample);
        const wrapper = shallowMount(EntityUuidIdDTOMapsIdUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
