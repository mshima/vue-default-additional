import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import EntityCustomSequenceUpdate from './entity-custom-sequence-update.vue';
import EntityCustomSequenceService from './entity-custom-sequence.service';
import AlertService from '@/shared/alert/alert.service';

type EntityCustomSequenceUpdateComponentType = InstanceType<typeof EntityCustomSequenceUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const entityCustomSequenceSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<EntityCustomSequenceUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('EntityCustomSequence Management Update Component', () => {
    let comp: EntityCustomSequenceUpdateComponentType;
    let entityCustomSequenceServiceStub: SinonStubbedInstance<EntityCustomSequenceService>;

    beforeEach(() => {
      route = {};
      entityCustomSequenceServiceStub = sinon.createStubInstance<EntityCustomSequenceService>(EntityCustomSequenceService);
      entityCustomSequenceServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          entityCustomSequenceService: () => entityCustomSequenceServiceStub,
        },
      };
    });

    afterEach(() => {
      vitest.resetAllMocks();
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const wrapper = shallowMount(EntityCustomSequenceUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.entityCustomSequence = entityCustomSequenceSample;
        entityCustomSequenceServiceStub.update.resolves(entityCustomSequenceSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(entityCustomSequenceServiceStub.update.calledWith(entityCustomSequenceSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        entityCustomSequenceServiceStub.create.resolves(entity);
        const wrapper = shallowMount(EntityCustomSequenceUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.entityCustomSequence = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(entityCustomSequenceServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        entityCustomSequenceServiceStub.find.resolves(entityCustomSequenceSample);
        entityCustomSequenceServiceStub.retrieve.resolves([entityCustomSequenceSample]);

        // WHEN
        route = {
          params: {
            entityCustomSequenceId: `${entityCustomSequenceSample.id}`,
          },
        };
        const wrapper = shallowMount(EntityCustomSequenceUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.entityCustomSequence).toMatchObject(entityCustomSequenceSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        entityCustomSequenceServiceStub.find.resolves(entityCustomSequenceSample);
        const wrapper = shallowMount(EntityCustomSequenceUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
