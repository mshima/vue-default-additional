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

describe('UuidIdFilteringRelationship e2e test', () => {
  const uuidIdFilteringRelationshipPageUrl = '/uuid-id-filtering-relationship';
  const uuidIdFilteringRelationshipPageUrlPattern = new RegExp('/uuid-id-filtering-relationship(\\?.*)?$');
  const username = Cypress.env('E2E_USERNAME') ?? 'user';
  const password = Cypress.env('E2E_PASSWORD') ?? 'user';
  const uuidIdFilteringRelationshipSample = {};

  let uuidIdFilteringRelationship;

  beforeEach(() => {
    cy.login(username, password);
  });

  beforeEach(() => {
    cy.intercept('GET', '/api/uuid-id-filtering-relationships+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/uuid-id-filtering-relationships').as('postEntityRequest');
    cy.intercept('DELETE', '/api/uuid-id-filtering-relationships/*').as('deleteEntityRequest');
  });

  afterEach(() => {
    if (uuidIdFilteringRelationship) {
      cy.authenticatedRequest({
        method: 'DELETE',
        url: `/api/uuid-id-filtering-relationships/${uuidIdFilteringRelationship.relatedId}`,
      }).then(() => {
        uuidIdFilteringRelationship = undefined;
      });
    }
  });

  it('UuidIdFilteringRelationships menu should load UuidIdFilteringRelationships page', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('uuid-id-filtering-relationship');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response?.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('UuidIdFilteringRelationship').should('exist');
    cy.url().should('match', uuidIdFilteringRelationshipPageUrlPattern);
  });

  describe('UuidIdFilteringRelationship page', () => {
    describe('create button click', () => {
      beforeEach(() => {
        cy.visit(uuidIdFilteringRelationshipPageUrl);
        cy.wait('@entitiesRequest');
      });

      it('should load create UuidIdFilteringRelationship page', () => {
        cy.get(entityCreateButtonSelector).click();
        cy.url().should('match', new RegExp('/uuid-id-filtering-relationship/new$'));
        cy.getEntityCreateUpdateHeading('UuidIdFilteringRelationship');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', uuidIdFilteringRelationshipPageUrlPattern);
      });
    });

    describe('with existing value', () => {
      beforeEach(() => {
        cy.authenticatedRequest({
          method: 'POST',
          url: '/api/uuid-id-filtering-relationships',
          body: uuidIdFilteringRelationshipSample,
        }).then(({ body }) => {
          uuidIdFilteringRelationship = body;

          cy.intercept(
            {
              method: 'GET',
              url: '/api/uuid-id-filtering-relationships+(?*|)',
              times: 1,
            },
            {
              statusCode: 200,
              body: [uuidIdFilteringRelationship],
            },
          ).as('entitiesRequestInternal');
        });

        cy.visit(uuidIdFilteringRelationshipPageUrl);

        cy.wait('@entitiesRequestInternal');
      });

      it('detail button click should load details UuidIdFilteringRelationship page', () => {
        cy.get(entityDetailsButtonSelector).first().click();
        cy.getEntityDetailsHeading('uuidIdFilteringRelationship');
        cy.get(entityDetailsBackButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', uuidIdFilteringRelationshipPageUrlPattern);
      });

      it('edit button click should load edit UuidIdFilteringRelationship page and go back', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('UuidIdFilteringRelationship');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', uuidIdFilteringRelationshipPageUrlPattern);
      });

      it('edit button click should load edit UuidIdFilteringRelationship page and save', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('UuidIdFilteringRelationship');
        cy.get(entityCreateSaveButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', uuidIdFilteringRelationshipPageUrlPattern);
      });

      it('last delete button click should delete instance of UuidIdFilteringRelationship', () => {
        cy.get(entityDeleteButtonSelector).last().click();
        cy.getEntityDeleteDialogHeading('uuidIdFilteringRelationship').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click();
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', uuidIdFilteringRelationshipPageUrlPattern);

        uuidIdFilteringRelationship = undefined;
      });
    });
  });

  describe('new UuidIdFilteringRelationship page', () => {
    beforeEach(() => {
      cy.visit(`${uuidIdFilteringRelationshipPageUrl}`);
      cy.get(entityCreateButtonSelector).click();
      cy.getEntityCreateUpdateHeading('UuidIdFilteringRelationship');
    });

    it('should create an instance of UuidIdFilteringRelationship', () => {
      cy.get(entityCreateSaveButtonSelector).click();

      cy.wait('@postEntityRequest').then(({ response }) => {
        expect(response?.statusCode).to.equal(201);
        uuidIdFilteringRelationship = response.body;
      });
      cy.wait('@entitiesRequest').then(({ response }) => {
        expect(response?.statusCode).to.equal(200);
      });
      cy.url().should('match', uuidIdFilteringRelationshipPageUrlPattern);
    });
  });
});
