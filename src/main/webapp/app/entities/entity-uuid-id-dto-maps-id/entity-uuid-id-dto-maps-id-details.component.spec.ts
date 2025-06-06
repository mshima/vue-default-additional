import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import EntityUuidIdDTOMapsIdDetails from './entity-uuid-id-dto-maps-id-details.vue';
import EntityUuidIdDTOMapsIdService from './entity-uuid-id-dto-maps-id.service';
import AlertService from '@/shared/alert/alert.service';

type EntityUuidIdDTOMapsIdDetailsComponentType = InstanceType<typeof EntityUuidIdDTOMapsIdDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const entityUuidIdDTOMapsIdSample = { id: '9fec3727-3421-4967-b213-ba36557ca194' };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('EntityUuidIdDTOMapsId Management Detail Component', () => {
    let entityUuidIdDTOMapsIdServiceStub: SinonStubbedInstance<EntityUuidIdDTOMapsIdService>;
    let mountOptions: MountingOptions<EntityUuidIdDTOMapsIdDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      entityUuidIdDTOMapsIdServiceStub = sinon.createStubInstance<EntityUuidIdDTOMapsIdService>(EntityUuidIdDTOMapsIdService);

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
          entityUuidIdDTOMapsIdService: () => entityUuidIdDTOMapsIdServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        entityUuidIdDTOMapsIdServiceStub.find.resolves(entityUuidIdDTOMapsIdSample);
        route = {
          params: {
            entityUuidIdDTOMapsIdId: '' + '9fec3727-3421-4967-b213-ba36557ca194',
          },
        };
        const wrapper = shallowMount(EntityUuidIdDTOMapsIdDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.entityUuidIdDTOMapsId).toMatchObject(entityUuidIdDTOMapsIdSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        entityUuidIdDTOMapsIdServiceStub.find.resolves(entityUuidIdDTOMapsIdSample);
        const wrapper = shallowMount(EntityUuidIdDTOMapsIdDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
