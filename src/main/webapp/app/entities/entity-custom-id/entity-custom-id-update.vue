<template>
  <div class="d-flex justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate @submit.prevent="save()">
        <h2
          id="jhipsterVueApp.entityCustomId.home.createOrEditLabel"
          data-cy="EntityCustomIdCreateUpdateHeading"
          v-text="t$('jhipsterVueApp.entityCustomId.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="entityCustomId.customId">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="customId" v-model="entityCustomId.customId" readonly />
          </div>
          <div class="form-group">
            <label v-text="t$('jhipsterVueApp.entityCustomId.manyToManyBack')" for="entity-custom-id-manyToManyBack"></label>
            <select
              class="form-control"
              id="entity-custom-id-manyToManyBacks"
              data-cy="manyToManyBack"
              multiple
              name="manyToManyBack"
              v-if="entityCustomId.manyToManyBacks !== undefined"
              v-model="entityCustomId.manyToManyBacks"
            >
              <option
                :value="getSelected(entityCustomId.manyToManyBacks, entityCustomIdRelationshipOption, 'relatedId')"
                v-for="entityCustomIdRelationshipOption in entityCustomIdRelationships"
                :key="entityCustomIdRelationshipOption.relatedId"
              >
                {{ entityCustomIdRelationshipOption.relatedId }}
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
<script lang="ts" src="./entity-custom-id-update.component.ts"></script>
