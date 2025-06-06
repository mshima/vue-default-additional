import axios from 'axios';

import { type IEntityCustomIdDTOMapsId } from '@/shared/model/entity-custom-id-dto-maps-id.model';

const baseApiUrl = 'api/entity-custom-id-dto-maps-ids';

export default class EntityCustomIdDTOMapsIdService {
  find(customId: number): Promise<IEntityCustomIdDTOMapsId> {
    return new Promise<IEntityCustomIdDTOMapsId>((resolve, reject) => {
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

  create(entity: IEntityCustomIdDTOMapsId): Promise<IEntityCustomIdDTOMapsId> {
    return new Promise<IEntityCustomIdDTOMapsId>((resolve, reject) => {
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

  update(entity: IEntityCustomIdDTOMapsId): Promise<IEntityCustomIdDTOMapsId> {
    return new Promise<IEntityCustomIdDTOMapsId>((resolve, reject) => {
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

  partialUpdate(entity: IEntityCustomIdDTOMapsId): Promise<IEntityCustomIdDTOMapsId> {
    return new Promise<IEntityCustomIdDTOMapsId>((resolve, reject) => {
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
