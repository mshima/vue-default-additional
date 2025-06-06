import axios from 'axios';

import { type IEntityCustomIdRequiredDTOMapsId } from '@/shared/model/entity-custom-id-required-dto-maps-id.model';

const baseApiUrl = 'api/entity-custom-id-required-dto-maps-ids';

export default class EntityCustomIdRequiredDTOMapsIdService {
  find(customId: number): Promise<IEntityCustomIdRequiredDTOMapsId> {
    return new Promise<IEntityCustomIdRequiredDTOMapsId>((resolve, reject) => {
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

  create(entity: IEntityCustomIdRequiredDTOMapsId): Promise<IEntityCustomIdRequiredDTOMapsId> {
    return new Promise<IEntityCustomIdRequiredDTOMapsId>((resolve, reject) => {
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

  update(entity: IEntityCustomIdRequiredDTOMapsId): Promise<IEntityCustomIdRequiredDTOMapsId> {
    return new Promise<IEntityCustomIdRequiredDTOMapsId>((resolve, reject) => {
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

  partialUpdate(entity: IEntityCustomIdRequiredDTOMapsId): Promise<IEntityCustomIdRequiredDTOMapsId> {
    return new Promise<IEntityCustomIdRequiredDTOMapsId>((resolve, reject) => {
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
