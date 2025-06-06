import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import EntityUuidIdDTORelDetails from './entity-uuid-id-dto-rel-details.vue';
import EntityUuidIdDTORelService from './entity-uuid-id-dto-rel.service';
import AlertService from '@/shared/alert/alert.service';

type EntityUuidIdDTORelDetailsComponentType = InstanceType<typeof EntityUuidIdDTORelDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const entityUuidIdDTORelSample = { id: '9fec3727-3421-4967-b213-ba36557ca194' };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('EntityUuidIdDTORel Management Detail Component', () => {
    let entityUuidIdDTORelServiceStub: SinonStubbedInstance<EntityUuidIdDTORelService>;
    let mountOptions: MountingOptions<EntityUuidIdDTORelDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      entityUuidIdDTORelServiceStub = sinon.createStubInstance<EntityUuidIdDTORelService>(EntityUuidIdDTORelService);

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
          entityUuidIdDTORelService: () => entityUuidIdDTORelServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        entityUuidIdDTORelServiceStub.find.resolves(entityUuidIdDTORelSample);
        route = {
          params: {
            entityUuidIdDTORelId: '' + '9fec3727-3421-4967-b213-ba36557ca194',
          },
        };
        const wrapper = shallowMount(EntityUuidIdDTORelDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.entityUuidIdDTORel).toMatchObject(entityUuidIdDTORelSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        entityUuidIdDTORelServiceStub.find.resolves(entityUuidIdDTORelSample);
        const wrapper = shallowMount(EntityUuidIdDTORelDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
