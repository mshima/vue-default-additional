<template>
  <div class="d-flex justify-content-center">
    <div class="col-8">
      <div v-if="entityUuidIdDTOMapsId">
        <h2 class="jh-entity-heading" data-cy="entityUuidIdDTOMapsIdDetailsHeading">
          <span v-text="t$('jhipsterVueApp.entityUuidIdDTOMapsId.detail.title')"></span> {{ entityUuidIdDTOMapsId.id }}
        </h2>
        <dl class="row-md jh-entity-details">
          <dt>
            <span v-text="t$('jhipsterVueApp.entityUuidIdDTOMapsId.entityUuidIdDTO')"></span>
          </dt>
          <dd>
            <div v-if="entityUuidIdDTOMapsId.entityUuidIdDTO">
              <router-link :to="{ name: 'EntityUuidIdDTOView', params: { entityUuidIdDTOId: entityUuidIdDTOMapsId.entityUuidIdDTO.id } }">{{
                entityUuidIdDTOMapsId.entityUuidIdDTO.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jhipsterVueApp.entityUuidIdDTOMapsId.manyToManyMapsIdBack')"></span>
          </dt>
          <dd>
            <span v-for="(manyToManyMapsIdBack, i) in entityUuidIdDTOMapsId.manyToManyMapsIdBacks" :key="manyToManyMapsIdBack.id"
              >{{ i > 0 ? ', ' : '' }}
              <router-link :to="{ name: 'EntityUuidIdDTORelView', params: { entityUuidIdDTORelId: manyToManyMapsIdBack.id } }">{{
                manyToManyMapsIdBack.id
              }}</router-link>
            </span>
          </dd>
        </dl>
        <button type="submit" @click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="entityUuidIdDTOMapsId.id"
          :to="{ name: 'EntityUuidIdDTOMapsIdEdit', params: { entityUuidIdDTOMapsIdId: entityUuidIdDTOMapsId.id } }"
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

<script lang="ts" src="./entity-uuid-id-dto-maps-id-details.component.ts"></script>
