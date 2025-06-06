import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import EntityCustomIdDTOMapsIdDetails from './entity-custom-id-dto-maps-id-details.vue';
import EntityCustomIdDTOMapsIdService from './entity-custom-id-dto-maps-id.service';
import AlertService from '@/shared/alert/alert.service';

type EntityCustomIdDTOMapsIdDetailsComponentType = InstanceType<typeof EntityCustomIdDTOMapsIdDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const entityCustomIdDTOMapsIdSample = { customId: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('EntityCustomIdDTOMapsId Management Detail Component', () => {
    let entityCustomIdDTOMapsIdServiceStub: SinonStubbedInstance<EntityCustomIdDTOMapsIdService>;
    let mountOptions: MountingOptions<EntityCustomIdDTOMapsIdDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      entityCustomIdDTOMapsIdServiceStub = sinon.createStubInstance<EntityCustomIdDTOMapsIdService>(EntityCustomIdDTOMapsIdService);

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
          entityCustomIdDTOMapsIdService: () => entityCustomIdDTOMapsIdServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        entityCustomIdDTOMapsIdServiceStub.find.resolves(entityCustomIdDTOMapsIdSample);
        route = {
          params: {
            entityCustomIdDTOMapsIdId: `${123}`,
          },
        };
        const wrapper = shallowMount(EntityCustomIdDTOMapsIdDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.entityCustomIdDTOMapsId).toMatchObject(entityCustomIdDTOMapsIdSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        entityCustomIdDTOMapsIdServiceStub.find.resolves(entityCustomIdDTOMapsIdSample);
        const wrapper = shallowMount(EntityCustomIdDTOMapsIdDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
