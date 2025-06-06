<template>
  <div class="d-flex justify-content-center">
    <div class="col-8">
      <div v-if="entityCustomIdDTOMapsId">
        <h2 class="jh-entity-heading" data-cy="entityCustomIdDTOMapsIdDetailsHeading">
          <span v-text="t$('jhipsterVueApp.entityCustomIdDTOMapsId.detail.title')"></span> {{ entityCustomIdDTOMapsId.customId }}
        </h2>
        <dl class="row-md jh-entity-details">
          <dt>
            <span v-text="t$('jhipsterVueApp.entityCustomIdDTOMapsId.entityCustomIdDTO')"></span>
          </dt>
          <dd>
            <div v-if="entityCustomIdDTOMapsId.entityCustomIdDTO">
              <router-link
                :to="{ name: 'EntityCustomIdDTOView', params: { entityCustomIdDTOId: entityCustomIdDTOMapsId.entityCustomIdDTO.customId } }"
                >{{ entityCustomIdDTOMapsId.entityCustomIdDTO.customId }}</router-link
              >
            </div>
          </dd>
          <dt>
            <span v-text="t$('jhipsterVueApp.entityCustomIdDTOMapsId.manyToManyMapsIdBack')"></span>
          </dt>
          <dd>
            <span v-for="(manyToManyMapsIdBack, i) in entityCustomIdDTOMapsId.manyToManyMapsIdBacks" :key="manyToManyMapsIdBack.relatedId"
              >{{ i > 0 ? ', ' : '' }}
              <router-link :to="{ name: 'EntityCustomIdDTORelView', params: { entityCustomIdDTORelId: manyToManyMapsIdBack.relatedId } }">{{
                manyToManyMapsIdBack.relatedId
              }}</router-link>
            </span>
          </dd>
        </dl>
        <button type="submit" @click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="entityCustomIdDTOMapsId.customId"
          :to="{ name: 'EntityCustomIdDTOMapsIdEdit', params: { entityCustomIdDTOMapsIdId: entityCustomIdDTOMapsId.customId } }"
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

<script lang="ts" src="./entity-custom-id-dto-maps-id-details.component.ts"></script>
