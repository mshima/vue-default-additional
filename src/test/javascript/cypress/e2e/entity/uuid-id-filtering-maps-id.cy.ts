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

describe('UuidIdFilteringMapsId e2e test', () => {
  const uuidIdFilteringMapsIdPageUrl = '/uuid-id-filtering-maps-id';
  const uuidIdFilteringMapsIdPageUrlPattern = new RegExp('/uuid-id-filtering-maps-id(\\?.*)?$');
  const username = Cypress.env('E2E_USERNAME') ?? 'user';
  const password = Cypress.env('E2E_PASSWORD') ?? 'user';
  const uuidIdFilteringMapsIdSample = {};

  let uuidIdFilteringMapsId;
  let uuidIdFiltering;

  beforeEach(() => {
    cy.login(username, password);
  });

  beforeEach(() => {
    // create an instance at the required relationship entity:
    cy.authenticatedRequest({
      method: 'POST',
      url: '/api/uuid-id-filterings',
      body: {},
    }).then(({ body }) => {
      uuidIdFiltering = body;
    });
  });

  beforeEach(() => {
    cy.intercept('GET', '/api/uuid-id-filtering-maps-ids+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/uuid-id-filtering-maps-ids').as('postEntityRequest');
    cy.intercept('DELETE', '/api/uuid-id-filtering-maps-ids/*').as('deleteEntityRequest');
  });

  beforeEach(() => {
    // Simulate relationships api for better performance and reproducibility.
    cy.intercept('GET', '/api/uuid-id-filterings', {
      statusCode: 200,
      body: [uuidIdFiltering],
    });

    cy.intercept('GET', '/api/uuid-id-filtering-relationships', {
      statusCode: 200,
      body: [],
    });
  });

  afterEach(() => {
    if (uuidIdFilteringMapsId) {
      cy.authenticatedRequest({
        method: 'DELETE',
        url: `/api/uuid-id-filtering-maps-ids/${uuidIdFilteringMapsId.customId}`,
      }).then(() => {
        uuidIdFilteringMapsId = undefined;
      });
    }
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

  it('UuidIdFilteringMapsIds menu should load UuidIdFilteringMapsIds page', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('uuid-id-filtering-maps-id');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response?.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('UuidIdFilteringMapsId').should('exist');
    cy.url().should('match', uuidIdFilteringMapsIdPageUrlPattern);
  });

  describe('UuidIdFilteringMapsId page', () => {
    describe('create button click', () => {
      beforeEach(() => {
        cy.visit(uuidIdFilteringMapsIdPageUrl);
        cy.wait('@entitiesRequest');
      });

      it('should load create UuidIdFilteringMapsId page', () => {
        cy.get(entityCreateButtonSelector).click();
        cy.url().should('match', new RegExp('/uuid-id-filtering-maps-id/new$'));
        cy.getEntityCreateUpdateHeading('UuidIdFilteringMapsId');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', uuidIdFilteringMapsIdPageUrlPattern);
      });
    });

    describe('with existing value', () => {
      beforeEach(() => {
        cy.authenticatedRequest({
          method: 'POST',
          url: '/api/uuid-id-filtering-maps-ids',
          body: {
            ...uuidIdFilteringMapsIdSample,
            uuidIdFiltering,
          },
        }).then(({ body }) => {
          uuidIdFilteringMapsId = body;

          cy.intercept(
            {
              method: 'GET',
              url: '/api/uuid-id-filtering-maps-ids+(?*|)',
              times: 1,
            },
            {
              statusCode: 200,
              body: [uuidIdFilteringMapsId],
            },
          ).as('entitiesRequestInternal');
        });

        cy.visit(uuidIdFilteringMapsIdPageUrl);

        cy.wait('@entitiesRequestInternal');
      });

      it('detail button click should load details UuidIdFilteringMapsId page', () => {
        cy.get(entityDetailsButtonSelector).first().click();
        cy.getEntityDetailsHeading('uuidIdFilteringMapsId');
        cy.get(entityDetailsBackButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', uuidIdFilteringMapsIdPageUrlPattern);
      });

      it('edit button click should load edit UuidIdFilteringMapsId page and go back', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('UuidIdFilteringMapsId');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', uuidIdFilteringMapsIdPageUrlPattern);
      });

      // Reason: jpaMetamodelFiltering with required relationship missbehavior
      it.skip('edit button click should load edit UuidIdFilteringMapsId page and save', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('UuidIdFilteringMapsId');
        cy.get(entityCreateSaveButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', uuidIdFilteringMapsIdPageUrlPattern);
      });

      it('last delete button click should delete instance of UuidIdFilteringMapsId', () => {
        cy.get(entityDeleteButtonSelector).last().click();
        cy.getEntityDeleteDialogHeading('uuidIdFilteringMapsId').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click();
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', uuidIdFilteringMapsIdPageUrlPattern);

        uuidIdFilteringMapsId = undefined;
      });
    });
  });

  describe('new UuidIdFilteringMapsId page', () => {
    beforeEach(() => {
      cy.visit(`${uuidIdFilteringMapsIdPageUrl}`);
      cy.get(entityCreateButtonSelector).click();
      cy.getEntityCreateUpdateHeading('UuidIdFilteringMapsId');
    });

    it('should create an instance of UuidIdFilteringMapsId', () => {
      cy.get(`[data-cy="uuidIdFiltering"]`).select(1);

      cy.get(entityCreateSaveButtonSelector).click();

      cy.wait('@postEntityRequest').then(({ response }) => {
        expect(response?.statusCode).to.equal(201);
        uuidIdFilteringMapsId = response.body;
      });
      cy.wait('@entitiesRequest').then(({ response }) => {
        expect(response?.statusCode).to.equal(200);
      });
      cy.url().should('match', uuidIdFilteringMapsIdPageUrlPattern);
    });
  });
});
