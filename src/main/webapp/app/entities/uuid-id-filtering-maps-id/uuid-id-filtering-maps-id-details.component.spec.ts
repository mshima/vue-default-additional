import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import UuidIdFilteringMapsIdDetails from './uuid-id-filtering-maps-id-details.vue';
import UuidIdFilteringMapsIdService from './uuid-id-filtering-maps-id.service';
import AlertService from '@/shared/alert/alert.service';

type UuidIdFilteringMapsIdDetailsComponentType = InstanceType<typeof UuidIdFilteringMapsIdDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const uuidIdFilteringMapsIdSample = { customId: '9fec3727-3421-4967-b213-ba36557ca194' };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('UuidIdFilteringMapsId Management Detail Component', () => {
    let uuidIdFilteringMapsIdServiceStub: SinonStubbedInstance<UuidIdFilteringMapsIdService>;
    let mountOptions: MountingOptions<UuidIdFilteringMapsIdDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      uuidIdFilteringMapsIdServiceStub = sinon.createStubInstance<UuidIdFilteringMapsIdService>(UuidIdFilteringMapsIdService);

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
          uuidIdFilteringMapsIdService: () => uuidIdFilteringMapsIdServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        uuidIdFilteringMapsIdServiceStub.find.resolves(uuidIdFilteringMapsIdSample);
        route = {
          params: {
            uuidIdFilteringMapsIdId: '' + '9fec3727-3421-4967-b213-ba36557ca194',
          },
        };
        const wrapper = shallowMount(UuidIdFilteringMapsIdDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.uuidIdFilteringMapsId).toMatchObject(uuidIdFilteringMapsIdSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        uuidIdFilteringMapsIdServiceStub.find.resolves(uuidIdFilteringMapsIdSample);
        const wrapper = shallowMount(UuidIdFilteringMapsIdDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
