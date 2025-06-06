import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import UuidIdFilteringRelationshipUpdate from './uuid-id-filtering-relationship-update.vue';
import UuidIdFilteringRelationshipService from './uuid-id-filtering-relationship.service';
import AlertService from '@/shared/alert/alert.service';

import UuidIdFilteringService from '@/entities/uuid-id-filtering/uuid-id-filtering.service';
import UuidIdFilteringMapsIdService from '@/entities/uuid-id-filtering-maps-id/uuid-id-filtering-maps-id.service';

type UuidIdFilteringRelationshipUpdateComponentType = InstanceType<typeof UuidIdFilteringRelationshipUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const uuidIdFilteringRelationshipSample = { relatedId: '9fec3727-3421-4967-b213-ba36557ca194' };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<UuidIdFilteringRelationshipUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('UuidIdFilteringRelationship Management Update Component', () => {
    let comp: UuidIdFilteringRelationshipUpdateComponentType;
    let uuidIdFilteringRelationshipServiceStub: SinonStubbedInstance<UuidIdFilteringRelationshipService>;

    beforeEach(() => {
      route = {};
      uuidIdFilteringRelationshipServiceStub =
        sinon.createStubInstance<UuidIdFilteringRelationshipService>(UuidIdFilteringRelationshipService);
      uuidIdFilteringRelationshipServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          uuidIdFilteringRelationshipService: () => uuidIdFilteringRelationshipServiceStub,
          uuidIdFilteringService: () =>
            sinon.createStubInstance<UuidIdFilteringService>(UuidIdFilteringService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          uuidIdFilteringMapsIdService: () =>
            sinon.createStubInstance<UuidIdFilteringMapsIdService>(UuidIdFilteringMapsIdService, {
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
        const wrapper = shallowMount(UuidIdFilteringRelationshipUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.uuidIdFilteringRelationship = uuidIdFilteringRelationshipSample;
        uuidIdFilteringRelationshipServiceStub.update.resolves(uuidIdFilteringRelationshipSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(uuidIdFilteringRelationshipServiceStub.update.calledWith(uuidIdFilteringRelationshipSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        uuidIdFilteringRelationshipServiceStub.create.resolves(entity);
        const wrapper = shallowMount(UuidIdFilteringRelationshipUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.uuidIdFilteringRelationship = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(uuidIdFilteringRelationshipServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        uuidIdFilteringRelationshipServiceStub.find.resolves(uuidIdFilteringRelationshipSample);
        uuidIdFilteringRelationshipServiceStub.retrieve.resolves([uuidIdFilteringRelationshipSample]);

        // WHEN
        route = {
          params: {
            uuidIdFilteringRelationshipId: `${uuidIdFilteringRelationshipSample.relatedId}`,
          },
        };
        const wrapper = shallowMount(UuidIdFilteringRelationshipUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.uuidIdFilteringRelationship).toMatchObject(uuidIdFilteringRelationshipSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        uuidIdFilteringRelationshipServiceStub.find.resolves(uuidIdFilteringRelationshipSample);
        const wrapper = shallowMount(UuidIdFilteringRelationshipUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
