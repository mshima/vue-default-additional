import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import EntityCustomSequence from './entity-custom-sequence.vue';
import EntityCustomSequenceService from './entity-custom-sequence.service';
import AlertService from '@/shared/alert/alert.service';

type EntityCustomSequenceComponentType = InstanceType<typeof EntityCustomSequence>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('EntityCustomSequence Management Component', () => {
    let entityCustomSequenceServiceStub: SinonStubbedInstance<EntityCustomSequenceService>;
    let mountOptions: MountingOptions<EntityCustomSequenceComponentType>['global'];

    beforeEach(() => {
      entityCustomSequenceServiceStub = sinon.createStubInstance<EntityCustomSequenceService>(EntityCustomSequenceService);
      entityCustomSequenceServiceStub.retrieve.resolves({ headers: {} });

      alertService = new AlertService({
        i18n: { t: vitest.fn() } as any,
        toastController: {
          show: vitest.fn(),
        } as any,
      });

      mountOptions = {
        stubs: {
          bModal: bModalStub as any,
          'font-awesome-icon': true,
          'b-badge': true,
          'b-button': true,
          'router-link': true,
        },
        directives: {
          'b-modal': {},
        },
        provide: {
          alertService,
          entityCustomSequenceService: () => entityCustomSequenceServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        entityCustomSequenceServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(EntityCustomSequence, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(entityCustomSequenceServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.entityCustomSequences[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: EntityCustomSequenceComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(EntityCustomSequence, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        entityCustomSequenceServiceStub.retrieve.reset();
        entityCustomSequenceServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        entityCustomSequenceServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeEntityCustomSequence();
        await comp.$nextTick(); // clear components

        // THEN
        expect(entityCustomSequenceServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(entityCustomSequenceServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
