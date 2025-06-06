import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import EntityCustomIdRequiredDTODetails from './entity-custom-id-required-dto-details.vue';
import EntityCustomIdRequiredDTOService from './entity-custom-id-required-dto.service';
import AlertService from '@/shared/alert/alert.service';

type EntityCustomIdRequiredDTODetailsComponentType = InstanceType<typeof EntityCustomIdRequiredDTODetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const entityCustomIdRequiredDTOSample = { customId: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('EntityCustomIdRequiredDTO Management Detail Component', () => {
    let entityCustomIdRequiredDTOServiceStub: SinonStubbedInstance<EntityCustomIdRequiredDTOService>;
    let mountOptions: MountingOptions<EntityCustomIdRequiredDTODetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      entityCustomIdRequiredDTOServiceStub = sinon.createStubInstance<EntityCustomIdRequiredDTOService>(EntityCustomIdRequiredDTOService);

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
          entityCustomIdRequiredDTOService: () => entityCustomIdRequiredDTOServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        entityCustomIdRequiredDTOServiceStub.find.resolves(entityCustomIdRequiredDTOSample);
        route = {
          params: {
            entityCustomIdRequiredDTOId: `${123}`,
          },
        };
        const wrapper = shallowMount(EntityCustomIdRequiredDTODetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.entityCustomIdRequiredDTO).toMatchObject(entityCustomIdRequiredDTOSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        entityCustomIdRequiredDTOServiceStub.find.resolves(entityCustomIdRequiredDTOSample);
        const wrapper = shallowMount(EntityCustomIdRequiredDTODetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
