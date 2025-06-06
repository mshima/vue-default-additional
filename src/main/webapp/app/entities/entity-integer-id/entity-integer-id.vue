<template>
  <div>
    <h2 id="page-heading" data-cy="EntityIntegerIdHeading">
      <span v-text="t$('jhipsterVueApp.entityIntegerId.home.title')" id="entity-integer-id-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info me-2" @click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jhipsterVueApp.entityIntegerId.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'EntityIntegerIdCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-entity-integer-id"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jhipsterVueApp.entityIntegerId.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && entityIntegerIds && entityIntegerIds.length === 0">
      <span v-text="t$('jhipsterVueApp.entityIntegerId.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="entityIntegerIds && entityIntegerIds.length > 0">
      <table class="table table-striped" aria-describedby="entityIntegerIds">
        <thead>
          <tr>
            <th scope="col"><span v-text="t$('global.field.id')"></span></th>
            <th scope="col"><span v-text="t$('jhipsterVueApp.entityIntegerId.name')"></span></th>
            <th scope="col"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="entityIntegerId in entityIntegerIds" :key="entityIntegerId.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'EntityIntegerIdView', params: { entityIntegerIdId: entityIntegerId.id } }">{{
                entityIntegerId.id
              }}</router-link>
            </td>
            <td>{{ entityIntegerId.name }}</td>
            <td class="text-end">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'EntityIntegerIdView', params: { entityIntegerIdId: entityIntegerId.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'EntityIntegerIdEdit', params: { entityIntegerIdId: entityIntegerId.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  @click="prepareRemove(entityIntegerId)"
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
          id="jhipsterVueApp.entityIntegerId.delete.question"
          data-cy="entityIntegerIdDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-entityIntegerId-heading" v-text="t$('jhipsterVueApp.entityIntegerId.delete.question', { id: removeId })"></p>
      </div>
      <template #footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" @click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-entityIntegerId"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            @click="removeEntityIntegerId()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./entity-integer-id.component.ts"></script>
