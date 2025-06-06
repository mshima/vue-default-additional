import axios from 'axios';

import { type IUuidIdFiltering } from '@/shared/model/uuid-id-filtering.model';

const baseApiUrl = 'api/uuid-id-filterings';

export default class UuidIdFilteringService {
  find(customId: string): Promise<IUuidIdFiltering> {
    return new Promise<IUuidIdFiltering>((resolve, reject) => {
      axios
        .get(`${baseApiUrl}/${customId}`)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  retrieve(): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .get(baseApiUrl)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  delete(customId: string): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .delete(`${baseApiUrl}/${customId}`)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  create(entity: IUuidIdFiltering): Promise<IUuidIdFiltering> {
    return new Promise<IUuidIdFiltering>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}`, entity)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  update(entity: IUuidIdFiltering): Promise<IUuidIdFiltering> {
    return new Promise<IUuidIdFiltering>((resolve, reject) => {
      axios
        .put(`${baseApiUrl}/${entity.customId}`, entity)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  partialUpdate(entity: IUuidIdFiltering): Promise<IUuidIdFiltering> {
    return new Promise<IUuidIdFiltering>((resolve, reject) => {
      axios
        .patch(`${baseApiUrl}/${entity.customId}`, entity)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
