import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import EntityCustomIdRelationshipDetails from './entity-custom-id-relationship-details.vue';
import EntityCustomIdRelationshipService from './entity-custom-id-relationship.service';
import AlertService from '@/shared/alert/alert.service';

type EntityCustomIdRelationshipDetailsComponentType = InstanceType<typeof EntityCustomIdRelationshipDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const entityCustomIdRelationshipSample = { relatedId: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('EntityCustomIdRelationship Management Detail Component', () => {
    let entityCustomIdRelationshipServiceStub: SinonStubbedInstance<EntityCustomIdRelationshipService>;
    let mountOptions: MountingOptions<EntityCustomIdRelationshipDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      entityCustomIdRelationshipServiceStub =
        sinon.createStubInstance<EntityCustomIdRelationshipService>(EntityCustomIdRelationshipService);

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
          entityCustomIdRelationshipService: () => entityCustomIdRelationshipServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        entityCustomIdRelationshipServiceStub.find.resolves(entityCustomIdRelationshipSample);
        route = {
          params: {
            entityCustomIdRelationshipId: `${123}`,
          },
        };
        const wrapper = shallowMount(EntityCustomIdRelationshipDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.entityCustomIdRelationship).toMatchObject(entityCustomIdRelationshipSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        entityCustomIdRelationshipServiceStub.find.resolves(entityCustomIdRelationshipSample);
        const wrapper = shallowMount(EntityCustomIdRelationshipDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
