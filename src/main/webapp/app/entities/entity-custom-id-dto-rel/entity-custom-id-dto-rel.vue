<template>
  <div>
    <h2 id="page-heading" data-cy="EntityCustomIdDTORelHeading">
      <span v-text="t$('jhipsterVueApp.entityCustomIdDTORel.home.title')" id="entity-custom-id-dto-rel-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info me-2" @click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jhipsterVueApp.entityCustomIdDTORel.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'EntityCustomIdDTORelCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-entity-custom-id-dto-rel"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jhipsterVueApp.entityCustomIdDTORel.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && entityCustomIdDTORels && entityCustomIdDTORels.length === 0">
      <span v-text="t$('jhipsterVueApp.entityCustomIdDTORel.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="entityCustomIdDTORels && entityCustomIdDTORels.length > 0">
      <table class="table table-striped" aria-describedby="entityCustomIdDTORels">
        <thead>
          <tr>
            <th scope="col"><span v-text="t$('global.field.id')"></span></th>
            <th scope="col"><span v-text="t$('jhipsterVueApp.entityCustomIdDTORel.oneToOne')"></span></th>
            <th scope="col"><span v-text="t$('jhipsterVueApp.entityCustomIdDTORel.oneToOneMapsId')"></span></th>
            <th scope="col"><span v-text="t$('jhipsterVueApp.entityCustomIdDTORel.manyToOne')"></span></th>
            <th scope="col"><span v-text="t$('jhipsterVueApp.entityCustomIdDTORel.manyToOneMapsId')"></span></th>
            <th scope="col"><span v-text="t$('jhipsterVueApp.entityCustomIdDTORel.manyToMany')"></span></th>
            <th scope="col"><span v-text="t$('jhipsterVueApp.entityCustomIdDTORel.manyToManyMapsId')"></span></th>
            <th scope="col"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="entityCustomIdDTORel in entityCustomIdDTORels" :key="entityCustomIdDTORel.relatedId" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'EntityCustomIdDTORelView', params: { entityCustomIdDTORelId: entityCustomIdDTORel.relatedId } }">{{
                entityCustomIdDTORel.relatedId
              }}</router-link>
            </td>
            <td>
              <div v-if="entityCustomIdDTORel.oneToOne">
                <router-link
                  :to="{ name: 'EntityCustomIdDTOView', params: { entityCustomIdDTOId: entityCustomIdDTORel.oneToOne.customId } }"
                  >{{ entityCustomIdDTORel.oneToOne.customId }}</router-link
                >
              </div>
            </td>
            <td>
              <div v-if="entityCustomIdDTORel.oneToOneMapsId">
                <router-link
                  :to="{
                    name: 'EntityCustomIdDTOMapsIdView',
                    params: { entityCustomIdDTOMapsIdId: entityCustomIdDTORel.oneToOneMapsId.customId },
                  }"
                  >{{ entityCustomIdDTORel.oneToOneMapsId.customId }}</router-link
                >
              </div>
            </td>
            <td>
              <div v-if="entityCustomIdDTORel.manyToOne">
                <router-link
                  :to="{ name: 'EntityCustomIdDTOView', params: { entityCustomIdDTOId: entityCustomIdDTORel.manyToOne.customId } }"
                  >{{ entityCustomIdDTORel.manyToOne.customId }}</router-link
                >
              </div>
            </td>
            <td>
              <div v-if="entityCustomIdDTORel.manyToOneMapsId">
                <router-link
                  :to="{
                    name: 'EntityCustomIdDTOMapsIdView',
                    params: { entityCustomIdDTOMapsIdId: entityCustomIdDTORel.manyToOneMapsId.customId },
                  }"
                  >{{ entityCustomIdDTORel.manyToOneMapsId.customId }}</router-link
                >
              </div>
            </td>
            <td>
              <span v-for="(manyToMany, i) in entityCustomIdDTORel.manyToManies" :key="manyToMany.relatedId"
                >{{ i > 0 ? ', ' : '' }}
                <router-link
                  class="form-control-static"
                  :to="{ name: 'EntityCustomIdDTOView', params: { entityCustomIdDTOId: manyToMany.customId } }"
                  >{{ manyToMany.customId }}</router-link
                >
              </span>
            </td>
            <td>
              <span v-for="(manyToManyMapsId, i) in entityCustomIdDTORel.manyToManyMapsIds" :key="manyToManyMapsId.relatedId"
                >{{ i > 0 ? ', ' : '' }}
                <router-link
                  class="form-control-static"
                  :to="{ name: 'EntityCustomIdDTOMapsIdView', params: { entityCustomIdDTOMapsIdId: manyToManyMapsId.customId } }"
                  >{{ manyToManyMapsId.customId }}</router-link
                >
              </span>
            </td>
            <td class="text-end">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'EntityCustomIdDTORelView', params: { entityCustomIdDTORelId: entityCustomIdDTORel.relatedId } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'EntityCustomIdDTORelEdit', params: { entityCustomIdDTORelId: entityCustomIdDTORel.relatedId } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  @click="prepareRemove(entityCustomIdDTORel)"
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
          id="jhipsterVueApp.entityCustomIdDTORel.delete.question"
          data-cy="entityCustomIdDTORelDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p
          id="jhi-delete-entityCustomIdDTORel-heading"
          v-text="t$('jhipsterVueApp.entityCustomIdDTORel.delete.question', { id: removeId })"
        ></p>
      </div>
      <template #footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" @click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-entityCustomIdDTORel"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            @click="removeEntityCustomIdDTORel()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./entity-custom-id-dto-rel.component.ts"></script>
