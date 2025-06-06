import axios from 'axios';

import { type IUuidIdFilteringRelationship } from '@/shared/model/uuid-id-filtering-relationship.model';

const baseApiUrl = 'api/uuid-id-filtering-relationships';

export default class UuidIdFilteringRelationshipService {
  find(relatedId: string): Promise<IUuidIdFilteringRelationship> {
    return new Promise<IUuidIdFilteringRelationship>((resolve, reject) => {
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

  delete(relatedId: string): Promise<any> {
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

  create(entity: IUuidIdFilteringRelationship): Promise<IUuidIdFilteringRelationship> {
    return new Promise<IUuidIdFilteringRelationship>((resolve, reject) => {
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

  update(entity: IUuidIdFilteringRelationship): Promise<IUuidIdFilteringRelationship> {
    return new Promise<IUuidIdFilteringRelationship>((resolve, reject) => {
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

  partialUpdate(entity: IUuidIdFilteringRelationship): Promise<IUuidIdFilteringRelationship> {
    return new Promise<IUuidIdFilteringRelationship>((resolve, reject) => {
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
