<template>
  <div>
    <h2 id="page-heading" data-cy="EntityCustomIdDTOMapsIdHeading">
      <span v-text="t$('jhipsterVueApp.entityCustomIdDTOMapsId.home.title')" id="entity-custom-id-dto-maps-id-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info me-2" @click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jhipsterVueApp.entityCustomIdDTOMapsId.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'EntityCustomIdDTOMapsIdCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-entity-custom-id-dto-maps-id"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jhipsterVueApp.entityCustomIdDTOMapsId.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && entityCustomIdDTOMapsIds && entityCustomIdDTOMapsIds.length === 0">
      <span v-text="t$('jhipsterVueApp.entityCustomIdDTOMapsId.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="entityCustomIdDTOMapsIds && entityCustomIdDTOMapsIds.length > 0">
      <table class="table table-striped" aria-describedby="entityCustomIdDTOMapsIds">
        <thead>
          <tr>
            <th scope="col"><span v-text="t$('global.field.id')"></span></th>
            <th scope="col"><span v-text="t$('jhipsterVueApp.entityCustomIdDTOMapsId.entityCustomIdDTO')"></span></th>
            <th scope="col"><span v-text="t$('jhipsterVueApp.entityCustomIdDTOMapsId.manyToManyMapsIdBack')"></span></th>
            <th scope="col"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="entityCustomIdDTOMapsId in entityCustomIdDTOMapsIds" :key="entityCustomIdDTOMapsId.customId" data-cy="entityTable">
            <td>
              <router-link
                :to="{ name: 'EntityCustomIdDTOMapsIdView', params: { entityCustomIdDTOMapsIdId: entityCustomIdDTOMapsId.customId } }"
                >{{ entityCustomIdDTOMapsId.customId }}</router-link
              >
            </td>
            <td>
              <div v-if="entityCustomIdDTOMapsId.entityCustomIdDTO">
                <router-link
                  :to="{
                    name: 'EntityCustomIdDTOView',
                    params: { entityCustomIdDTOId: entityCustomIdDTOMapsId.entityCustomIdDTO.customId },
                  }"
                  >{{ entityCustomIdDTOMapsId.entityCustomIdDTO.customId }}</router-link
                >
              </div>
            </td>
            <td>
              <span v-for="(manyToManyMapsIdBack, i) in entityCustomIdDTOMapsId.manyToManyMapsIdBacks" :key="manyToManyMapsIdBack.customId"
                >{{ i > 0 ? ', ' : '' }}
                <router-link
                  class="form-control-static"
                  :to="{ name: 'EntityCustomIdDTORelView', params: { entityCustomIdDTORelId: manyToManyMapsIdBack.relatedId } }"
                  >{{ manyToManyMapsIdBack.relatedId }}</router-link
                >
              </span>
            </td>
            <td class="text-end">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'EntityCustomIdDTOMapsIdView', params: { entityCustomIdDTOMapsIdId: entityCustomIdDTOMapsId.customId } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'EntityCustomIdDTOMapsIdEdit', params: { entityCustomIdDTOMapsIdId: entityCustomIdDTOMapsId.customId } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  @click="prepareRemove(entityCustomIdDTOMapsId)"
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
          id="jhipsterVueApp.entityCustomIdDTOMapsId.delete.question"
          data-cy="entityCustomIdDTOMapsIdDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p
          id="jhi-delete-entityCustomIdDTOMapsId-heading"
          v-text="t$('jhipsterVueApp.entityCustomIdDTOMapsId.delete.question', { id: removeId })"
        ></p>
      </div>
      <template #footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" @click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-entityCustomIdDTOMapsId"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            @click="removeEntityCustomIdDTOMapsId()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./entity-custom-id-dto-maps-id.component.ts"></script>
