<template>
  <div>
    <h2 id="page-heading" data-cy="UuidIdFilteringRelationshipHeading">
      <span v-text="t$('jhipsterVueApp.uuidIdFilteringRelationship.home.title')" id="uuid-id-filtering-relationship-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info me-2" @click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jhipsterVueApp.uuidIdFilteringRelationship.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'UuidIdFilteringRelationshipCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-uuid-id-filtering-relationship"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jhipsterVueApp.uuidIdFilteringRelationship.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && uuidIdFilteringRelationships && uuidIdFilteringRelationships.length === 0">
      <span v-text="t$('jhipsterVueApp.uuidIdFilteringRelationship.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="uuidIdFilteringRelationships && uuidIdFilteringRelationships.length > 0">
      <table class="table table-striped" aria-describedby="uuidIdFilteringRelationships">
        <thead>
          <tr>
            <th scope="col"><span v-text="t$('global.field.id')"></span></th>
            <th scope="col"><span v-text="t$('jhipsterVueApp.uuidIdFilteringRelationship.oneToOne')"></span></th>
            <th scope="col"><span v-text="t$('jhipsterVueApp.uuidIdFilteringRelationship.oneToOneMapsId')"></span></th>
            <th scope="col"><span v-text="t$('jhipsterVueApp.uuidIdFilteringRelationship.manyToOne')"></span></th>
            <th scope="col"><span v-text="t$('jhipsterVueApp.uuidIdFilteringRelationship.manyToOneMapsId')"></span></th>
            <th scope="col"><span v-text="t$('jhipsterVueApp.uuidIdFilteringRelationship.manyToMany')"></span></th>
            <th scope="col"><span v-text="t$('jhipsterVueApp.uuidIdFilteringRelationship.manyToManyMapsId')"></span></th>
            <th scope="col"></th>
          </tr>
        </thead>
        <tbody>
          <tr
            v-for="uuidIdFilteringRelationship in uuidIdFilteringRelationships"
            :key="uuidIdFilteringRelationship.relatedId"
            data-cy="entityTable"
          >
            <td>
              <router-link
                :to="{
                  name: 'UuidIdFilteringRelationshipView',
                  params: { uuidIdFilteringRelationshipId: uuidIdFilteringRelationship.relatedId },
                }"
                >{{ uuidIdFilteringRelationship.relatedId }}</router-link
              >
            </td>
            <td>
              <div v-if="uuidIdFilteringRelationship.oneToOne">
                <router-link
                  :to="{ name: 'UuidIdFilteringView', params: { uuidIdFilteringId: uuidIdFilteringRelationship.oneToOne.customId } }"
                  >{{ uuidIdFilteringRelationship.oneToOne.customId }}</router-link
                >
              </div>
            </td>
            <td>
              <div v-if="uuidIdFilteringRelationship.oneToOneMapsId">
                <router-link
                  :to="{
                    name: 'UuidIdFilteringMapsIdView',
                    params: { uuidIdFilteringMapsIdId: uuidIdFilteringRelationship.oneToOneMapsId.customId },
                  }"
                  >{{ uuidIdFilteringRelationship.oneToOneMapsId.customId }}</router-link
                >
              </div>
            </td>
            <td>
              <div v-if="uuidIdFilteringRelationship.manyToOne">
                <router-link
                  :to="{ name: 'UuidIdFilteringView', params: { uuidIdFilteringId: uuidIdFilteringRelationship.manyToOne.customId } }"
                  >{{ uuidIdFilteringRelationship.manyToOne.customId }}</router-link
                >
              </div>
            </td>
            <td>
              <div v-if="uuidIdFilteringRelationship.manyToOneMapsId">
                <router-link
                  :to="{
                    name: 'UuidIdFilteringMapsIdView',
                    params: { uuidIdFilteringMapsIdId: uuidIdFilteringRelationship.manyToOneMapsId.customId },
                  }"
                  >{{ uuidIdFilteringRelationship.manyToOneMapsId.customId }}</router-link
                >
              </div>
            </td>
            <td>
              <span v-for="(manyToMany, i) in uuidIdFilteringRelationship.manyToManies" :key="manyToMany.relatedId"
                >{{ i > 0 ? ', ' : '' }}
                <router-link
                  class="form-control-static"
                  :to="{ name: 'UuidIdFilteringView', params: { uuidIdFilteringId: manyToMany.customId } }"
                  >{{ manyToMany.customId }}</router-link
                >
              </span>
            </td>
            <td>
              <span v-for="(manyToManyMapsId, i) in uuidIdFilteringRelationship.manyToManyMapsIds" :key="manyToManyMapsId.relatedId"
                >{{ i > 0 ? ', ' : '' }}
                <router-link
                  class="form-control-static"
                  :to="{ name: 'UuidIdFilteringMapsIdView', params: { uuidIdFilteringMapsIdId: manyToManyMapsId.customId } }"
                  >{{ manyToManyMapsId.customId }}</router-link
                >
              </span>
            </td>
            <td class="text-end">
              <div class="btn-group">
                <router-link
                  :to="{
                    name: 'UuidIdFilteringRelationshipView',
                    params: { uuidIdFilteringRelationshipId: uuidIdFilteringRelationship.relatedId },
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
                    name: 'UuidIdFilteringRelationshipEdit',
                    params: { uuidIdFilteringRelationshipId: uuidIdFilteringRelationship.relatedId },
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
                  @click="prepareRemove(uuidIdFilteringRelationship)"
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
          id="jhipsterVueApp.uuidIdFilteringRelationship.delete.question"
          data-cy="uuidIdFilteringRelationshipDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p
          id="jhi-delete-uuidIdFilteringRelationship-heading"
          v-text="t$('jhipsterVueApp.uuidIdFilteringRelationship.delete.question', { id: removeId })"
        ></p>
      </div>
      <template #footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" @click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-uuidIdFilteringRelationship"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            @click="removeUuidIdFilteringRelationship()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./uuid-id-filtering-relationship.component.ts"></script>
