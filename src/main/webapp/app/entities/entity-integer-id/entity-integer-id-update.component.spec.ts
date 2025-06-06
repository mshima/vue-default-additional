import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import EntityIntegerIdUpdate from './entity-integer-id-update.vue';
import EntityIntegerIdService from './entity-integer-id.service';
import AlertService from '@/shared/alert/alert.service';

type EntityIntegerIdUpdateComponentType = InstanceType<typeof EntityIntegerIdUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const entityIntegerIdSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<EntityIntegerIdUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('EntityIntegerId Management Update Component', () => {
    let comp: EntityIntegerIdUpdateComponentType;
    let entityIntegerIdServiceStub: SinonStubbedInstance<EntityIntegerIdService>;

    beforeEach(() => {
      route = {};
      entityIntegerIdServiceStub = sinon.createStubInstance<EntityIntegerIdService>(EntityIntegerIdService);
      entityIntegerIdServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          entityIntegerIdService: () => entityIntegerIdServiceStub,
        },
      };
    });

    afterEach(() => {
      vitest.resetAllMocks();
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const wrapper = shallowMount(EntityIntegerIdUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.entityIntegerId = entityIntegerIdSample;
        entityIntegerIdServiceStub.update.resolves(entityIntegerIdSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(entityIntegerIdServiceStub.update.calledWith(entityIntegerIdSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        entityIntegerIdServiceStub.create.resolves(entity);
        const wrapper = shallowMount(EntityIntegerIdUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.entityIntegerId = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(entityIntegerIdServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        entityIntegerIdServiceStub.find.resolves(entityIntegerIdSample);
        entityIntegerIdServiceStub.retrieve.resolves([entityIntegerIdSample]);

        // WHEN
        route = {
          params: {
            entityIntegerIdId: `${entityIntegerIdSample.id}`,
          },
        };
        const wrapper = shallowMount(EntityIntegerIdUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.entityIntegerId).toMatchObject(entityIntegerIdSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        entityIntegerIdServiceStub.find.resolves(entityIntegerIdSample);
        const wrapper = shallowMount(EntityIntegerIdUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
