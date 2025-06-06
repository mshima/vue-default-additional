<template>
  <div class="d-flex justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate @submit.prevent="save()">
        <h2
          id="jhipsterVueApp.entityCustomIdDTOMapsId.home.createOrEditLabel"
          data-cy="EntityCustomIdDTOMapsIdCreateUpdateHeading"
          v-text="t$('jhipsterVueApp.entityCustomIdDTOMapsId.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="entityCustomIdDTOMapsId.customId">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="customId" v-model="entityCustomIdDTOMapsId.customId" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jhipsterVueApp.entityCustomIdDTOMapsId.entityCustomIdDTO')"
              for="entity-custom-id-dto-maps-id-entityCustomIdDTO"
            ></label>
            <select
              class="form-control"
              id="entity-custom-id-dto-maps-id-entityCustomIdDTO"
              data-cy="entityCustomIdDTO"
              name="entityCustomIdDTO"
              v-model="entityCustomIdDTOMapsId.entityCustomIdDTO"
            >
              <option :value="null"></option>
              <option
                :value="
                  entityCustomIdDTOMapsId.entityCustomIdDTO &&
                  entityCustomIdDTOOption.customId === entityCustomIdDTOMapsId.entityCustomIdDTO.customId
                    ? entityCustomIdDTOMapsId.entityCustomIdDTO
                    : entityCustomIdDTOOption
                "
                v-for="entityCustomIdDTOOption in entityCustomIdDTOS"
                :key="entityCustomIdDTOOption.customId"
              >
                {{ entityCustomIdDTOOption.customId }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label
              v-text="t$('jhipsterVueApp.entityCustomIdDTOMapsId.manyToManyMapsIdBack')"
              for="entity-custom-id-dto-maps-id-manyToManyMapsIdBack"
            ></label>
            <select
              class="form-control"
              id="entity-custom-id-dto-maps-id-manyToManyMapsIdBacks"
              data-cy="manyToManyMapsIdBack"
              multiple
              name="manyToManyMapsIdBack"
              v-if="entityCustomIdDTOMapsId.manyToManyMapsIdBacks !== undefined"
              v-model="entityCustomIdDTOMapsId.manyToManyMapsIdBacks"
            >
              <option
                :value="getSelected(entityCustomIdDTOMapsId.manyToManyMapsIdBacks, entityCustomIdDTORelOption, 'relatedId')"
                v-for="entityCustomIdDTORelOption in entityCustomIdDTORels"
                :key="entityCustomIdDTORelOption.relatedId"
              >
                {{ entityCustomIdDTORelOption.relatedId }}
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
<script lang="ts" src="./entity-custom-id-dto-maps-id-update.component.ts"></script>
