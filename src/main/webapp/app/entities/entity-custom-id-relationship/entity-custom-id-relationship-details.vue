<template>
  <div class="d-flex justify-content-center">
    <div class="col-8">
      <div v-if="entityCustomIdRelationship">
        <h2 class="jh-entity-heading" data-cy="entityCustomIdRelationshipDetailsHeading">
          <span v-text="t$('jhipsterVueApp.entityCustomIdRelationship.detail.title')"></span> {{ entityCustomIdRelationship.relatedId }}
        </h2>
        <dl class="row-md jh-entity-details">
          <dt>
            <span v-text="t$('jhipsterVueApp.entityCustomIdRelationship.oneToOne')"></span>
          </dt>
          <dd>
            <div v-if="entityCustomIdRelationship.oneToOne">
              <router-link
                :to="{ name: 'EntityCustomIdView', params: { entityCustomIdId: entityCustomIdRelationship.oneToOne.customId } }"
                >{{ entityCustomIdRelationship.oneToOne.customId }}</router-link
              >
            </div>
          </dd>
          <dt>
            <span v-text="t$('jhipsterVueApp.entityCustomIdRelationship.oneToOneMapsId')"></span>
          </dt>
          <dd>
            <div v-if="entityCustomIdRelationship.oneToOneMapsId">
              <router-link
                :to="{
                  name: 'EntityCustomIdMapsIdView',
                  params: { entityCustomIdMapsIdId: entityCustomIdRelationship.oneToOneMapsId.customId },
                }"
                >{{ entityCustomIdRelationship.oneToOneMapsId.customId }}</router-link
              >
            </div>
          </dd>
          <dt>
            <span v-text="t$('jhipsterVueApp.entityCustomIdRelationship.manyToOne')"></span>
          </dt>
          <dd>
            <div v-if="entityCustomIdRelationship.manyToOne">
              <router-link
                :to="{ name: 'EntityCustomIdView', params: { entityCustomIdId: entityCustomIdRelationship.manyToOne.customId } }"
                >{{ entityCustomIdRelationship.manyToOne.customId }}</router-link
              >
            </div>
          </dd>
          <dt>
            <span v-text="t$('jhipsterVueApp.entityCustomIdRelationship.manyToOneMapsId')"></span>
          </dt>
          <dd>
            <div v-if="entityCustomIdRelationship.manyToOneMapsId">
              <router-link
                :to="{
                  name: 'EntityCustomIdMapsIdView',
                  params: { entityCustomIdMapsIdId: entityCustomIdRelationship.manyToOneMapsId.customId },
                }"
                >{{ entityCustomIdRelationship.manyToOneMapsId.customId }}</router-link
              >
            </div>
          </dd>
          <dt>
            <span v-text="t$('jhipsterVueApp.entityCustomIdRelationship.manyToMany')"></span>
          </dt>
          <dd>
            <span v-for="(manyToMany, i) in entityCustomIdRelationship.manyToManies" :key="manyToMany.customId"
              >{{ i > 0 ? ', ' : '' }}
              <router-link :to="{ name: 'EntityCustomIdView', params: { entityCustomIdId: manyToMany.customId } }">{{
                manyToMany.customId
              }}</router-link>
            </span>
          </dd>
          <dt>
            <span v-text="t$('jhipsterVueApp.entityCustomIdRelationship.manyToManyMapsId')"></span>
          </dt>
          <dd>
            <span v-for="(manyToManyMapsId, i) in entityCustomIdRelationship.manyToManyMapsIds" :key="manyToManyMapsId.customId"
              >{{ i > 0 ? ', ' : '' }}
              <router-link :to="{ name: 'EntityCustomIdMapsIdView', params: { entityCustomIdMapsIdId: manyToManyMapsId.customId } }">{{
                manyToManyMapsId.customId
              }}</router-link>
            </span>
          </dd>
        </dl>
        <button type="submit" @click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="entityCustomIdRelationship.relatedId"
          :to="{ name: 'EntityCustomIdRelationshipEdit', params: { entityCustomIdRelationshipId: entityCustomIdRelationship.relatedId } }"
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

<script lang="ts" src="./entity-custom-id-relationship-details.component.ts"></script>
