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

describe('EntityUuidIdDTORel e2e test', () => {
  const entityUuidIdDTORelPageUrl = '/entity-uuid-id-dto-rel';
  const entityUuidIdDTORelPageUrlPattern = new RegExp('/entity-uuid-id-dto-rel(\\?.*)?$');
  const username = Cypress.env('E2E_USERNAME') ?? 'user';
  const password = Cypress.env('E2E_PASSWORD') ?? 'user';
  const entityUuidIdDTORelSample = {};

  let entityUuidIdDTORel;

  beforeEach(() => {
    cy.login(username, password);
  });

  beforeEach(() => {
    cy.intercept('GET', '/api/entity-uuid-id-dto-rels+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/entity-uuid-id-dto-rels').as('postEntityRequest');
    cy.intercept('DELETE', '/api/entity-uuid-id-dto-rels/*').as('deleteEntityRequest');
  });

  afterEach(() => {
    if (entityUuidIdDTORel) {
      cy.authenticatedRequest({
        method: 'DELETE',
        url: `/api/entity-uuid-id-dto-rels/${entityUuidIdDTORel.id}`,
      }).then(() => {
        entityUuidIdDTORel = undefined;
      });
    }
  });

  it('EntityUuidIdDTORels menu should load EntityUuidIdDTORels page', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('entity-uuid-id-dto-rel');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response?.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('EntityUuidIdDTORel').should('exist');
    cy.url().should('match', entityUuidIdDTORelPageUrlPattern);
  });

  describe('EntityUuidIdDTORel page', () => {
    describe('create button click', () => {
      beforeEach(() => {
        cy.visit(entityUuidIdDTORelPageUrl);
        cy.wait('@entitiesRequest');
      });

      it('should load create EntityUuidIdDTORel page', () => {
        cy.get(entityCreateButtonSelector).click();
        cy.url().should('match', new RegExp('/entity-uuid-id-dto-rel/new$'));
        cy.getEntityCreateUpdateHeading('EntityUuidIdDTORel');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', entityUuidIdDTORelPageUrlPattern);
      });
    });

    describe('with existing value', () => {
      beforeEach(() => {
        cy.authenticatedRequest({
          method: 'POST',
          url: '/api/entity-uuid-id-dto-rels',
          body: entityUuidIdDTORelSample,
        }).then(({ body }) => {
          entityUuidIdDTORel = body;

          cy.intercept(
            {
              method: 'GET',
              url: '/api/entity-uuid-id-dto-rels+(?*|)',
              times: 1,
            },
            {
              statusCode: 200,
              body: [entityUuidIdDTORel],
            },
          ).as('entitiesRequestInternal');
        });

        cy.visit(entityUuidIdDTORelPageUrl);

        cy.wait('@entitiesRequestInternal');
      });

      it('detail button click should load details EntityUuidIdDTORel page', () => {
        cy.get(entityDetailsButtonSelector).first().click();
        cy.getEntityDetailsHeading('entityUuidIdDTORel');
        cy.get(entityDetailsBackButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', entityUuidIdDTORelPageUrlPattern);
      });

      it('edit button click should load edit EntityUuidIdDTORel page and go back', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('EntityUuidIdDTORel');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', entityUuidIdDTORelPageUrlPattern);
      });

      it('edit button click should load edit EntityUuidIdDTORel page and save', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('EntityUuidIdDTORel');
        cy.get(entityCreateSaveButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', entityUuidIdDTORelPageUrlPattern);
      });

      it('last delete button click should delete instance of EntityUuidIdDTORel', () => {
        cy.get(entityDeleteButtonSelector).last().click();
        cy.getEntityDeleteDialogHeading('entityUuidIdDTORel').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click();
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', entityUuidIdDTORelPageUrlPattern);

        entityUuidIdDTORel = undefined;
      });
    });
  });

  describe('new EntityUuidIdDTORel page', () => {
    beforeEach(() => {
      cy.visit(`${entityUuidIdDTORelPageUrl}`);
      cy.get(entityCreateButtonSelector).click();
      cy.getEntityCreateUpdateHeading('EntityUuidIdDTORel');
    });

    it('should create an instance of EntityUuidIdDTORel', () => {
      cy.get(entityCreateSaveButtonSelector).click();

      cy.wait('@postEntityRequest').then(({ response }) => {
        expect(response?.statusCode).to.equal(201);
        entityUuidIdDTORel = response.body;
      });
      cy.wait('@entitiesRequest').then(({ response }) => {
        expect(response?.statusCode).to.equal(200);
      });
      cy.url().should('match', entityUuidIdDTORelPageUrlPattern);
    });
  });
});
