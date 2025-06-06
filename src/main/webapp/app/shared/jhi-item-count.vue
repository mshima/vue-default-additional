<template>
  <div class="info">
    <span v-text="t$('global.item-count', { first, second, total })"></span>
  </div>
</template>

<script lang="ts" src="./jhi-item-count.component.ts"></script>
