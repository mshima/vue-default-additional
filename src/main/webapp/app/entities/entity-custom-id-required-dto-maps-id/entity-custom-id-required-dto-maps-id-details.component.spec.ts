import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import EntityCustomIdRequiredDTOMapsIdDetails from './entity-custom-id-required-dto-maps-id-details.vue';
import EntityCustomIdRequiredDTOMapsIdService from './entity-custom-id-required-dto-maps-id.service';
import AlertService from '@/shared/alert/alert.service';

type EntityCustomIdRequiredDTOMapsIdDetailsComponentType = InstanceType<typeof EntityCustomIdRequiredDTOMapsIdDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const entityCustomIdRequiredDTOMapsIdSample = { customId: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('EntityCustomIdRequiredDTOMapsId Management Detail Component', () => {
    let entityCustomIdRequiredDTOMapsIdServiceStub: SinonStubbedInstance<EntityCustomIdRequiredDTOMapsIdService>;
    let mountOptions: MountingOptions<EntityCustomIdRequiredDTOMapsIdDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      entityCustomIdRequiredDTOMapsIdServiceStub = sinon.createStubInstance<EntityCustomIdRequiredDTOMapsIdService>(
        EntityCustomIdRequiredDTOMapsIdService,
      );

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
          entityCustomIdRequiredDTOMapsIdService: () => entityCustomIdRequiredDTOMapsIdServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        entityCustomIdRequiredDTOMapsIdServiceStub.find.resolves(entityCustomIdRequiredDTOMapsIdSample);
        route = {
          params: {
            entityCustomIdRequiredDTOMapsIdId: `${123}`,
          },
        };
        const wrapper = shallowMount(EntityCustomIdRequiredDTOMapsIdDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.entityCustomIdRequiredDTOMapsId).toMatchObject(entityCustomIdRequiredDTOMapsIdSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        entityCustomIdRequiredDTOMapsIdServiceStub.find.resolves(entityCustomIdRequiredDTOMapsIdSample);
        const wrapper = shallowMount(EntityCustomIdRequiredDTOMapsIdDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
