import {
  entityConfirmDeleteButtonSelector,
  entityCreateButtonSelector,
  entityCreateCancelButtonSelector,
  entityCreateSaveButtonSelector,
  entityDeleteButtonSelector,
  entityDetailsBackButtonSelector,
  entityDetailsButtonSelector,
  entityEditButtonSelector,
  entityTableSelector,
} from '../../support/entity';

describe('EntityUuidIdMapsId e2e test', () => {
  const entityUuidIdMapsIdPageUrl = '/entity-uuid-id-maps-id';
  const entityUuidIdMapsIdPageUrlPattern = new RegExp('/entity-uuid-id-maps-id(\\?.*)?$');
  const username = Cypress.env('E2E_USERNAME') ?? 'user';
  const password = Cypress.env('E2E_PASSWORD') ?? 'user';
  const entityUuidIdMapsIdSample = {};

  let entityUuidIdMapsId;
  let entityUuidId;

  beforeEach(() => {
    cy.login(username, password);
  });

  beforeEach(() => {
    // create an instance at the required relationship entity:
    cy.authenticatedRequest({
      method: 'POST',
      url: '/api/entity-uuid-ids',
      body: {},
    }).then(({ body }) => {
      entityUuidId = body;
    });
  });

  beforeEach(() => {
    cy.intercept('GET', '/api/entity-uuid-id-maps-ids+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/entity-uuid-id-maps-ids').as('postEntityRequest');
    cy.intercept('DELETE', '/api/entity-uuid-id-maps-ids/*').as('deleteEntityRequest');
  });

  beforeEach(() => {
    // Simulate relationships api for better performance and reproducibility.
    cy.intercept('GET', '/api/entity-uuid-ids', {
      statusCode: 200,
      body: [entityUuidId],
    });

    cy.intercept('GET', '/api/entity-uuid-id-relationships', {
      statusCode: 200,
      body: [],
    });
  });

  afterEach(() => {
    if (entityUuidIdMapsId) {
      cy.authenticatedRequest({
        method: 'DELETE',
        url: `/api/entity-uuid-id-maps-ids/${entityUuidIdMapsId.id}`,
      }).then(() => {
        entityUuidIdMapsId = undefined;
      });
    }
  });

  afterEach(() => {
    if (entityUuidId) {
      cy.authenticatedRequest({
        method: 'DELETE',
        url: `/api/entity-uuid-ids/${entityUuidId.id}`,
      }).then(() => {
        entityUuidId = undefined;
      });
    }
  });

  it('EntityUuidIdMapsIds menu should load EntityUuidIdMapsIds page', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('entity-uuid-id-maps-id');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response?.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('EntityUuidIdMapsId').should('exist');
    cy.url().should('match', entityUuidIdMapsIdPageUrlPattern);
  });

  describe('EntityUuidIdMapsId page', () => {
    describe('create button click', () => {
      beforeEach(() => {
        cy.visit(entityUuidIdMapsIdPageUrl);
        cy.wait('@entitiesRequest');
      });

      it('should load create EntityUuidIdMapsId page', () => {
        cy.get(entityCreateButtonSelector).click();
        cy.url().should('match', new RegExp('/entity-uuid-id-maps-id/new$'));
        cy.getEntityCreateUpdateHeading('EntityUuidIdMapsId');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', entityUuidIdMapsIdPageUrlPattern);
      });
    });

    describe('with existing value', () => {
      beforeEach(() => {
        cy.authenticatedRequest({
          method: 'POST',
          url: '/api/entity-uuid-id-maps-ids',
          body: {
            ...entityUuidIdMapsIdSample,
            entityUuidId,
          },
        }).then(({ body }) => {
          entityUuidIdMapsId = body;

          cy.intercept(
            {
              method: 'GET',
              url: '/api/entity-uuid-id-maps-ids+(?*|)',
              times: 1,
            },
            {
              statusCode: 200,
              body: [entityUuidIdMapsId],
            },
          ).as('entitiesRequestInternal');
        });

        cy.visit(entityUuidIdMapsIdPageUrl);

        cy.wait('@entitiesRequestInternal');
      });

      it('detail button click should load details EntityUuidIdMapsId page', () => {
        cy.get(entityDetailsButtonSelector).first().click();
        cy.getEntityDetailsHeading('entityUuidIdMapsId');
        cy.get(entityDetailsBackButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', entityUuidIdMapsIdPageUrlPattern);
      });

      it('edit button click should load edit EntityUuidIdMapsId page and go back', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('EntityUuidIdMapsId');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', entityUuidIdMapsIdPageUrlPattern);
      });

      it('edit button click should load edit EntityUuidIdMapsId page and save', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('EntityUuidIdMapsId');
        cy.get(entityCreateSaveButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', entityUuidIdMapsIdPageUrlPattern);
      });

      it('last delete button click should delete instance of EntityUuidIdMapsId', () => {
        cy.get(entityDeleteButtonSelector).last().click();
        cy.getEntityDeleteDialogHeading('entityUuidIdMapsId').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click();
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', entityUuidIdMapsIdPageUrlPattern);

        entityUuidIdMapsId = undefined;
      });
    });
  });

  describe('new EntityUuidIdMapsId page', () => {
    beforeEach(() => {
      cy.visit(`${entityUuidIdMapsIdPageUrl}`);
      cy.get(entityCreateButtonSelector).click();
      cy.getEntityCreateUpdateHeading('EntityUuidIdMapsId');
    });

    it('should create an instance of EntityUuidIdMapsId', () => {
      cy.get(`[data-cy="entityUuidId"]`).select(1);

      cy.get(entityCreateSaveButtonSelector).click();

      cy.wait('@postEntityRequest').then(({ response }) => {
        expect(response?.statusCode).to.equal(201);
        entityUuidIdMapsId = response.body;
      });
      cy.wait('@entitiesRequest').then(({ response }) => {
        expect(response?.statusCode).to.equal(200);
      });
      cy.url().should('match', entityUuidIdMapsIdPageUrlPattern);
    });
  });
});
