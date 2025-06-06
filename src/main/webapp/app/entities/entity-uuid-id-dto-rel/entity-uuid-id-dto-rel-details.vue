<template>
  <div class="d-flex justify-content-center">
    <div class="col-8">
      <div v-if="entityUuidIdDTORel">
        <h2 class="jh-entity-heading" data-cy="entityUuidIdDTORelDetailsHeading">
          <span v-text="t$('jhipsterVueApp.entityUuidIdDTORel.detail.title')"></span> {{ entityUuidIdDTORel.id }}
        </h2>
        <dl class="row-md jh-entity-details">
          <dt>
            <span v-text="t$('jhipsterVueApp.entityUuidIdDTORel.oneToOne')"></span>
          </dt>
          <dd>
            <div v-if="entityUuidIdDTORel.oneToOne">
              <router-link :to="{ name: 'EntityUuidIdDTOView', params: { entityUuidIdDTOId: entityUuidIdDTORel.oneToOne.id } }">{{
                entityUuidIdDTORel.oneToOne.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jhipsterVueApp.entityUuidIdDTORel.oneToOneMapsId')"></span>
          </dt>
          <dd>
            <div v-if="entityUuidIdDTORel.oneToOneMapsId">
              <router-link
                :to="{ name: 'EntityUuidIdDTOMapsIdView', params: { entityUuidIdDTOMapsIdId: entityUuidIdDTORel.oneToOneMapsId.id } }"
                >{{ entityUuidIdDTORel.oneToOneMapsId.id }}</router-link
              >
            </div>
          </dd>
          <dt>
            <span v-text="t$('jhipsterVueApp.entityUuidIdDTORel.manyToOne')"></span>
          </dt>
          <dd>
            <div v-if="entityUuidIdDTORel.manyToOne">
              <router-link :to="{ name: 'EntityUuidIdDTOView', params: { entityUuidIdDTOId: entityUuidIdDTORel.manyToOne.id } }">{{
                entityUuidIdDTORel.manyToOne.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jhipsterVueApp.entityUuidIdDTORel.manyToOneMapsId')"></span>
          </dt>
          <dd>
            <div v-if="entityUuidIdDTORel.manyToOneMapsId">
              <router-link
                :to="{ name: 'EntityUuidIdDTOMapsIdView', params: { entityUuidIdDTOMapsIdId: entityUuidIdDTORel.manyToOneMapsId.id } }"
                >{{ entityUuidIdDTORel.manyToOneMapsId.id }}</router-link
              >
            </div>
          </dd>
          <dt>
            <span v-text="t$('jhipsterVueApp.entityUuidIdDTORel.manyToMany')"></span>
          </dt>
          <dd>
            <span v-for="(manyToMany, i) in entityUuidIdDTORel.manyToManies" :key="manyToMany.id"
              >{{ i > 0 ? ', ' : '' }}
              <router-link :to="{ name: 'EntityUuidIdDTOView', params: { entityUuidIdDTOId: manyToMany.id } }">{{
                manyToMany.id
              }}</router-link>
            </span>
          </dd>
          <dt>
            <span v-text="t$('jhipsterVueApp.entityUuidIdDTORel.manyToManyMapsId')"></span>
          </dt>
          <dd>
            <span v-for="(manyToManyMapsId, i) in entityUuidIdDTORel.manyToManyMapsIds" :key="manyToManyMapsId.id"
              >{{ i > 0 ? ', ' : '' }}
              <router-link :to="{ name: 'EntityUuidIdDTOMapsIdView', params: { entityUuidIdDTOMapsIdId: manyToManyMapsId.id } }">{{
                manyToManyMapsId.id
              }}</router-link>
            </span>
          </dd>
        </dl>
        <button type="submit" @click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="entityUuidIdDTORel.id"
          :to="{ name: 'EntityUuidIdDTORelEdit', params: { entityUuidIdDTORelId: entityUuidIdDTORel.id } }"
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

<script lang="ts" src="./entity-uuid-id-dto-rel-details.component.ts"></script>
