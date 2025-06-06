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

describe('EntityUuidIdDTOMapsId e2e test', () => {
  const entityUuidIdDTOMapsIdPageUrl = '/entity-uuid-id-dto-maps-id';
  const entityUuidIdDTOMapsIdPageUrlPattern = new RegExp('/entity-uuid-id-dto-maps-id(\\?.*)?$');
  const username = Cypress.env('E2E_USERNAME') ?? 'user';
  const password = Cypress.env('E2E_PASSWORD') ?? 'user';
  const entityUuidIdDTOMapsIdSample = {};

  let entityUuidIdDTOMapsId;
  let entityUuidIdDTO;

  beforeEach(() => {
    cy.login(username, password);
  });

  beforeEach(() => {
    // create an instance at the required relationship entity:
    cy.authenticatedRequest({
      method: 'POST',
      url: '/api/entity-uuid-id-dtos',
      body: {},
    }).then(({ body }) => {
      entityUuidIdDTO = body;
    });
  });

  beforeEach(() => {
    cy.intercept('GET', '/api/entity-uuid-id-dto-maps-ids+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/entity-uuid-id-dto-maps-ids').as('postEntityRequest');
    cy.intercept('DELETE', '/api/entity-uuid-id-dto-maps-ids/*').as('deleteEntityRequest');
  });

  beforeEach(() => {
    // Simulate relationships api for better performance and reproducibility.
    cy.intercept('GET', '/api/entity-uuid-id-dtos', {
      statusCode: 200,
      body: [entityUuidIdDTO],
    });

    cy.intercept('GET', '/api/entity-uuid-id-dto-rels', {
      statusCode: 200,
      body: [],
    });
  });

  afterEach(() => {
    if (entityUuidIdDTOMapsId) {
      cy.authenticatedRequest({
        method: 'DELETE',
        url: `/api/entity-uuid-id-dto-maps-ids/${entityUuidIdDTOMapsId.id}`,
      }).then(() => {
        entityUuidIdDTOMapsId = undefined;
      });
    }
  });

  afterEach(() => {
    if (entityUuidIdDTO) {
      cy.authenticatedRequest({
        method: 'DELETE',
        url: `/api/entity-uuid-id-dtos/${entityUuidIdDTO.id}`,
      }).then(() => {
        entityUuidIdDTO = undefined;
      });
    }
  });

  it('EntityUuidIdDTOMapsIds menu should load EntityUuidIdDTOMapsIds page', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('entity-uuid-id-dto-maps-id');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response?.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('EntityUuidIdDTOMapsId').should('exist');
    cy.url().should('match', entityUuidIdDTOMapsIdPageUrlPattern);
  });

  describe('EntityUuidIdDTOMapsId page', () => {
    describe('create button click', () => {
      beforeEach(() => {
        cy.visit(entityUuidIdDTOMapsIdPageUrl);
        cy.wait('@entitiesRequest');
      });

      it('should load create EntityUuidIdDTOMapsId page', () => {
        cy.get(entityCreateButtonSelector).click();
        cy.url().should('match', new RegExp('/entity-uuid-id-dto-maps-id/new$'));
        cy.getEntityCreateUpdateHeading('EntityUuidIdDTOMapsId');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', entityUuidIdDTOMapsIdPageUrlPattern);
      });
    });

    describe('with existing value', () => {
      beforeEach(() => {
        cy.authenticatedRequest({
          method: 'POST',
          url: '/api/entity-uuid-id-dto-maps-ids',
          body: {
            ...entityUuidIdDTOMapsIdSample,
            entityUuidIdDTO,
          },
        }).then(({ body }) => {
          entityUuidIdDTOMapsId = body;

          cy.intercept(
            {
              method: 'GET',
              url: '/api/entity-uuid-id-dto-maps-ids+(?*|)',
              times: 1,
            },
            {
              statusCode: 200,
              body: [entityUuidIdDTOMapsId],
            },
          ).as('entitiesRequestInternal');
        });

        cy.visit(entityUuidIdDTOMapsIdPageUrl);

        cy.wait('@entitiesRequestInternal');
      });

      it('detail button click should load details EntityUuidIdDTOMapsId page', () => {
        cy.get(entityDetailsButtonSelector).first().click();
        cy.getEntityDetailsHeading('entityUuidIdDTOMapsId');
        cy.get(entityDetailsBackButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', entityUuidIdDTOMapsIdPageUrlPattern);
      });

      it('edit button click should load edit EntityUuidIdDTOMapsId page and go back', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('EntityUuidIdDTOMapsId');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', entityUuidIdDTOMapsIdPageUrlPattern);
      });

      it('edit button click should load edit EntityUuidIdDTOMapsId page and save', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('EntityUuidIdDTOMapsId');
        cy.get(entityCreateSaveButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', entityUuidIdDTOMapsIdPageUrlPattern);
      });

      it('last delete button click should delete instance of EntityUuidIdDTOMapsId', () => {
        cy.get(entityDeleteButtonSelector).last().click();
        cy.getEntityDeleteDialogHeading('entityUuidIdDTOMapsId').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click();
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', entityUuidIdDTOMapsIdPageUrlPattern);

        entityUuidIdDTOMapsId = undefined;
      });
    });
  });

  describe('new EntityUuidIdDTOMapsId page', () => {
    beforeEach(() => {
      cy.visit(`${entityUuidIdDTOMapsIdPageUrl}`);
      cy.get(entityCreateButtonSelector).click();
      cy.getEntityCreateUpdateHeading('EntityUuidIdDTOMapsId');
    });

    it('should create an instance of EntityUuidIdDTOMapsId', () => {
      cy.get(`[data-cy="entityUuidIdDTO"]`).select(1);

      cy.get(entityCreateSaveButtonSelector).click();

      cy.wait('@postEntityRequest').then(({ response }) => {
        expect(response?.statusCode).to.equal(201);
        entityUuidIdDTOMapsId = response.body;
      });
      cy.wait('@entitiesRequest').then(({ response }) => {
        expect(response?.statusCode).to.equal(200);
      });
      cy.url().should('match', entityUuidIdDTOMapsIdPageUrlPattern);
    });
  });
});
