import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import EntityCustomIdUpdate from './entity-custom-id-update.vue';
import EntityCustomIdService from './entity-custom-id.service';
import AlertService from '@/shared/alert/alert.service';

import EntityCustomIdRelationshipService from '@/entities/entity-custom-id-relationship/entity-custom-id-relationship.service';

type EntityCustomIdUpdateComponentType = InstanceType<typeof EntityCustomIdUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const entityCustomIdSample = { customId: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<EntityCustomIdUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('EntityCustomId Management Update Component', () => {
    let comp: EntityCustomIdUpdateComponentType;
    let entityCustomIdServiceStub: SinonStubbedInstance<EntityCustomIdService>;

    beforeEach(() => {
      route = {};
      entityCustomIdServiceStub = sinon.createStubInstance<EntityCustomIdService>(EntityCustomIdService);
      entityCustomIdServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          entityCustomIdService: () => entityCustomIdServiceStub,
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
        const wrapper = shallowMount(EntityCustomIdUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.entityCustomId = entityCustomIdSample;
        entityCustomIdServiceStub.update.resolves(entityCustomIdSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(entityCustomIdServiceStub.update.calledWith(entityCustomIdSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        entityCustomIdServiceStub.create.resolves(entity);
        const wrapper = shallowMount(EntityCustomIdUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.entityCustomId = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(entityCustomIdServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        entityCustomIdServiceStub.find.resolves(entityCustomIdSample);
        entityCustomIdServiceStub.retrieve.resolves([entityCustomIdSample]);

        // WHEN
        route = {
          params: {
            entityCustomIdId: `${entityCustomIdSample.customId}`,
          },
        };
        const wrapper = shallowMount(EntityCustomIdUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.entityCustomId).toMatchObject(entityCustomIdSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        entityCustomIdServiceStub.find.resolves(entityCustomIdSample);
        const wrapper = shallowMount(EntityCustomIdUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
