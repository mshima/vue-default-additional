import axios from 'axios';

import { type IEntityUuidIdDTORel } from '@/shared/model/entity-uuid-id-dto-rel.model';

const baseApiUrl = 'api/entity-uuid-id-dto-rels';

export default class EntityUuidIdDTORelService {
  find(id: string): Promise<IEntityUuidIdDTORel> {
    return new Promise<IEntityUuidIdDTORel>((resolve, reject) => {
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

  create(entity: IEntityUuidIdDTORel): Promise<IEntityUuidIdDTORel> {
    return new Promise<IEntityUuidIdDTORel>((resolve, reject) => {
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

  update(entity: IEntityUuidIdDTORel): Promise<IEntityUuidIdDTORel> {
    return new Promise<IEntityUuidIdDTORel>((resolve, reject) => {
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

  partialUpdate(entity: IEntityUuidIdDTORel): Promise<IEntityUuidIdDTORel> {
    return new Promise<IEntityUuidIdDTORel>((resolve, reject) => {
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
