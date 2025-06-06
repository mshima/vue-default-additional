import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import EntityCustomIdDTOMapsIdUpdate from './entity-custom-id-dto-maps-id-update.vue';
import EntityCustomIdDTOMapsIdService from './entity-custom-id-dto-maps-id.service';
import AlertService from '@/shared/alert/alert.service';

import EntityCustomIdDTOService from '@/entities/entity-custom-id-dto/entity-custom-id-dto.service';
import EntityCustomIdDTORelService from '@/entities/entity-custom-id-dto-rel/entity-custom-id-dto-rel.service';

type EntityCustomIdDTOMapsIdUpdateComponentType = InstanceType<typeof EntityCustomIdDTOMapsIdUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const entityCustomIdDTOMapsIdSample = { customId: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<EntityCustomIdDTOMapsIdUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('EntityCustomIdDTOMapsId Management Update Component', () => {
    let comp: EntityCustomIdDTOMapsIdUpdateComponentType;
    let entityCustomIdDTOMapsIdServiceStub: SinonStubbedInstance<EntityCustomIdDTOMapsIdService>;

    beforeEach(() => {
      route = {};
      entityCustomIdDTOMapsIdServiceStub = sinon.createStubInstance<EntityCustomIdDTOMapsIdService>(EntityCustomIdDTOMapsIdService);
      entityCustomIdDTOMapsIdServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          entityCustomIdDTOMapsIdService: () => entityCustomIdDTOMapsIdServiceStub,
          entityCustomIdDTOService: () =>
            sinon.createStubInstance<EntityCustomIdDTOService>(EntityCustomIdDTOService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          entityCustomIdDTORelService: () =>
            sinon.createStubInstance<EntityCustomIdDTORelService>(EntityCustomIdDTORelService, {
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
        const wrapper = shallowMount(EntityCustomIdDTOMapsIdUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.entityCustomIdDTOMapsId = entityCustomIdDTOMapsIdSample;
        entityCustomIdDTOMapsIdServiceStub.update.resolves(entityCustomIdDTOMapsIdSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(entityCustomIdDTOMapsIdServiceStub.update.calledWith(entityCustomIdDTOMapsIdSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        entityCustomIdDTOMapsIdServiceStub.create.resolves(entity);
        const wrapper = shallowMount(EntityCustomIdDTOMapsIdUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.entityCustomIdDTOMapsId = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(entityCustomIdDTOMapsIdServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        entityCustomIdDTOMapsIdServiceStub.find.resolves(entityCustomIdDTOMapsIdSample);
        entityCustomIdDTOMapsIdServiceStub.retrieve.resolves([entityCustomIdDTOMapsIdSample]);

        // WHEN
        route = {
          params: {
            entityCustomIdDTOMapsIdId: `${entityCustomIdDTOMapsIdSample.customId}`,
          },
        };
        const wrapper = shallowMount(EntityCustomIdDTOMapsIdUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.entityCustomIdDTOMapsId).toMatchObject(entityCustomIdDTOMapsIdSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        entityCustomIdDTOMapsIdServiceStub.find.resolves(entityCustomIdDTOMapsIdSample);
        const wrapper = shallowMount(EntityCustomIdDTOMapsIdUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
