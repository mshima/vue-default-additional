<template>
  <div>
    <b-dropdown-item to="/entity-integer-id">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.entityIntegerId')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/entity-custom-sequence">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.entityCustomSequence')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/entity-uuid-id">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.entityUuidId')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/entity-uuid-id-maps-id">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.entityUuidIdMapsId')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/entity-uuid-id-relationship">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.entityUuidIdRelationship')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/entity-uuid-id-dto">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.entityUuidIdDto')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/entity-uuid-id-dto-maps-id">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.entityUuidIdDtoMapsId')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/entity-uuid-id-dto-rel">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.entityUuidIdDtoRel')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/entity-custom-id">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.entityCustomId')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/entity-custom-id-maps-id">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.entityCustomIdMapsId')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/entity-custom-id-relationship">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.entityCustomIdRelationship')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/entity-custom-id-dto">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.entityCustomIdDto')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/entity-custom-id-dto-maps-id">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.entityCustomIdDtoMapsId')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/entity-custom-id-dto-rel">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.entityCustomIdDtoRel')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/uuid-id-filtering">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.uuidIdFiltering')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/uuid-id-filtering-maps-id">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.uuidIdFilteringMapsId')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/uuid-id-filtering-relationship">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.uuidIdFilteringRelationship')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/entity-custom-id-required-dto">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.entityCustomIdRequiredDto')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/entity-custom-id-required-dto-maps-id">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.entityCustomIdRequiredDtoMapsId')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/entity-custom-id-required-dto-rel">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.entityCustomIdRequiredDtoRel')"></span>
    </b-dropdown-item>
    <!-- jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here -->
  </div>
</template>

<script lang="ts" src="./entities-menu.component.ts"></script>
