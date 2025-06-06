<template>
  <div class="d-flex justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate @submit.prevent="save()">
        <h2
          id="jhipsterVueApp.entityCustomIdDTORel.home.createOrEditLabel"
          data-cy="EntityCustomIdDTORelCreateUpdateHeading"
          v-text="t$('jhipsterVueApp.entityCustomIdDTORel.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="entityCustomIdDTORel.relatedId">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="relatedId" v-model="entityCustomIdDTORel.relatedId" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jhipsterVueApp.entityCustomIdDTORel.oneToOne')"
              for="entity-custom-id-dto-rel-oneToOne"
            ></label>
            <select
              class="form-control"
              id="entity-custom-id-dto-rel-oneToOne"
              data-cy="oneToOne"
              name="oneToOne"
              v-model="entityCustomIdDTORel.oneToOne"
            >
              <option :value="null"></option>
              <option
                :value="
                  entityCustomIdDTORel.oneToOne && entityCustomIdDTOOption.customId === entityCustomIdDTORel.oneToOne.customId
                    ? entityCustomIdDTORel.oneToOne
                    : entityCustomIdDTOOption
                "
                v-for="entityCustomIdDTOOption in entityCustomIdDTOS"
                :key="entityCustomIdDTOOption.customId"
              >
                {{ entityCustomIdDTOOption.customId }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jhipsterVueApp.entityCustomIdDTORel.oneToOneMapsId')"
              for="entity-custom-id-dto-rel-oneToOneMapsId"
            ></label>
            <select
              class="form-control"
              id="entity-custom-id-dto-rel-oneToOneMapsId"
              data-cy="oneToOneMapsId"
              name="oneToOneMapsId"
              v-model="entityCustomIdDTORel.oneToOneMapsId"
            >
              <option :value="null"></option>
              <option
                :value="
                  entityCustomIdDTORel.oneToOneMapsId &&
                  entityCustomIdDTOMapsIdOption.customId === entityCustomIdDTORel.oneToOneMapsId.customId
                    ? entityCustomIdDTORel.oneToOneMapsId
                    : entityCustomIdDTOMapsIdOption
                "
                v-for="entityCustomIdDTOMapsIdOption in entityCustomIdDTOMapsIds"
                :key="entityCustomIdDTOMapsIdOption.customId"
              >
                {{ entityCustomIdDTOMapsIdOption.customId }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jhipsterVueApp.entityCustomIdDTORel.manyToOne')"
              for="entity-custom-id-dto-rel-manyToOne"
            ></label>
            <select
              class="form-control"
              id="entity-custom-id-dto-rel-manyToOne"
              data-cy="manyToOne"
              name="manyToOne"
              v-model="entityCustomIdDTORel.manyToOne"
            >
              <option :value="null"></option>
              <option
                :value="
                  entityCustomIdDTORel.manyToOne && entityCustomIdDTOOption.customId === entityCustomIdDTORel.manyToOne.customId
                    ? entityCustomIdDTORel.manyToOne
                    : entityCustomIdDTOOption
                "
                v-for="entityCustomIdDTOOption in entityCustomIdDTOS"
                :key="entityCustomIdDTOOption.customId"
              >
                {{ entityCustomIdDTOOption.customId }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jhipsterVueApp.entityCustomIdDTORel.manyToOneMapsId')"
              for="entity-custom-id-dto-rel-manyToOneMapsId"
            ></label>
            <select
              class="form-control"
              id="entity-custom-id-dto-rel-manyToOneMapsId"
              data-cy="manyToOneMapsId"
              name="manyToOneMapsId"
              v-model="entityCustomIdDTORel.manyToOneMapsId"
            >
              <option :value="null"></option>
              <option
                :value="
                  entityCustomIdDTORel.manyToOneMapsId &&
                  entityCustomIdDTOMapsIdOption.customId === entityCustomIdDTORel.manyToOneMapsId.customId
                    ? entityCustomIdDTORel.manyToOneMapsId
                    : entityCustomIdDTOMapsIdOption
                "
                v-for="entityCustomIdDTOMapsIdOption in entityCustomIdDTOMapsIds"
                :key="entityCustomIdDTOMapsIdOption.customId"
              >
                {{ entityCustomIdDTOMapsIdOption.customId }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label v-text="t$('jhipsterVueApp.entityCustomIdDTORel.manyToMany')" for="entity-custom-id-dto-rel-manyToMany"></label>
            <select
              class="form-control"
              id="entity-custom-id-dto-rel-manyToManies"
              data-cy="manyToMany"
              multiple
              name="manyToMany"
              v-if="entityCustomIdDTORel.manyToManies !== undefined"
              v-model="entityCustomIdDTORel.manyToManies"
            >
              <option
                :value="getSelected(entityCustomIdDTORel.manyToManies, entityCustomIdDTOOption, 'customId')"
                v-for="entityCustomIdDTOOption in entityCustomIdDTOS"
                :key="entityCustomIdDTOOption.customId"
              >
                {{ entityCustomIdDTOOption.customId }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label
              v-text="t$('jhipsterVueApp.entityCustomIdDTORel.manyToManyMapsId')"
              for="entity-custom-id-dto-rel-manyToManyMapsId"
            ></label>
            <select
              class="form-control"
              id="entity-custom-id-dto-rel-manyToManyMapsIds"
              data-cy="manyToManyMapsId"
              multiple
              name="manyToManyMapsId"
              v-if="entityCustomIdDTORel.manyToManyMapsIds !== undefined"
              v-model="entityCustomIdDTORel.manyToManyMapsIds"
            >
              <option
                :value="getSelected(entityCustomIdDTORel.manyToManyMapsIds, entityCustomIdDTOMapsIdOption, 'customId')"
                v-for="entityCustomIdDTOMapsIdOption in entityCustomIdDTOMapsIds"
                :key="entityCustomIdDTOMapsIdOption.customId"
              >
                {{ entityCustomIdDTOMapsIdOption.customId }}
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
<script lang="ts" src="./entity-custom-id-dto-rel-update.component.ts"></script>
