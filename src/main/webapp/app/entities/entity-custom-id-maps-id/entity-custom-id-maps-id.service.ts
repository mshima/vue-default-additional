import axios from 'axios';

import { type IEntityCustomIdMapsId } from '@/shared/model/entity-custom-id-maps-id.model';

const baseApiUrl = 'api/entity-custom-id-maps-ids';

export default class EntityCustomIdMapsIdService {
  find(customId: number): Promise<IEntityCustomIdMapsId> {
    return new Promise<IEntityCustomIdMapsId>((resolve, reject) => {
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

  delete(customId: number): Promise<any> {
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

  create(entity: IEntityCustomIdMapsId): Promise<IEntityCustomIdMapsId> {
    return new Promise<IEntityCustomIdMapsId>((resolve, reject) => {
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

  update(entity: IEntityCustomIdMapsId): Promise<IEntityCustomIdMapsId> {
    return new Promise<IEntityCustomIdMapsId>((resolve, reject) => {
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

  partialUpdate(entity: IEntityCustomIdMapsId): Promise<IEntityCustomIdMapsId> {
    return new Promise<IEntityCustomIdMapsId>((resolve, reject) => {
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
