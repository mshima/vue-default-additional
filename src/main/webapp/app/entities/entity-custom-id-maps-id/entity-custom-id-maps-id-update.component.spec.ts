import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import EntityCustomIdMapsIdUpdate from './entity-custom-id-maps-id-update.vue';
import EntityCustomIdMapsIdService from './entity-custom-id-maps-id.service';
import AlertService from '@/shared/alert/alert.service';

import EntityCustomIdService from '@/entities/entity-custom-id/entity-custom-id.service';
import EntityCustomIdRelationshipService from '@/entities/entity-custom-id-relationship/entity-custom-id-relationship.service';

type EntityCustomIdMapsIdUpdateComponentType = InstanceType<typeof EntityCustomIdMapsIdUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const entityCustomIdMapsIdSample = { customId: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<EntityCustomIdMapsIdUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('EntityCustomIdMapsId Management Update Component', () => {
    let comp: EntityCustomIdMapsIdUpdateComponentType;
    let entityCustomIdMapsIdServiceStub: SinonStubbedInstance<EntityCustomIdMapsIdService>;

    beforeEach(() => {
      route = {};
      entityCustomIdMapsIdServiceStub = sinon.createStubInstance<EntityCustomIdMapsIdService>(EntityCustomIdMapsIdService);
      entityCustomIdMapsIdServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

      alertService = new AlertService({
        i18n: { t: vitest.fn() } as any,
        toastController: {
          show: vitest.fn(),
        } as any,
      });

      mountOptions = {
        stubs: {
          'font-awesome-icon': true,
          'b-input-group': true,
          'b-input-group-prepend': true,
          'b-form-datepicker': true,
          'b-form-input': true,
        },
        provide: {
          alertService,
          entityCustomIdMapsIdService: () => entityCustomIdMapsIdServiceStub,
          entityCustomIdService: () =>
            sinon.createStubInstance<EntityCustomIdService>(EntityCustomIdService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          entityCustomIdRelationshipService: () =>
            sinon.createStubInstance<EntityCustomIdRelationshipService>(EntityCustomIdRelationshipService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
        },
      };
    });

    afterEach(() => {
      vitest.resetAllMocks();
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const wrapper = shallowMount(EntityCustomIdMapsIdUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.entityCustomIdMapsId = entityCustomIdMapsIdSample;
        entityCustomIdMapsIdServiceStub.update.resolves(entityCustomIdMapsIdSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(entityCustomIdMapsIdServiceStub.update.calledWith(entityCustomIdMapsIdSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        entityCustomIdMapsIdServiceStub.create.resolves(entity);
        const wrapper = shallowMount(EntityCustomIdMapsIdUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.entityCustomIdMapsId = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(entityCustomIdMapsIdServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        entityCustomIdMapsIdServiceStub.find.resolves(entityCustomIdMapsIdSample);
        entityCustomIdMapsIdServiceStub.retrieve.resolves([entityCustomIdMapsIdSample]);

        // WHEN
        route = {
          params: {
            entityCustomIdMapsIdId: `${entityCustomIdMapsIdSample.customId}`,
          },
        };
        const wrapper = shallowMount(EntityCustomIdMapsIdUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.entityCustomIdMapsId).toMatchObject(entityCustomIdMapsIdSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        entityCustomIdMapsIdServiceStub.find.resolves(entityCustomIdMapsIdSample);
        const wrapper = shallowMount(EntityCustomIdMapsIdUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
