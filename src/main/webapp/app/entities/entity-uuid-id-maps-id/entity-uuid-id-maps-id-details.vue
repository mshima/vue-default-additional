<template>
  <div class="d-flex justify-content-center">
    <div class="col-8">
      <div v-if="entityUuidIdMapsId">
        <h2 class="jh-entity-heading" data-cy="entityUuidIdMapsIdDetailsHeading">
          <span v-text="t$('jhipsterVueApp.entityUuidIdMapsId.detail.title')"></span> {{ entityUuidIdMapsId.id }}
        </h2>
        <dl class="row-md jh-entity-details">
          <dt>
            <span v-text="t$('jhipsterVueApp.entityUuidIdMapsId.entityUuidId')"></span>
          </dt>
          <dd>
            <div v-if="entityUuidIdMapsId.entityUuidId">
              <router-link :to="{ name: 'EntityUuidIdView', params: { entityUuidIdId: entityUuidIdMapsId.entityUuidId.id } }">{{
                entityUuidIdMapsId.entityUuidId.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jhipsterVueApp.entityUuidIdMapsId.manyToManyMapsIdBack')"></span>
          </dt>
          <dd>
            <span v-for="(manyToManyMapsIdBack, i) in entityUuidIdMapsId.manyToManyMapsIdBacks" :key="manyToManyMapsIdBack.id"
              >{{ i > 0 ? ', ' : '' }}
              <router-link
                :to="{ name: 'EntityUuidIdRelationshipView', params: { entityUuidIdRelationshipId: manyToManyMapsIdBack.id } }"
                >{{ manyToManyMapsIdBack.id }}</router-link
              >
            </span>
          </dd>
        </dl>
        <button type="submit" @click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="entityUuidIdMapsId.id"
          :to="{ name: 'EntityUuidIdMapsIdEdit', params: { entityUuidIdMapsIdId: entityUuidIdMapsId.id } }"
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

<script lang="ts" src="./entity-uuid-id-maps-id-details.component.ts"></script>
