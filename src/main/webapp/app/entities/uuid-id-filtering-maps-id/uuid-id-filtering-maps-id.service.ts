import axios from 'axios';

import { type IUuidIdFilteringMapsId } from '@/shared/model/uuid-id-filtering-maps-id.model';

const baseApiUrl = 'api/uuid-id-filtering-maps-ids';

export default class UuidIdFilteringMapsIdService {
  find(customId: string): Promise<IUuidIdFilteringMapsId> {
    return new Promise<IUuidIdFilteringMapsId>((resolve, reject) => {
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

  create(entity: IUuidIdFilteringMapsId): Promise<IUuidIdFilteringMapsId> {
    return new Promise<IUuidIdFilteringMapsId>((resolve, reject) => {
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

  update(entity: IUuidIdFilteringMapsId): Promise<IUuidIdFilteringMapsId> {
    return new Promise<IUuidIdFilteringMapsId>((resolve, reject) => {
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

  partialUpdate(entity: IUuidIdFilteringMapsId): Promise<IUuidIdFilteringMapsId> {
    return new Promise<IUuidIdFilteringMapsId>((resolve, reject) => {
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
