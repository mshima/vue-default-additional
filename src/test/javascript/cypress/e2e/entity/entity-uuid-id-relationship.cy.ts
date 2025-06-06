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

describe('EntityUuidIdRelationship e2e test', () => {
  const entityUuidIdRelationshipPageUrl = '/entity-uuid-id-relationship';
  const entityUuidIdRelationshipPageUrlPattern = new RegExp('/entity-uuid-id-relationship(\\?.*)?$');
  const username = Cypress.env('E2E_USERNAME') ?? 'user';
  const password = Cypress.env('E2E_PASSWORD') ?? 'user';
  const entityUuidIdRelationshipSample = {};

  let entityUuidIdRelationship;

  beforeEach(() => {
    cy.login(username, password);
  });

  beforeEach(() => {
    cy.intercept('GET', '/api/entity-uuid-id-relationships+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/entity-uuid-id-relationships').as('postEntityRequest');
    cy.intercept('DELETE', '/api/entity-uuid-id-relationships/*').as('deleteEntityRequest');
  });

  afterEach(() => {
    if (entityUuidIdRelationship) {
      cy.authenticatedRequest({
        method: 'DELETE',
        url: `/api/entity-uuid-id-relationships/${entityUuidIdRelationship.id}`,
      }).then(() => {
        entityUuidIdRelationship = undefined;
      });
    }
  });

  it('EntityUuidIdRelationships menu should load EntityUuidIdRelationships page', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('entity-uuid-id-relationship');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response?.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('EntityUuidIdRelationship').should('exist');
    cy.url().should('match', entityUuidIdRelationshipPageUrlPattern);
  });

  describe('EntityUuidIdRelationship page', () => {
    describe('create button click', () => {
      beforeEach(() => {
        cy.visit(entityUuidIdRelationshipPageUrl);
        cy.wait('@entitiesRequest');
      });

      it('should load create EntityUuidIdRelationship page', () => {
        cy.get(entityCreateButtonSelector).click();
        cy.url().should('match', new RegExp('/entity-uuid-id-relationship/new$'));
        cy.getEntityCreateUpdateHeading('EntityUuidIdRelationship');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', entityUuidIdRelationshipPageUrlPattern);
      });
    });

    describe('with existing value', () => {
      beforeEach(() => {
        cy.authenticatedRequest({
          method: 'POST',
          url: '/api/entity-uuid-id-relationships',
          body: entityUuidIdRelationshipSample,
        }).then(({ body }) => {
          entityUuidIdRelationship = body;

          cy.intercept(
            {
              method: 'GET',
              url: '/api/entity-uuid-id-relationships+(?*|)',
              times: 1,
            },
            {
              statusCode: 200,
              body: [entityUuidIdRelationship],
            },
          ).as('entitiesRequestInternal');
        });

        cy.visit(entityUuidIdRelationshipPageUrl);

        cy.wait('@entitiesRequestInternal');
      });

      it('detail button click should load details EntityUuidIdRelationship page', () => {
        cy.get(entityDetailsButtonSelector).first().click();
        cy.getEntityDetailsHeading('entityUuidIdRelationship');
        cy.get(entityDetailsBackButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', entityUuidIdRelationshipPageUrlPattern);
      });

      it('edit button click should load edit EntityUuidIdRelationship page and go back', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('EntityUuidIdRelationship');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', entityUuidIdRelationshipPageUrlPattern);
      });

      it('edit button click should load edit EntityUuidIdRelationship page and save', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('EntityUuidIdRelationship');
        cy.get(entityCreateSaveButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', entityUuidIdRelationshipPageUrlPattern);
      });

      it('last delete button click should delete instance of EntityUuidIdRelationship', () => {
        cy.get(entityDeleteButtonSelector).last().click();
        cy.getEntityDeleteDialogHeading('entityUuidIdRelationship').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click();
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', entityUuidIdRelationshipPageUrlPattern);

        entityUuidIdRelationship = undefined;
      });
    });
  });

  describe('new EntityUuidIdRelationship page', () => {
    beforeEach(() => {
      cy.visit(`${entityUuidIdRelationshipPageUrl}`);
      cy.get(entityCreateButtonSelector).click();
      cy.getEntityCreateUpdateHeading('EntityUuidIdRelationship');
    });

    it('should create an instance of EntityUuidIdRelationship', () => {
      cy.get(entityCreateSaveButtonSelector).click();

      cy.wait('@postEntityRequest').then(({ response }) => {
        expect(response?.statusCode).to.equal(201);
        entityUuidIdRelationship = response.body;
      });
      cy.wait('@entitiesRequest').then(({ response }) => {
        expect(response?.statusCode).to.equal(200);
      });
      cy.url().should('match', entityUuidIdRelationshipPageUrlPattern);
    });
  });
});
