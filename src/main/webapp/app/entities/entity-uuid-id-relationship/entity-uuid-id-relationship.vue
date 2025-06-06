<template>
  <div>
    <h2 id="page-heading" data-cy="EntityUuidIdRelationshipHeading">
      <span v-text="t$('jhipsterVueApp.entityUuidIdRelationship.home.title')" id="entity-uuid-id-relationship-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info me-2" @click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jhipsterVueApp.entityUuidIdRelationship.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'EntityUuidIdRelationshipCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-entity-uuid-id-relationship"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jhipsterVueApp.entityUuidIdRelationship.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && entityUuidIdRelationships && entityUuidIdRelationships.length === 0">
      <span v-text="t$('jhipsterVueApp.entityUuidIdRelationship.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="entityUuidIdRelationships && entityUuidIdRelationships.length > 0">
      <table class="table table-striped" aria-describedby="entityUuidIdRelationships">
        <thead>
          <tr>
            <th scope="col"><span v-text="t$('global.field.id')"></span></th>
            <th scope="col"><span v-text="t$('jhipsterVueApp.entityUuidIdRelationship.oneToOne')"></span></th>
            <th scope="col"><span v-text="t$('jhipsterVueApp.entityUuidIdRelationship.oneToOneMapsId')"></span></th>
            <th scope="col"><span v-text="t$('jhipsterVueApp.entityUuidIdRelationship.manyToOne')"></span></th>
            <th scope="col"><span v-text="t$('jhipsterVueApp.entityUuidIdRelationship.manyToOneMapsId')"></span></th>
            <th scope="col"><span v-text="t$('jhipsterVueApp.entityUuidIdRelationship.manyToMany')"></span></th>
            <th scope="col"><span v-text="t$('jhipsterVueApp.entityUuidIdRelationship.manyToManyMapsId')"></span></th>
            <th scope="col"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="entityUuidIdRelationship in entityUuidIdRelationships" :key="entityUuidIdRelationship.id" data-cy="entityTable">
            <td>
              <router-link
                :to="{ name: 'EntityUuidIdRelationshipView', params: { entityUuidIdRelationshipId: entityUuidIdRelationship.id } }"
                >{{ entityUuidIdRelationship.id }}</router-link
              >
            </td>
            <td>
              <div v-if="entityUuidIdRelationship.oneToOne">
                <router-link :to="{ name: 'EntityUuidIdView', params: { entityUuidIdId: entityUuidIdRelationship.oneToOne.id } }">{{
                  entityUuidIdRelationship.oneToOne.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="entityUuidIdRelationship.oneToOneMapsId">
                <router-link
                  :to="{ name: 'EntityUuidIdMapsIdView', params: { entityUuidIdMapsIdId: entityUuidIdRelationship.oneToOneMapsId.id } }"
                  >{{ entityUuidIdRelationship.oneToOneMapsId.id }}</router-link
                >
              </div>
            </td>
            <td>
              <div v-if="entityUuidIdRelationship.manyToOne">
                <router-link :to="{ name: 'EntityUuidIdView', params: { entityUuidIdId: entityUuidIdRelationship.manyToOne.id } }">{{
                  entityUuidIdRelationship.manyToOne.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="entityUuidIdRelationship.manyToOneMapsId">
                <router-link
                  :to="{ name: 'EntityUuidIdMapsIdView', params: { entityUuidIdMapsIdId: entityUuidIdRelationship.manyToOneMapsId.id } }"
                  >{{ entityUuidIdRelationship.manyToOneMapsId.id }}</router-link
                >
              </div>
            </td>
            <td>
              <span v-for="(manyToMany, i) in entityUuidIdRelationship.manyToManies" :key="manyToMany.id"
                >{{ i > 0 ? ', ' : '' }}
                <router-link class="form-control-static" :to="{ name: 'EntityUuidIdView', params: { entityUuidIdId: manyToMany.id } }">{{
                  manyToMany.id
                }}</router-link>
              </span>
            </td>
            <td>
              <span v-for="(manyToManyMapsId, i) in entityUuidIdRelationship.manyToManyMapsIds" :key="manyToManyMapsId.id"
                >{{ i > 0 ? ', ' : '' }}
                <router-link
                  class="form-control-static"
                  :to="{ name: 'EntityUuidIdMapsIdView', params: { entityUuidIdMapsIdId: manyToManyMapsId.id } }"
                  >{{ manyToManyMapsId.id }}</router-link
                >
              </span>
            </td>
            <td class="text-end">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'EntityUuidIdRelationshipView', params: { entityUuidIdRelationshipId: entityUuidIdRelationship.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'EntityUuidIdRelationshipEdit', params: { entityUuidIdRelationshipId: entityUuidIdRelationship.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  @click="prepareRemove(entityUuidIdRelationship)"
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
          id="jhipsterVueApp.entityUuidIdRelationship.delete.question"
          data-cy="entityUuidIdRelationshipDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p
          id="jhi-delete-entityUuidIdRelationship-heading"
          v-text="t$('jhipsterVueApp.entityUuidIdRelationship.delete.question', { id: removeId })"
        ></p>
      </div>
      <template #footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" @click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-entityUuidIdRelationship"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            @click="removeEntityUuidIdRelationship()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./entity-uuid-id-relationship.component.ts"></script>
