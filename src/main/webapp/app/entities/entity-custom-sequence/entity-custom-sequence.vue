<template>
  <div>
    <h2 id="page-heading" data-cy="EntityCustomSequenceHeading">
      <span v-text="t$('jhipsterVueApp.entityCustomSequence.home.title')" id="entity-custom-sequence-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info me-2" @click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jhipsterVueApp.entityCustomSequence.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'EntityCustomSequenceCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-entity-custom-sequence"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jhipsterVueApp.entityCustomSequence.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && entityCustomSequences && entityCustomSequences.length === 0">
      <span v-text="t$('jhipsterVueApp.entityCustomSequence.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="entityCustomSequences && entityCustomSequences.length > 0">
      <table class="table table-striped" aria-describedby="entityCustomSequences">
        <thead>
          <tr>
            <th scope="col"><span v-text="t$('global.field.id')"></span></th>
            <th scope="col"><span v-text="t$('jhipsterVueApp.entityCustomSequence.name')"></span></th>
            <th scope="col"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="entityCustomSequence in entityCustomSequences" :key="entityCustomSequence.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'EntityCustomSequenceView', params: { entityCustomSequenceId: entityCustomSequence.id } }">{{
                entityCustomSequence.id
              }}</router-link>
            </td>
            <td>{{ entityCustomSequence.name }}</td>
            <td class="text-end">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'EntityCustomSequenceView', params: { entityCustomSequenceId: entityCustomSequence.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'EntityCustomSequenceEdit', params: { entityCustomSequenceId: entityCustomSequence.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  @click="prepareRemove(entityCustomSequence)"
                  variant="danger"
                  class="btn btn-sm"
                  data-cy="entityDeleteButton"
                  v-b-modal.removeEntity
                >
                  <font-awesome-icon icon="times"></font-awesome-icon>
                  <span class="d-none d-md-inline" v-text="t$('entity.action.delete')"></span>
                </b-button>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <b-modal ref="removeEntity" id="removeEntity">
      <template #title>
        <span
          id="jhipsterVueApp.entityCustomSequence.delete.question"
          data-cy="entityCustomSequenceDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p
          id="jhi-delete-entityCustomSequence-heading"
          v-text="t$('jhipsterVueApp.entityCustomSequence.delete.question', { id: removeId })"
        ></p>
      </div>
      <template #footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" @click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-entityCustomSequence"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            @click="removeEntityCustomSequence()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./entity-custom-sequence.component.ts"></script>
