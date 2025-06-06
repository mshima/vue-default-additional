<template>
  <div>
    <h2 id="page-heading" data-cy="EntityCustomIdRequiredDTORelHeading">
      <span v-text="t$('jhipsterVueApp.entityCustomIdRequiredDTORel.home.title')" id="entity-custom-id-required-dto-rel-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info me-2" @click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jhipsterVueApp.entityCustomIdRequiredDTORel.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'EntityCustomIdRequiredDTORelCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-entity-custom-id-required-dto-rel"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jhipsterVueApp.entityCustomIdRequiredDTORel.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && entityCustomIdRequiredDTORels && entityCustomIdRequiredDTORels.length === 0">
      <span v-text="t$('jhipsterVueApp.entityCustomIdRequiredDTORel.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="entityCustomIdRequiredDTORels && entityCustomIdRequiredDTORels.length > 0">
      <table class="table table-striped" aria-describedby="entityCustomIdRequiredDTORels">
        <thead>
          <tr>
            <th scope="col"><span v-text="t$('global.field.id')"></span></th>
            <th scope="col"><span v-text="t$('jhipsterVueApp.entityCustomIdRequiredDTORel.oneToOne')"></span></th>
            <th scope="col"><span v-text="t$('jhipsterVueApp.entityCustomIdRequiredDTORel.oneToOneMapsId')"></span></th>
            <th scope="col"><span v-text="t$('jhipsterVueApp.entityCustomIdRequiredDTORel.manyToOne')"></span></th>
            <th scope="col"><span v-text="t$('jhipsterVueApp.entityCustomIdRequiredDTORel.manyToOneMapsId')"></span></th>
            <th scope="col"><span v-text="t$('jhipsterVueApp.entityCustomIdRequiredDTORel.manyToMany')"></span></th>
            <th scope="col"><span v-text="t$('jhipsterVueApp.entityCustomIdRequiredDTORel.manyToManyMapsId')"></span></th>
            <th scope="col"></th>
          </tr>
        </thead>
        <tbody>
          <tr
            v-for="entityCustomIdRequiredDTORel in entityCustomIdRequiredDTORels"
            :key="entityCustomIdRequiredDTORel.relatedId"
            data-cy="entityTable"
          >
            <td>
              <router-link
                :to="{
                  name: 'EntityCustomIdRequiredDTORelView',
                  params: { entityCustomIdRequiredDTORelId: entityCustomIdRequiredDTORel.relatedId },
                }"
                >{{ entityCustomIdRequiredDTORel.relatedId }}</router-link
              >
            </td>
            <td>
              <div v-if="entityCustomIdRequiredDTORel.oneToOne">
                <router-link
                  :to="{
                    name: 'EntityCustomIdRequiredDTOView',
                    params: { entityCustomIdRequiredDTOId: entityCustomIdRequiredDTORel.oneToOne.customId },
                  }"
                  >{{ entityCustomIdRequiredDTORel.oneToOne.customId }}</router-link
                >
              </div>
            </td>
            <td>
              <div v-if="entityCustomIdRequiredDTORel.oneToOneMapsId">
                <router-link
                  :to="{
                    name: 'EntityCustomIdRequiredDTOMapsIdView',
                    params: { entityCustomIdRequiredDTOMapsIdId: entityCustomIdRequiredDTORel.oneToOneMapsId.customId },
                  }"
                  >{{ entityCustomIdRequiredDTORel.oneToOneMapsId.customId }}</router-link
                >
              </div>
            </td>
            <td>
              <div v-if="entityCustomIdRequiredDTORel.manyToOne">
                <router-link
                  :to="{
                    name: 'EntityCustomIdRequiredDTOView',
                    params: { entityCustomIdRequiredDTOId: entityCustomIdRequiredDTORel.manyToOne.customId },
                  }"
                  >{{ entityCustomIdRequiredDTORel.manyToOne.customId }}</router-link
                >
              </div>
            </td>
            <td>
              <div v-if="entityCustomIdRequiredDTORel.manyToOneMapsId">
                <router-link
                  :to="{
                    name: 'EntityCustomIdRequiredDTOMapsIdView',
                    params: { entityCustomIdRequiredDTOMapsIdId: entityCustomIdRequiredDTORel.manyToOneMapsId.customId },
                  }"
                  >{{ entityCustomIdRequiredDTORel.manyToOneMapsId.customId }}</router-link
                >
              </div>
            </td>
            <td>
              <span v-for="(manyToMany, i) in entityCustomIdRequiredDTORel.manyToManies" :key="manyToMany.relatedId"
                >{{ i > 0 ? ', ' : '' }}
                <router-link
                  class="form-control-static"
                  :to="{ name: 'EntityCustomIdRequiredDTOView', params: { entityCustomIdRequiredDTOId: manyToMany.customId } }"
                  >{{ manyToMany.customId }}</router-link
                >
              </span>
            </td>
            <td>
              <span v-for="(manyToManyMapsId, i) in entityCustomIdRequiredDTORel.manyToManyMapsIds" :key="manyToManyMapsId.relatedId"
                >{{ i > 0 ? ', ' : '' }}
                <router-link
                  class="form-control-static"
                  :to="{
                    name: 'EntityCustomIdRequiredDTOMapsIdView',
                    params: { entityCustomIdRequiredDTOMapsIdId: manyToManyMapsId.customId },
                  }"
                  >{{ manyToManyMapsId.customId }}</router-link
                >
              </span>
            </td>
            <td class="text-end">
              <div class="btn-group">
                <router-link
                  :to="{
                    name: 'EntityCustomIdRequiredDTORelView',
                    params: { entityCustomIdRequiredDTORelId: entityCustomIdRequiredDTORel.relatedId },
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
                    name: 'EntityCustomIdRequiredDTORelEdit',
                    params: { entityCustomIdRequiredDTORelId: entityCustomIdRequiredDTORel.relatedId },
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
                  @click="prepareRemove(entityCustomIdRequiredDTORel)"
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
          id="jhipsterVueApp.entityCustomIdRequiredDTORel.delete.question"
          data-cy="entityCustomIdRequiredDTORelDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p
          id="jhi-delete-entityCustomIdRequiredDTORel-heading"
          v-text="t$('jhipsterVueApp.entityCustomIdRequiredDTORel.delete.question', { id: removeId })"
        ></p>
      </div>
      <template #footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" @click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-entityCustomIdRequiredDTORel"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            @click="removeEntityCustomIdRequiredDTORel()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./entity-custom-id-required-dto-rel.component.ts"></script>
