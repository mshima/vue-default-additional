import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import EntityUuidIdMapsIdDetails from './entity-uuid-id-maps-id-details.vue';
import EntityUuidIdMapsIdService from './entity-uuid-id-maps-id.service';
import AlertService from '@/shared/alert/alert.service';

type EntityUuidIdMapsIdDetailsComponentType = InstanceType<typeof EntityUuidIdMapsIdDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const entityUuidIdMapsIdSample = { id: '9fec3727-3421-4967-b213-ba36557ca194' };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('EntityUuidIdMapsId Management Detail Component', () => {
    let entityUuidIdMapsIdServiceStub: SinonStubbedInstance<EntityUuidIdMapsIdService>;
    let mountOptions: MountingOptions<EntityUuidIdMapsIdDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      entityUuidIdMapsIdServiceStub = sinon.createStubInstance<EntityUuidIdMapsIdService>(EntityUuidIdMapsIdService);

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
          entityUuidIdMapsIdService: () => entityUuidIdMapsIdServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        entityUuidIdMapsIdServiceStub.find.resolves(entityUuidIdMapsIdSample);
        route = {
          params: {
            entityUuidIdMapsIdId: '' + '9fec3727-3421-4967-b213-ba36557ca194',
          },
        };
        const wrapper = shallowMount(EntityUuidIdMapsIdDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.entityUuidIdMapsId).toMatchObject(entityUuidIdMapsIdSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        entityUuidIdMapsIdServiceStub.find.resolves(entityUuidIdMapsIdSample);
        const wrapper = shallowMount(EntityUuidIdMapsIdDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
