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

describe('EntityUuidIdDTO e2e test', () => {
  const entityUuidIdDTOPageUrl = '/entity-uuid-id-dto';
  const entityUuidIdDTOPageUrlPattern = new RegExp('/entity-uuid-id-dto(\\?.*)?$');
  const username = Cypress.env('E2E_USERNAME') ?? 'user';
  const password = Cypress.env('E2E_PASSWORD') ?? 'user';
  const entityUuidIdDTOSample = {};

  let entityUuidIdDTO;

  beforeEach(() => {
    cy.login(username, password);
  });

  beforeEach(() => {
    cy.intercept('GET', '/api/entity-uuid-id-dtos+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/entity-uuid-id-dtos').as('postEntityRequest');
    cy.intercept('DELETE', '/api/entity-uuid-id-dtos/*').as('deleteEntityRequest');
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

  it('EntityUuidIdDTOS menu should load EntityUuidIdDTOS page', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('entity-uuid-id-dto');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response?.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('EntityUuidIdDTO').should('exist');
    cy.url().should('match', entityUuidIdDTOPageUrlPattern);
  });

  describe('EntityUuidIdDTO page', () => {
    describe('create button click', () => {
      beforeEach(() => {
        cy.visit(entityUuidIdDTOPageUrl);
        cy.wait('@entitiesRequest');
      });

      it('should load create EntityUuidIdDTO page', () => {
        cy.get(entityCreateButtonSelector).click();
        cy.url().should('match', new RegExp('/entity-uuid-id-dto/new$'));
        cy.getEntityCreateUpdateHeading('EntityUuidIdDTO');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', entityUuidIdDTOPageUrlPattern);
      });
    });

    describe('with existing value', () => {
      beforeEach(() => {
        cy.authenticatedRequest({
          method: 'POST',
          url: '/api/entity-uuid-id-dtos',
          body: entityUuidIdDTOSample,
        }).then(({ body }) => {
          entityUuidIdDTO = body;

          cy.intercept(
            {
              method: 'GET',
              url: '/api/entity-uuid-id-dtos+(?*|)',
              times: 1,
            },
            {
              statusCode: 200,
              body: [entityUuidIdDTO],
            },
          ).as('entitiesRequestInternal');
        });

        cy.visit(entityUuidIdDTOPageUrl);

        cy.wait('@entitiesRequestInternal');
      });

      it('detail button click should load details EntityUuidIdDTO page', () => {
        cy.get(entityDetailsButtonSelector).first().click();
        cy.getEntityDetailsHeading('entityUuidIdDTO');
        cy.get(entityDetailsBackButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', entityUuidIdDTOPageUrlPattern);
      });

      it('edit button click should load edit EntityUuidIdDTO page and go back', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('EntityUuidIdDTO');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', entityUuidIdDTOPageUrlPattern);
      });

      it('edit button click should load edit EntityUuidIdDTO page and save', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('EntityUuidIdDTO');
        cy.get(entityCreateSaveButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', entityUuidIdDTOPageUrlPattern);
      });

      it('last delete button click should delete instance of EntityUuidIdDTO', () => {
        cy.get(entityDeleteButtonSelector).last().click();
        cy.getEntityDeleteDialogHeading('entityUuidIdDTO').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click();
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', entityUuidIdDTOPageUrlPattern);

        entityUuidIdDTO = undefined;
      });
    });
  });

  describe('new EntityUuidIdDTO page', () => {
    beforeEach(() => {
      cy.visit(`${entityUuidIdDTOPageUrl}`);
      cy.get(entityCreateButtonSelector).click();
      cy.getEntityCreateUpdateHeading('EntityUuidIdDTO');
    });

    it('should create an instance of EntityUuidIdDTO', () => {
      cy.get(entityCreateSaveButtonSelector).click();

      cy.wait('@postEntityRequest').then(({ response }) => {
        expect(response?.statusCode).to.equal(201);
        entityUuidIdDTO = response.body;
      });
      cy.wait('@entitiesRequest').then(({ response }) => {
        expect(response?.statusCode).to.equal(200);
      });
      cy.url().should('match', entityUuidIdDTOPageUrlPattern);
    });
  });
});
