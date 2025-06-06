import axios from 'axios';

import { type IEntityUuidIdRelationship } from '@/shared/model/entity-uuid-id-relationship.model';

const baseApiUrl = 'api/entity-uuid-id-relationships';

export default class EntityUuidIdRelationshipService {
  find(id: string): Promise<IEntityUuidIdRelationship> {
    return new Promise<IEntityUuidIdRelationship>((resolve, reject) => {
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

  create(entity: IEntityUuidIdRelationship): Promise<IEntityUuidIdRelationship> {
    return new Promise<IEntityUuidIdRelationship>((resolve, reject) => {
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

  update(entity: IEntityUuidIdRelationship): Promise<IEntityUuidIdRelationship> {
    return new Promise<IEntityUuidIdRelationship>((resolve, reject) => {
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

  partialUpdate(entity: IEntityUuidIdRelationship): Promise<IEntityUuidIdRelationship> {
    return new Promise<IEntityUuidIdRelationship>((resolve, reject) => {
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
