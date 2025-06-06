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

describe('EntityCustomIdDTOMapsId e2e test', () => {
  const entityCustomIdDTOMapsIdPageUrl = '/entity-custom-id-dto-maps-id';
  const entityCustomIdDTOMapsIdPageUrlPattern = new RegExp('/entity-custom-id-dto-maps-id(\\?.*)?$');
  const username = Cypress.env('E2E_USERNAME') ?? 'user';
  const password = Cypress.env('E2E_PASSWORD') ?? 'user';
  const entityCustomIdDTOMapsIdSample = {};

  let entityCustomIdDTOMapsId;
  let entityCustomIdDTO;

  beforeEach(() => {
    cy.login(username, password);
  });

  beforeEach(() => {
    // create an instance at the required relationship entity:
    cy.authenticatedRequest({
      method: 'POST',
      url: '/api/entity-custom-id-dtos',
      body: {},
    }).then(({ body }) => {
      entityCustomIdDTO = body;
    });
  });

  beforeEach(() => {
    cy.intercept('GET', '/api/entity-custom-id-dto-maps-ids+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/entity-custom-id-dto-maps-ids').as('postEntityRequest');
    cy.intercept('DELETE', '/api/entity-custom-id-dto-maps-ids/*').as('deleteEntityRequest');
  });

  beforeEach(() => {
    // Simulate relationships api for better performance and reproducibility.
    cy.intercept('GET', '/api/entity-custom-id-dtos', {
      statusCode: 200,
      body: [entityCustomIdDTO],
    });

    cy.intercept('GET', '/api/entity-custom-id-dto-rels', {
      statusCode: 200,
      body: [],
    });
  });

  afterEach(() => {
    if (entityCustomIdDTOMapsId) {
      cy.authenticatedRequest({
        method: 'DELETE',
        url: `/api/entity-custom-id-dto-maps-ids/${entityCustomIdDTOMapsId.customId}`,
      }).then(() => {
        entityCustomIdDTOMapsId = undefined;
      });
    }
  });

  afterEach(() => {
    if (entityCustomIdDTO) {
      cy.authenticatedRequest({
        method: 'DELETE',
        url: `/api/entity-custom-id-dtos/${entityCustomIdDTO.customId}`,
      }).then(() => {
        entityCustomIdDTO = undefined;
      });
    }
  });

  it('EntityCustomIdDTOMapsIds menu should load EntityCustomIdDTOMapsIds page', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('entity-custom-id-dto-maps-id');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response?.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('EntityCustomIdDTOMapsId').should('exist');
    cy.url().should('match', entityCustomIdDTOMapsIdPageUrlPattern);
  });

  describe('EntityCustomIdDTOMapsId page', () => {
    describe('create button click', () => {
      beforeEach(() => {
        cy.visit(entityCustomIdDTOMapsIdPageUrl);
        cy.wait('@entitiesRequest');
      });

      it('should load create EntityCustomIdDTOMapsId page', () => {
        cy.get(entityCreateButtonSelector).click();
        cy.url().should('match', new RegExp('/entity-custom-id-dto-maps-id/new$'));
        cy.getEntityCreateUpdateHeading('EntityCustomIdDTOMapsId');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', entityCustomIdDTOMapsIdPageUrlPattern);
      });
    });

    describe('with existing value', () => {
      beforeEach(() => {
        cy.authenticatedRequest({
          method: 'POST',
          url: '/api/entity-custom-id-dto-maps-ids',
          body: {
            ...entityCustomIdDTOMapsIdSample,
            entityCustomIdDTO,
          },
        }).then(({ body }) => {
          entityCustomIdDTOMapsId = body;

          cy.intercept(
            {
              method: 'GET',
              url: '/api/entity-custom-id-dto-maps-ids+(?*|)',
              times: 1,
            },
            {
              statusCode: 200,
              body: [entityCustomIdDTOMapsId],
            },
          ).as('entitiesRequestInternal');
        });

        cy.visit(entityCustomIdDTOMapsIdPageUrl);

        cy.wait('@entitiesRequestInternal');
      });

      it('detail button click should load details EntityCustomIdDTOMapsId page', () => {
        cy.get(entityDetailsButtonSelector).first().click();
        cy.getEntityDetailsHeading('entityCustomIdDTOMapsId');
        cy.get(entityDetailsBackButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', entityCustomIdDTOMapsIdPageUrlPattern);
      });

      it('edit button click should load edit EntityCustomIdDTOMapsId page and go back', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('EntityCustomIdDTOMapsId');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', entityCustomIdDTOMapsIdPageUrlPattern);
      });

      it('edit button click should load edit EntityCustomIdDTOMapsId page and save', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('EntityCustomIdDTOMapsId');
        cy.get(entityCreateSaveButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', entityCustomIdDTOMapsIdPageUrlPattern);
      });

      it('last delete button click should delete instance of EntityCustomIdDTOMapsId', () => {
        cy.get(entityDeleteButtonSelector).last().click();
        cy.getEntityDeleteDialogHeading('entityCustomIdDTOMapsId').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click();
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', entityCustomIdDTOMapsIdPageUrlPattern);

        entityCustomIdDTOMapsId = undefined;
      });
    });
  });

  describe('new EntityCustomIdDTOMapsId page', () => {
    beforeEach(() => {
      cy.visit(`${entityCustomIdDTOMapsIdPageUrl}`);
      cy.get(entityCreateButtonSelector).click();
      cy.getEntityCreateUpdateHeading('EntityCustomIdDTOMapsId');
    });

    it('should create an instance of EntityCustomIdDTOMapsId', () => {
      cy.get(`[data-cy="entityCustomIdDTO"]`).select(1);

      cy.get(entityCreateSaveButtonSelector).click();

      cy.wait('@postEntityRequest').then(({ response }) => {
        expect(response?.statusCode).to.equal(201);
        entityCustomIdDTOMapsId = response.body;
      });
      cy.wait('@entitiesRequest').then(({ response }) => {
        expect(response?.statusCode).to.equal(200);
      });
      cy.url().should('match', entityCustomIdDTOMapsIdPageUrlPattern);
    });
  });
});
