<template>
  <div class="d-flex justify-content-center">
    <div class="col-8">
      <div v-if="entityCustomId">
        <h2 class="jh-entity-heading" data-cy="entityCustomIdDetailsHeading">
          <span v-text="t$('jhipsterVueApp.entityCustomId.detail.title')"></span> {{ entityCustomId.customId }}
        </h2>
        <dl class="row-md jh-entity-details">
          <dt>
            <span v-text="t$('jhipsterVueApp.entityCustomId.manyToManyBack')"></span>
          </dt>
          <dd>
            <span v-for="(manyToManyBack, i) in entityCustomId.manyToManyBacks" :key="manyToManyBack.relatedId"
              >{{ i > 0 ? ', ' : '' }}
              <router-link
                :to="{ name: 'EntityCustomIdRelationshipView', params: { entityCustomIdRelationshipId: manyToManyBack.relatedId } }"
                >{{ manyToManyBack.relatedId }}</router-link
              >
            </span>
          </dd>
        </dl>
        <button type="submit" @click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="entityCustomId.customId"
          :to="{ name: 'EntityCustomIdEdit', params: { entityCustomIdId: entityCustomId.customId } }"
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

<script lang="ts" src="./entity-custom-id-details.component.ts"></script>
