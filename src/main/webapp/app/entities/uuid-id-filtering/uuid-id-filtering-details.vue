<template>
  <div class="d-flex justify-content-center">
    <div class="col-8">
      <div v-if="uuidIdFiltering">
        <h2 class="jh-entity-heading" data-cy="uuidIdFilteringDetailsHeading">
          <span v-text="t$('jhipsterVueApp.uuidIdFiltering.detail.title')"></span> {{ uuidIdFiltering.customId }}
        </h2>
        <dl class="row-md jh-entity-details">
          <dt>
            <span v-text="t$('jhipsterVueApp.uuidIdFiltering.manyToManyBack')"></span>
          </dt>
          <dd>
            <span v-for="(manyToManyBack, i) in uuidIdFiltering.manyToManyBacks" :key="manyToManyBack.relatedId"
              >{{ i > 0 ? ', ' : '' }}
              <router-link
                :to="{ name: 'UuidIdFilteringRelationshipView', params: { uuidIdFilteringRelationshipId: manyToManyBack.relatedId } }"
                >{{ manyToManyBack.relatedId }}</router-link
              >
            </span>
          </dd>
        </dl>
        <button type="submit" @click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="uuidIdFiltering.customId"
          :to="{ name: 'UuidIdFilteringEdit', params: { uuidIdFilteringId: uuidIdFiltering.customId } }"
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

<script lang="ts" src="./uuid-id-filtering-details.component.ts"></script>
