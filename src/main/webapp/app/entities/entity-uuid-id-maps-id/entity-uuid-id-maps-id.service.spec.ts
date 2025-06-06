import axios from 'axios';
import sinon from 'sinon';

import EntityUuidIdMapsIdService from './entity-uuid-id-maps-id.service';
import { EntityUuidIdMapsId } from '@/shared/model/entity-uuid-id-maps-id.model';

const error = {
  response: {
    status: null,
    data: {
      type: null,
    },
  },
};

const axiosStub = {
  get: sinon.stub(axios, 'get'),
  post: sinon.stub(axios, 'post'),
  put: sinon.stub(axios, 'put'),
  patch: sinon.stub(axios, 'patch'),
  delete: sinon.stub(axios, 'delete'),
};

describe('Service Tests', () => {
  describe('EntityUuidIdMapsId Service', () => {
    let service: EntityUuidIdMapsIdService;
    let elemDefault;

    beforeEach(() => {
      service = new EntityUuidIdMapsIdService();
      elemDefault = new EntityUuidIdMapsId('9fec3727-3421-4967-b213-ba36557ca194');
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = { ...elemDefault };
        axiosStub.get.resolves({ data: returnedFromService });

        return service.find('9fec3727-3421-4967-b213-ba36557ca194').then(res => {
          expect(res).toMatchObject(elemDefault);
        });
      });

      it('should not find an element', async () => {
        axiosStub.get.rejects(error);
        return service
          .find('9fec3727-3421-4967-b213-ba36557ca194')
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should create a EntityUuidIdMapsId', async () => {
        const returnedFromService = { id: '9fec3727-3421-4967-b213-ba36557ca194', ...elemDefault };
        const expected = { ...returnedFromService };

        axiosStub.post.resolves({ data: returnedFromService });
        return service.create({}).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not create a EntityUuidIdMapsId', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a EntityUuidIdMapsId', async () => {
        const returnedFromService = { ...elemDefault };

        const expected = { ...returnedFromService };
        axiosStub.put.resolves({ data: returnedFromService });

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a EntityUuidIdMapsId', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a EntityUuidIdMapsId', async () => {
        const patchObject = { ...new EntityUuidIdMapsId() };
        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = { ...returnedFromService };
        axiosStub.patch.resolves({ data: returnedFromService });

        return service.partialUpdate(patchObject).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not partial update a EntityUuidIdMapsId', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of EntityUuidIdMapsId', async () => {
        const returnedFromService = { ...elemDefault };
        const expected = { ...returnedFromService };
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve().then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of EntityUuidIdMapsId', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a EntityUuidIdMapsId', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete('9fec3727-3421-4967-b213-ba36557ca194').then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a EntityUuidIdMapsId', async () => {
        axiosStub.delete.rejects(error);

        return service
          .delete('9fec3727-3421-4967-b213-ba36557ca194')
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });
    });
  });
});
