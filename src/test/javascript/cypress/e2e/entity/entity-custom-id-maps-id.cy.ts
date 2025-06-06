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

describe('EntityCustomIdMapsId e2e test', () => {
  const entityCustomIdMapsIdPageUrl = '/entity-custom-id-maps-id';
  const entityCustomIdMapsIdPageUrlPattern = new RegExp('/entity-custom-id-maps-id(\\?.*)?$');
  const username = Cypress.env('E2E_USERNAME') ?? 'user';
  const password = Cypress.env('E2E_PASSWORD') ?? 'user';
  const entityCustomIdMapsIdSample = {};

  let entityCustomIdMapsId;
  let entityCustomId;

  beforeEach(() => {
    cy.login(username, password);
  });

  beforeEach(() => {
    // create an instance at the required relationship entity:
    cy.authenticatedRequest({
      method: 'POST',
      url: '/api/entity-custom-ids',
      body: {},
    }).then(({ body }) => {
      entityCustomId = body;
    });
  });

  beforeEach(() => {
    cy.intercept('GET', '/api/entity-custom-id-maps-ids+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/entity-custom-id-maps-ids').as('postEntityRequest');
    cy.intercept('DELETE', '/api/entity-custom-id-maps-ids/*').as('deleteEntityRequest');
  });

  beforeEach(() => {
    // Simulate relationships api for better performance and reproducibility.
    cy.intercept('GET', '/api/entity-custom-ids', {
      statusCode: 200,
      body: [entityCustomId],
    });

    cy.intercept('GET', '/api/entity-custom-id-relationships', {
      statusCode: 200,
      body: [],
    });
  });

  afterEach(() => {
    if (entityCustomIdMapsId) {
      cy.authenticatedRequest({
        method: 'DELETE',
        url: `/api/entity-custom-id-maps-ids/${entityCustomIdMapsId.customId}`,
      }).then(() => {
        entityCustomIdMapsId = undefined;
      });
    }
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

  it('EntityCustomIdMapsIds menu should load EntityCustomIdMapsIds page', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('entity-custom-id-maps-id');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response?.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('EntityCustomIdMapsId').should('exist');
    cy.url().should('match', entityCustomIdMapsIdPageUrlPattern);
  });

  describe('EntityCustomIdMapsId page', () => {
    describe('create button click', () => {
      beforeEach(() => {
        cy.visit(entityCustomIdMapsIdPageUrl);
        cy.wait('@entitiesRequest');
      });

      it('should load create EntityCustomIdMapsId page', () => {
        cy.get(entityCreateButtonSelector).click();
        cy.url().should('match', new RegExp('/entity-custom-id-maps-id/new$'));
        cy.getEntityCreateUpdateHeading('EntityCustomIdMapsId');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', entityCustomIdMapsIdPageUrlPattern);
      });
    });

    describe('with existing value', () => {
      beforeEach(() => {
        cy.authenticatedRequest({
          method: 'POST',
          url: '/api/entity-custom-id-maps-ids',
          body: {
            ...entityCustomIdMapsIdSample,
            entityCustomId,
          },
        }).then(({ body }) => {
          entityCustomIdMapsId = body;

          cy.intercept(
            {
              method: 'GET',
              url: '/api/entity-custom-id-maps-ids+(?*|)',
              times: 1,
            },
            {
              statusCode: 200,
              body: [entityCustomIdMapsId],
            },
          ).as('entitiesRequestInternal');
        });

        cy.visit(entityCustomIdMapsIdPageUrl);

        cy.wait('@entitiesRequestInternal');
      });

      it('detail button click should load details EntityCustomIdMapsId page', () => {
        cy.get(entityDetailsButtonSelector).first().click();
        cy.getEntityDetailsHeading('entityCustomIdMapsId');
        cy.get(entityDetailsBackButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', entityCustomIdMapsIdPageUrlPattern);
      });

      it('edit button click should load edit EntityCustomIdMapsId page and go back', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('EntityCustomIdMapsId');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', entityCustomIdMapsIdPageUrlPattern);
      });

      it('edit button click should load edit EntityCustomIdMapsId page and save', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('EntityCustomIdMapsId');
        cy.get(entityCreateSaveButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', entityCustomIdMapsIdPageUrlPattern);
      });

      it('last delete button click should delete instance of EntityCustomIdMapsId', () => {
        cy.get(entityDeleteButtonSelector).last().click();
        cy.getEntityDeleteDialogHeading('entityCustomIdMapsId').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click();
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', entityCustomIdMapsIdPageUrlPattern);

        entityCustomIdMapsId = undefined;
      });
    });
  });

  describe('new EntityCustomIdMapsId page', () => {
    beforeEach(() => {
      cy.visit(`${entityCustomIdMapsIdPageUrl}`);
      cy.get(entityCreateButtonSelector).click();
      cy.getEntityCreateUpdateHeading('EntityCustomIdMapsId');
    });

    it('should create an instance of EntityCustomIdMapsId', () => {
      cy.get(`[data-cy="entityCustomId"]`).select(1);

      cy.get(entityCreateSaveButtonSelector).click();

      cy.wait('@postEntityRequest').then(({ response }) => {
        expect(response?.statusCode).to.equal(201);
        entityCustomIdMapsId = response.body;
      });
      cy.wait('@entitiesRequest').then(({ response }) => {
        expect(response?.statusCode).to.equal(200);
      });
      cy.url().should('match', entityCustomIdMapsIdPageUrlPattern);
    });
  });
});
