import axios from 'axios';

import { type IEntityUuidIdMapsId } from '@/shared/model/entity-uuid-id-maps-id.model';

const baseApiUrl = 'api/entity-uuid-id-maps-ids';

export default class EntityUuidIdMapsIdService {
  find(id: string): Promise<IEntityUuidIdMapsId> {
    return new Promise<IEntityUuidIdMapsId>((resolve, reject) => {
      axios
        .get(`${baseApiUrl}/${id}`)
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

  delete(id: string): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .delete(`${baseApiUrl}/${id}`)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  create(entity: IEntityUuidIdMapsId): Promise<IEntityUuidIdMapsId> {
    return new Promise<IEntityUuidIdMapsId>((resolve, reject) => {
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

  update(entity: IEntityUuidIdMapsId): Promise<IEntityUuidIdMapsId> {
    return new Promise<IEntityUuidIdMapsId>((resolve, reject) => {
      axios
        .put(`${baseApiUrl}/${entity.id}`, entity)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  partialUpdate(entity: IEntityUuidIdMapsId): Promise<IEntityUuidIdMapsId> {
    return new Promise<IEntityUuidIdMapsId>((resolve, reject) => {
      axios
        .patch(`${baseApiUrl}/${entity.id}`, entity)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
