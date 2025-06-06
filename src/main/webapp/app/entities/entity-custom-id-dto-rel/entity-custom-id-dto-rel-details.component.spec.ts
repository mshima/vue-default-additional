import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import EntityCustomIdDTORelDetails from './entity-custom-id-dto-rel-details.vue';
import EntityCustomIdDTORelService from './entity-custom-id-dto-rel.service';
import AlertService from '@/shared/alert/alert.service';

type EntityCustomIdDTORelDetailsComponentType = InstanceType<typeof EntityCustomIdDTORelDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const entityCustomIdDTORelSample = { relatedId: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('EntityCustomIdDTORel Management Detail Component', () => {
    let entityCustomIdDTORelServiceStub: SinonStubbedInstance<EntityCustomIdDTORelService>;
    let mountOptions: MountingOptions<EntityCustomIdDTORelDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      entityCustomIdDTORelServiceStub = sinon.createStubInstance<EntityCustomIdDTORelService>(EntityCustomIdDTORelService);

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
          entityCustomIdDTORelService: () => entityCustomIdDTORelServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        entityCustomIdDTORelServiceStub.find.resolves(entityCustomIdDTORelSample);
        route = {
          params: {
            entityCustomIdDTORelId: `${123}`,
          },
        };
        const wrapper = shallowMount(EntityCustomIdDTORelDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.entityCustomIdDTORel).toMatchObject(entityCustomIdDTORelSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        entityCustomIdDTORelServiceStub.find.resolves(entityCustomIdDTORelSample);
        const wrapper = shallowMount(EntityCustomIdDTORelDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
