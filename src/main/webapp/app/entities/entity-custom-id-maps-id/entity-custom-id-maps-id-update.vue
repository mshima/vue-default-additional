<template>
  <div class="d-flex justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate @submit.prevent="save()">
        <h2
          id="jhipsterVueApp.entityCustomIdMapsId.home.createOrEditLabel"
          data-cy="EntityCustomIdMapsIdCreateUpdateHeading"
          v-text="t$('jhipsterVueApp.entityCustomIdMapsId.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="entityCustomIdMapsId.customId">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="customId" v-model="entityCustomIdMapsId.customId" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jhipsterVueApp.entityCustomIdMapsId.entityCustomId')"
              for="entity-custom-id-maps-id-entityCustomId"
            ></label>
            <select
              class="form-control"
              id="entity-custom-id-maps-id-entityCustomId"
              data-cy="entityCustomId"
              name="entityCustomId"
              v-model="entityCustomIdMapsId.entityCustomId"
            >
              <option :value="null"></option>
              <option
                :value="
                  entityCustomIdMapsId.entityCustomId && entityCustomIdOption.customId === entityCustomIdMapsId.entityCustomId.customId
                    ? entityCustomIdMapsId.entityCustomId
                    : entityCustomIdOption
                "
                v-for="entityCustomIdOption in entityCustomIds"
                :key="entityCustomIdOption.customId"
              >
                {{ entityCustomIdOption.customId }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label
              v-text="t$('jhipsterVueApp.entityCustomIdMapsId.manyToManyMapsIdBack')"
              for="entity-custom-id-maps-id-manyToManyMapsIdBack"
            ></label>
            <select
              class="form-control"
              id="entity-custom-id-maps-id-manyToManyMapsIdBacks"
              data-cy="manyToManyMapsIdBack"
              multiple
              name="manyToManyMapsIdBack"
              v-if="entityCustomIdMapsId.manyToManyMapsIdBacks !== undefined"
              v-model="entityCustomIdMapsId.manyToManyMapsIdBacks"
            >
              <option
                :value="getSelected(entityCustomIdMapsId.manyToManyMapsIdBacks, entityCustomIdRelationshipOption, 'relatedId')"
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
<script lang="ts" src="./entity-custom-id-maps-id-update.component.ts"></script>
