<template>
  <div class="d-flex justify-content-center">
    <div class="col-8">
      <div v-if="entityCustomIdRequiredDTOMapsId">
        <h2 class="jh-entity-heading" data-cy="entityCustomIdRequiredDTOMapsIdDetailsHeading">
          <span v-text="t$('jhipsterVueApp.entityCustomIdRequiredDTOMapsId.detail.title')"></span>
          {{ entityCustomIdRequiredDTOMapsId.customId }}
        </h2>
        <dl class="row-md jh-entity-details">
          <dt>
            <span v-text="t$('jhipsterVueApp.entityCustomIdRequiredDTOMapsId.entityCustomIdRequiredDTO')"></span>
          </dt>
          <dd>
            <div v-if="entityCustomIdRequiredDTOMapsId.entityCustomIdRequiredDTO">
              <router-link
                :to="{
                  name: 'EntityCustomIdRequiredDTOView',
                  params: { entityCustomIdRequiredDTOId: entityCustomIdRequiredDTOMapsId.entityCustomIdRequiredDTO.customId },
                }"
                >{{ entityCustomIdRequiredDTOMapsId.entityCustomIdRequiredDTO.customId }}</router-link
              >
            </div>
          </dd>
          <dt>
            <span v-text="t$('jhipsterVueApp.entityCustomIdRequiredDTOMapsId.manyToManyMapsIdBack')"></span>
          </dt>
          <dd>
            <span
              v-for="(manyToManyMapsIdBack, i) in entityCustomIdRequiredDTOMapsId.manyToManyMapsIdBacks"
              :key="manyToManyMapsIdBack.relatedId"
              >{{ i > 0 ? ', ' : '' }}
              <router-link
                :to="{
                  name: 'EntityCustomIdRequiredDTORelView',
                  params: { entityCustomIdRequiredDTORelId: manyToManyMapsIdBack.relatedId },
                }"
                >{{ manyToManyMapsIdBack.relatedId }}</router-link
              >
            </span>
          </dd>
        </dl>
        <button type="submit" @click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="entityCustomIdRequiredDTOMapsId.customId"
          :to="{
            name: 'EntityCustomIdRequiredDTOMapsIdEdit',
            params: { entityCustomIdRequiredDTOMapsIdId: entityCustomIdRequiredDTOMapsId.customId },
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

<script lang="ts" src="./entity-custom-id-required-dto-maps-id-details.component.ts"></script>
