<template>
  <div>
    <h2 id="page-heading" data-cy="EntityCustomIdRequiredDTOMapsIdHeading">
      <span
        v-text="t$('jhipsterVueApp.entityCustomIdRequiredDTOMapsId.home.title')"
        id="entity-custom-id-required-dto-maps-id-heading"
      ></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info me-2" @click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jhipsterVueApp.entityCustomIdRequiredDTOMapsId.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'EntityCustomIdRequiredDTOMapsIdCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-entity-custom-id-required-dto-maps-id"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jhipsterVueApp.entityCustomIdRequiredDTOMapsId.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div
      class="alert alert-warning"
      v-if="!isFetching && entityCustomIdRequiredDTOMapsIds && entityCustomIdRequiredDTOMapsIds.length === 0"
    >
      <span v-text="t$('jhipsterVueApp.entityCustomIdRequiredDTOMapsId.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="entityCustomIdRequiredDTOMapsIds && entityCustomIdRequiredDTOMapsIds.length > 0">
      <table class="table table-striped" aria-describedby="entityCustomIdRequiredDTOMapsIds">
        <thead>
          <tr>
            <th scope="col"><span v-text="t$('global.field.id')"></span></th>
            <th scope="col"><span v-text="t$('jhipsterVueApp.entityCustomIdRequiredDTOMapsId.entityCustomIdRequiredDTO')"></span></th>
            <th scope="col"><span v-text="t$('jhipsterVueApp.entityCustomIdRequiredDTOMapsId.manyToManyMapsIdBack')"></span></th>
            <th scope="col"></th>
          </tr>
        </thead>
        <tbody>
          <tr
            v-for="entityCustomIdRequiredDTOMapsId in entityCustomIdRequiredDTOMapsIds"
            :key="entityCustomIdRequiredDTOMapsId.customId"
            data-cy="entityTable"
          >
            <td>
              <router-link
                :to="{
                  name: 'EntityCustomIdRequiredDTOMapsIdView',
                  params: { entityCustomIdRequiredDTOMapsIdId: entityCustomIdRequiredDTOMapsId.customId },
                }"
                >{{ entityCustomIdRequiredDTOMapsId.customId }}</router-link
              >
            </td>
            <td>
              <div v-if="entityCustomIdRequiredDTOMapsId.entityCustomIdRequiredDTO">
                <router-link
                  :to="{
                    name: 'EntityCustomIdRequiredDTOView',
                    params: { entityCustomIdRequiredDTOId: entityCustomIdRequiredDTOMapsId.entityCustomIdRequiredDTO.customId },
                  }"
                  >{{ entityCustomIdRequiredDTOMapsId.entityCustomIdRequiredDTO.customId }}</router-link
                >
              </div>
            </td>
            <td>
              <span
                v-for="(manyToManyMapsIdBack, i) in entityCustomIdRequiredDTOMapsId.manyToManyMapsIdBacks"
                :key="manyToManyMapsIdBack.customId"
                >{{ i > 0 ? ', ' : '' }}
                <router-link
                  class="form-control-static"
                  :to="{
                    name: 'EntityCustomIdRequiredDTORelView',
                    params: { entityCustomIdRequiredDTORelId: manyToManyMapsIdBack.relatedId },
                  }"
                  >{{ manyToManyMapsIdBack.relatedId }}</router-link
                >
              </span>
            </td>
            <td class="text-end">
              <div class="btn-group">
                <router-link
                  :to="{
                    name: 'EntityCustomIdRequiredDTOMapsIdView',
                    params: { entityCustomIdRequiredDTOMapsIdId: entityCustomIdRequiredDTOMapsId.customId },
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
                    name: 'EntityCustomIdRequiredDTOMapsIdEdit',
                    params: { entityCustomIdRequiredDTOMapsIdId: entityCustomIdRequiredDTOMapsId.customId },
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
                  @click="prepareRemove(entityCustomIdRequiredDTOMapsId)"
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
          id="jhipsterVueApp.entityCustomIdRequiredDTOMapsId.delete.question"
          data-cy="entityCustomIdRequiredDTOMapsIdDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p
          id="jhi-delete-entityCustomIdRequiredDTOMapsId-heading"
          v-text="t$('jhipsterVueApp.entityCustomIdRequiredDTOMapsId.delete.question', { id: removeId })"
        ></p>
      </div>
      <template #footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" @click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-entityCustomIdRequiredDTOMapsId"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            @click="removeEntityCustomIdRequiredDTOMapsId()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./entity-custom-id-required-dto-maps-id.component.ts"></script>
