<template>
  <div>
    <h2 id="page-heading" data-cy="UuidIdFilteringMapsIdHeading">
      <span v-text="t$('jhipsterVueApp.uuidIdFilteringMapsId.home.title')" id="uuid-id-filtering-maps-id-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info me-2" @click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jhipsterVueApp.uuidIdFilteringMapsId.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'UuidIdFilteringMapsIdCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-uuid-id-filtering-maps-id"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jhipsterVueApp.uuidIdFilteringMapsId.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && uuidIdFilteringMapsIds && uuidIdFilteringMapsIds.length === 0">
      <span v-text="t$('jhipsterVueApp.uuidIdFilteringMapsId.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="uuidIdFilteringMapsIds && uuidIdFilteringMapsIds.length > 0">
      <table class="table table-striped" aria-describedby="uuidIdFilteringMapsIds">
        <thead>
          <tr>
            <th scope="col"><span v-text="t$('global.field.id')"></span></th>
            <th scope="col"><span v-text="t$('jhipsterVueApp.uuidIdFilteringMapsId.uuidIdFiltering')"></span></th>
            <th scope="col"><span v-text="t$('jhipsterVueApp.uuidIdFilteringMapsId.manyToManyMapsIdBack')"></span></th>
            <th scope="col"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="uuidIdFilteringMapsId in uuidIdFilteringMapsIds" :key="uuidIdFilteringMapsId.customId" data-cy="entityTable">
            <td>
              <router-link
                :to="{ name: 'UuidIdFilteringMapsIdView', params: { uuidIdFilteringMapsIdId: uuidIdFilteringMapsId.customId } }"
                >{{ uuidIdFilteringMapsId.customId }}</router-link
              >
            </td>
            <td>
              <div v-if="uuidIdFilteringMapsId.uuidIdFiltering">
                <router-link
                  :to="{ name: 'UuidIdFilteringView', params: { uuidIdFilteringId: uuidIdFilteringMapsId.uuidIdFiltering.customId } }"
                  >{{ uuidIdFilteringMapsId.uuidIdFiltering.customId }}</router-link
                >
              </div>
            </td>
            <td>
              <span v-for="(manyToManyMapsIdBack, i) in uuidIdFilteringMapsId.manyToManyMapsIdBacks" :key="manyToManyMapsIdBack.customId"
                >{{ i > 0 ? ', ' : '' }}
                <router-link
                  class="form-control-static"
                  :to="{
                    name: 'UuidIdFilteringRelationshipView',
                    params: { uuidIdFilteringRelationshipId: manyToManyMapsIdBack.relatedId },
                  }"
                  >{{ manyToManyMapsIdBack.relatedId }}</router-link
                >
              </span>
            </td>
            <td class="text-end">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'UuidIdFilteringMapsIdView', params: { uuidIdFilteringMapsIdId: uuidIdFilteringMapsId.customId } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'UuidIdFilteringMapsIdEdit', params: { uuidIdFilteringMapsIdId: uuidIdFilteringMapsId.customId } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  @click="prepareRemove(uuidIdFilteringMapsId)"
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
          id="jhipsterVueApp.uuidIdFilteringMapsId.delete.question"
          data-cy="uuidIdFilteringMapsIdDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p
          id="jhi-delete-uuidIdFilteringMapsId-heading"
          v-text="t$('jhipsterVueApp.uuidIdFilteringMapsId.delete.question', { id: removeId })"
        ></p>
      </div>
      <template #footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" @click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-uuidIdFilteringMapsId"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            @click="removeUuidIdFilteringMapsId()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./uuid-id-filtering-maps-id.component.ts"></script>
