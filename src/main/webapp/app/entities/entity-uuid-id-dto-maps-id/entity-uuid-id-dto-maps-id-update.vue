<template>
  <div class="d-flex justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate @submit.prevent="save()">
        <h2
          id="jhipsterVueApp.entityUuidIdDTOMapsId.home.createOrEditLabel"
          data-cy="EntityUuidIdDTOMapsIdCreateUpdateHeading"
          v-text="t$('jhipsterVueApp.entityUuidIdDTOMapsId.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="entityUuidIdDTOMapsId.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="entityUuidIdDTOMapsId.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jhipsterVueApp.entityUuidIdDTOMapsId.entityUuidIdDTO')"
              for="entity-uuid-id-dto-maps-id-entityUuidIdDTO"
            ></label>
            <select
              class="form-control"
              id="entity-uuid-id-dto-maps-id-entityUuidIdDTO"
              data-cy="entityUuidIdDTO"
              name="entityUuidIdDTO"
              v-model="entityUuidIdDTOMapsId.entityUuidIdDTO"
            >
              <option :value="null"></option>
              <option
                :value="
                  entityUuidIdDTOMapsId.entityUuidIdDTO && entityUuidIdDTOOption.id === entityUuidIdDTOMapsId.entityUuidIdDTO.id
                    ? entityUuidIdDTOMapsId.entityUuidIdDTO
                    : entityUuidIdDTOOption
                "
                v-for="entityUuidIdDTOOption in entityUuidIdDTOS"
                :key="entityUuidIdDTOOption.id"
              >
                {{ entityUuidIdDTOOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label
              v-text="t$('jhipsterVueApp.entityUuidIdDTOMapsId.manyToManyMapsIdBack')"
              for="entity-uuid-id-dto-maps-id-manyToManyMapsIdBack"
            ></label>
            <select
              class="form-control"
              id="entity-uuid-id-dto-maps-id-manyToManyMapsIdBacks"
              data-cy="manyToManyMapsIdBack"
              multiple
              name="manyToManyMapsIdBack"
              v-if="entityUuidIdDTOMapsId.manyToManyMapsIdBacks !== undefined"
              v-model="entityUuidIdDTOMapsId.manyToManyMapsIdBacks"
            >
              <option
                :value="getSelected(entityUuidIdDTOMapsId.manyToManyMapsIdBacks, entityUuidIdDTORelOption, 'id')"
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
<script lang="ts" src="./entity-uuid-id-dto-maps-id-update.component.ts"></script>
