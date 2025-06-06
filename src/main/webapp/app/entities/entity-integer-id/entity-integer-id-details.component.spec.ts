import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import EntityIntegerIdDetails from './entity-integer-id-details.vue';
import EntityIntegerIdService from './entity-integer-id.service';
import AlertService from '@/shared/alert/alert.service';

type EntityIntegerIdDetailsComponentType = InstanceType<typeof EntityIntegerIdDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const entityIntegerIdSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('EntityIntegerId Management Detail Component', () => {
    let entityIntegerIdServiceStub: SinonStubbedInstance<EntityIntegerIdService>;
    let mountOptions: MountingOptions<EntityIntegerIdDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      entityIntegerIdServiceStub = sinon.createStubInstance<EntityIntegerIdService>(EntityIntegerIdService);

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
          entityIntegerIdService: () => entityIntegerIdServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        entityIntegerIdServiceStub.find.resolves(entityIntegerIdSample);
        route = {
          params: {
            entityIntegerIdId: `${123}`,
          },
        };
        const wrapper = shallowMount(EntityIntegerIdDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.entityIntegerId).toMatchObject(entityIntegerIdSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        entityIntegerIdServiceStub.find.resolves(entityIntegerIdSample);
        const wrapper = shallowMount(EntityIntegerIdDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
