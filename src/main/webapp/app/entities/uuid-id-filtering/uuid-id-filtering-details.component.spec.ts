import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import UuidIdFilteringDetails from './uuid-id-filtering-details.vue';
import UuidIdFilteringService from './uuid-id-filtering.service';
import AlertService from '@/shared/alert/alert.service';

type UuidIdFilteringDetailsComponentType = InstanceType<typeof UuidIdFilteringDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const uuidIdFilteringSample = { customId: '9fec3727-3421-4967-b213-ba36557ca194' };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('UuidIdFiltering Management Detail Component', () => {
    let uuidIdFilteringServiceStub: SinonStubbedInstance<UuidIdFilteringService>;
    let mountOptions: MountingOptions<UuidIdFilteringDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      uuidIdFilteringServiceStub = sinon.createStubInstance<UuidIdFilteringService>(UuidIdFilteringService);

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
          uuidIdFilteringService: () => uuidIdFilteringServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        uuidIdFilteringServiceStub.find.resolves(uuidIdFilteringSample);
        route = {
          params: {
            uuidIdFilteringId: '' + '9fec3727-3421-4967-b213-ba36557ca194',
          },
        };
        const wrapper = shallowMount(UuidIdFilteringDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.uuidIdFiltering).toMatchObject(uuidIdFilteringSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        uuidIdFilteringServiceStub.find.resolves(uuidIdFilteringSample);
        const wrapper = shallowMount(UuidIdFilteringDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
