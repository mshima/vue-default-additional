<template>
  <div class="modal-body pad">
    <div v-if="currentHealth && currentHealth.details">
      <h5 v-text="t$('health.details.properties')"></h5>
      <div class="table-responsive">
        <table class="table table-striped" aria-describedby="Health">
          <thead>
            <tr>
              <th class="text-start" v-text="t$('health.details.name')" scope="col"></th>
              <th class="text-start" v-text="t$('health.details.value')" scope="col"></th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(item, index) in currentHealth.details.details" :key="index">
              <td class="text-start">{{ index }}</td>
              <td class="text-start">{{ readableValue(item) }}</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
    <div v-if="currentHealth && currentHealth.error">
      <h4 v-text="t$('health.details.error')"></h4>
      <pre>{{ currentHealth.error }}</pre>
    </div>
  </div>
</template>

<script lang="ts" src="./health-modal.component.ts"></script>
