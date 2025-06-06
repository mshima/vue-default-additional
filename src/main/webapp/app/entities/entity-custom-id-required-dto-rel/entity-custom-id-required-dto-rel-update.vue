<template>
  <div class="d-flex justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate @submit.prevent="save()">
        <h2
          id="jhipsterVueApp.entityCustomIdRequiredDTORel.home.createOrEditLabel"
          data-cy="EntityCustomIdRequiredDTORelCreateUpdateHeading"
          v-text="t$('jhipsterVueApp.entityCustomIdRequiredDTORel.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="entityCustomIdRequiredDTORel.relatedId">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="relatedId" v-model="entityCustomIdRequiredDTORel.relatedId" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jhipsterVueApp.entityCustomIdRequiredDTORel.oneToOne')"
              for="entity-custom-id-required-dto-rel-oneToOne"
            ></label>
            <select
              class="form-control"
              id="entity-custom-id-required-dto-rel-oneToOne"
              data-cy="oneToOne"
              name="oneToOne"
              v-model="entityCustomIdRequiredDTORel.oneToOne"
              required
            >
              <option v-if="!entityCustomIdRequiredDTORel.oneToOne" :value="null" selected></option>
              <option
                :value="
                  entityCustomIdRequiredDTORel.oneToOne &&
                  entityCustomIdRequiredDTOOption.customId === entityCustomIdRequiredDTORel.oneToOne.customId
                    ? entityCustomIdRequiredDTORel.oneToOne
                    : entityCustomIdRequiredDTOOption
                "
                v-for="entityCustomIdRequiredDTOOption in entityCustomIdRequiredDTOS"
                :key="entityCustomIdRequiredDTOOption.customId"
              >
                {{ entityCustomIdRequiredDTOOption.customId }}
              </option>
            </select>
          </div>
          <div v-if="v$.oneToOne.$anyDirty && v$.oneToOne.$invalid">
            <small class="form-text text-danger" v-for="error of v$.oneToOne.$errors" :key="error.$uid">{{ error.$message }}</small>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jhipsterVueApp.entityCustomIdRequiredDTORel.oneToOneMapsId')"
              for="entity-custom-id-required-dto-rel-oneToOneMapsId"
            ></label>
            <select
              class="form-control"
              id="entity-custom-id-required-dto-rel-oneToOneMapsId"
              data-cy="oneToOneMapsId"
              name="oneToOneMapsId"
              v-model="entityCustomIdRequiredDTORel.oneToOneMapsId"
              required
            >
              <option v-if="!entityCustomIdRequiredDTORel.oneToOneMapsId" :value="null" selected></option>
              <option
                :value="
                  entityCustomIdRequiredDTORel.oneToOneMapsId &&
                  entityCustomIdRequiredDTOMapsIdOption.customId === entityCustomIdRequiredDTORel.oneToOneMapsId.customId
                    ? entityCustomIdRequiredDTORel.oneToOneMapsId
                    : entityCustomIdRequiredDTOMapsIdOption
                "
                v-for="entityCustomIdRequiredDTOMapsIdOption in entityCustomIdRequiredDTOMapsIds"
                :key="entityCustomIdRequiredDTOMapsIdOption.customId"
              >
                {{ entityCustomIdRequiredDTOMapsIdOption.customId }}
              </option>
            </select>
          </div>
          <div v-if="v$.oneToOneMapsId.$anyDirty && v$.oneToOneMapsId.$invalid">
            <small class="form-text text-danger" v-for="error of v$.oneToOneMapsId.$errors" :key="error.$uid">{{ error.$message }}</small>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jhipsterVueApp.entityCustomIdRequiredDTORel.manyToOne')"
              for="entity-custom-id-required-dto-rel-manyToOne"
            ></label>
            <select
              class="form-control"
              id="entity-custom-id-required-dto-rel-manyToOne"
              data-cy="manyToOne"
              name="manyToOne"
              v-model="entityCustomIdRequiredDTORel.manyToOne"
              required
            >
              <option v-if="!entityCustomIdRequiredDTORel.manyToOne" :value="null" selected></option>
              <option
                :value="
                  entityCustomIdRequiredDTORel.manyToOne &&
                  entityCustomIdRequiredDTOOption.customId === entityCustomIdRequiredDTORel.manyToOne.customId
                    ? entityCustomIdRequiredDTORel.manyToOne
                    : entityCustomIdRequiredDTOOption
                "
                v-for="entityCustomIdRequiredDTOOption in entityCustomIdRequiredDTOS"
                :key="entityCustomIdRequiredDTOOption.customId"
              >
                {{ entityCustomIdRequiredDTOOption.customId }}
              </option>
            </select>
          </div>
          <div v-if="v$.manyToOne.$anyDirty && v$.manyToOne.$invalid">
            <small class="form-text text-danger" v-for="error of v$.manyToOne.$errors" :key="error.$uid">{{ error.$message }}</small>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jhipsterVueApp.entityCustomIdRequiredDTORel.manyToOneMapsId')"
              for="entity-custom-id-required-dto-rel-manyToOneMapsId"
            ></label>
            <select
              class="form-control"
              id="entity-custom-id-required-dto-rel-manyToOneMapsId"
              data-cy="manyToOneMapsId"
              name="manyToOneMapsId"
              v-model="entityCustomIdRequiredDTORel.manyToOneMapsId"
              required
            >
              <option v-if="!entityCustomIdRequiredDTORel.manyToOneMapsId" :value="null" selected></option>
              <option
                :value="
                  entityCustomIdRequiredDTORel.manyToOneMapsId &&
                  entityCustomIdRequiredDTOMapsIdOption.customId === entityCustomIdRequiredDTORel.manyToOneMapsId.customId
                    ? entityCustomIdRequiredDTORel.manyToOneMapsId
                    : entityCustomIdRequiredDTOMapsIdOption
                "
                v-for="entityCustomIdRequiredDTOMapsIdOption in entityCustomIdRequiredDTOMapsIds"
                :key="entityCustomIdRequiredDTOMapsIdOption.customId"
              >
                {{ entityCustomIdRequiredDTOMapsIdOption.customId }}
              </option>
            </select>
          </div>
          <div v-if="v$.manyToOneMapsId.$anyDirty && v$.manyToOneMapsId.$invalid">
            <small class="form-text text-danger" v-for="error of v$.manyToOneMapsId.$errors" :key="error.$uid">{{ error.$message }}</small>
          </div>
          <div class="form-group">
            <label
              v-text="t$('jhipsterVueApp.entityCustomIdRequiredDTORel.manyToMany')"
              for="entity-custom-id-required-dto-rel-manyToMany"
            ></label>
            <select
              class="form-control"
              id="entity-custom-id-required-dto-rel-manyToManies"
              data-cy="manyToMany"
              multiple
              name="manyToMany"
              v-if="entityCustomIdRequiredDTORel.manyToManies !== undefined"
              v-model="entityCustomIdRequiredDTORel.manyToManies"
              required
            >
              <option
                :value="getSelected(entityCustomIdRequiredDTORel.manyToManies, entityCustomIdRequiredDTOOption, 'customId')"
                v-for="entityCustomIdRequiredDTOOption in entityCustomIdRequiredDTOS"
                :key="entityCustomIdRequiredDTOOption.customId"
              >
                {{ entityCustomIdRequiredDTOOption.customId }}
              </option>
            </select>
          </div>
          <div v-if="v$.manyToManies.$anyDirty && v$.manyToManies.$invalid">
            <small class="form-text text-danger" v-for="error of v$.manyToManies.$errors" :key="error.$uid">{{ error.$message }}</small>
          </div>
          <div class="form-group">
            <label
              v-text="t$('jhipsterVueApp.entityCustomIdRequiredDTORel.manyToManyMapsId')"
              for="entity-custom-id-required-dto-rel-manyToManyMapsId"
            ></label>
            <select
              class="form-control"
              id="entity-custom-id-required-dto-rel-manyToManyMapsIds"
              data-cy="manyToManyMapsId"
              multiple
              name="manyToManyMapsId"
              v-if="entityCustomIdRequiredDTORel.manyToManyMapsIds !== undefined"
              v-model="entityCustomIdRequiredDTORel.manyToManyMapsIds"
              required
            >
              <option
                :value="getSelected(entityCustomIdRequiredDTORel.manyToManyMapsIds, entityCustomIdRequiredDTOMapsIdOption, 'customId')"
                v-for="entityCustomIdRequiredDTOMapsIdOption in entityCustomIdRequiredDTOMapsIds"
                :key="entityCustomIdRequiredDTOMapsIdOption.customId"
              >
                {{ entityCustomIdRequiredDTOMapsIdOption.customId }}
              </option>
            </select>
          </div>
          <div v-if="v$.manyToManyMapsIds.$anyDirty && v$.manyToManyMapsIds.$invalid">
            <small class="form-text text-danger" v-for="error of v$.manyToManyMapsIds.$errors" :key="error.$uid">{{
              error.$message
            }}</small>
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
<script lang="ts" src="./entity-custom-id-required-dto-rel-update.component.ts"></script>
