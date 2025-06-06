<template>
  <div class="d-flex justify-content-center">
    <div class="col-8">
      <div v-if="entityCustomIdRequiredDTORel">
        <h2 class="jh-entity-heading" data-cy="entityCustomIdRequiredDTORelDetailsHeading">
          <span v-text="t$('jhipsterVueApp.entityCustomIdRequiredDTORel.detail.title')"></span> {{ entityCustomIdRequiredDTORel.relatedId }}
        </h2>
        <dl class="row-md jh-entity-details">
          <dt>
            <span v-text="t$('jhipsterVueApp.entityCustomIdRequiredDTORel.oneToOne')"></span>
          </dt>
          <dd>
            <div v-if="entityCustomIdRequiredDTORel.oneToOne">
              <router-link
                :to="{
                  name: 'EntityCustomIdRequiredDTOView',
                  params: { entityCustomIdRequiredDTOId: entityCustomIdRequiredDTORel.oneToOne.customId },
                }"
                >{{ entityCustomIdRequiredDTORel.oneToOne.customId }}</router-link
              >
            </div>
          </dd>
          <dt>
            <span v-text="t$('jhipsterVueApp.entityCustomIdRequiredDTORel.oneToOneMapsId')"></span>
          </dt>
          <dd>
            <div v-if="entityCustomIdRequiredDTORel.oneToOneMapsId">
              <router-link
                :to="{
                  name: 'EntityCustomIdRequiredDTOMapsIdView',
                  params: { entityCustomIdRequiredDTOMapsIdId: entityCustomIdRequiredDTORel.oneToOneMapsId.customId },
                }"
                >{{ entityCustomIdRequiredDTORel.oneToOneMapsId.customId }}</router-link
              >
            </div>
          </dd>
          <dt>
            <span v-text="t$('jhipsterVueApp.entityCustomIdRequiredDTORel.manyToOne')"></span>
          </dt>
          <dd>
            <div v-if="entityCustomIdRequiredDTORel.manyToOne">
              <router-link
                :to="{
                  name: 'EntityCustomIdRequiredDTOView',
                  params: { entityCustomIdRequiredDTOId: entityCustomIdRequiredDTORel.manyToOne.customId },
                }"
                >{{ entityCustomIdRequiredDTORel.manyToOne.customId }}</router-link
              >
            </div>
          </dd>
          <dt>
            <span v-text="t$('jhipsterVueApp.entityCustomIdRequiredDTORel.manyToOneMapsId')"></span>
          </dt>
          <dd>
            <div v-if="entityCustomIdRequiredDTORel.manyToOneMapsId">
              <router-link
                :to="{
                  name: 'EntityCustomIdRequiredDTOMapsIdView',
                  params: { entityCustomIdRequiredDTOMapsIdId: entityCustomIdRequiredDTORel.manyToOneMapsId.customId },
                }"
                >{{ entityCustomIdRequiredDTORel.manyToOneMapsId.customId }}</router-link
              >
            </div>
          </dd>
          <dt>
            <span v-text="t$('jhipsterVueApp.entityCustomIdRequiredDTORel.manyToMany')"></span>
          </dt>
          <dd>
            <span v-for="(manyToMany, i) in entityCustomIdRequiredDTORel.manyToManies" :key="manyToMany.customId"
              >{{ i > 0 ? ', ' : '' }}
              <router-link :to="{ name: 'EntityCustomIdRequiredDTOView', params: { entityCustomIdRequiredDTOId: manyToMany.customId } }">{{
                manyToMany.customId
              }}</router-link>
            </span>
          </dd>
          <dt>
            <span v-text="t$('jhipsterVueApp.entityCustomIdRequiredDTORel.manyToManyMapsId')"></span>
          </dt>
          <dd>
            <span v-for="(manyToManyMapsId, i) in entityCustomIdRequiredDTORel.manyToManyMapsIds" :key="manyToManyMapsId.customId"
              >{{ i > 0 ? ', ' : '' }}
              <router-link
                :to="{
                  name: 'EntityCustomIdRequiredDTOMapsIdView',
                  params: { entityCustomIdRequiredDTOMapsIdId: manyToManyMapsId.customId },
                }"
                >{{ manyToManyMapsId.customId }}</router-link
              >
            </span>
          </dd>
        </dl>
        <button type="submit" @click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="entityCustomIdRequiredDTORel.relatedId"
          :to="{
            name: 'EntityCustomIdRequiredDTORelEdit',
            params: { entityCustomIdRequiredDTORelId: entityCustomIdRequiredDTORel.relatedId },
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

<script lang="ts" src="./entity-custom-id-required-dto-rel-details.component.ts"></script>
