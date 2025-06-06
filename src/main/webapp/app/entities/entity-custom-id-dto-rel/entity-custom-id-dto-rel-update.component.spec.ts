import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import EntityCustomIdDTORelUpdate from './entity-custom-id-dto-rel-update.vue';
import EntityCustomIdDTORelService from './entity-custom-id-dto-rel.service';
import AlertService from '@/shared/alert/alert.service';

import EntityCustomIdDTOService from '@/entities/entity-custom-id-dto/entity-custom-id-dto.service';
import EntityCustomIdDTOMapsIdService from '@/entities/entity-custom-id-dto-maps-id/entity-custom-id-dto-maps-id.service';

type EntityCustomIdDTORelUpdateComponentType = InstanceType<typeof EntityCustomIdDTORelUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const entityCustomIdDTORelSample = { relatedId: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<EntityCustomIdDTORelUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('EntityCustomIdDTORel Management Update Component', () => {
    let comp: EntityCustomIdDTORelUpdateComponentType;
    let entityCustomIdDTORelServiceStub: SinonStubbedInstance<EntityCustomIdDTORelService>;

    beforeEach(() => {
      route = {};
      entityCustomIdDTORelServiceStub = sinon.createStubInstance<EntityCustomIdDTORelService>(EntityCustomIdDTORelService);
      entityCustomIdDTORelServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          entityCustomIdDTORelService: () => entityCustomIdDTORelServiceStub,
          entityCustomIdDTOService: () =>
            sinon.createStubInstance<EntityCustomIdDTOService>(EntityCustomIdDTOService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          entityCustomIdDTOMapsIdService: () =>
            sinon.createStubInstance<EntityCustomIdDTOMapsIdService>(EntityCustomIdDTOMapsIdService, {
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
        const wrapper = shallowMount(EntityCustomIdDTORelUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.entityCustomIdDTORel = entityCustomIdDTORelSample;
        entityCustomIdDTORelServiceStub.update.resolves(entityCustomIdDTORelSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(entityCustomIdDTORelServiceStub.update.calledWith(entityCustomIdDTORelSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        entityCustomIdDTORelServiceStub.create.resolves(entity);
        const wrapper = shallowMount(EntityCustomIdDTORelUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.entityCustomIdDTORel = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(entityCustomIdDTORelServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        entityCustomIdDTORelServiceStub.find.resolves(entityCustomIdDTORelSample);
        entityCustomIdDTORelServiceStub.retrieve.resolves([entityCustomIdDTORelSample]);

        // WHEN
        route = {
          params: {
            entityCustomIdDTORelId: `${entityCustomIdDTORelSample.relatedId}`,
          },
        };
        const wrapper = shallowMount(EntityCustomIdDTORelUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.entityCustomIdDTORel).toMatchObject(entityCustomIdDTORelSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        entityCustomIdDTORelServiceStub.find.resolves(entityCustomIdDTORelSample);
        const wrapper = shallowMount(EntityCustomIdDTORelUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
