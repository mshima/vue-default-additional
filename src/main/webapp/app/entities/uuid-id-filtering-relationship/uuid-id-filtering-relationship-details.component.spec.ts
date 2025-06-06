import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import UuidIdFilteringRelationshipDetails from './uuid-id-filtering-relationship-details.vue';
import UuidIdFilteringRelationshipService from './uuid-id-filtering-relationship.service';
import AlertService from '@/shared/alert/alert.service';

type UuidIdFilteringRelationshipDetailsComponentType = InstanceType<typeof UuidIdFilteringRelationshipDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const uuidIdFilteringRelationshipSample = { relatedId: '9fec3727-3421-4967-b213-ba36557ca194' };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('UuidIdFilteringRelationship Management Detail Component', () => {
    let uuidIdFilteringRelationshipServiceStub: SinonStubbedInstance<UuidIdFilteringRelationshipService>;
    let mountOptions: MountingOptions<UuidIdFilteringRelationshipDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      uuidIdFilteringRelationshipServiceStub =
        sinon.createStubInstance<UuidIdFilteringRelationshipService>(UuidIdFilteringRelationshipService);

      alertService = new AlertService({
        i18n: { t: vitest.fn() } as any,
        toastController: {
          show: vitest.fn(),
        } as any,
      });

      mountOptions = {
        stubs: {
          'font-awesome-icon': true,
          'router-link': true,
        },
        provide: {
          alertService,
          uuidIdFilteringRelationshipService: () => uuidIdFilteringRelationshipServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        uuidIdFilteringRelationshipServiceStub.find.resolves(uuidIdFilteringRelationshipSample);
        route = {
          params: {
            uuidIdFilteringRelationshipId: '' + '9fec3727-3421-4967-b213-ba36557ca194',
          },
        };
        const wrapper = shallowMount(UuidIdFilteringRelationshipDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.uuidIdFilteringRelationship).toMatchObject(uuidIdFilteringRelationshipSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        uuidIdFilteringRelationshipServiceStub.find.resolves(uuidIdFilteringRelationshipSample);
        const wrapper = shallowMount(UuidIdFilteringRelationshipDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
