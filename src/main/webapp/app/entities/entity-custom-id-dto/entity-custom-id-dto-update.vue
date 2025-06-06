<template>
  <div class="d-flex justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate @submit.prevent="save()">
        <h2
          id="jhipsterVueApp.entityCustomIdDTO.home.createOrEditLabel"
          data-cy="EntityCustomIdDTOCreateUpdateHeading"
          v-text="t$('jhipsterVueApp.entityCustomIdDTO.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="entityCustomIdDTO.customId">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="customId" v-model="entityCustomIdDTO.customId" readonly />
          </div>
          <div class="form-group">
            <label v-text="t$('jhipsterVueApp.entityCustomIdDTO.manyToManyBack')" for="entity-custom-id-dto-manyToManyBack"></label>
            <select
              class="form-control"
              id="entity-custom-id-dto-manyToManyBacks"
              data-cy="manyToManyBack"
              multiple
              name="manyToManyBack"
              v-if="entityCustomIdDTO.manyToManyBacks !== undefined"
              v-model="entityCustomIdDTO.manyToManyBacks"
            >
              <option
                :value="getSelected(entityCustomIdDTO.manyToManyBacks, entityCustomIdDTORelOption, 'relatedId')"
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
<script lang="ts" src="./entity-custom-id-dto-update.component.ts"></script>
