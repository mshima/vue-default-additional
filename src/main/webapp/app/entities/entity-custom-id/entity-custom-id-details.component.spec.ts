import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import EntityCustomIdDetails from './entity-custom-id-details.vue';
import EntityCustomIdService from './entity-custom-id.service';
import AlertService from '@/shared/alert/alert.service';

type EntityCustomIdDetailsComponentType = InstanceType<typeof EntityCustomIdDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const entityCustomIdSample = { customId: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('EntityCustomId Management Detail Component', () => {
    let entityCustomIdServiceStub: SinonStubbedInstance<EntityCustomIdService>;
    let mountOptions: MountingOptions<EntityCustomIdDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      entityCustomIdServiceStub = sinon.createStubInstance<EntityCustomIdService>(EntityCustomIdService);

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
          entityCustomIdService: () => entityCustomIdServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        entityCustomIdServiceStub.find.resolves(entityCustomIdSample);
        route = {
          params: {
            entityCustomIdId: `${123}`,
          },
        };
        const wrapper = shallowMount(EntityCustomIdDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.entityCustomId).toMatchObject(entityCustomIdSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        entityCustomIdServiceStub.find.resolves(entityCustomIdSample);
        const wrapper = shallowMount(EntityCustomIdDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
