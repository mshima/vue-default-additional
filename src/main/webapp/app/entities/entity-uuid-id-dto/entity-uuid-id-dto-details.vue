<template>
  <div class="d-flex justify-content-center">
    <div class="col-8">
      <div v-if="entityUuidIdDTO">
        <h2 class="jh-entity-heading" data-cy="entityUuidIdDTODetailsHeading">
          <span v-text="t$('jhipsterVueApp.entityUuidIdDTO.detail.title')"></span> {{ entityUuidIdDTO.id }}
        </h2>
        <dl class="row-md jh-entity-details">
          <dt>
            <span v-text="t$('jhipsterVueApp.entityUuidIdDTO.manyToManyBack')"></span>
          </dt>
          <dd>
            <span v-for="(manyToManyBack, i) in entityUuidIdDTO.manyToManyBacks" :key="manyToManyBack.id"
              >{{ i > 0 ? ', ' : '' }}
              <router-link :to="{ name: 'EntityUuidIdDTORelView', params: { entityUuidIdDTORelId: manyToManyBack.id } }">{{
                manyToManyBack.id
              }}</router-link>
            </span>
          </dd>
        </dl>
        <button type="submit" @click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="entityUuidIdDTO.id"
          :to="{ name: 'EntityUuidIdDTOEdit', params: { entityUuidIdDTOId: entityUuidIdDTO.id } }"
          custom
          v-slot="{ navigate }"
        >
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.edit')"></span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./entity-uuid-id-dto-details.component.ts"></script>
