<template>
  <div class="d-flex justify-content-center">
    <div class="col-8">
      <div v-if="entityUuidIdRelationship">
        <h2 class="jh-entity-heading" data-cy="entityUuidIdRelationshipDetailsHeading">
          <span v-text="t$('jhipsterVueApp.entityUuidIdRelationship.detail.title')"></span> {{ entityUuidIdRelationship.id }}
        </h2>
        <dl class="row-md jh-entity-details">
          <dt>
            <span v-text="t$('jhipsterVueApp.entityUuidIdRelationship.oneToOne')"></span>
          </dt>
          <dd>
            <div v-if="entityUuidIdRelationship.oneToOne">
              <router-link :to="{ name: 'EntityUuidIdView', params: { entityUuidIdId: entityUuidIdRelationship.oneToOne.id } }">{{
                entityUuidIdRelationship.oneToOne.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jhipsterVueApp.entityUuidIdRelationship.oneToOneMapsId')"></span>
          </dt>
          <dd>
            <div v-if="entityUuidIdRelationship.oneToOneMapsId">
              <router-link
                :to="{ name: 'EntityUuidIdMapsIdView', params: { entityUuidIdMapsIdId: entityUuidIdRelationship.oneToOneMapsId.id } }"
                >{{ entityUuidIdRelationship.oneToOneMapsId.id }}</router-link
              >
            </div>
          </dd>
          <dt>
            <span v-text="t$('jhipsterVueApp.entityUuidIdRelationship.manyToOne')"></span>
          </dt>
          <dd>
            <div v-if="entityUuidIdRelationship.manyToOne">
              <router-link :to="{ name: 'EntityUuidIdView', params: { entityUuidIdId: entityUuidIdRelationship.manyToOne.id } }">{{
                entityUuidIdRelationship.manyToOne.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jhipsterVueApp.entityUuidIdRelationship.manyToOneMapsId')"></span>
          </dt>
          <dd>
            <div v-if="entityUuidIdRelationship.manyToOneMapsId">
              <router-link
                :to="{ name: 'EntityUuidIdMapsIdView', params: { entityUuidIdMapsIdId: entityUuidIdRelationship.manyToOneMapsId.id } }"
                >{{ entityUuidIdRelationship.manyToOneMapsId.id }}</router-link
              >
            </div>
          </dd>
          <dt>
            <span v-text="t$('jhipsterVueApp.entityUuidIdRelationship.manyToMany')"></span>
          </dt>
          <dd>
            <span v-for="(manyToMany, i) in entityUuidIdRelationship.manyToManies" :key="manyToMany.id"
              >{{ i > 0 ? ', ' : '' }}
              <router-link :to="{ name: 'EntityUuidIdView', params: { entityUuidIdId: manyToMany.id } }">{{ manyToMany.id }}</router-link>
            </span>
          </dd>
          <dt>
            <span v-text="t$('jhipsterVueApp.entityUuidIdRelationship.manyToManyMapsId')"></span>
          </dt>
          <dd>
            <span v-for="(manyToManyMapsId, i) in entityUuidIdRelationship.manyToManyMapsIds" :key="manyToManyMapsId.id"
              >{{ i > 0 ? ', ' : '' }}
              <router-link :to="{ name: 'EntityUuidIdMapsIdView', params: { entityUuidIdMapsIdId: manyToManyMapsId.id } }">{{
                manyToManyMapsId.id
              }}</router-link>
            </span>
          </dd>
        </dl>
        <button type="submit" @click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="entityUuidIdRelationship.id"
          :to="{ name: 'EntityUuidIdRelationshipEdit', params: { entityUuidIdRelationshipId: entityUuidIdRelationship.id } }"
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

<script lang="ts" src="./entity-uuid-id-relationship-details.component.ts"></script>
