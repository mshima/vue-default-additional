import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import EntityCustomIdRequiredDTORelUpdate from './entity-custom-id-required-dto-rel-update.vue';
import EntityCustomIdRequiredDTORelService from './entity-custom-id-required-dto-rel.service';
import AlertService from '@/shared/alert/alert.service';

import EntityCustomIdRequiredDTOService from '@/entities/entity-custom-id-required-dto/entity-custom-id-required-dto.service';
import EntityCustomIdRequiredDTOMapsIdService from '@/entities/entity-custom-id-required-dto-maps-id/entity-custom-id-required-dto-maps-id.service';

type EntityCustomIdRequiredDTORelUpdateComponentType = InstanceType<typeof EntityCustomIdRequiredDTORelUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const entityCustomIdRequiredDTORelSample = { relatedId: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<EntityCustomIdRequiredDTORelUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('EntityCustomIdRequiredDTORel Management Update Component', () => {
    let comp: EntityCustomIdRequiredDTORelUpdateComponentType;
    let entityCustomIdRequiredDTORelServiceStub: SinonStubbedInstance<EntityCustomIdRequiredDTORelService>;

    beforeEach(() => {
      route = {};
      entityCustomIdRequiredDTORelServiceStub =
        sinon.createStubInstance<EntityCustomIdRequiredDTORelService>(EntityCustomIdRequiredDTORelService);
      entityCustomIdRequiredDTORelServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          entityCustomIdRequiredDTORelService: () => entityCustomIdRequiredDTORelServiceStub,
          entityCustomIdRequiredDTOService: () =>
            sinon.createStubInstance<EntityCustomIdRequiredDTOService>(EntityCustomIdRequiredDTOService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          entityCustomIdRequiredDTOMapsIdService: () =>
            sinon.createStubInstance<EntityCustomIdRequiredDTOMapsIdService>(EntityCustomIdRequiredDTOMapsIdService, {
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
        const wrapper = shallowMount(EntityCustomIdRequiredDTORelUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.entityCustomIdRequiredDTORel = entityCustomIdRequiredDTORelSample;
        entityCustomIdRequiredDTORelServiceStub.update.resolves(entityCustomIdRequiredDTORelSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(entityCustomIdRequiredDTORelServiceStub.update.calledWith(entityCustomIdRequiredDTORelSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        entityCustomIdRequiredDTORelServiceStub.create.resolves(entity);
        const wrapper = shallowMount(EntityCustomIdRequiredDTORelUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.entityCustomIdRequiredDTORel = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(entityCustomIdRequiredDTORelServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        entityCustomIdRequiredDTORelServiceStub.find.resolves(entityCustomIdRequiredDTORelSample);
        entityCustomIdRequiredDTORelServiceStub.retrieve.resolves([entityCustomIdRequiredDTORelSample]);

        // WHEN
        route = {
          params: {
            entityCustomIdRequiredDTORelId: `${entityCustomIdRequiredDTORelSample.relatedId}`,
          },
        };
        const wrapper = shallowMount(EntityCustomIdRequiredDTORelUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.entityCustomIdRequiredDTORel).toMatchObject(entityCustomIdRequiredDTORelSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        entityCustomIdRequiredDTORelServiceStub.find.resolves(entityCustomIdRequiredDTORelSample);
        const wrapper = shallowMount(EntityCustomIdRequiredDTORelUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
