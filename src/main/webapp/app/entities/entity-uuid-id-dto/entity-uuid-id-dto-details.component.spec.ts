import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import EntityUuidIdDTODetails from './entity-uuid-id-dto-details.vue';
import EntityUuidIdDTOService from './entity-uuid-id-dto.service';
import AlertService from '@/shared/alert/alert.service';

type EntityUuidIdDTODetailsComponentType = InstanceType<typeof EntityUuidIdDTODetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const entityUuidIdDTOSample = { id: '9fec3727-3421-4967-b213-ba36557ca194' };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('EntityUuidIdDTO Management Detail Component', () => {
    let entityUuidIdDTOServiceStub: SinonStubbedInstance<EntityUuidIdDTOService>;
    let mountOptions: MountingOptions<EntityUuidIdDTODetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      entityUuidIdDTOServiceStub = sinon.createStubInstance<EntityUuidIdDTOService>(EntityUuidIdDTOService);

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
          entityUuidIdDTOService: () => entityUuidIdDTOServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        entityUuidIdDTOServiceStub.find.resolves(entityUuidIdDTOSample);
        route = {
          params: {
            entityUuidIdDTOId: '' + '9fec3727-3421-4967-b213-ba36557ca194',
          },
        };
        const wrapper = shallowMount(EntityUuidIdDTODetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.entityUuidIdDTO).toMatchObject(entityUuidIdDTOSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        entityUuidIdDTOServiceStub.find.resolves(entityUuidIdDTOSample);
        const wrapper = shallowMount(EntityUuidIdDTODetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
