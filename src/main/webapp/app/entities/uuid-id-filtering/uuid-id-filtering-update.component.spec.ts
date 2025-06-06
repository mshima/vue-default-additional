import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import UuidIdFilteringUpdate from './uuid-id-filtering-update.vue';
import UuidIdFilteringService from './uuid-id-filtering.service';
import AlertService from '@/shared/alert/alert.service';

import UuidIdFilteringRelationshipService from '@/entities/uuid-id-filtering-relationship/uuid-id-filtering-relationship.service';

type UuidIdFilteringUpdateComponentType = InstanceType<typeof UuidIdFilteringUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const uuidIdFilteringSample = { customId: '9fec3727-3421-4967-b213-ba36557ca194' };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<UuidIdFilteringUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('UuidIdFiltering Management Update Component', () => {
    let comp: UuidIdFilteringUpdateComponentType;
    let uuidIdFilteringServiceStub: SinonStubbedInstance<UuidIdFilteringService>;

    beforeEach(() => {
      route = {};
      uuidIdFilteringServiceStub = sinon.createStubInstance<UuidIdFilteringService>(UuidIdFilteringService);
      uuidIdFilteringServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          uuidIdFilteringService: () => uuidIdFilteringServiceStub,
          uuidIdFilteringRelationshipService: () =>
            sinon.createStubInstance<UuidIdFilteringRelationshipService>(UuidIdFilteringRelationshipService, {
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
        const wrapper = shallowMount(UuidIdFilteringUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.uuidIdFiltering = uuidIdFilteringSample;
        uuidIdFilteringServiceStub.update.resolves(uuidIdFilteringSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(uuidIdFilteringServiceStub.update.calledWith(uuidIdFilteringSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        uuidIdFilteringServiceStub.create.resolves(entity);
        const wrapper = shallowMount(UuidIdFilteringUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.uuidIdFiltering = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(uuidIdFilteringServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        uuidIdFilteringServiceStub.find.resolves(uuidIdFilteringSample);
        uuidIdFilteringServiceStub.retrieve.resolves([uuidIdFilteringSample]);

        // WHEN
        route = {
          params: {
            uuidIdFilteringId: `${uuidIdFilteringSample.customId}`,
          },
        };
        const wrapper = shallowMount(UuidIdFilteringUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.uuidIdFiltering).toMatchObject(uuidIdFilteringSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        uuidIdFilteringServiceStub.find.resolves(uuidIdFilteringSample);
        const wrapper = shallowMount(UuidIdFilteringUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
