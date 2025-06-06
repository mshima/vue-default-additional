<template>
  <div class="d-flex justify-content-center">
    <div class="col-8">
      <div v-if="entityCustomIdMapsId">
        <h2 class="jh-entity-heading" data-cy="entityCustomIdMapsIdDetailsHeading">
          <span v-text="t$('jhipsterVueApp.entityCustomIdMapsId.detail.title')"></span> {{ entityCustomIdMapsId.customId }}
        </h2>
        <dl class="row-md jh-entity-details">
          <dt>
            <span v-text="t$('jhipsterVueApp.entityCustomIdMapsId.entityCustomId')"></span>
          </dt>
          <dd>
            <div v-if="entityCustomIdMapsId.entityCustomId">
              <router-link
                :to="{ name: 'EntityCustomIdView', params: { entityCustomIdId: entityCustomIdMapsId.entityCustomId.customId } }"
                >{{ entityCustomIdMapsId.entityCustomId.customId }}</router-link
              >
            </div>
          </dd>
          <dt>
            <span v-text="t$('jhipsterVueApp.entityCustomIdMapsId.manyToManyMapsIdBack')"></span>
          </dt>
          <dd>
            <span v-for="(manyToManyMapsIdBack, i) in entityCustomIdMapsId.manyToManyMapsIdBacks" :key="manyToManyMapsIdBack.relatedId"
              >{{ i > 0 ? ', ' : '' }}
              <router-link
                :to="{ name: 'EntityCustomIdRelationshipView', params: { entityCustomIdRelationshipId: manyToManyMapsIdBack.relatedId } }"
                >{{ manyToManyMapsIdBack.relatedId }}</router-link
              >
            </span>
          </dd>
        </dl>
        <button type="submit" @click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="entityCustomIdMapsId.customId"
          :to="{ name: 'EntityCustomIdMapsIdEdit', params: { entityCustomIdMapsIdId: entityCustomIdMapsId.customId } }"
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

<script lang="ts" src="./entity-custom-id-maps-id-details.component.ts"></script>
