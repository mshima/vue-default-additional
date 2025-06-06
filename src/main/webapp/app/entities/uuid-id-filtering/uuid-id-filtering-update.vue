<template>
  <div class="d-flex justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate @submit.prevent="save()">
        <h2
          id="jhipsterVueApp.uuidIdFiltering.home.createOrEditLabel"
          data-cy="UuidIdFilteringCreateUpdateHeading"
          v-text="t$('jhipsterVueApp.uuidIdFiltering.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="uuidIdFiltering.customId">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="customId" v-model="uuidIdFiltering.customId" readonly />
          </div>
          <div class="form-group">
            <label v-text="t$('jhipsterVueApp.uuidIdFiltering.manyToManyBack')" for="uuid-id-filtering-manyToManyBack"></label>
            <select
              class="form-control"
              id="uuid-id-filtering-manyToManyBacks"
              data-cy="manyToManyBack"
              multiple
              name="manyToManyBack"
              v-if="uuidIdFiltering.manyToManyBacks !== undefined"
              v-model="uuidIdFiltering.manyToManyBacks"
            >
              <option
                :value="getSelected(uuidIdFiltering.manyToManyBacks, uuidIdFilteringRelationshipOption, 'relatedId')"
                v-for="uuidIdFilteringRelationshipOption in uuidIdFilteringRelationships"
                :key="uuidIdFilteringRelationshipOption.relatedId"
              >
                {{ uuidIdFilteringRelationshipOption.relatedId }}
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
<script lang="ts" src="./uuid-id-filtering-update.component.ts"></script>
