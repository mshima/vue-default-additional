import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import UuidIdFilteringMapsIdUpdate from './uuid-id-filtering-maps-id-update.vue';
import UuidIdFilteringMapsIdService from './uuid-id-filtering-maps-id.service';
import AlertService from '@/shared/alert/alert.service';

import UuidIdFilteringService from '@/entities/uuid-id-filtering/uuid-id-filtering.service';
import UuidIdFilteringRelationshipService from '@/entities/uuid-id-filtering-relationship/uuid-id-filtering-relationship.service';

type UuidIdFilteringMapsIdUpdateComponentType = InstanceType<typeof UuidIdFilteringMapsIdUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const uuidIdFilteringMapsIdSample = { customId: '9fec3727-3421-4967-b213-ba36557ca194' };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<UuidIdFilteringMapsIdUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('UuidIdFilteringMapsId Management Update Component', () => {
    let comp: UuidIdFilteringMapsIdUpdateComponentType;
    let uuidIdFilteringMapsIdServiceStub: SinonStubbedInstance<UuidIdFilteringMapsIdService>;

    beforeEach(() => {
      route = {};
      uuidIdFilteringMapsIdServiceStub = sinon.createStubInstance<UuidIdFilteringMapsIdService>(UuidIdFilteringMapsIdService);
      uuidIdFilteringMapsIdServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          uuidIdFilteringMapsIdService: () => uuidIdFilteringMapsIdServiceStub,
          uuidIdFilteringService: () =>
            sinon.createStubInstance<UuidIdFilteringService>(UuidIdFilteringService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
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
        const wrapper = shallowMount(UuidIdFilteringMapsIdUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.uuidIdFilteringMapsId = uuidIdFilteringMapsIdSample;
        uuidIdFilteringMapsIdServiceStub.update.resolves(uuidIdFilteringMapsIdSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(uuidIdFilteringMapsIdServiceStub.update.calledWith(uuidIdFilteringMapsIdSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        uuidIdFilteringMapsIdServiceStub.create.resolves(entity);
        const wrapper = shallowMount(UuidIdFilteringMapsIdUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.uuidIdFilteringMapsId = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(uuidIdFilteringMapsIdServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        uuidIdFilteringMapsIdServiceStub.find.resolves(uuidIdFilteringMapsIdSample);
        uuidIdFilteringMapsIdServiceStub.retrieve.resolves([uuidIdFilteringMapsIdSample]);

        // WHEN
        route = {
          params: {
            uuidIdFilteringMapsIdId: `${uuidIdFilteringMapsIdSample.customId}`,
          },
        };
        const wrapper = shallowMount(UuidIdFilteringMapsIdUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.uuidIdFilteringMapsId).toMatchObject(uuidIdFilteringMapsIdSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        uuidIdFilteringMapsIdServiceStub.find.resolves(uuidIdFilteringMapsIdSample);
        const wrapper = shallowMount(UuidIdFilteringMapsIdUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
