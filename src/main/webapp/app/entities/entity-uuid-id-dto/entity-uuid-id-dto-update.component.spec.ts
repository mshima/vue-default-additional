import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import EntityUuidIdDTOUpdate from './entity-uuid-id-dto-update.vue';
import EntityUuidIdDTOService from './entity-uuid-id-dto.service';
import AlertService from '@/shared/alert/alert.service';

import EntityUuidIdDTORelService from '@/entities/entity-uuid-id-dto-rel/entity-uuid-id-dto-rel.service';

type EntityUuidIdDTOUpdateComponentType = InstanceType<typeof EntityUuidIdDTOUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const entityUuidIdDTOSample = { id: '9fec3727-3421-4967-b213-ba36557ca194' };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<EntityUuidIdDTOUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('EntityUuidIdDTO Management Update Component', () => {
    let comp: EntityUuidIdDTOUpdateComponentType;
    let entityUuidIdDTOServiceStub: SinonStubbedInstance<EntityUuidIdDTOService>;

    beforeEach(() => {
      route = {};
      entityUuidIdDTOServiceStub = sinon.createStubInstance<EntityUuidIdDTOService>(EntityUuidIdDTOService);
      entityUuidIdDTOServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          entityUuidIdDTOService: () => entityUuidIdDTOServiceStub,
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
        const wrapper = shallowMount(EntityUuidIdDTOUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.entityUuidIdDTO = entityUuidIdDTOSample;
        entityUuidIdDTOServiceStub.update.resolves(entityUuidIdDTOSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(entityUuidIdDTOServiceStub.update.calledWith(entityUuidIdDTOSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        entityUuidIdDTOServiceStub.create.resolves(entity);
        const wrapper = shallowMount(EntityUuidIdDTOUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.entityUuidIdDTO = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(entityUuidIdDTOServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        entityUuidIdDTOServiceStub.find.resolves(entityUuidIdDTOSample);
        entityUuidIdDTOServiceStub.retrieve.resolves([entityUuidIdDTOSample]);

        // WHEN
        route = {
          params: {
            entityUuidIdDTOId: `${entityUuidIdDTOSample.id}`,
          },
        };
        const wrapper = shallowMount(EntityUuidIdDTOUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.entityUuidIdDTO).toMatchObject(entityUuidIdDTOSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        entityUuidIdDTOServiceStub.find.resolves(entityUuidIdDTOSample);
        const wrapper = shallowMount(EntityUuidIdDTOUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
