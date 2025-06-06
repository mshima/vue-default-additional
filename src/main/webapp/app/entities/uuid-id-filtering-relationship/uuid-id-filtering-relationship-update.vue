<template>
  <div class="d-flex justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate @submit.prevent="save()">
        <h2
          id="jhipsterVueApp.uuidIdFilteringRelationship.home.createOrEditLabel"
          data-cy="UuidIdFilteringRelationshipCreateUpdateHeading"
          v-text="t$('jhipsterVueApp.uuidIdFilteringRelationship.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="uuidIdFilteringRelationship.relatedId">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="relatedId" v-model="uuidIdFilteringRelationship.relatedId" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jhipsterVueApp.uuidIdFilteringRelationship.oneToOne')"
              for="uuid-id-filtering-relationship-oneToOne"
            ></label>
            <select
              class="form-control"
              id="uuid-id-filtering-relationship-oneToOne"
              data-cy="oneToOne"
              name="oneToOne"
              v-model="uuidIdFilteringRelationship.oneToOne"
            >
              <option :value="null"></option>
              <option
                :value="
                  uuidIdFilteringRelationship.oneToOne && uuidIdFilteringOption.customId === uuidIdFilteringRelationship.oneToOne.customId
                    ? uuidIdFilteringRelationship.oneToOne
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
              class="form-control-label"
              v-text="t$('jhipsterVueApp.uuidIdFilteringRelationship.oneToOneMapsId')"
              for="uuid-id-filtering-relationship-oneToOneMapsId"
            ></label>
            <select
              class="form-control"
              id="uuid-id-filtering-relationship-oneToOneMapsId"
              data-cy="oneToOneMapsId"
              name="oneToOneMapsId"
              v-model="uuidIdFilteringRelationship.oneToOneMapsId"
            >
              <option :value="null"></option>
              <option
                :value="
                  uuidIdFilteringRelationship.oneToOneMapsId &&
                  uuidIdFilteringMapsIdOption.customId === uuidIdFilteringRelationship.oneToOneMapsId.customId
                    ? uuidIdFilteringRelationship.oneToOneMapsId
                    : uuidIdFilteringMapsIdOption
                "
                v-for="uuidIdFilteringMapsIdOption in uuidIdFilteringMapsIds"
                :key="uuidIdFilteringMapsIdOption.customId"
              >
                {{ uuidIdFilteringMapsIdOption.customId }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jhipsterVueApp.uuidIdFilteringRelationship.manyToOne')"
              for="uuid-id-filtering-relationship-manyToOne"
            ></label>
            <select
              class="form-control"
              id="uuid-id-filtering-relationship-manyToOne"
              data-cy="manyToOne"
              name="manyToOne"
              v-model="uuidIdFilteringRelationship.manyToOne"
            >
              <option :value="null"></option>
              <option
                :value="
                  uuidIdFilteringRelationship.manyToOne && uuidIdFilteringOption.customId === uuidIdFilteringRelationship.manyToOne.customId
                    ? uuidIdFilteringRelationship.manyToOne
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
              class="form-control-label"
              v-text="t$('jhipsterVueApp.uuidIdFilteringRelationship.manyToOneMapsId')"
              for="uuid-id-filtering-relationship-manyToOneMapsId"
            ></label>
            <select
              class="form-control"
              id="uuid-id-filtering-relationship-manyToOneMapsId"
              data-cy="manyToOneMapsId"
              name="manyToOneMapsId"
              v-model="uuidIdFilteringRelationship.manyToOneMapsId"
            >
              <option :value="null"></option>
              <option
                :value="
                  uuidIdFilteringRelationship.manyToOneMapsId &&
                  uuidIdFilteringMapsIdOption.customId === uuidIdFilteringRelationship.manyToOneMapsId.customId
                    ? uuidIdFilteringRelationship.manyToOneMapsId
                    : uuidIdFilteringMapsIdOption
                "
                v-for="uuidIdFilteringMapsIdOption in uuidIdFilteringMapsIds"
                :key="uuidIdFilteringMapsIdOption.customId"
              >
                {{ uuidIdFilteringMapsIdOption.customId }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label
              v-text="t$('jhipsterVueApp.uuidIdFilteringRelationship.manyToMany')"
              for="uuid-id-filtering-relationship-manyToMany"
            ></label>
            <select
              class="form-control"
              id="uuid-id-filtering-relationship-manyToManies"
              data-cy="manyToMany"
              multiple
              name="manyToMany"
              v-if="uuidIdFilteringRelationship.manyToManies !== undefined"
              v-model="uuidIdFilteringRelationship.manyToManies"
            >
              <option
                :value="getSelected(uuidIdFilteringRelationship.manyToManies, uuidIdFilteringOption, 'customId')"
                v-for="uuidIdFilteringOption in uuidIdFilterings"
                :key="uuidIdFilteringOption.customId"
              >
                {{ uuidIdFilteringOption.customId }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label
              v-text="t$('jhipsterVueApp.uuidIdFilteringRelationship.manyToManyMapsId')"
              for="uuid-id-filtering-relationship-manyToManyMapsId"
            ></label>
            <select
              class="form-control"
              id="uuid-id-filtering-relationship-manyToManyMapsIds"
              data-cy="manyToManyMapsId"
              multiple
              name="manyToManyMapsId"
              v-if="uuidIdFilteringRelationship.manyToManyMapsIds !== undefined"
              v-model="uuidIdFilteringRelationship.manyToManyMapsIds"
            >
              <option
                :value="getSelected(uuidIdFilteringRelationship.manyToManyMapsIds, uuidIdFilteringMapsIdOption, 'customId')"
                v-for="uuidIdFilteringMapsIdOption in uuidIdFilteringMapsIds"
                :key="uuidIdFilteringMapsIdOption.customId"
              >
                {{ uuidIdFilteringMapsIdOption.customId }}
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
<script lang="ts" src="./uuid-id-filtering-relationship-update.component.ts"></script>
