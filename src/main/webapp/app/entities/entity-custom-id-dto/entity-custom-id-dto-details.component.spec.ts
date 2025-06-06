import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import EntityCustomIdDTODetails from './entity-custom-id-dto-details.vue';
import EntityCustomIdDTOService from './entity-custom-id-dto.service';
import AlertService from '@/shared/alert/alert.service';

type EntityCustomIdDTODetailsComponentType = InstanceType<typeof EntityCustomIdDTODetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const entityCustomIdDTOSample = { customId: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('EntityCustomIdDTO Management Detail Component', () => {
    let entityCustomIdDTOServiceStub: SinonStubbedInstance<EntityCustomIdDTOService>;
    let mountOptions: MountingOptions<EntityCustomIdDTODetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      entityCustomIdDTOServiceStub = sinon.createStubInstance<EntityCustomIdDTOService>(EntityCustomIdDTOService);

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
          entityCustomIdDTOService: () => entityCustomIdDTOServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        entityCustomIdDTOServiceStub.find.resolves(entityCustomIdDTOSample);
        route = {
          params: {
            entityCustomIdDTOId: `${123}`,
          },
        };
        const wrapper = shallowMount(EntityCustomIdDTODetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.entityCustomIdDTO).toMatchObject(entityCustomIdDTOSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        entityCustomIdDTOServiceStub.find.resolves(entityCustomIdDTOSample);
        const wrapper = shallowMount(EntityCustomIdDTODetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
