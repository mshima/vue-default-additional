<template>
  <div class="d-flex justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate @submit.prevent="save()">
        <h2
          id="jhipsterVueApp.entityUuidIdDTORel.home.createOrEditLabel"
          data-cy="EntityUuidIdDTORelCreateUpdateHeading"
          v-text="t$('jhipsterVueApp.entityUuidIdDTORel.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="entityUuidIdDTORel.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="entityUuidIdDTORel.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jhipsterVueApp.entityUuidIdDTORel.oneToOne')"
              for="entity-uuid-id-dto-rel-oneToOne"
            ></label>
            <select
              class="form-control"
              id="entity-uuid-id-dto-rel-oneToOne"
              data-cy="oneToOne"
              name="oneToOne"
              v-model="entityUuidIdDTORel.oneToOne"
            >
              <option :value="null"></option>
              <option
                :value="
                  entityUuidIdDTORel.oneToOne && entityUuidIdDTOOption.id === entityUuidIdDTORel.oneToOne.id
                    ? entityUuidIdDTORel.oneToOne
                    : entityUuidIdDTOOption
                "
                v-for="entityUuidIdDTOOption in entityUuidIdDTOS"
                :key="entityUuidIdDTOOption.id"
              >
                {{ entityUuidIdDTOOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jhipsterVueApp.entityUuidIdDTORel.oneToOneMapsId')"
              for="entity-uuid-id-dto-rel-oneToOneMapsId"
            ></label>
            <select
              class="form-control"
              id="entity-uuid-id-dto-rel-oneToOneMapsId"
              data-cy="oneToOneMapsId"
              name="oneToOneMapsId"
              v-model="entityUuidIdDTORel.oneToOneMapsId"
            >
              <option :value="null"></option>
              <option
                :value="
                  entityUuidIdDTORel.oneToOneMapsId && entityUuidIdDTOMapsIdOption.id === entityUuidIdDTORel.oneToOneMapsId.id
                    ? entityUuidIdDTORel.oneToOneMapsId
                    : entityUuidIdDTOMapsIdOption
                "
                v-for="entityUuidIdDTOMapsIdOption in entityUuidIdDTOMapsIds"
                :key="entityUuidIdDTOMapsIdOption.id"
              >
                {{ entityUuidIdDTOMapsIdOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jhipsterVueApp.entityUuidIdDTORel.manyToOne')"
              for="entity-uuid-id-dto-rel-manyToOne"
            ></label>
            <select
              class="form-control"
              id="entity-uuid-id-dto-rel-manyToOne"
              data-cy="manyToOne"
              name="manyToOne"
              v-model="entityUuidIdDTORel.manyToOne"
            >
              <option :value="null"></option>
              <option
                :value="
                  entityUuidIdDTORel.manyToOne && entityUuidIdDTOOption.id === entityUuidIdDTORel.manyToOne.id
                    ? entityUuidIdDTORel.manyToOne
                    : entityUuidIdDTOOption
                "
                v-for="entityUuidIdDTOOption in entityUuidIdDTOS"
                :key="entityUuidIdDTOOption.id"
              >
                {{ entityUuidIdDTOOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jhipsterVueApp.entityUuidIdDTORel.manyToOneMapsId')"
              for="entity-uuid-id-dto-rel-manyToOneMapsId"
            ></label>
            <select
              class="form-control"
              id="entity-uuid-id-dto-rel-manyToOneMapsId"
              data-cy="manyToOneMapsId"
              name="manyToOneMapsId"
              v-model="entityUuidIdDTORel.manyToOneMapsId"
            >
              <option :value="null"></option>
              <option
                :value="
                  entityUuidIdDTORel.manyToOneMapsId && entityUuidIdDTOMapsIdOption.id === entityUuidIdDTORel.manyToOneMapsId.id
                    ? entityUuidIdDTORel.manyToOneMapsId
                    : entityUuidIdDTOMapsIdOption
                "
                v-for="entityUuidIdDTOMapsIdOption in entityUuidIdDTOMapsIds"
                :key="entityUuidIdDTOMapsIdOption.id"
              >
                {{ entityUuidIdDTOMapsIdOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label v-text="t$('jhipsterVueApp.entityUuidIdDTORel.manyToMany')" for="entity-uuid-id-dto-rel-manyToMany"></label>
            <select
              class="form-control"
              id="entity-uuid-id-dto-rel-manyToManies"
              data-cy="manyToMany"
              multiple
              name="manyToMany"
              v-if="entityUuidIdDTORel.manyToManies !== undefined"
              v-model="entityUuidIdDTORel.manyToManies"
            >
              <option
                :value="getSelected(entityUuidIdDTORel.manyToManies, entityUuidIdDTOOption, 'id')"
                v-for="entityUuidIdDTOOption in entityUuidIdDTOS"
                :key="entityUuidIdDTOOption.id"
              >
                {{ entityUuidIdDTOOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label v-text="t$('jhipsterVueApp.entityUuidIdDTORel.manyToManyMapsId')" for="entity-uuid-id-dto-rel-manyToManyMapsId"></label>
            <select
              class="form-control"
              id="entity-uuid-id-dto-rel-manyToManyMapsIds"
              data-cy="manyToManyMapsId"
              multiple
              name="manyToManyMapsId"
              v-if="entityUuidIdDTORel.manyToManyMapsIds !== undefined"
              v-model="entityUuidIdDTORel.manyToManyMapsIds"
            >
              <option
                :value="getSelected(entityUuidIdDTORel.manyToManyMapsIds, entityUuidIdDTOMapsIdOption, 'id')"
                v-for="entityUuidIdDTOMapsIdOption in entityUuidIdDTOMapsIds"
                :key="entityUuidIdDTOMapsIdOption.id"
              >
                {{ entityUuidIdDTOMapsIdOption.id }}
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
<script lang="ts" src="./entity-uuid-id-dto-rel-update.component.ts"></script>
