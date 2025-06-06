import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import EntityCustomIdRequiredDTOMapsIdUpdate from './entity-custom-id-required-dto-maps-id-update.vue';
import EntityCustomIdRequiredDTOMapsIdService from './entity-custom-id-required-dto-maps-id.service';
import AlertService from '@/shared/alert/alert.service';

import EntityCustomIdRequiredDTOService from '@/entities/entity-custom-id-required-dto/entity-custom-id-required-dto.service';
import EntityCustomIdRequiredDTORelService from '@/entities/entity-custom-id-required-dto-rel/entity-custom-id-required-dto-rel.service';

type EntityCustomIdRequiredDTOMapsIdUpdateComponentType = InstanceType<typeof EntityCustomIdRequiredDTOMapsIdUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const entityCustomIdRequiredDTOMapsIdSample = { customId: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<EntityCustomIdRequiredDTOMapsIdUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('EntityCustomIdRequiredDTOMapsId Management Update Component', () => {
    let comp: EntityCustomIdRequiredDTOMapsIdUpdateComponentType;
    let entityCustomIdRequiredDTOMapsIdServiceStub: SinonStubbedInstance<EntityCustomIdRequiredDTOMapsIdService>;

    beforeEach(() => {
      route = {};
      entityCustomIdRequiredDTOMapsIdServiceStub = sinon.createStubInstance<EntityCustomIdRequiredDTOMapsIdService>(
        EntityCustomIdRequiredDTOMapsIdService,
      );
      entityCustomIdRequiredDTOMapsIdServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          entityCustomIdRequiredDTOMapsIdService: () => entityCustomIdRequiredDTOMapsIdServiceStub,
          entityCustomIdRequiredDTOService: () =>
            sinon.createStubInstance<EntityCustomIdRequiredDTOService>(EntityCustomIdRequiredDTOService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          entityCustomIdRequiredDTORelService: () =>
            sinon.createStubInstance<EntityCustomIdRequiredDTORelService>(EntityCustomIdRequiredDTORelService, {
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
        const wrapper = shallowMount(EntityCustomIdRequiredDTOMapsIdUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.entityCustomIdRequiredDTOMapsId = entityCustomIdRequiredDTOMapsIdSample;
        entityCustomIdRequiredDTOMapsIdServiceStub.update.resolves(entityCustomIdRequiredDTOMapsIdSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(entityCustomIdRequiredDTOMapsIdServiceStub.update.calledWith(entityCustomIdRequiredDTOMapsIdSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        entityCustomIdRequiredDTOMapsIdServiceStub.create.resolves(entity);
        const wrapper = shallowMount(EntityCustomIdRequiredDTOMapsIdUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.entityCustomIdRequiredDTOMapsId = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(entityCustomIdRequiredDTOMapsIdServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        entityCustomIdRequiredDTOMapsIdServiceStub.find.resolves(entityCustomIdRequiredDTOMapsIdSample);
        entityCustomIdRequiredDTOMapsIdServiceStub.retrieve.resolves([entityCustomIdRequiredDTOMapsIdSample]);

        // WHEN
        route = {
          params: {
            entityCustomIdRequiredDTOMapsIdId: `${entityCustomIdRequiredDTOMapsIdSample.customId}`,
          },
        };
        const wrapper = shallowMount(EntityCustomIdRequiredDTOMapsIdUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.entityCustomIdRequiredDTOMapsId).toMatchObject(entityCustomIdRequiredDTOMapsIdSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        entityCustomIdRequiredDTOMapsIdServiceStub.find.resolves(entityCustomIdRequiredDTOMapsIdSample);
        const wrapper = shallowMount(EntityCustomIdRequiredDTOMapsIdUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
