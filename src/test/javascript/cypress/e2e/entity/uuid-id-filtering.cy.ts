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

describe('UuidIdFiltering e2e test', () => {
  const uuidIdFilteringPageUrl = '/uuid-id-filtering';
  const uuidIdFilteringPageUrlPattern = new RegExp('/uuid-id-filtering(\\?.*)?$');
  const username = Cypress.env('E2E_USERNAME') ?? 'user';
  const password = Cypress.env('E2E_PASSWORD') ?? 'user';
  const uuidIdFilteringSample = {};

  let uuidIdFiltering;

  beforeEach(() => {
    cy.login(username, password);
  });

  beforeEach(() => {
    cy.intercept('GET', '/api/uuid-id-filterings+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/uuid-id-filterings').as('postEntityRequest');
    cy.intercept('DELETE', '/api/uuid-id-filterings/*').as('deleteEntityRequest');
  });

  afterEach(() => {
    if (uuidIdFiltering) {
      cy.authenticatedRequest({
        method: 'DELETE',
        url: `/api/uuid-id-filterings/${uuidIdFiltering.customId}`,
      }).then(() => {
        uuidIdFiltering = undefined;
      });
    }
  });

  it('UuidIdFilterings menu should load UuidIdFilterings page', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('uuid-id-filtering');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response?.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('UuidIdFiltering').should('exist');
    cy.url().should('match', uuidIdFilteringPageUrlPattern);
  });

  describe('UuidIdFiltering page', () => {
    describe('create button click', () => {
      beforeEach(() => {
        cy.visit(uuidIdFilteringPageUrl);
        cy.wait('@entitiesRequest');
      });

      it('should load create UuidIdFiltering page', () => {
        cy.get(entityCreateButtonSelector).click();
        cy.url().should('match', new RegExp('/uuid-id-filtering/new$'));
        cy.getEntityCreateUpdateHeading('UuidIdFiltering');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', uuidIdFilteringPageUrlPattern);
      });
    });

    describe('with existing value', () => {
      beforeEach(() => {
        cy.authenticatedRequest({
          method: 'POST',
          url: '/api/uuid-id-filterings',
          body: uuidIdFilteringSample,
        }).then(({ body }) => {
          uuidIdFiltering = body;

          cy.intercept(
            {
              method: 'GET',
              url: '/api/uuid-id-filterings+(?*|)',
              times: 1,
            },
            {
              statusCode: 200,
              body: [uuidIdFiltering],
            },
          ).as('entitiesRequestInternal');
        });

        cy.visit(uuidIdFilteringPageUrl);

        cy.wait('@entitiesRequestInternal');
      });

      it('detail button click should load details UuidIdFiltering page', () => {
        cy.get(entityDetailsButtonSelector).first().click();
        cy.getEntityDetailsHeading('uuidIdFiltering');
        cy.get(entityDetailsBackButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', uuidIdFilteringPageUrlPattern);
      });

      it('edit button click should load edit UuidIdFiltering page and go back', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('UuidIdFiltering');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', uuidIdFilteringPageUrlPattern);
      });

      it('edit button click should load edit UuidIdFiltering page and save', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('UuidIdFiltering');
        cy.get(entityCreateSaveButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', uuidIdFilteringPageUrlPattern);
      });

      it('last delete button click should delete instance of UuidIdFiltering', () => {
        cy.get(entityDeleteButtonSelector).last().click();
        cy.getEntityDeleteDialogHeading('uuidIdFiltering').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click();
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', uuidIdFilteringPageUrlPattern);

        uuidIdFiltering = undefined;
      });
    });
  });

  describe('new UuidIdFiltering page', () => {
    beforeEach(() => {
      cy.visit(`${uuidIdFilteringPageUrl}`);
      cy.get(entityCreateButtonSelector).click();
      cy.getEntityCreateUpdateHeading('UuidIdFiltering');
    });

    it('should create an instance of UuidIdFiltering', () => {
      cy.get(entityCreateSaveButtonSelector).click();

      cy.wait('@postEntityRequest').then(({ response }) => {
        expect(response?.statusCode).to.equal(201);
        uuidIdFiltering = response.body;
      });
      cy.wait('@entitiesRequest').then(({ response }) => {
        expect(response?.statusCode).to.equal(200);
      });
      cy.url().should('match', uuidIdFilteringPageUrlPattern);
    });
  });
});
