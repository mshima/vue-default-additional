<template>
  <div>
    <h2>
      <span id="user-management-page-heading" v-text="t$('userManagement.home.title')" data-cy="userManagementPageHeading"></span>

      <div class="d-flex justify-content-end">
        <button class="btn btn-info me-2" @click="handleSyncList" :disabled="isLoading">
          <font-awesome-icon icon="sync" :spin="isLoading"></font-awesome-icon>
          <span v-text="t$('userManagement.home.refreshListLabel')"></span>
        </button>
        <router-link custom v-slot="{ navigate }" :to="{ name: 'JhiUserCreate' }">
          <button @click="navigate" class="btn btn-primary jh-create-entity">
            <font-awesome-icon icon="plus"></font-awesome-icon> <span v-text="t$('userManagement.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <div class="table-responsive" v-if="users">
      <table class="table table-striped" aria-describedby="Users">
        <thead>
          <tr>
            <th scope="col" @click="changeOrder('id')">
              <span v-text="t$('global.field.id')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator>
            </th>
            <th scope="col" @click="changeOrder('login')">
              <span v-text="t$('userManagement.login')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'login'"></jhi-sort-indicator>
            </th>
            <th scope="col" @click="changeOrder('email')">
              <span v-text="t$('userManagement.email')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'email'"></jhi-sort-indicator>
            </th>
            <th scope="col"></th>
            <th scope="col" @click="changeOrder('langKey')">
              <span v-text="t$('userManagement.langKey')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'langKey'"></jhi-sort-indicator>
            </th>
            <th scope="col"><span v-text="t$('userManagement.profiles')"></span></th>
            <th scope="col" @click="changeOrder('createdDate')">
              <span v-text="t$('userManagement.createdDate')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'createdDate'"></jhi-sort-indicator>
            </th>
            <th scope="col" @click="changeOrder('lastModifiedBy')">
              <span v-text="t$('userManagement.lastModifiedBy')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'lastModifiedBy'"></jhi-sort-indicator>
            </th>
            <th scope="col" id="modified-date-sort" @click="changeOrder('lastModifiedDate')">
              <span v-text="t$('userManagement.lastModifiedDate')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'lastModifiedDate'"></jhi-sort-indicator>
            </th>
            <th scope="col"></th>
          </tr>
        </thead>
        <tbody v-if="users">
          <tr v-for="user in users" :key="user.id" :id="user.login">
            <td>
              <router-link :to="{ name: 'JhiUserView', params: { userId: user.login } }">{{ user.id }}</router-link>
            </td>
            <td>{{ user.login }}</td>
            <td class="jhi-user-email">{{ user.email }}</td>
            <td>
              <button
                class="btn btn-danger btn-sm deactivated"
                @click="setActive(user, true)"
                v-if="!user.activated"
                v-text="t$('userManagement.deactivated')"
              ></button>
              <button
                class="btn btn-success btn-sm"
                @click="setActive(user, false)"
                v-if="user.activated"
                :disabled="username === user.login"
                v-text="t$('userManagement.activated')"
              ></button>
            </td>
            <td>{{ user.langKey }}</td>
            <td>
              <div v-for="authority of user.authorities" :key="authority">
                <span class="badge bg-info">{{ authority }}</span>
              </div>
            </td>
            <td>{{ formatDate(user.createdDate) }}</td>
            <td>{{ user.lastModifiedBy }}</td>
            <td>{{ formatDate(user.lastModifiedDate) }}</td>
            <td class="text-end">
              <div class="btn-group">
                <router-link :to="{ name: 'JhiUserView', params: { userId: user.login } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'JhiUserEdit', params: { userId: user.login } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button @click="prepareRemove(user)" variant="danger" class="btn btn-sm delete" :disabled="username === user.login">
                  <font-awesome-icon icon="times"></font-awesome-icon>
                  <span class="d-none d-md-inline" v-text="t$('entity.action.delete')"></span>
                </b-button>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
      <b-modal ref="removeUser" id="removeUser" :title="t$('entity.delete.title')" @ok="deleteUser()">
        <div class="modal-body">
          <p id="jhi-delete-user-heading" v-text="t$('userManagement.delete.question', { login: removeId })"></p>
        </div>
        <template #footer>
          <div>
            <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" @click="closeDialog()"></button>
            <button
              type="button"
              class="btn btn-primary"
              id="confirm-delete-user"
              v-text="t$('entity.action.delete')"
              @click="deleteUser()"
            ></button>
          </div>
        </template>
      </b-modal>
    </div>
    <div v-show="users && users.length > 0">
      <div class="d-flex justify-content-center">
        <jhi-item-count :page="page" :total="queryCount" :items-per-page="itemsPerPage"></jhi-item-count>
      </div>
      <div class="d-flex justify-content-center">
        <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./user-management.component.ts"></script>
