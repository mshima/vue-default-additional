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

describe('EntityCustomIdDTORel e2e test', () => {
  const entityCustomIdDTORelPageUrl = '/entity-custom-id-dto-rel';
  const entityCustomIdDTORelPageUrlPattern = new RegExp('/entity-custom-id-dto-rel(\\?.*)?$');
  const username = Cypress.env('E2E_USERNAME') ?? 'user';
  const password = Cypress.env('E2E_PASSWORD') ?? 'user';
  const entityCustomIdDTORelSample = {};

  let entityCustomIdDTORel;

  beforeEach(() => {
    cy.login(username, password);
  });

  beforeEach(() => {
    cy.intercept('GET', '/api/entity-custom-id-dto-rels+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/entity-custom-id-dto-rels').as('postEntityRequest');
    cy.intercept('DELETE', '/api/entity-custom-id-dto-rels/*').as('deleteEntityRequest');
  });

  afterEach(() => {
    if (entityCustomIdDTORel) {
      cy.authenticatedRequest({
        method: 'DELETE',
        url: `/api/entity-custom-id-dto-rels/${entityCustomIdDTORel.relatedId}`,
      }).then(() => {
        entityCustomIdDTORel = undefined;
      });
    }
  });

  it('EntityCustomIdDTORels menu should load EntityCustomIdDTORels page', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('entity-custom-id-dto-rel');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response?.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('EntityCustomIdDTORel').should('exist');
    cy.url().should('match', entityCustomIdDTORelPageUrlPattern);
  });

  describe('EntityCustomIdDTORel page', () => {
    describe('create button click', () => {
      beforeEach(() => {
        cy.visit(entityCustomIdDTORelPageUrl);
        cy.wait('@entitiesRequest');
      });

      it('should load create EntityCustomIdDTORel page', () => {
        cy.get(entityCreateButtonSelector).click();
        cy.url().should('match', new RegExp('/entity-custom-id-dto-rel/new$'));
        cy.getEntityCreateUpdateHeading('EntityCustomIdDTORel');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', entityCustomIdDTORelPageUrlPattern);
      });
    });

    describe('with existing value', () => {
      beforeEach(() => {
        cy.authenticatedRequest({
          method: 'POST',
          url: '/api/entity-custom-id-dto-rels',
          body: entityCustomIdDTORelSample,
        }).then(({ body }) => {
          entityCustomIdDTORel = body;

          cy.intercept(
            {
              method: 'GET',
              url: '/api/entity-custom-id-dto-rels+(?*|)',
              times: 1,
            },
            {
              statusCode: 200,
              body: [entityCustomIdDTORel],
            },
          ).as('entitiesRequestInternal');
        });

        cy.visit(entityCustomIdDTORelPageUrl);

        cy.wait('@entitiesRequestInternal');
      });

      it('detail button click should load details EntityCustomIdDTORel page', () => {
        cy.get(entityDetailsButtonSelector).first().click();
        cy.getEntityDetailsHeading('entityCustomIdDTORel');
        cy.get(entityDetailsBackButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', entityCustomIdDTORelPageUrlPattern);
      });

      it('edit button click should load edit EntityCustomIdDTORel page and go back', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('EntityCustomIdDTORel');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', entityCustomIdDTORelPageUrlPattern);
      });

      it('edit button click should load edit EntityCustomIdDTORel page and save', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('EntityCustomIdDTORel');
        cy.get(entityCreateSaveButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', entityCustomIdDTORelPageUrlPattern);
      });

      it('last delete button click should delete instance of EntityCustomIdDTORel', () => {
        cy.get(entityDeleteButtonSelector).last().click();
        cy.getEntityDeleteDialogHeading('entityCustomIdDTORel').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click();
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', entityCustomIdDTORelPageUrlPattern);

        entityCustomIdDTORel = undefined;
      });
    });
  });

  describe('new EntityCustomIdDTORel page', () => {
    beforeEach(() => {
      cy.visit(`${entityCustomIdDTORelPageUrl}`);
      cy.get(entityCreateButtonSelector).click();
      cy.getEntityCreateUpdateHeading('EntityCustomIdDTORel');
    });

    it('should create an instance of EntityCustomIdDTORel', () => {
      cy.get(entityCreateSaveButtonSelector).click();

      cy.wait('@postEntityRequest').then(({ response }) => {
        expect(response?.statusCode).to.equal(201);
        entityCustomIdDTORel = response.body;
      });
      cy.wait('@entitiesRequest').then(({ response }) => {
        expect(response?.statusCode).to.equal(200);
      });
      cy.url().should('match', entityCustomIdDTORelPageUrlPattern);
    });
  });
});
