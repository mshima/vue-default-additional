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

describe('EntityCustomIdRequiredDTOMapsId e2e test', () => {
  const entityCustomIdRequiredDTOMapsIdPageUrl = '/entity-custom-id-required-dto-maps-id';
  const entityCustomIdRequiredDTOMapsIdPageUrlPattern = new RegExp('/entity-custom-id-required-dto-maps-id(\\?.*)?$');
  const username = Cypress.env('E2E_USERNAME') ?? 'user';
  const password = Cypress.env('E2E_PASSWORD') ?? 'user';
  const entityCustomIdRequiredDTOMapsIdSample = {};

  let entityCustomIdRequiredDTOMapsId;
  let entityCustomIdRequiredDTO;

  beforeEach(() => {
    cy.login(username, password);
  });

  beforeEach(() => {
    // create an instance at the required relationship entity:
    cy.authenticatedRequest({
      method: 'POST',
      url: '/api/entity-custom-id-required-dtos',
      body: {},
    }).then(({ body }) => {
      entityCustomIdRequiredDTO = body;
    });
  });

  beforeEach(() => {
    cy.intercept('GET', '/api/entity-custom-id-required-dto-maps-ids+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/entity-custom-id-required-dto-maps-ids').as('postEntityRequest');
    cy.intercept('DELETE', '/api/entity-custom-id-required-dto-maps-ids/*').as('deleteEntityRequest');
  });

  beforeEach(() => {
    // Simulate relationships api for better performance and reproducibility.
    cy.intercept('GET', '/api/entity-custom-id-required-dtos', {
      statusCode: 200,
      body: [entityCustomIdRequiredDTO],
    });

    cy.intercept('GET', '/api/entity-custom-id-required-dto-rels', {
      statusCode: 200,
      body: [],
    });
  });

  afterEach(() => {
    if (entityCustomIdRequiredDTOMapsId) {
      cy.authenticatedRequest({
        method: 'DELETE',
        url: `/api/entity-custom-id-required-dto-maps-ids/${entityCustomIdRequiredDTOMapsId.customId}`,
      }).then(() => {
        entityCustomIdRequiredDTOMapsId = undefined;
      });
    }
  });

  afterEach(() => {
    if (entityCustomIdRequiredDTO) {
      cy.authenticatedRequest({
        method: 'DELETE',
        url: `/api/entity-custom-id-required-dtos/${entityCustomIdRequiredDTO.customId}`,
      }).then(() => {
        entityCustomIdRequiredDTO = undefined;
      });
    }
  });

  it('EntityCustomIdRequiredDTOMapsIds menu should load EntityCustomIdRequiredDTOMapsIds page', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('entity-custom-id-required-dto-maps-id');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response?.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('EntityCustomIdRequiredDTOMapsId').should('exist');
    cy.url().should('match', entityCustomIdRequiredDTOMapsIdPageUrlPattern);
  });

  describe('EntityCustomIdRequiredDTOMapsId page', () => {
    describe('create button click', () => {
      beforeEach(() => {
        cy.visit(entityCustomIdRequiredDTOMapsIdPageUrl);
        cy.wait('@entitiesRequest');
      });

      it('should load create EntityCustomIdRequiredDTOMapsId page', () => {
        cy.get(entityCreateButtonSelector).click();
        cy.url().should('match', new RegExp('/entity-custom-id-required-dto-maps-id/new$'));
        cy.getEntityCreateUpdateHeading('EntityCustomIdRequiredDTOMapsId');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', entityCustomIdRequiredDTOMapsIdPageUrlPattern);
      });
    });

    describe('with existing value', () => {
      beforeEach(() => {
        cy.authenticatedRequest({
          method: 'POST',
          url: '/api/entity-custom-id-required-dto-maps-ids',
          body: {
            ...entityCustomIdRequiredDTOMapsIdSample,
            entityCustomIdRequiredDTO,
          },
        }).then(({ body }) => {
          entityCustomIdRequiredDTOMapsId = body;

          cy.intercept(
            {
              method: 'GET',
              url: '/api/entity-custom-id-required-dto-maps-ids+(?*|)',
              times: 1,
            },
            {
              statusCode: 200,
              body: [entityCustomIdRequiredDTOMapsId],
            },
          ).as('entitiesRequestInternal');
        });

        cy.visit(entityCustomIdRequiredDTOMapsIdPageUrl);

        cy.wait('@entitiesRequestInternal');
      });

      it('detail button click should load details EntityCustomIdRequiredDTOMapsId page', () => {
        cy.get(entityDetailsButtonSelector).first().click();
        cy.getEntityDetailsHeading('entityCustomIdRequiredDTOMapsId');
        cy.get(entityDetailsBackButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', entityCustomIdRequiredDTOMapsIdPageUrlPattern);
      });

      it('edit button click should load edit EntityCustomIdRequiredDTOMapsId page and go back', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('EntityCustomIdRequiredDTOMapsId');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', entityCustomIdRequiredDTOMapsIdPageUrlPattern);
      });

      it('edit button click should load edit EntityCustomIdRequiredDTOMapsId page and save', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('EntityCustomIdRequiredDTOMapsId');
        cy.get(entityCreateSaveButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', entityCustomIdRequiredDTOMapsIdPageUrlPattern);
      });

      it('last delete button click should delete instance of EntityCustomIdRequiredDTOMapsId', () => {
        cy.get(entityDeleteButtonSelector).last().click();
        cy.getEntityDeleteDialogHeading('entityCustomIdRequiredDTOMapsId').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click();
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', entityCustomIdRequiredDTOMapsIdPageUrlPattern);

        entityCustomIdRequiredDTOMapsId = undefined;
      });
    });
  });

  describe('new EntityCustomIdRequiredDTOMapsId page', () => {
    beforeEach(() => {
      cy.visit(`${entityCustomIdRequiredDTOMapsIdPageUrl}`);
      cy.get(entityCreateButtonSelector).click();
      cy.getEntityCreateUpdateHeading('EntityCustomIdRequiredDTOMapsId');
    });

    it('should create an instance of EntityCustomIdRequiredDTOMapsId', () => {
      cy.get(`[data-cy="entityCustomIdRequiredDTO"]`).select(1);

      cy.get(entityCreateSaveButtonSelector).click();

      cy.wait('@postEntityRequest').then(({ response }) => {
        expect(response?.statusCode).to.equal(201);
        entityCustomIdRequiredDTOMapsId = response.body;
      });
      cy.wait('@entitiesRequest').then(({ response }) => {
        expect(response?.statusCode).to.equal(200);
      });
      cy.url().should('match', entityCustomIdRequiredDTOMapsIdPageUrlPattern);
    });
  });
});
