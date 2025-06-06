<template>
  <div class="d-flex justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate @submit.prevent="save()">
        <h2
          id="jhipsterVueApp.entityUuidIdMapsId.home.createOrEditLabel"
          data-cy="EntityUuidIdMapsIdCreateUpdateHeading"
          v-text="t$('jhipsterVueApp.entityUuidIdMapsId.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="entityUuidIdMapsId.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="entityUuidIdMapsId.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jhipsterVueApp.entityUuidIdMapsId.entityUuidId')"
              for="entity-uuid-id-maps-id-entityUuidId"
            ></label>
            <select
              class="form-control"
              id="entity-uuid-id-maps-id-entityUuidId"
              data-cy="entityUuidId"
              name="entityUuidId"
              v-model="entityUuidIdMapsId.entityUuidId"
            >
              <option :value="null"></option>
              <option
                :value="
                  entityUuidIdMapsId.entityUuidId && entityUuidIdOption.id === entityUuidIdMapsId.entityUuidId.id
                    ? entityUuidIdMapsId.entityUuidId
                    : entityUuidIdOption
                "
                v-for="entityUuidIdOption in entityUuidIds"
                :key="entityUuidIdOption.id"
              >
                {{ entityUuidIdOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label
              v-text="t$('jhipsterVueApp.entityUuidIdMapsId.manyToManyMapsIdBack')"
              for="entity-uuid-id-maps-id-manyToManyMapsIdBack"
            ></label>
            <select
              class="form-control"
              id="entity-uuid-id-maps-id-manyToManyMapsIdBacks"
              data-cy="manyToManyMapsIdBack"
              multiple
              name="manyToManyMapsIdBack"
              v-if="entityUuidIdMapsId.manyToManyMapsIdBacks !== undefined"
              v-model="entityUuidIdMapsId.manyToManyMapsIdBacks"
            >
              <option
                :value="getSelected(entityUuidIdMapsId.manyToManyMapsIdBacks, entityUuidIdRelationshipOption, 'id')"
                v-for="entityUuidIdRelationshipOption in entityUuidIdRelationships"
                :key="entityUuidIdRelationshipOption.id"
              >
                {{ entityUuidIdRelationshipOption.id }}
              </option>
            </select>
          </div>
        </div>
        <div>
          <button type="button" id="cancel-save" data-cy="entityCreateCancelButton" class="btn btn-secondary" @click="previousState()">
            <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.cancel')"></span>
          </button>
          <button
            type="submit"
            id="save-entity"
            data-cy="entityCreateSaveButton"
            :disabled="v$.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.save')"></span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./entity-uuid-id-maps-id-update.component.ts"></script>
