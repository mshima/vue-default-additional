import axios from 'axios';

import { type IEntityUuidIdDTOMapsId } from '@/shared/model/entity-uuid-id-dto-maps-id.model';

const baseApiUrl = 'api/entity-uuid-id-dto-maps-ids';

export default class EntityUuidIdDTOMapsIdService {
  find(id: string): Promise<IEntityUuidIdDTOMapsId> {
    return new Promise<IEntityUuidIdDTOMapsId>((resolve, reject) => {
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

  create(entity: IEntityUuidIdDTOMapsId): Promise<IEntityUuidIdDTOMapsId> {
    return new Promise<IEntityUuidIdDTOMapsId>((resolve, reject) => {
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

  update(entity: IEntityUuidIdDTOMapsId): Promise<IEntityUuidIdDTOMapsId> {
    return new Promise<IEntityUuidIdDTOMapsId>((resolve, reject) => {
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

  partialUpdate(entity: IEntityUuidIdDTOMapsId): Promise<IEntityUuidIdDTOMapsId> {
    return new Promise<IEntityUuidIdDTOMapsId>((resolve, reject) => {
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
