<template>
  <div class="d-flex justify-content-center">
    <div class="col-8">
      <div v-if="uuidIdFilteringRelationship">
        <h2 class="jh-entity-heading" data-cy="uuidIdFilteringRelationshipDetailsHeading">
          <span v-text="t$('jhipsterVueApp.uuidIdFilteringRelationship.detail.title')"></span> {{ uuidIdFilteringRelationship.relatedId }}
        </h2>
        <dl class="row-md jh-entity-details">
          <dt>
            <span v-text="t$('jhipsterVueApp.uuidIdFilteringRelationship.oneToOne')"></span>
          </dt>
          <dd>
            <div v-if="uuidIdFilteringRelationship.oneToOne">
              <router-link
                :to="{ name: 'UuidIdFilteringView', params: { uuidIdFilteringId: uuidIdFilteringRelationship.oneToOne.customId } }"
                >{{ uuidIdFilteringRelationship.oneToOne.customId }}</router-link
              >
            </div>
          </dd>
          <dt>
            <span v-text="t$('jhipsterVueApp.uuidIdFilteringRelationship.oneToOneMapsId')"></span>
          </dt>
          <dd>
            <div v-if="uuidIdFilteringRelationship.oneToOneMapsId">
              <router-link
                :to="{
                  name: 'UuidIdFilteringMapsIdView',
                  params: { uuidIdFilteringMapsIdId: uuidIdFilteringRelationship.oneToOneMapsId.customId },
                }"
                >{{ uuidIdFilteringRelationship.oneToOneMapsId.customId }}</router-link
              >
            </div>
          </dd>
          <dt>
            <span v-text="t$('jhipsterVueApp.uuidIdFilteringRelationship.manyToOne')"></span>
          </dt>
          <dd>
            <div v-if="uuidIdFilteringRelationship.manyToOne">
              <router-link
                :to="{ name: 'UuidIdFilteringView', params: { uuidIdFilteringId: uuidIdFilteringRelationship.manyToOne.customId } }"
                >{{ uuidIdFilteringRelationship.manyToOne.customId }}</router-link
              >
            </div>
          </dd>
          <dt>
            <span v-text="t$('jhipsterVueApp.uuidIdFilteringRelationship.manyToOneMapsId')"></span>
          </dt>
          <dd>
            <div v-if="uuidIdFilteringRelationship.manyToOneMapsId">
              <router-link
                :to="{
                  name: 'UuidIdFilteringMapsIdView',
                  params: { uuidIdFilteringMapsIdId: uuidIdFilteringRelationship.manyToOneMapsId.customId },
                }"
                >{{ uuidIdFilteringRelationship.manyToOneMapsId.customId }}</router-link
              >
            </div>
          </dd>
          <dt>
            <span v-text="t$('jhipsterVueApp.uuidIdFilteringRelationship.manyToMany')"></span>
          </dt>
          <dd>
            <span v-for="(manyToMany, i) in uuidIdFilteringRelationship.manyToManies" :key="manyToMany.customId"
              >{{ i > 0 ? ', ' : '' }}
              <router-link :to="{ name: 'UuidIdFilteringView', params: { uuidIdFilteringId: manyToMany.customId } }">{{
                manyToMany.customId
              }}</router-link>
            </span>
          </dd>
          <dt>
            <span v-text="t$('jhipsterVueApp.uuidIdFilteringRelationship.manyToManyMapsId')"></span>
          </dt>
          <dd>
            <span v-for="(manyToManyMapsId, i) in uuidIdFilteringRelationship.manyToManyMapsIds" :key="manyToManyMapsId.customId"
              >{{ i > 0 ? ', ' : '' }}
              <router-link :to="{ name: 'UuidIdFilteringMapsIdView', params: { uuidIdFilteringMapsIdId: manyToManyMapsId.customId } }">{{
                manyToManyMapsId.customId
              }}</router-link>
            </span>
          </dd>
        </dl>
        <button type="submit" @click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="uuidIdFilteringRelationship.relatedId"
          :to="{
            name: 'UuidIdFilteringRelationshipEdit',
            params: { uuidIdFilteringRelationshipId: uuidIdFilteringRelationship.relatedId },
          }"
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

<script lang="ts" src="./uuid-id-filtering-relationship-details.component.ts"></script>
