<template>
  <div>
    <h2 id="page-heading" data-cy="EntityUuidIdDTOMapsIdHeading">
      <span v-text="t$('jhipsterVueApp.entityUuidIdDTOMapsId.home.title')" id="entity-uuid-id-dto-maps-id-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info me-2" @click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jhipsterVueApp.entityUuidIdDTOMapsId.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'EntityUuidIdDTOMapsIdCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-entity-uuid-id-dto-maps-id"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jhipsterVueApp.entityUuidIdDTOMapsId.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && entityUuidIdDTOMapsIds && entityUuidIdDTOMapsIds.length === 0">
      <span v-text="t$('jhipsterVueApp.entityUuidIdDTOMapsId.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="entityUuidIdDTOMapsIds && entityUuidIdDTOMapsIds.length > 0">
      <table class="table table-striped" aria-describedby="entityUuidIdDTOMapsIds">
        <thead>
          <tr>
            <th scope="col"><span v-text="t$('global.field.id')"></span></th>
            <th scope="col"><span v-text="t$('jhipsterVueApp.entityUuidIdDTOMapsId.entityUuidIdDTO')"></span></th>
            <th scope="col"><span v-text="t$('jhipsterVueApp.entityUuidIdDTOMapsId.manyToManyMapsIdBack')"></span></th>
            <th scope="col"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="entityUuidIdDTOMapsId in entityUuidIdDTOMapsIds" :key="entityUuidIdDTOMapsId.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'EntityUuidIdDTOMapsIdView', params: { entityUuidIdDTOMapsIdId: entityUuidIdDTOMapsId.id } }">{{
                entityUuidIdDTOMapsId.id
              }}</router-link>
            </td>
            <td>
              <div v-if="entityUuidIdDTOMapsId.entityUuidIdDTO">
                <router-link
                  :to="{ name: 'EntityUuidIdDTOView', params: { entityUuidIdDTOId: entityUuidIdDTOMapsId.entityUuidIdDTO.id } }"
                  >{{ entityUuidIdDTOMapsId.entityUuidIdDTO.id }}</router-link
                >
              </div>
            </td>
            <td>
              <span v-for="(manyToManyMapsIdBack, i) in entityUuidIdDTOMapsId.manyToManyMapsIdBacks" :key="manyToManyMapsIdBack.id"
                >{{ i > 0 ? ', ' : '' }}
                <router-link
                  class="form-control-static"
                  :to="{ name: 'EntityUuidIdDTORelView', params: { entityUuidIdDTORelId: manyToManyMapsIdBack.id } }"
                  >{{ manyToManyMapsIdBack.id }}</router-link
                >
              </span>
            </td>
            <td class="text-end">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'EntityUuidIdDTOMapsIdView', params: { entityUuidIdDTOMapsIdId: entityUuidIdDTOMapsId.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'EntityUuidIdDTOMapsIdEdit', params: { entityUuidIdDTOMapsIdId: entityUuidIdDTOMapsId.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  @click="prepareRemove(entityUuidIdDTOMapsId)"
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
          id="jhipsterVueApp.entityUuidIdDTOMapsId.delete.question"
          data-cy="entityUuidIdDTOMapsIdDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p
          id="jhi-delete-entityUuidIdDTOMapsId-heading"
          v-text="t$('jhipsterVueApp.entityUuidIdDTOMapsId.delete.question', { id: removeId })"
        ></p>
      </div>
      <template #footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" @click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-entityUuidIdDTOMapsId"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            @click="removeEntityUuidIdDTOMapsId()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./entity-uuid-id-dto-maps-id.component.ts"></script>
