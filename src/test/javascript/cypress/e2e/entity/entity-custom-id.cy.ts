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

describe('EntityCustomId e2e test', () => {
  const entityCustomIdPageUrl = '/entity-custom-id';
  const entityCustomIdPageUrlPattern = new RegExp('/entity-custom-id(\\?.*)?$');
  const username = Cypress.env('E2E_USERNAME') ?? 'user';
  const password = Cypress.env('E2E_PASSWORD') ?? 'user';
  const entityCustomIdSample = {};

  let entityCustomId;

  beforeEach(() => {
    cy.login(username, password);
  });

  beforeEach(() => {
    cy.intercept('GET', '/api/entity-custom-ids+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/entity-custom-ids').as('postEntityRequest');
    cy.intercept('DELETE', '/api/entity-custom-ids/*').as('deleteEntityRequest');
  });

  afterEach(() => {
    if (entityCustomId) {
      cy.authenticatedRequest({
        method: 'DELETE',
        url: `/api/entity-custom-ids/${entityCustomId.customId}`,
      }).then(() => {
        entityCustomId = undefined;
      });
    }
  });

  it('EntityCustomIds menu should load EntityCustomIds page', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('entity-custom-id');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response?.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('EntityCustomId').should('exist');
    cy.url().should('match', entityCustomIdPageUrlPattern);
  });

  describe('EntityCustomId page', () => {
    describe('create button click', () => {
      beforeEach(() => {
        cy.visit(entityCustomIdPageUrl);
        cy.wait('@entitiesRequest');
      });

      it('should load create EntityCustomId page', () => {
        cy.get(entityCreateButtonSelector).click();
        cy.url().should('match', new RegExp('/entity-custom-id/new$'));
        cy.getEntityCreateUpdateHeading('EntityCustomId');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', entityCustomIdPageUrlPattern);
      });
    });

    describe('with existing value', () => {
      beforeEach(() => {
        cy.authenticatedRequest({
          method: 'POST',
          url: '/api/entity-custom-ids',
          body: entityCustomIdSample,
        }).then(({ body }) => {
          entityCustomId = body;

          cy.intercept(
            {
              method: 'GET',
              url: '/api/entity-custom-ids+(?*|)',
              times: 1,
            },
            {
              statusCode: 200,
              body: [entityCustomId],
            },
          ).as('entitiesRequestInternal');
        });

        cy.visit(entityCustomIdPageUrl);

        cy.wait('@entitiesRequestInternal');
      });

      it('detail button click should load details EntityCustomId page', () => {
        cy.get(entityDetailsButtonSelector).first().click();
        cy.getEntityDetailsHeading('entityCustomId');
        cy.get(entityDetailsBackButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', entityCustomIdPageUrlPattern);
      });

      it('edit button click should load edit EntityCustomId page and go back', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('EntityCustomId');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', entityCustomIdPageUrlPattern);
      });

      it('edit button click should load edit EntityCustomId page and save', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('EntityCustomId');
        cy.get(entityCreateSaveButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', entityCustomIdPageUrlPattern);
      });

      it('last delete button click should delete instance of EntityCustomId', () => {
        cy.get(entityDeleteButtonSelector).last().click();
        cy.getEntityDeleteDialogHeading('entityCustomId').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click();
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', entityCustomIdPageUrlPattern);

        entityCustomId = undefined;
      });
    });
  });

  describe('new EntityCustomId page', () => {
    beforeEach(() => {
      cy.visit(`${entityCustomIdPageUrl}`);
      cy.get(entityCreateButtonSelector).click();
      cy.getEntityCreateUpdateHeading('EntityCustomId');
    });

    it('should create an instance of EntityCustomId', () => {
      cy.get(entityCreateSaveButtonSelector).click();

      cy.wait('@postEntityRequest').then(({ response }) => {
        expect(response?.statusCode).to.equal(201);
        entityCustomId = response.body;
      });
      cy.wait('@entitiesRequest').then(({ response }) => {
        expect(response?.statusCode).to.equal(200);
      });
      cy.url().should('match', entityCustomIdPageUrlPattern);
    });
  });
});
