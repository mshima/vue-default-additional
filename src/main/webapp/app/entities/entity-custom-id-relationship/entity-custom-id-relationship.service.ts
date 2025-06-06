import axios from 'axios';

import { type IEntityCustomIdRelationship } from '@/shared/model/entity-custom-id-relationship.model';

const baseApiUrl = 'api/entity-custom-id-relationships';

export default class EntityCustomIdRelationshipService {
  find(relatedId: number): Promise<IEntityCustomIdRelationship> {
    return new Promise<IEntityCustomIdRelationship>((resolve, reject) => {
      axios
        .get(`${baseApiUrl}/${relatedId}`)
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

  delete(relatedId: number): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .delete(`${baseApiUrl}/${relatedId}`)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  create(entity: IEntityCustomIdRelationship): Promise<IEntityCustomIdRelationship> {
    return new Promise<IEntityCustomIdRelationship>((resolve, reject) => {
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

  update(entity: IEntityCustomIdRelationship): Promise<IEntityCustomIdRelationship> {
    return new Promise<IEntityCustomIdRelationship>((resolve, reject) => {
      axios
        .put(`${baseApiUrl}/${entity.relatedId}`, entity)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  partialUpdate(entity: IEntityCustomIdRelationship): Promise<IEntityCustomIdRelationship> {
    return new Promise<IEntityCustomIdRelationship>((resolve, reject) => {
      axios
        .patch(`${baseApiUrl}/${entity.relatedId}`, entity)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
