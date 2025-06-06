<template>
  <div>
    <h2 id="page-heading" data-cy="EntityUuidIdMapsIdHeading">
      <span v-text="t$('jhipsterVueApp.entityUuidIdMapsId.home.title')" id="entity-uuid-id-maps-id-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info me-2" @click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jhipsterVueApp.entityUuidIdMapsId.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'EntityUuidIdMapsIdCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-entity-uuid-id-maps-id"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jhipsterVueApp.entityUuidIdMapsId.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && entityUuidIdMapsIds && entityUuidIdMapsIds.length === 0">
      <span v-text="t$('jhipsterVueApp.entityUuidIdMapsId.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="entityUuidIdMapsIds && entityUuidIdMapsIds.length > 0">
      <table class="table table-striped" aria-describedby="entityUuidIdMapsIds">
        <thead>
          <tr>
            <th scope="col"><span v-text="t$('global.field.id')"></span></th>
            <th scope="col"><span v-text="t$('jhipsterVueApp.entityUuidIdMapsId.entityUuidId')"></span></th>
            <th scope="col"><span v-text="t$('jhipsterVueApp.entityUuidIdMapsId.manyToManyMapsIdBack')"></span></th>
            <th scope="col"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="entityUuidIdMapsId in entityUuidIdMapsIds" :key="entityUuidIdMapsId.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'EntityUuidIdMapsIdView', params: { entityUuidIdMapsIdId: entityUuidIdMapsId.id } }">{{
                entityUuidIdMapsId.id
              }}</router-link>
            </td>
            <td>
              <div v-if="entityUuidIdMapsId.entityUuidId">
                <router-link :to="{ name: 'EntityUuidIdView', params: { entityUuidIdId: entityUuidIdMapsId.entityUuidId.id } }">{{
                  entityUuidIdMapsId.entityUuidId.id
                }}</router-link>
              </div>
            </td>
            <td>
              <span v-for="(manyToManyMapsIdBack, i) in entityUuidIdMapsId.manyToManyMapsIdBacks" :key="manyToManyMapsIdBack.id"
                >{{ i > 0 ? ', ' : '' }}
                <router-link
                  class="form-control-static"
                  :to="{ name: 'EntityUuidIdRelationshipView', params: { entityUuidIdRelationshipId: manyToManyMapsIdBack.id } }"
                  >{{ manyToManyMapsIdBack.id }}</router-link
                >
              </span>
            </td>
            <td class="text-end">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'EntityUuidIdMapsIdView', params: { entityUuidIdMapsIdId: entityUuidIdMapsId.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'EntityUuidIdMapsIdEdit', params: { entityUuidIdMapsIdId: entityUuidIdMapsId.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  @click="prepareRemove(entityUuidIdMapsId)"
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
          id="jhipsterVueApp.entityUuidIdMapsId.delete.question"
          data-cy="entityUuidIdMapsIdDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p
          id="jhi-delete-entityUuidIdMapsId-heading"
          v-text="t$('jhipsterVueApp.entityUuidIdMapsId.delete.question', { id: removeId })"
        ></p>
      </div>
      <template #footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" @click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-entityUuidIdMapsId"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            @click="removeEntityUuidIdMapsId()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./entity-uuid-id-maps-id.component.ts"></script>
