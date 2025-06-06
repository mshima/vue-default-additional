<template>
  <div class="d-flex justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate @submit.prevent="save()">
        <h2
          id="jhipsterVueApp.entityUuidIdRelationship.home.createOrEditLabel"
          data-cy="EntityUuidIdRelationshipCreateUpdateHeading"
          v-text="t$('jhipsterVueApp.entityUuidIdRelationship.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="entityUuidIdRelationship.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="entityUuidIdRelationship.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jhipsterVueApp.entityUuidIdRelationship.oneToOne')"
              for="entity-uuid-id-relationship-oneToOne"
            ></label>
            <select
              class="form-control"
              id="entity-uuid-id-relationship-oneToOne"
              data-cy="oneToOne"
              name="oneToOne"
              v-model="entityUuidIdRelationship.oneToOne"
            >
              <option :value="null"></option>
              <option
                :value="
                  entityUuidIdRelationship.oneToOne && entityUuidIdOption.id === entityUuidIdRelationship.oneToOne.id
                    ? entityUuidIdRelationship.oneToOne
                    : entityUuidIdOption
                "
                v-for="entityUuidIdOption in entityUuidIds"
                :key="entityUuidIdOption.id"
              >
                {{ entityUuidIdOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jhipsterVueApp.entityUuidIdRelationship.oneToOneMapsId')"
              for="entity-uuid-id-relationship-oneToOneMapsId"
            ></label>
            <select
              class="form-control"
              id="entity-uuid-id-relationship-oneToOneMapsId"
              data-cy="oneToOneMapsId"
              name="oneToOneMapsId"
              v-model="entityUuidIdRelationship.oneToOneMapsId"
            >
              <option :value="null"></option>
              <option
                :value="
                  entityUuidIdRelationship.oneToOneMapsId && entityUuidIdMapsIdOption.id === entityUuidIdRelationship.oneToOneMapsId.id
                    ? entityUuidIdRelationship.oneToOneMapsId
                    : entityUuidIdMapsIdOption
                "
                v-for="entityUuidIdMapsIdOption in entityUuidIdMapsIds"
                :key="entityUuidIdMapsIdOption.id"
              >
                {{ entityUuidIdMapsIdOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jhipsterVueApp.entityUuidIdRelationship.manyToOne')"
              for="entity-uuid-id-relationship-manyToOne"
            ></label>
            <select
              class="form-control"
              id="entity-uuid-id-relationship-manyToOne"
              data-cy="manyToOne"
              name="manyToOne"
              v-model="entityUuidIdRelationship.manyToOne"
            >
              <option :value="null"></option>
              <option
                :value="
                  entityUuidIdRelationship.manyToOne && entityUuidIdOption.id === entityUuidIdRelationship.manyToOne.id
                    ? entityUuidIdRelationship.manyToOne
                    : entityUuidIdOption
                "
                v-for="entityUuidIdOption in entityUuidIds"
                :key="entityUuidIdOption.id"
              >
                {{ entityUuidIdOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jhipsterVueApp.entityUuidIdRelationship.manyToOneMapsId')"
              for="entity-uuid-id-relationship-manyToOneMapsId"
            ></label>
            <select
              class="form-control"
              id="entity-uuid-id-relationship-manyToOneMapsId"
              data-cy="manyToOneMapsId"
              name="manyToOneMapsId"
              v-model="entityUuidIdRelationship.manyToOneMapsId"
            >
              <option :value="null"></option>
              <option
                :value="
                  entityUuidIdRelationship.manyToOneMapsId && entityUuidIdMapsIdOption.id === entityUuidIdRelationship.manyToOneMapsId.id
                    ? entityUuidIdRelationship.manyToOneMapsId
                    : entityUuidIdMapsIdOption
                "
                v-for="entityUuidIdMapsIdOption in entityUuidIdMapsIds"
                :key="entityUuidIdMapsIdOption.id"
              >
                {{ entityUuidIdMapsIdOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label v-text="t$('jhipsterVueApp.entityUuidIdRelationship.manyToMany')" for="entity-uuid-id-relationship-manyToMany"></label>
            <select
              class="form-control"
              id="entity-uuid-id-relationship-manyToManies"
              data-cy="manyToMany"
              multiple
              name="manyToMany"
              v-if="entityUuidIdRelationship.manyToManies !== undefined"
              v-model="entityUuidIdRelationship.manyToManies"
            >
              <option
                :value="getSelected(entityUuidIdRelationship.manyToManies, entityUuidIdOption, 'id')"
                v-for="entityUuidIdOption in entityUuidIds"
                :key="entityUuidIdOption.id"
              >
                {{ entityUuidIdOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label
              v-text="t$('jhipsterVueApp.entityUuidIdRelationship.manyToManyMapsId')"
              for="entity-uuid-id-relationship-manyToManyMapsId"
            ></label>
            <select
              class="form-control"
              id="entity-uuid-id-relationship-manyToManyMapsIds"
              data-cy="manyToManyMapsId"
              multiple
              name="manyToManyMapsId"
              v-if="entityUuidIdRelationship.manyToManyMapsIds !== undefined"
              v-model="entityUuidIdRelationship.manyToManyMapsIds"
            >
              <option
                :value="getSelected(entityUuidIdRelationship.manyToManyMapsIds, entityUuidIdMapsIdOption, 'id')"
                v-for="entityUuidIdMapsIdOption in entityUuidIdMapsIds"
                :key="entityUuidIdMapsIdOption.id"
              >
                {{ entityUuidIdMapsIdOption.id }}
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
<script lang="ts" src="./entity-uuid-id-relationship-update.component.ts"></script>
