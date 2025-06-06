<template>
  <div>
    <h2 id="page-heading" data-cy="EntityUuidIdDTOHeading">
      <span v-text="t$('jhipsterVueApp.entityUuidIdDTO.home.title')" id="entity-uuid-id-dto-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info me-2" @click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jhipsterVueApp.entityUuidIdDTO.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'EntityUuidIdDTOCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-entity-uuid-id-dto"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jhipsterVueApp.entityUuidIdDTO.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && entityUuidIdDTOS && entityUuidIdDTOS.length === 0">
      <span v-text="t$('jhipsterVueApp.entityUuidIdDTO.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="entityUuidIdDTOS && entityUuidIdDTOS.length > 0">
      <table class="table table-striped" aria-describedby="entityUuidIdDTOS">
        <thead>
          <tr>
            <th scope="col"><span v-text="t$('global.field.id')"></span></th>
            <th scope="col"><span v-text="t$('jhipsterVueApp.entityUuidIdDTO.manyToManyBack')"></span></th>
            <th scope="col"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="entityUuidIdDTO in entityUuidIdDTOS" :key="entityUuidIdDTO.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'EntityUuidIdDTOView', params: { entityUuidIdDTOId: entityUuidIdDTO.id } }">{{
                entityUuidIdDTO.id
              }}</router-link>
            </td>
            <td>
              <span v-for="(manyToManyBack, i) in entityUuidIdDTO.manyToManyBacks" :key="manyToManyBack.id"
                >{{ i > 0 ? ', ' : '' }}
                <router-link
                  class="form-control-static"
                  :to="{ name: 'EntityUuidIdDTORelView', params: { entityUuidIdDTORelId: manyToManyBack.id } }"
                  >{{ manyToManyBack.id }}</router-link
                >
              </span>
            </td>
            <td class="text-end">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'EntityUuidIdDTOView', params: { entityUuidIdDTOId: entityUuidIdDTO.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'EntityUuidIdDTOEdit', params: { entityUuidIdDTOId: entityUuidIdDTO.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  @click="prepareRemove(entityUuidIdDTO)"
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
          id="jhipsterVueApp.entityUuidIdDTO.delete.question"
          data-cy="entityUuidIdDTODeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-entityUuidIdDTO-heading" v-text="t$('jhipsterVueApp.entityUuidIdDTO.delete.question', { id: removeId })"></p>
      </div>
      <template #footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" @click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-entityUuidIdDTO"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            @click="removeEntityUuidIdDTO()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./entity-uuid-id-dto.component.ts"></script>
