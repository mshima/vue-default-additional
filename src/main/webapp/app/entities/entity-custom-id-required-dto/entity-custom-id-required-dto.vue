<template>
  <div>
    <h2 id="page-heading" data-cy="EntityCustomIdRequiredDTOHeading">
      <span v-text="t$('jhipsterVueApp.entityCustomIdRequiredDTO.home.title')" id="entity-custom-id-required-dto-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info me-2" @click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jhipsterVueApp.entityCustomIdRequiredDTO.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'EntityCustomIdRequiredDTOCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-entity-custom-id-required-dto"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jhipsterVueApp.entityCustomIdRequiredDTO.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && entityCustomIdRequiredDTOS && entityCustomIdRequiredDTOS.length === 0">
      <span v-text="t$('jhipsterVueApp.entityCustomIdRequiredDTO.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="entityCustomIdRequiredDTOS && entityCustomIdRequiredDTOS.length > 0">
      <table class="table table-striped" aria-describedby="entityCustomIdRequiredDTOS">
        <thead>
          <tr>
            <th scope="col"><span v-text="t$('global.field.id')"></span></th>
            <th scope="col"><span v-text="t$('jhipsterVueApp.entityCustomIdRequiredDTO.manyToManyBack')"></span></th>
            <th scope="col"></th>
          </tr>
        </thead>
        <tbody>
          <tr
            v-for="entityCustomIdRequiredDTO in entityCustomIdRequiredDTOS"
            :key="entityCustomIdRequiredDTO.customId"
            data-cy="entityTable"
          >
            <td>
              <router-link
                :to="{ name: 'EntityCustomIdRequiredDTOView', params: { entityCustomIdRequiredDTOId: entityCustomIdRequiredDTO.customId } }"
                >{{ entityCustomIdRequiredDTO.customId }}</router-link
              >
            </td>
            <td>
              <span v-for="(manyToManyBack, i) in entityCustomIdRequiredDTO.manyToManyBacks" :key="manyToManyBack.customId"
                >{{ i > 0 ? ', ' : '' }}
                <router-link
                  class="form-control-static"
                  :to="{ name: 'EntityCustomIdRequiredDTORelView', params: { entityCustomIdRequiredDTORelId: manyToManyBack.relatedId } }"
                  >{{ manyToManyBack.relatedId }}</router-link
                >
              </span>
            </td>
            <td class="text-end">
              <div class="btn-group">
                <router-link
                  :to="{
                    name: 'EntityCustomIdRequiredDTOView',
                    params: { entityCustomIdRequiredDTOId: entityCustomIdRequiredDTO.customId },
                  }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{
                    name: 'EntityCustomIdRequiredDTOEdit',
                    params: { entityCustomIdRequiredDTOId: entityCustomIdRequiredDTO.customId },
                  }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  @click="prepareRemove(entityCustomIdRequiredDTO)"
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
          id="jhipsterVueApp.entityCustomIdRequiredDTO.delete.question"
          data-cy="entityCustomIdRequiredDTODeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p
          id="jhi-delete-entityCustomIdRequiredDTO-heading"
          v-text="t$('jhipsterVueApp.entityCustomIdRequiredDTO.delete.question', { id: removeId })"
        ></p>
      </div>
      <template #footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" @click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-entityCustomIdRequiredDTO"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            @click="removeEntityCustomIdRequiredDTO()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./entity-custom-id-required-dto.component.ts"></script>
