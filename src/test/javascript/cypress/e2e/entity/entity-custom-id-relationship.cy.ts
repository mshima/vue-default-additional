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

describe('EntityCustomIdRelationship e2e test', () => {
  const entityCustomIdRelationshipPageUrl = '/entity-custom-id-relationship';
  const entityCustomIdRelationshipPageUrlPattern = new RegExp('/entity-custom-id-relationship(\\?.*)?$');
  const username = Cypress.env('E2E_USERNAME') ?? 'user';
  const password = Cypress.env('E2E_PASSWORD') ?? 'user';
  const entityCustomIdRelationshipSample = {};

  let entityCustomIdRelationship;

  beforeEach(() => {
    cy.login(username, password);
  });

  beforeEach(() => {
    cy.intercept('GET', '/api/entity-custom-id-relationships+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/entity-custom-id-relationships').as('postEntityRequest');
    cy.intercept('DELETE', '/api/entity-custom-id-relationships/*').as('deleteEntityRequest');
  });

  afterEach(() => {
    if (entityCustomIdRelationship) {
      cy.authenticatedRequest({
        method: 'DELETE',
        url: `/api/entity-custom-id-relationships/${entityCustomIdRelationship.relatedId}`,
      }).then(() => {
        entityCustomIdRelationship = undefined;
      });
    }
  });

  it('EntityCustomIdRelationships menu should load EntityCustomIdRelationships page', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('entity-custom-id-relationship');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response?.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('EntityCustomIdRelationship').should('exist');
    cy.url().should('match', entityCustomIdRelationshipPageUrlPattern);
  });

  describe('EntityCustomIdRelationship page', () => {
    describe('create button click', () => {
      beforeEach(() => {
        cy.visit(entityCustomIdRelationshipPageUrl);
        cy.wait('@entitiesRequest');
      });

      it('should load create EntityCustomIdRelationship page', () => {
        cy.get(entityCreateButtonSelector).click();
        cy.url().should('match', new RegExp('/entity-custom-id-relationship/new$'));
        cy.getEntityCreateUpdateHeading('EntityCustomIdRelationship');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', entityCustomIdRelationshipPageUrlPattern);
      });
    });

    describe('with existing value', () => {
      beforeEach(() => {
        cy.authenticatedRequest({
          method: 'POST',
          url: '/api/entity-custom-id-relationships',
          body: entityCustomIdRelationshipSample,
        }).then(({ body }) => {
          entityCustomIdRelationship = body;

          cy.intercept(
            {
              method: 'GET',
              url: '/api/entity-custom-id-relationships+(?*|)',
              times: 1,
            },
            {
              statusCode: 200,
              body: [entityCustomIdRelationship],
            },
          ).as('entitiesRequestInternal');
        });

        cy.visit(entityCustomIdRelationshipPageUrl);

        cy.wait('@entitiesRequestInternal');
      });

      it('detail button click should load details EntityCustomIdRelationship page', () => {
        cy.get(entityDetailsButtonSelector).first().click();
        cy.getEntityDetailsHeading('entityCustomIdRelationship');
        cy.get(entityDetailsBackButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', entityCustomIdRelationshipPageUrlPattern);
      });

      it('edit button click should load edit EntityCustomIdRelationship page and go back', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('EntityCustomIdRelationship');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', entityCustomIdRelationshipPageUrlPattern);
      });

      it('edit button click should load edit EntityCustomIdRelationship page and save', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('EntityCustomIdRelationship');
        cy.get(entityCreateSaveButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', entityCustomIdRelationshipPageUrlPattern);
      });

      it('last delete button click should delete instance of EntityCustomIdRelationship', () => {
        cy.get(entityDeleteButtonSelector).last().click();
        cy.getEntityDeleteDialogHeading('entityCustomIdRelationship').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click();
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', entityCustomIdRelationshipPageUrlPattern);

        entityCustomIdRelationship = undefined;
      });
    });
  });

  describe('new EntityCustomIdRelationship page', () => {
    beforeEach(() => {
      cy.visit(`${entityCustomIdRelationshipPageUrl}`);
      cy.get(entityCreateButtonSelector).click();
      cy.getEntityCreateUpdateHeading('EntityCustomIdRelationship');
    });

    it('should create an instance of EntityCustomIdRelationship', () => {
      cy.get(entityCreateSaveButtonSelector).click();

      cy.wait('@postEntityRequest').then(({ response }) => {
        expect(response?.statusCode).to.equal(201);
        entityCustomIdRelationship = response.body;
      });
      cy.wait('@entitiesRequest').then(({ response }) => {
        expect(response?.statusCode).to.equal(200);
      });
      cy.url().should('match', entityCustomIdRelationshipPageUrlPattern);
    });
  });
});
