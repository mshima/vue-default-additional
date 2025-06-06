import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import EntityCustomIdMapsIdDetails from './entity-custom-id-maps-id-details.vue';
import EntityCustomIdMapsIdService from './entity-custom-id-maps-id.service';
import AlertService from '@/shared/alert/alert.service';

type EntityCustomIdMapsIdDetailsComponentType = InstanceType<typeof EntityCustomIdMapsIdDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const entityCustomIdMapsIdSample = { customId: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('EntityCustomIdMapsId Management Detail Component', () => {
    let entityCustomIdMapsIdServiceStub: SinonStubbedInstance<EntityCustomIdMapsIdService>;
    let mountOptions: MountingOptions<EntityCustomIdMapsIdDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      entityCustomIdMapsIdServiceStub = sinon.createStubInstance<EntityCustomIdMapsIdService>(EntityCustomIdMapsIdService);

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
          entityCustomIdMapsIdService: () => entityCustomIdMapsIdServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        entityCustomIdMapsIdServiceStub.find.resolves(entityCustomIdMapsIdSample);
        route = {
          params: {
            entityCustomIdMapsIdId: `${123}`,
          },
        };
        const wrapper = shallowMount(EntityCustomIdMapsIdDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.entityCustomIdMapsId).toMatchObject(entityCustomIdMapsIdSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        entityCustomIdMapsIdServiceStub.find.resolves(entityCustomIdMapsIdSample);
        const wrapper = shallowMount(EntityCustomIdMapsIdDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
