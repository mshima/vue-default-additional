import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import EntityCustomSequenceDetails from './entity-custom-sequence-details.vue';
import EntityCustomSequenceService from './entity-custom-sequence.service';
import AlertService from '@/shared/alert/alert.service';

type EntityCustomSequenceDetailsComponentType = InstanceType<typeof EntityCustomSequenceDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const entityCustomSequenceSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('EntityCustomSequence Management Detail Component', () => {
    let entityCustomSequenceServiceStub: SinonStubbedInstance<EntityCustomSequenceService>;
    let mountOptions: MountingOptions<EntityCustomSequenceDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      entityCustomSequenceServiceStub = sinon.createStubInstance<EntityCustomSequenceService>(EntityCustomSequenceService);

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
          entityCustomSequenceService: () => entityCustomSequenceServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        entityCustomSequenceServiceStub.find.resolves(entityCustomSequenceSample);
        route = {
          params: {
            entityCustomSequenceId: `${123}`,
          },
        };
        const wrapper = shallowMount(EntityCustomSequenceDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.entityCustomSequence).toMatchObject(entityCustomSequenceSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        entityCustomSequenceServiceStub.find.resolves(entityCustomSequenceSample);
        const wrapper = shallowMount(EntityCustomSequenceDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
