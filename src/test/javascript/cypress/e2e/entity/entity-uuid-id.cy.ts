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

describe('EntityUuidId e2e test', () => {
  const entityUuidIdPageUrl = '/entity-uuid-id';
  const entityUuidIdPageUrlPattern = new RegExp('/entity-uuid-id(\\?.*)?$');
  const username = Cypress.env('E2E_USERNAME') ?? 'user';
  const password = Cypress.env('E2E_PASSWORD') ?? 'user';
  const entityUuidIdSample = {};

  let entityUuidId;

  beforeEach(() => {
    cy.login(username, password);
  });

  beforeEach(() => {
    cy.intercept('GET', '/api/entity-uuid-ids+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/entity-uuid-ids').as('postEntityRequest');
    cy.intercept('DELETE', '/api/entity-uuid-ids/*').as('deleteEntityRequest');
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

  it('EntityUuidIds menu should load EntityUuidIds page', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('entity-uuid-id');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response?.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('EntityUuidId').should('exist');
    cy.url().should('match', entityUuidIdPageUrlPattern);
  });

  describe('EntityUuidId page', () => {
    describe('create button click', () => {
      beforeEach(() => {
        cy.visit(entityUuidIdPageUrl);
        cy.wait('@entitiesRequest');
      });

      it('should load create EntityUuidId page', () => {
        cy.get(entityCreateButtonSelector).click();
        cy.url().should('match', new RegExp('/entity-uuid-id/new$'));
        cy.getEntityCreateUpdateHeading('EntityUuidId');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', entityUuidIdPageUrlPattern);
      });
    });

    describe('with existing value', () => {
      beforeEach(() => {
        cy.authenticatedRequest({
          method: 'POST',
          url: '/api/entity-uuid-ids',
          body: entityUuidIdSample,
        }).then(({ body }) => {
          entityUuidId = body;

          cy.intercept(
            {
              method: 'GET',
              url: '/api/entity-uuid-ids+(?*|)',
              times: 1,
            },
            {
              statusCode: 200,
              body: [entityUuidId],
            },
          ).as('entitiesRequestInternal');
        });

        cy.visit(entityUuidIdPageUrl);

        cy.wait('@entitiesRequestInternal');
      });

      it('detail button click should load details EntityUuidId page', () => {
        cy.get(entityDetailsButtonSelector).first().click();
        cy.getEntityDetailsHeading('entityUuidId');
        cy.get(entityDetailsBackButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', entityUuidIdPageUrlPattern);
      });

      it('edit button click should load edit EntityUuidId page and go back', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('EntityUuidId');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', entityUuidIdPageUrlPattern);
      });

      it('edit button click should load edit EntityUuidId page and save', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('EntityUuidId');
        cy.get(entityCreateSaveButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', entityUuidIdPageUrlPattern);
      });

      it('last delete button click should delete instance of EntityUuidId', () => {
        cy.get(entityDeleteButtonSelector).last().click();
        cy.getEntityDeleteDialogHeading('entityUuidId').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click();
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', entityUuidIdPageUrlPattern);

        entityUuidId = undefined;
      });
    });
  });

  describe('new EntityUuidId page', () => {
    beforeEach(() => {
      cy.visit(`${entityUuidIdPageUrl}`);
      cy.get(entityCreateButtonSelector).click();
      cy.getEntityCreateUpdateHeading('EntityUuidId');
    });

    it('should create an instance of EntityUuidId', () => {
      cy.get(entityCreateSaveButtonSelector).click();

      cy.wait('@postEntityRequest').then(({ response }) => {
        expect(response?.statusCode).to.equal(201);
        entityUuidId = response.body;
      });
      cy.wait('@entitiesRequest').then(({ response }) => {
        expect(response?.statusCode).to.equal(200);
      });
      cy.url().should('match', entityUuidIdPageUrlPattern);
    });
  });
});
