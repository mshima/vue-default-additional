<template>
  <div class="table-responsive">
    <h2 id="logs-page-heading" v-text="t$('logs.title')" data-cy="logsPageHeading"></h2>

    <div v-if="loggers">
      <p v-text="t$('logs.nbloggers', { total: loggers.length })"></p>

      <span v-text="t$('logs.filter')"></span> <input type="text" v-model="filtered" class="form-control" />

      <table class="table table-sm table-striped table-bordered" aria-describedby="Logs">
        <thead>
          <tr title="click to order">
            <th @click="changeOrder('name')" scope="col">
              <span v-text="t$('logs.table.name')"></span>
              <jhi-sort-indicator :current-order="orderProp" :reverse="reverse" :field-name="'name'"></jhi-sort-indicator>
            </th>
            <th @click="changeOrder('level')" scope="col">
              <span v-text="t$('logs.table.level')"></span>
              <jhi-sort-indicator :current-order="orderProp" :reverse="reverse" :field-name="'level'"></jhi-sort-indicator>
            </th>
          </tr>
        </thead>

        <tr v-for="logger in filteredLoggers" :key="logger.name">
          <td>
            <small>{{ logger.name }}</small>
          </td>
          <td>
            <button
              @click="updateLevel(logger.name, 'TRACE')"
              :class="logger.level === 'TRACE' ? 'btn-primary' : 'btn-light'"
              class="btn btn-sm"
            >
              TRACE
            </button>
            <button
              @click="updateLevel(logger.name, 'DEBUG')"
              :class="logger.level === 'DEBUG' ? 'btn-success' : 'btn-light'"
              class="btn btn-sm"
            >
              DEBUG
            </button>
            <button
              @click="updateLevel(logger.name, 'INFO')"
              :class="logger.level === 'INFO' ? 'btn-info' : 'btn-light'"
              class="btn btn-sm"
            >
              INFO
            </button>
            <button
              @click="updateLevel(logger.name, 'WARN')"
              :class="logger.level === 'WARN' ? 'btn-warning' : 'btn-light'"
              class="btn btn-sm"
            >
              WARN
            </button>
            <button
              @click="updateLevel(logger.name, 'ERROR')"
              :class="logger.level === 'ERROR' ? 'btn-danger' : 'btn-light'"
              class="btn btn-sm"
            >
              ERROR
            </button>
            <button
              @click="updateLevel(logger.name, 'OFF')"
              :class="logger.level === 'OFF' ? 'btn-secondary' : 'btn-light'"
              class="btn btn-sm"
            >
              OFF
            </button>
          </td>
        </tr>
      </table>
    </div>
  </div>
</template>

<script lang="ts" src="./logs.component.ts"></script>
