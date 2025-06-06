<template>
  <div class="d-flex justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate @submit.prevent="save()">
        <h2
          id="jhipsterVueApp.entityCustomIdRequiredDTOMapsId.home.createOrEditLabel"
          data-cy="EntityCustomIdRequiredDTOMapsIdCreateUpdateHeading"
          v-text="t$('jhipsterVueApp.entityCustomIdRequiredDTOMapsId.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="entityCustomIdRequiredDTOMapsId.customId">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="customId" v-model="entityCustomIdRequiredDTOMapsId.customId" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jhipsterVueApp.entityCustomIdRequiredDTOMapsId.entityCustomIdRequiredDTO')"
              for="entity-custom-id-required-dto-maps-id-entityCustomIdRequiredDTO"
            ></label>
            <select
              class="form-control"
              id="entity-custom-id-required-dto-maps-id-entityCustomIdRequiredDTO"
              data-cy="entityCustomIdRequiredDTO"
              name="entityCustomIdRequiredDTO"
              v-model="entityCustomIdRequiredDTOMapsId.entityCustomIdRequiredDTO"
            >
              <option :value="null"></option>
              <option
                :value="
                  entityCustomIdRequiredDTOMapsId.entityCustomIdRequiredDTO &&
                  entityCustomIdRequiredDTOOption.customId === entityCustomIdRequiredDTOMapsId.entityCustomIdRequiredDTO.customId
                    ? entityCustomIdRequiredDTOMapsId.entityCustomIdRequiredDTO
                    : entityCustomIdRequiredDTOOption
                "
                v-for="entityCustomIdRequiredDTOOption in entityCustomIdRequiredDTOS"
                :key="entityCustomIdRequiredDTOOption.customId"
              >
                {{ entityCustomIdRequiredDTOOption.customId }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label
              v-text="t$('jhipsterVueApp.entityCustomIdRequiredDTOMapsId.manyToManyMapsIdBack')"
              for="entity-custom-id-required-dto-maps-id-manyToManyMapsIdBack"
            ></label>
            <select
              class="form-control"
              id="entity-custom-id-required-dto-maps-id-manyToManyMapsIdBacks"
              data-cy="manyToManyMapsIdBack"
              multiple
              name="manyToManyMapsIdBack"
              v-if="entityCustomIdRequiredDTOMapsId.manyToManyMapsIdBacks !== undefined"
              v-model="entityCustomIdRequiredDTOMapsId.manyToManyMapsIdBacks"
            >
              <option
                :value="getSelected(entityCustomIdRequiredDTOMapsId.manyToManyMapsIdBacks, entityCustomIdRequiredDTORelOption, 'relatedId')"
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
<script lang="ts" src="./entity-custom-id-required-dto-maps-id-update.component.ts"></script>
