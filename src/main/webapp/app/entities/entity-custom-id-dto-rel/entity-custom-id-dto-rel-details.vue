<template>
  <div class="d-flex justify-content-center">
    <div class="col-8">
      <div v-if="entityCustomIdDTORel">
        <h2 class="jh-entity-heading" data-cy="entityCustomIdDTORelDetailsHeading">
          <span v-text="t$('jhipsterVueApp.entityCustomIdDTORel.detail.title')"></span> {{ entityCustomIdDTORel.relatedId }}
        </h2>
        <dl class="row-md jh-entity-details">
          <dt>
            <span v-text="t$('jhipsterVueApp.entityCustomIdDTORel.oneToOne')"></span>
          </dt>
          <dd>
            <div v-if="entityCustomIdDTORel.oneToOne">
              <router-link
                :to="{ name: 'EntityCustomIdDTOView', params: { entityCustomIdDTOId: entityCustomIdDTORel.oneToOne.customId } }"
                >{{ entityCustomIdDTORel.oneToOne.customId }}</router-link
              >
            </div>
          </dd>
          <dt>
            <span v-text="t$('jhipsterVueApp.entityCustomIdDTORel.oneToOneMapsId')"></span>
          </dt>
          <dd>
            <div v-if="entityCustomIdDTORel.oneToOneMapsId">
              <router-link
                :to="{
                  name: 'EntityCustomIdDTOMapsIdView',
                  params: { entityCustomIdDTOMapsIdId: entityCustomIdDTORel.oneToOneMapsId.customId },
                }"
                >{{ entityCustomIdDTORel.oneToOneMapsId.customId }}</router-link
              >
            </div>
          </dd>
          <dt>
            <span v-text="t$('jhipsterVueApp.entityCustomIdDTORel.manyToOne')"></span>
          </dt>
          <dd>
            <div v-if="entityCustomIdDTORel.manyToOne">
              <router-link
                :to="{ name: 'EntityCustomIdDTOView', params: { entityCustomIdDTOId: entityCustomIdDTORel.manyToOne.customId } }"
                >{{ entityCustomIdDTORel.manyToOne.customId }}</router-link
              >
            </div>
          </dd>
          <dt>
            <span v-text="t$('jhipsterVueApp.entityCustomIdDTORel.manyToOneMapsId')"></span>
          </dt>
          <dd>
            <div v-if="entityCustomIdDTORel.manyToOneMapsId">
              <router-link
                :to="{
                  name: 'EntityCustomIdDTOMapsIdView',
                  params: { entityCustomIdDTOMapsIdId: entityCustomIdDTORel.manyToOneMapsId.customId },
                }"
                >{{ entityCustomIdDTORel.manyToOneMapsId.customId }}</router-link
              >
            </div>
          </dd>
          <dt>
            <span v-text="t$('jhipsterVueApp.entityCustomIdDTORel.manyToMany')"></span>
          </dt>
          <dd>
            <span v-for="(manyToMany, i) in entityCustomIdDTORel.manyToManies" :key="manyToMany.customId"
              >{{ i > 0 ? ', ' : '' }}
              <router-link :to="{ name: 'EntityCustomIdDTOView', params: { entityCustomIdDTOId: manyToMany.customId } }">{{
                manyToMany.customId
              }}</router-link>
            </span>
          </dd>
          <dt>
            <span v-text="t$('jhipsterVueApp.entityCustomIdDTORel.manyToManyMapsId')"></span>
          </dt>
          <dd>
            <span v-for="(manyToManyMapsId, i) in entityCustomIdDTORel.manyToManyMapsIds" :key="manyToManyMapsId.customId"
              >{{ i > 0 ? ', ' : '' }}
              <router-link
                :to="{ name: 'EntityCustomIdDTOMapsIdView', params: { entityCustomIdDTOMapsIdId: manyToManyMapsId.customId } }"
                >{{ manyToManyMapsId.customId }}</router-link
              >
            </span>
          </dd>
        </dl>
        <button type="submit" @click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="entityCustomIdDTORel.relatedId"
          :to="{ name: 'EntityCustomIdDTORelEdit', params: { entityCustomIdDTORelId: entityCustomIdDTORel.relatedId } }"
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

<script lang="ts" src="./entity-custom-id-dto-rel-details.component.ts"></script>
