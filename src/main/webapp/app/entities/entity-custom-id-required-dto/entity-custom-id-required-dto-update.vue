<template>
  <div class="d-flex justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate @submit.prevent="save()">
        <h2
          id="jhipsterVueApp.entityCustomIdRequiredDTO.home.createOrEditLabel"
          data-cy="EntityCustomIdRequiredDTOCreateUpdateHeading"
          v-text="t$('jhipsterVueApp.entityCustomIdRequiredDTO.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="entityCustomIdRequiredDTO.customId">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="customId" v-model="entityCustomIdRequiredDTO.customId" readonly />
          </div>
          <div class="form-group">
            <label
              v-text="t$('jhipsterVueApp.entityCustomIdRequiredDTO.manyToManyBack')"
              for="entity-custom-id-required-dto-manyToManyBack"
            ></label>
            <select
              class="form-control"
              id="entity-custom-id-required-dto-manyToManyBacks"
              data-cy="manyToManyBack"
              multiple
              name="manyToManyBack"
              v-if="entityCustomIdRequiredDTO.manyToManyBacks !== undefined"
              v-model="entityCustomIdRequiredDTO.manyToManyBacks"
            >
              <option
                :value="getSelected(entityCustomIdRequiredDTO.manyToManyBacks, entityCustomIdRequiredDTORelOption, 'relatedId')"
                v-for="entityCustomIdRequiredDTORelOption in entityCustomIdRequiredDTORels"
                :key="entityCustomIdRequiredDTORelOption.relatedId"
              >
                {{ entityCustomIdRequiredDTORelOption.relatedId }}
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
<script lang="ts" src="./entity-custom-id-required-dto-update.component.ts"></script>
