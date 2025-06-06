import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import EntityUuidIdDetails from './entity-uuid-id-details.vue';
import EntityUuidIdService from './entity-uuid-id.service';
import AlertService from '@/shared/alert/alert.service';

type EntityUuidIdDetailsComponentType = InstanceType<typeof EntityUuidIdDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const entityUuidIdSample = { id: '9fec3727-3421-4967-b213-ba36557ca194' };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('EntityUuidId Management Detail Component', () => {
    let entityUuidIdServiceStub: SinonStubbedInstance<EntityUuidIdService>;
    let mountOptions: MountingOptions<EntityUuidIdDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      entityUuidIdServiceStub = sinon.createStubInstance<EntityUuidIdService>(EntityUuidIdService);

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
          entityUuidIdService: () => entityUuidIdServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        entityUuidIdServiceStub.find.resolves(entityUuidIdSample);
        route = {
          params: {
            entityUuidIdId: '' + '9fec3727-3421-4967-b213-ba36557ca194',
          },
        };
        const wrapper = shallowMount(EntityUuidIdDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.entityUuidId).toMatchObject(entityUuidIdSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        entityUuidIdServiceStub.find.resolves(entityUuidIdSample);
        const wrapper = shallowMount(EntityUuidIdDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
