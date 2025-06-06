<template>
  <div>
    <h2 id="page-heading" data-cy="EntityCustomIdDTOHeading">
      <span v-text="t$('jhipsterVueApp.entityCustomIdDTO.home.title')" id="entity-custom-id-dto-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info me-2" @click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jhipsterVueApp.entityCustomIdDTO.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'EntityCustomIdDTOCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-entity-custom-id-dto"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jhipsterVueApp.entityCustomIdDTO.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && entityCustomIdDTOS && entityCustomIdDTOS.length === 0">
      <span v-text="t$('jhipsterVueApp.entityCustomIdDTO.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="entityCustomIdDTOS && entityCustomIdDTOS.length > 0">
      <table class="table table-striped" aria-describedby="entityCustomIdDTOS">
        <thead>
          <tr>
            <th scope="col"><span v-text="t$('global.field.id')"></span></th>
            <th scope="col"><span v-text="t$('jhipsterVueApp.entityCustomIdDTO.manyToManyBack')"></span></th>
            <th scope="col"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="entityCustomIdDTO in entityCustomIdDTOS" :key="entityCustomIdDTO.customId" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'EntityCustomIdDTOView', params: { entityCustomIdDTOId: entityCustomIdDTO.customId } }">{{
                entityCustomIdDTO.customId
              }}</router-link>
            </td>
            <td>
              <span v-for="(manyToManyBack, i) in entityCustomIdDTO.manyToManyBacks" :key="manyToManyBack.customId"
                >{{ i > 0 ? ', ' : '' }}
                <router-link
                  class="form-control-static"
                  :to="{ name: 'EntityCustomIdDTORelView', params: { entityCustomIdDTORelId: manyToManyBack.relatedId } }"
                  >{{ manyToManyBack.relatedId }}</router-link
                >
              </span>
            </td>
            <td class="text-end">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'EntityCustomIdDTOView', params: { entityCustomIdDTOId: entityCustomIdDTO.customId } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'EntityCustomIdDTOEdit', params: { entityCustomIdDTOId: entityCustomIdDTO.customId } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  @click="prepareRemove(entityCustomIdDTO)"
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
          id="jhipsterVueApp.entityCustomIdDTO.delete.question"
          data-cy="entityCustomIdDTODeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-entityCustomIdDTO-heading" v-text="t$('jhipsterVueApp.entityCustomIdDTO.delete.question', { id: removeId })"></p>
      </div>
      <template #footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" @click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-entityCustomIdDTO"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            @click="removeEntityCustomIdDTO()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./entity-custom-id-dto.component.ts"></script>
