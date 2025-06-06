import axios from 'axios';

import { type IEntityCustomIdDTORel } from '@/shared/model/entity-custom-id-dto-rel.model';

const baseApiUrl = 'api/entity-custom-id-dto-rels';

export default class EntityCustomIdDTORelService {
  find(relatedId: number): Promise<IEntityCustomIdDTORel> {
    return new Promise<IEntityCustomIdDTORel>((resolve, reject) => {
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

  create(entity: IEntityCustomIdDTORel): Promise<IEntityCustomIdDTORel> {
    return new Promise<IEntityCustomIdDTORel>((resolve, reject) => {
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

  update(entity: IEntityCustomIdDTORel): Promise<IEntityCustomIdDTORel> {
    return new Promise<IEntityCustomIdDTORel>((resolve, reject) => {
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

  partialUpdate(entity: IEntityCustomIdDTORel): Promise<IEntityCustomIdDTORel> {
    return new Promise<IEntityCustomIdDTORel>((resolve, reject) => {
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
