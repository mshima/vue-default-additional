import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import EntityCustomIdRequiredDTOUpdate from './entity-custom-id-required-dto-update.vue';
import EntityCustomIdRequiredDTOService from './entity-custom-id-required-dto.service';
import AlertService from '@/shared/alert/alert.service';

import EntityCustomIdRequiredDTORelService from '@/entities/entity-custom-id-required-dto-rel/entity-custom-id-required-dto-rel.service';

type EntityCustomIdRequiredDTOUpdateComponentType = InstanceType<typeof EntityCustomIdRequiredDTOUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const entityCustomIdRequiredDTOSample = { customId: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<EntityCustomIdRequiredDTOUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('EntityCustomIdRequiredDTO Management Update Component', () => {
    let comp: EntityCustomIdRequiredDTOUpdateComponentType;
    let entityCustomIdRequiredDTOServiceStub: SinonStubbedInstance<EntityCustomIdRequiredDTOService>;

    beforeEach(() => {
      route = {};
      entityCustomIdRequiredDTOServiceStub = sinon.createStubInstance<EntityCustomIdRequiredDTOService>(EntityCustomIdRequiredDTOService);
      entityCustomIdRequiredDTOServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          entityCustomIdRequiredDTOService: () => entityCustomIdRequiredDTOServiceStub,
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
        const wrapper = shallowMount(EntityCustomIdRequiredDTOUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.entityCustomIdRequiredDTO = entityCustomIdRequiredDTOSample;
        entityCustomIdRequiredDTOServiceStub.update.resolves(entityCustomIdRequiredDTOSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(entityCustomIdRequiredDTOServiceStub.update.calledWith(entityCustomIdRequiredDTOSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        entityCustomIdRequiredDTOServiceStub.create.resolves(entity);
        const wrapper = shallowMount(EntityCustomIdRequiredDTOUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.entityCustomIdRequiredDTO = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(entityCustomIdRequiredDTOServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        entityCustomIdRequiredDTOServiceStub.find.resolves(entityCustomIdRequiredDTOSample);
        entityCustomIdRequiredDTOServiceStub.retrieve.resolves([entityCustomIdRequiredDTOSample]);

        // WHEN
        route = {
          params: {
            entityCustomIdRequiredDTOId: `${entityCustomIdRequiredDTOSample.customId}`,
          },
        };
        const wrapper = shallowMount(EntityCustomIdRequiredDTOUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.entityCustomIdRequiredDTO).toMatchObject(entityCustomIdRequiredDTOSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        entityCustomIdRequiredDTOServiceStub.find.resolves(entityCustomIdRequiredDTOSample);
        const wrapper = shallowMount(EntityCustomIdRequiredDTOUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
