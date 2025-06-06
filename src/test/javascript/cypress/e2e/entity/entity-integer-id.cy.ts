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

describe('EntityIntegerId e2e test', () => {
  const entityIntegerIdPageUrl = '/entity-integer-id';
  const entityIntegerIdPageUrlPattern = new RegExp('/entity-integer-id(\\?.*)?$');
  const username = Cypress.env('E2E_USERNAME') ?? 'user';
  const password = Cypress.env('E2E_PASSWORD') ?? 'user';
  const entityIntegerIdSample = {};

  let entityIntegerId;

  beforeEach(() => {
    cy.login(username, password);
  });

  beforeEach(() => {
    cy.intercept('GET', '/api/entity-integer-ids+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/entity-integer-ids').as('postEntityRequest');
    cy.intercept('DELETE', '/api/entity-integer-ids/*').as('deleteEntityRequest');
  });

  afterEach(() => {
    if (entityIntegerId) {
      cy.authenticatedRequest({
        method: 'DELETE',
        url: `/api/entity-integer-ids/${entityIntegerId.id}`,
      }).then(() => {
        entityIntegerId = undefined;
      });
    }
  });

  it('EntityIntegerIds menu should load EntityIntegerIds page', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('entity-integer-id');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response?.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('EntityIntegerId').should('exist');
    cy.url().should('match', entityIntegerIdPageUrlPattern);
  });

  describe('EntityIntegerId page', () => {
    describe('create button click', () => {
      beforeEach(() => {
        cy.visit(entityIntegerIdPageUrl);
        cy.wait('@entitiesRequest');
      });

      it('should load create EntityIntegerId page', () => {
        cy.get(entityCreateButtonSelector).click();
        cy.url().should('match', new RegExp('/entity-integer-id/new$'));
        cy.getEntityCreateUpdateHeading('EntityIntegerId');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', entityIntegerIdPageUrlPattern);
      });
    });

    describe('with existing value', () => {
      beforeEach(() => {
        cy.authenticatedRequest({
          method: 'POST',
          url: '/api/entity-integer-ids',
          body: entityIntegerIdSample,
        }).then(({ body }) => {
          entityIntegerId = body;

          cy.intercept(
            {
              method: 'GET',
              url: '/api/entity-integer-ids+(?*|)',
              times: 1,
            },
            {
              statusCode: 200,
              body: [entityIntegerId],
            },
          ).as('entitiesRequestInternal');
        });

        cy.visit(entityIntegerIdPageUrl);

        cy.wait('@entitiesRequestInternal');
      });

      it('detail button click should load details EntityIntegerId page', () => {
        cy.get(entityDetailsButtonSelector).first().click();
        cy.getEntityDetailsHeading('entityIntegerId');
        cy.get(entityDetailsBackButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', entityIntegerIdPageUrlPattern);
      });

      it('edit button click should load edit EntityIntegerId page and go back', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('EntityIntegerId');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', entityIntegerIdPageUrlPattern);
      });

      it('edit button click should load edit EntityIntegerId page and save', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('EntityIntegerId');
        cy.get(entityCreateSaveButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', entityIntegerIdPageUrlPattern);
      });

      it('last delete button click should delete instance of EntityIntegerId', () => {
        cy.get(entityDeleteButtonSelector).last().click();
        cy.getEntityDeleteDialogHeading('entityIntegerId').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click();
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', entityIntegerIdPageUrlPattern);

        entityIntegerId = undefined;
      });
    });
  });

  describe('new EntityIntegerId page', () => {
    beforeEach(() => {
      cy.visit(`${entityIntegerIdPageUrl}`);
      cy.get(entityCreateButtonSelector).click();
      cy.getEntityCreateUpdateHeading('EntityIntegerId');
    });

    it('should create an instance of EntityIntegerId', () => {
      cy.get(`[data-cy="name"]`).type('narrowcast yet readmit');
      cy.get(`[data-cy="name"]`).should('have.value', 'narrowcast yet readmit');

      cy.get(entityCreateSaveButtonSelector).click();

      cy.wait('@postEntityRequest').then(({ response }) => {
        expect(response?.statusCode).to.equal(201);
        entityIntegerId = response.body;
      });
      cy.wait('@entitiesRequest').then(({ response }) => {
        expect(response?.statusCode).to.equal(200);
      });
      cy.url().should('match', entityIntegerIdPageUrlPattern);
    });
  });
});
