<template>
  <div>
    <h2 id="page-heading" data-cy="EntityUuidIdHeading">
      <span v-text="t$('jhipsterVueApp.entityUuidId.home.title')" id="entity-uuid-id-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info me-2" @click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jhipsterVueApp.entityUuidId.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'EntityUuidIdCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-entity-uuid-id"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jhipsterVueApp.entityUuidId.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && entityUuidIds && entityUuidIds.length === 0">
      <span v-text="t$('jhipsterVueApp.entityUuidId.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="entityUuidIds && entityUuidIds.length > 0">
      <table class="table table-striped" aria-describedby="entityUuidIds">
        <thead>
          <tr>
            <th scope="col"><span v-text="t$('global.field.id')"></span></th>
            <th scope="col"><span v-text="t$('jhipsterVueApp.entityUuidId.manyToManyBack')"></span></th>
            <th scope="col"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="entityUuidId in entityUuidIds" :key="entityUuidId.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'EntityUuidIdView', params: { entityUuidIdId: entityUuidId.id } }">{{
                entityUuidId.id
              }}</router-link>
            </td>
            <td>
              <span v-for="(manyToManyBack, i) in entityUuidId.manyToManyBacks" :key="manyToManyBack.id"
                >{{ i > 0 ? ', ' : '' }}
                <router-link
                  class="form-control-static"
                  :to="{ name: 'EntityUuidIdRelationshipView', params: { entityUuidIdRelationshipId: manyToManyBack.id } }"
                  >{{ manyToManyBack.id }}</router-link
                >
              </span>
            </td>
            <td class="text-end">
              <div class="btn-group">
                <router-link :to="{ name: 'EntityUuidIdView', params: { entityUuidIdId: entityUuidId.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'EntityUuidIdEdit', params: { entityUuidIdId: entityUuidId.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  @click="prepareRemove(entityUuidId)"
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
          id="jhipsterVueApp.entityUuidId.delete.question"
          data-cy="entityUuidIdDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-entityUuidId-heading" v-text="t$('jhipsterVueApp.entityUuidId.delete.question', { id: removeId })"></p>
      </div>
      <template #footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" @click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-entityUuidId"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            @click="removeEntityUuidId()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./entity-uuid-id.component.ts"></script>
