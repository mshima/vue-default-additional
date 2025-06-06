<template>
  <div class="d-flex justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate @submit.prevent="save()">
        <h2
          id="jhipsterVueApp.uuidIdFilteringMapsId.home.createOrEditLabel"
          data-cy="UuidIdFilteringMapsIdCreateUpdateHeading"
          v-text="t$('jhipsterVueApp.uuidIdFilteringMapsId.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="uuidIdFilteringMapsId.customId">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="customId" v-model="uuidIdFilteringMapsId.customId" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jhipsterVueApp.uuidIdFilteringMapsId.uuidIdFiltering')"
              for="uuid-id-filtering-maps-id-uuidIdFiltering"
            ></label>
            <select
              class="form-control"
              id="uuid-id-filtering-maps-id-uuidIdFiltering"
              data-cy="uuidIdFiltering"
              name="uuidIdFiltering"
              v-model="uuidIdFilteringMapsId.uuidIdFiltering"
            >
              <option :value="null"></option>
              <option
                :value="
                  uuidIdFilteringMapsId.uuidIdFiltering && uuidIdFilteringOption.customId === uuidIdFilteringMapsId.uuidIdFiltering.customId
                    ? uuidIdFilteringMapsId.uuidIdFiltering
                    : uuidIdFilteringOption
                "
                v-for="uuidIdFilteringOption in uuidIdFilterings"
                :key="uuidIdFilteringOption.customId"
              >
                {{ uuidIdFilteringOption.customId }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label
              v-text="t$('jhipsterVueApp.uuidIdFilteringMapsId.manyToManyMapsIdBack')"
              for="uuid-id-filtering-maps-id-manyToManyMapsIdBack"
            ></label>
            <select
              class="form-control"
              id="uuid-id-filtering-maps-id-manyToManyMapsIdBacks"
              data-cy="manyToManyMapsIdBack"
              multiple
              name="manyToManyMapsIdBack"
              v-if="uuidIdFilteringMapsId.manyToManyMapsIdBacks !== undefined"
              v-model="uuidIdFilteringMapsId.manyToManyMapsIdBacks"
            >
              <option
                :value="getSelected(uuidIdFilteringMapsId.manyToManyMapsIdBacks, uuidIdFilteringRelationshipOption, 'relatedId')"
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
<script lang="ts" src="./uuid-id-filtering-maps-id-update.component.ts"></script>
