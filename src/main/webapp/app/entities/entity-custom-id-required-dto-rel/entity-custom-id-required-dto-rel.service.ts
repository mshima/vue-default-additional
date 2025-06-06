import axios from 'axios';

import { type IEntityCustomIdRequiredDTORel } from '@/shared/model/entity-custom-id-required-dto-rel.model';

const baseApiUrl = 'api/entity-custom-id-required-dto-rels';

export default class EntityCustomIdRequiredDTORelService {
  find(relatedId: number): Promise<IEntityCustomIdRequiredDTORel> {
    return new Promise<IEntityCustomIdRequiredDTORel>((resolve, reject) => {
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

  create(entity: IEntityCustomIdRequiredDTORel): Promise<IEntityCustomIdRequiredDTORel> {
    return new Promise<IEntityCustomIdRequiredDTORel>((resolve, reject) => {
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

  update(entity: IEntityCustomIdRequiredDTORel): Promise<IEntityCustomIdRequiredDTORel> {
    return new Promise<IEntityCustomIdRequiredDTORel>((resolve, reject) => {
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

  partialUpdate(entity: IEntityCustomIdRequiredDTORel): Promise<IEntityCustomIdRequiredDTORel> {
    return new Promise<IEntityCustomIdRequiredDTORel>((resolve, reject) => {
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
