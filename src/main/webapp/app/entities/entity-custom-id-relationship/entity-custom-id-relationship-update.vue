<template>
  <div class="d-flex justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate @submit.prevent="save()">
        <h2
          id="jhipsterVueApp.entityCustomIdRelationship.home.createOrEditLabel"
          data-cy="EntityCustomIdRelationshipCreateUpdateHeading"
          v-text="t$('jhipsterVueApp.entityCustomIdRelationship.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="entityCustomIdRelationship.relatedId">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="relatedId" v-model="entityCustomIdRelationship.relatedId" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jhipsterVueApp.entityCustomIdRelationship.oneToOne')"
              for="entity-custom-id-relationship-oneToOne"
            ></label>
            <select
              class="form-control"
              id="entity-custom-id-relationship-oneToOne"
              data-cy="oneToOne"
              name="oneToOne"
              v-model="entityCustomIdRelationship.oneToOne"
            >
              <option :value="null"></option>
              <option
                :value="
                  entityCustomIdRelationship.oneToOne && entityCustomIdOption.customId === entityCustomIdRelationship.oneToOne.customId
                    ? entityCustomIdRelationship.oneToOne
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
              class="form-control-label"
              v-text="t$('jhipsterVueApp.entityCustomIdRelationship.oneToOneMapsId')"
              for="entity-custom-id-relationship-oneToOneMapsId"
            ></label>
            <select
              class="form-control"
              id="entity-custom-id-relationship-oneToOneMapsId"
              data-cy="oneToOneMapsId"
              name="oneToOneMapsId"
              v-model="entityCustomIdRelationship.oneToOneMapsId"
            >
              <option :value="null"></option>
              <option
                :value="
                  entityCustomIdRelationship.oneToOneMapsId &&
                  entityCustomIdMapsIdOption.customId === entityCustomIdRelationship.oneToOneMapsId.customId
                    ? entityCustomIdRelationship.oneToOneMapsId
                    : entityCustomIdMapsIdOption
                "
                v-for="entityCustomIdMapsIdOption in entityCustomIdMapsIds"
                :key="entityCustomIdMapsIdOption.customId"
              >
                {{ entityCustomIdMapsIdOption.customId }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jhipsterVueApp.entityCustomIdRelationship.manyToOne')"
              for="entity-custom-id-relationship-manyToOne"
            ></label>
            <select
              class="form-control"
              id="entity-custom-id-relationship-manyToOne"
              data-cy="manyToOne"
              name="manyToOne"
              v-model="entityCustomIdRelationship.manyToOne"
            >
              <option :value="null"></option>
              <option
                :value="
                  entityCustomIdRelationship.manyToOne && entityCustomIdOption.customId === entityCustomIdRelationship.manyToOne.customId
                    ? entityCustomIdRelationship.manyToOne
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
              class="form-control-label"
              v-text="t$('jhipsterVueApp.entityCustomIdRelationship.manyToOneMapsId')"
              for="entity-custom-id-relationship-manyToOneMapsId"
            ></label>
            <select
              class="form-control"
              id="entity-custom-id-relationship-manyToOneMapsId"
              data-cy="manyToOneMapsId"
              name="manyToOneMapsId"
              v-model="entityCustomIdRelationship.manyToOneMapsId"
            >
              <option :value="null"></option>
              <option
                :value="
                  entityCustomIdRelationship.manyToOneMapsId &&
                  entityCustomIdMapsIdOption.customId === entityCustomIdRelationship.manyToOneMapsId.customId
                    ? entityCustomIdRelationship.manyToOneMapsId
                    : entityCustomIdMapsIdOption
                "
                v-for="entityCustomIdMapsIdOption in entityCustomIdMapsIds"
                :key="entityCustomIdMapsIdOption.customId"
              >
                {{ entityCustomIdMapsIdOption.customId }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label
              v-text="t$('jhipsterVueApp.entityCustomIdRelationship.manyToMany')"
              for="entity-custom-id-relationship-manyToMany"
            ></label>
            <select
              class="form-control"
              id="entity-custom-id-relationship-manyToManies"
              data-cy="manyToMany"
              multiple
              name="manyToMany"
              v-if="entityCustomIdRelationship.manyToManies !== undefined"
              v-model="entityCustomIdRelationship.manyToManies"
            >
              <option
                :value="getSelected(entityCustomIdRelationship.manyToManies, entityCustomIdOption, 'customId')"
                v-for="entityCustomIdOption in entityCustomIds"
                :key="entityCustomIdOption.customId"
              >
                {{ entityCustomIdOption.customId }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label
              v-text="t$('jhipsterVueApp.entityCustomIdRelationship.manyToManyMapsId')"
              for="entity-custom-id-relationship-manyToManyMapsId"
            ></label>
            <select
              class="form-control"
              id="entity-custom-id-relationship-manyToManyMapsIds"
              data-cy="manyToManyMapsId"
              multiple
              name="manyToManyMapsId"
              v-if="entityCustomIdRelationship.manyToManyMapsIds !== undefined"
              v-model="entityCustomIdRelationship.manyToManyMapsIds"
            >
              <option
                :value="getSelected(entityCustomIdRelationship.manyToManyMapsIds, entityCustomIdMapsIdOption, 'customId')"
                v-for="entityCustomIdMapsIdOption in entityCustomIdMapsIds"
                :key="entityCustomIdMapsIdOption.customId"
              >
                {{ entityCustomIdMapsIdOption.customId }}
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
<script lang="ts" src="./entity-custom-id-relationship-update.component.ts"></script>
