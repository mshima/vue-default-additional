<template>
  <div class="d-flex justify-content-center">
    <div class="col-8">
      <div v-if="entityCustomSequence">
        <h2 class="jh-entity-heading" data-cy="entityCustomSequenceDetailsHeading">
          <span v-text="t$('jhipsterVueApp.entityCustomSequence.detail.title')"></span> {{ entityCustomSequence.id }}
        </h2>
        <dl class="row-md jh-entity-details">
          <dt>
            <span v-text="t$('jhipsterVueApp.entityCustomSequence.name')"></span>
          </dt>
          <dd>
            <span>{{ entityCustomSequence.name }}</span>
          </dd>
        </dl>
        <button type="submit" @click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="entityCustomSequence.id"
          :to="{ name: 'EntityCustomSequenceEdit', params: { entityCustomSequenceId: entityCustomSequence.id } }"
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

<script lang="ts" src="./entity-custom-sequence-details.component.ts"></script>
