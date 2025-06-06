<template>
  <div class="d-flex justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate @submit.prevent="save()">
        <h2
          id="jhipsterVueApp.entityUuidIdDTO.home.createOrEditLabel"
          data-cy="EntityUuidIdDTOCreateUpdateHeading"
          v-text="t$('jhipsterVueApp.entityUuidIdDTO.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="entityUuidIdDTO.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="entityUuidIdDTO.id" readonly />
          </div>
          <div class="form-group">
            <label v-text="t$('jhipsterVueApp.entityUuidIdDTO.manyToManyBack')" for="entity-uuid-id-dto-manyToManyBack"></label>
            <select
              class="form-control"
              id="entity-uuid-id-dto-manyToManyBacks"
              data-cy="manyToManyBack"
              multiple
              name="manyToManyBack"
              v-if="entityUuidIdDTO.manyToManyBacks !== undefined"
              v-model="entityUuidIdDTO.manyToManyBacks"
            >
              <option
                :value="getSelected(entityUuidIdDTO.manyToManyBacks, entityUuidIdDTORelOption, 'id')"
                v-for="entityUuidIdDTORelOption in entityUuidIdDTORels"
                :key="entityUuidIdDTORelOption.id"
              >
                {{ entityUuidIdDTORelOption.id }}
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
<script lang="ts" src="./entity-uuid-id-dto-update.component.ts"></script>
