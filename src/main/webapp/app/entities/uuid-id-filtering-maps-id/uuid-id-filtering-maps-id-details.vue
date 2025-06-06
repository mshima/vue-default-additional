<template>
  <div class="d-flex justify-content-center">
    <div class="col-8">
      <div v-if="uuidIdFilteringMapsId">
        <h2 class="jh-entity-heading" data-cy="uuidIdFilteringMapsIdDetailsHeading">
          <span v-text="t$('jhipsterVueApp.uuidIdFilteringMapsId.detail.title')"></span> {{ uuidIdFilteringMapsId.customId }}
        </h2>
        <dl class="row-md jh-entity-details">
          <dt>
            <span v-text="t$('jhipsterVueApp.uuidIdFilteringMapsId.uuidIdFiltering')"></span>
          </dt>
          <dd>
            <div v-if="uuidIdFilteringMapsId.uuidIdFiltering">
              <router-link
                :to="{ name: 'UuidIdFilteringView', params: { uuidIdFilteringId: uuidIdFilteringMapsId.uuidIdFiltering.customId } }"
                >{{ uuidIdFilteringMapsId.uuidIdFiltering.customId }}</router-link
              >
            </div>
          </dd>
          <dt>
            <span v-text="t$('jhipsterVueApp.uuidIdFilteringMapsId.manyToManyMapsIdBack')"></span>
          </dt>
          <dd>
            <span v-for="(manyToManyMapsIdBack, i) in uuidIdFilteringMapsId.manyToManyMapsIdBacks" :key="manyToManyMapsIdBack.relatedId"
              >{{ i > 0 ? ', ' : '' }}
              <router-link
                :to="{ name: 'UuidIdFilteringRelationshipView', params: { uuidIdFilteringRelationshipId: manyToManyMapsIdBack.relatedId } }"
                >{{ manyToManyMapsIdBack.relatedId }}</router-link
              >
            </span>
          </dd>
        </dl>
        <button type="submit" @click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="uuidIdFilteringMapsId.customId"
          :to="{ name: 'UuidIdFilteringMapsIdEdit', params: { uuidIdFilteringMapsIdId: uuidIdFilteringMapsId.customId } }"
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

<script lang="ts" src="./uuid-id-filtering-maps-id-details.component.ts"></script>
