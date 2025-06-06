<template>
  <div>
    <h2 id="page-heading" data-cy="EntityCustomIdRelationshipHeading">
      <span v-text="t$('jhipsterVueApp.entityCustomIdRelationship.home.title')" id="entity-custom-id-relationship-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info me-2" @click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jhipsterVueApp.entityCustomIdRelationship.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'EntityCustomIdRelationshipCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-entity-custom-id-relationship"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jhipsterVueApp.entityCustomIdRelationship.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && entityCustomIdRelationships && entityCustomIdRelationships.length === 0">
      <span v-text="t$('jhipsterVueApp.entityCustomIdRelationship.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="entityCustomIdRelationships && entityCustomIdRelationships.length > 0">
      <table class="table table-striped" aria-describedby="entityCustomIdRelationships">
        <thead>
          <tr>
            <th scope="col"><span v-text="t$('global.field.id')"></span></th>
            <th scope="col"><span v-text="t$('jhipsterVueApp.entityCustomIdRelationship.oneToOne')"></span></th>
            <th scope="col"><span v-text="t$('jhipsterVueApp.entityCustomIdRelationship.oneToOneMapsId')"></span></th>
            <th scope="col"><span v-text="t$('jhipsterVueApp.entityCustomIdRelationship.manyToOne')"></span></th>
            <th scope="col"><span v-text="t$('jhipsterVueApp.entityCustomIdRelationship.manyToOneMapsId')"></span></th>
            <th scope="col"><span v-text="t$('jhipsterVueApp.entityCustomIdRelationship.manyToMany')"></span></th>
            <th scope="col"><span v-text="t$('jhipsterVueApp.entityCustomIdRelationship.manyToManyMapsId')"></span></th>
            <th scope="col"></th>
          </tr>
        </thead>
        <tbody>
          <tr
            v-for="entityCustomIdRelationship in entityCustomIdRelationships"
            :key="entityCustomIdRelationship.relatedId"
            data-cy="entityTable"
          >
            <td>
              <router-link
                :to="{
                  name: 'EntityCustomIdRelationshipView',
                  params: { entityCustomIdRelationshipId: entityCustomIdRelationship.relatedId },
                }"
                >{{ entityCustomIdRelationship.relatedId }}</router-link
              >
            </td>
            <td>
              <div v-if="entityCustomIdRelationship.oneToOne">
                <router-link
                  :to="{ name: 'EntityCustomIdView', params: { entityCustomIdId: entityCustomIdRelationship.oneToOne.customId } }"
                  >{{ entityCustomIdRelationship.oneToOne.customId }}</router-link
                >
              </div>
            </td>
            <td>
              <div v-if="entityCustomIdRelationship.oneToOneMapsId">
                <router-link
                  :to="{
                    name: 'EntityCustomIdMapsIdView',
                    params: { entityCustomIdMapsIdId: entityCustomIdRelationship.oneToOneMapsId.customId },
                  }"
                  >{{ entityCustomIdRelationship.oneToOneMapsId.customId }}</router-link
                >
              </div>
            </td>
            <td>
              <div v-if="entityCustomIdRelationship.manyToOne">
                <router-link
                  :to="{ name: 'EntityCustomIdView', params: { entityCustomIdId: entityCustomIdRelationship.manyToOne.customId } }"
                  >{{ entityCustomIdRelationship.manyToOne.customId }}</router-link
                >
              </div>
            </td>
            <td>
              <div v-if="entityCustomIdRelationship.manyToOneMapsId">
                <router-link
                  :to="{
                    name: 'EntityCustomIdMapsIdView',
                    params: { entityCustomIdMapsIdId: entityCustomIdRelationship.manyToOneMapsId.customId },
                  }"
                  >{{ entityCustomIdRelationship.manyToOneMapsId.customId }}</router-link
                >
              </div>
            </td>
            <td>
              <span v-for="(manyToMany, i) in entityCustomIdRelationship.manyToManies" :key="manyToMany.relatedId"
                >{{ i > 0 ? ', ' : '' }}
                <router-link
                  class="form-control-static"
                  :to="{ name: 'EntityCustomIdView', params: { entityCustomIdId: manyToMany.customId } }"
                  >{{ manyToMany.customId }}</router-link
                >
              </span>
            </td>
            <td>
              <span v-for="(manyToManyMapsId, i) in entityCustomIdRelationship.manyToManyMapsIds" :key="manyToManyMapsId.relatedId"
                >{{ i > 0 ? ', ' : '' }}
                <router-link
                  class="form-control-static"
                  :to="{ name: 'EntityCustomIdMapsIdView', params: { entityCustomIdMapsIdId: manyToManyMapsId.customId } }"
                  >{{ manyToManyMapsId.customId }}</router-link
                >
              </span>
            </td>
            <td class="text-end">
              <div class="btn-group">
                <router-link
                  :to="{
                    name: 'EntityCustomIdRelationshipView',
                    params: { entityCustomIdRelationshipId: entityCustomIdRelationship.relatedId },
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
                    name: 'EntityCustomIdRelationshipEdit',
                    params: { entityCustomIdRelationshipId: entityCustomIdRelationship.relatedId },
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
                  @click="prepareRemove(entityCustomIdRelationship)"
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
          id="jhipsterVueApp.entityCustomIdRelationship.delete.question"
          data-cy="entityCustomIdRelationshipDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p
          id="jhi-delete-entityCustomIdRelationship-heading"
          v-text="t$('jhipsterVueApp.entityCustomIdRelationship.delete.question', { id: removeId })"
        ></p>
      </div>
      <template #footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" @click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-entityCustomIdRelationship"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            @click="removeEntityCustomIdRelationship()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./entity-custom-id-relationship.component.ts"></script>
