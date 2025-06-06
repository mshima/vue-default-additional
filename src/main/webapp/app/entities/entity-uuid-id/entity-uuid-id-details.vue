<template>
  <div class="d-flex justify-content-center">
    <div class="col-8">
      <div v-if="entityUuidId">
        <h2 class="jh-entity-heading" data-cy="entityUuidIdDetailsHeading">
          <span v-text="t$('jhipsterVueApp.entityUuidId.detail.title')"></span> {{ entityUuidId.id }}
        </h2>
        <dl class="row-md jh-entity-details">
          <dt>
            <span v-text="t$('jhipsterVueApp.entityUuidId.manyToManyBack')"></span>
          </dt>
          <dd>
            <span v-for="(manyToManyBack, i) in entityUuidId.manyToManyBacks" :key="manyToManyBack.id"
              >{{ i > 0 ? ', ' : '' }}
              <router-link :to="{ name: 'EntityUuidIdRelationshipView', params: { entityUuidIdRelationshipId: manyToManyBack.id } }">{{
                manyToManyBack.id
              }}</router-link>
            </span>
          </dd>
        </dl>
        <button type="submit" @click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="entityUuidId.id"
          :to="{ name: 'EntityUuidIdEdit', params: { entityUuidIdId: entityUuidId.id } }"
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

<script lang="ts" src="./entity-uuid-id-details.component.ts"></script>
