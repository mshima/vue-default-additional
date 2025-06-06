<template>
  <div>
    <h2 id="page-heading" data-cy="EntityUuidIdDTORelHeading">
      <span v-text="t$('jhipsterVueApp.entityUuidIdDTORel.home.title')" id="entity-uuid-id-dto-rel-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info me-2" @click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jhipsterVueApp.entityUuidIdDTORel.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'EntityUuidIdDTORelCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-entity-uuid-id-dto-rel"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jhipsterVueApp.entityUuidIdDTORel.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && entityUuidIdDTORels && entityUuidIdDTORels.length === 0">
      <span v-text="t$('jhipsterVueApp.entityUuidIdDTORel.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="entityUuidIdDTORels && entityUuidIdDTORels.length > 0">
      <table class="table table-striped" aria-describedby="entityUuidIdDTORels">
        <thead>
          <tr>
            <th scope="col"><span v-text="t$('global.field.id')"></span></th>
            <th scope="col"><span v-text="t$('jhipsterVueApp.entityUuidIdDTORel.oneToOne')"></span></th>
            <th scope="col"><span v-text="t$('jhipsterVueApp.entityUuidIdDTORel.oneToOneMapsId')"></span></th>
            <th scope="col"><span v-text="t$('jhipsterVueApp.entityUuidIdDTORel.manyToOne')"></span></th>
            <th scope="col"><span v-text="t$('jhipsterVueApp.entityUuidIdDTORel.manyToOneMapsId')"></span></th>
            <th scope="col"><span v-text="t$('jhipsterVueApp.entityUuidIdDTORel.manyToMany')"></span></th>
            <th scope="col"><span v-text="t$('jhipsterVueApp.entityUuidIdDTORel.manyToManyMapsId')"></span></th>
            <th scope="col"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="entityUuidIdDTORel in entityUuidIdDTORels" :key="entityUuidIdDTORel.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'EntityUuidIdDTORelView', params: { entityUuidIdDTORelId: entityUuidIdDTORel.id } }">{{
                entityUuidIdDTORel.id
              }}</router-link>
            </td>
            <td>
              <div v-if="entityUuidIdDTORel.oneToOne">
                <router-link :to="{ name: 'EntityUuidIdDTOView', params: { entityUuidIdDTOId: entityUuidIdDTORel.oneToOne.id } }">{{
                  entityUuidIdDTORel.oneToOne.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="entityUuidIdDTORel.oneToOneMapsId">
                <router-link
                  :to="{ name: 'EntityUuidIdDTOMapsIdView', params: { entityUuidIdDTOMapsIdId: entityUuidIdDTORel.oneToOneMapsId.id } }"
                  >{{ entityUuidIdDTORel.oneToOneMapsId.id }}</router-link
                >
              </div>
            </td>
            <td>
              <div v-if="entityUuidIdDTORel.manyToOne">
                <router-link :to="{ name: 'EntityUuidIdDTOView', params: { entityUuidIdDTOId: entityUuidIdDTORel.manyToOne.id } }">{{
                  entityUuidIdDTORel.manyToOne.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="entityUuidIdDTORel.manyToOneMapsId">
                <router-link
                  :to="{ name: 'EntityUuidIdDTOMapsIdView', params: { entityUuidIdDTOMapsIdId: entityUuidIdDTORel.manyToOneMapsId.id } }"
                  >{{ entityUuidIdDTORel.manyToOneMapsId.id }}</router-link
                >
              </div>
            </td>
            <td>
              <span v-for="(manyToMany, i) in entityUuidIdDTORel.manyToManies" :key="manyToMany.id"
                >{{ i > 0 ? ', ' : '' }}
                <router-link
                  class="form-control-static"
                  :to="{ name: 'EntityUuidIdDTOView', params: { entityUuidIdDTOId: manyToMany.id } }"
                  >{{ manyToMany.id }}</router-link
                >
              </span>
            </td>
            <td>
              <span v-for="(manyToManyMapsId, i) in entityUuidIdDTORel.manyToManyMapsIds" :key="manyToManyMapsId.id"
                >{{ i > 0 ? ', ' : '' }}
                <router-link
                  class="form-control-static"
                  :to="{ name: 'EntityUuidIdDTOMapsIdView', params: { entityUuidIdDTOMapsIdId: manyToManyMapsId.id } }"
                  >{{ manyToManyMapsId.id }}</router-link
                >
              </span>
            </td>
            <td class="text-end">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'EntityUuidIdDTORelView', params: { entityUuidIdDTORelId: entityUuidIdDTORel.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'EntityUuidIdDTORelEdit', params: { entityUuidIdDTORelId: entityUuidIdDTORel.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  @click="prepareRemove(entityUuidIdDTORel)"
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
          id="jhipsterVueApp.entityUuidIdDTORel.delete.question"
          data-cy="entityUuidIdDTORelDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p
          id="jhi-delete-entityUuidIdDTORel-heading"
          v-text="t$('jhipsterVueApp.entityUuidIdDTORel.delete.question', { id: removeId })"
        ></p>
      </div>
      <template #footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" @click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-entityUuidIdDTORel"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            @click="removeEntityUuidIdDTORel()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./entity-uuid-id-dto-rel.component.ts"></script>
