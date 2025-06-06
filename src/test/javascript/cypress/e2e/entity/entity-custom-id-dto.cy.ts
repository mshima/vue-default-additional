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

describe('EntityCustomIdDTO e2e test', () => {
  const entityCustomIdDTOPageUrl = '/entity-custom-id-dto';
  const entityCustomIdDTOPageUrlPattern = new RegExp('/entity-custom-id-dto(\\?.*)?$');
  const username = Cypress.env('E2E_USERNAME') ?? 'user';
  const password = Cypress.env('E2E_PASSWORD') ?? 'user';
  const entityCustomIdDTOSample = {};

  let entityCustomIdDTO;

  beforeEach(() => {
    cy.login(username, password);
  });

  beforeEach(() => {
    cy.intercept('GET', '/api/entity-custom-id-dtos+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/entity-custom-id-dtos').as('postEntityRequest');
    cy.intercept('DELETE', '/api/entity-custom-id-dtos/*').as('deleteEntityRequest');
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

  it('EntityCustomIdDTOS menu should load EntityCustomIdDTOS page', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('entity-custom-id-dto');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response?.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('EntityCustomIdDTO').should('exist');
    cy.url().should('match', entityCustomIdDTOPageUrlPattern);
  });

  describe('EntityCustomIdDTO page', () => {
    describe('create button click', () => {
      beforeEach(() => {
        cy.visit(entityCustomIdDTOPageUrl);
        cy.wait('@entitiesRequest');
      });

      it('should load create EntityCustomIdDTO page', () => {
        cy.get(entityCreateButtonSelector).click();
        cy.url().should('match', new RegExp('/entity-custom-id-dto/new$'));
        cy.getEntityCreateUpdateHeading('EntityCustomIdDTO');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', entityCustomIdDTOPageUrlPattern);
      });
    });

    describe('with existing value', () => {
      beforeEach(() => {
        cy.authenticatedRequest({
          method: 'POST',
          url: '/api/entity-custom-id-dtos',
          body: entityCustomIdDTOSample,
        }).then(({ body }) => {
          entityCustomIdDTO = body;

          cy.intercept(
            {
              method: 'GET',
              url: '/api/entity-custom-id-dtos+(?*|)',
              times: 1,
            },
            {
              statusCode: 200,
              body: [entityCustomIdDTO],
            },
          ).as('entitiesRequestInternal');
        });

        cy.visit(entityCustomIdDTOPageUrl);

        cy.wait('@entitiesRequestInternal');
      });

      it('detail button click should load details EntityCustomIdDTO page', () => {
        cy.get(entityDetailsButtonSelector).first().click();
        cy.getEntityDetailsHeading('entityCustomIdDTO');
        cy.get(entityDetailsBackButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', entityCustomIdDTOPageUrlPattern);
      });

      it('edit button click should load edit EntityCustomIdDTO page and go back', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('EntityCustomIdDTO');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', entityCustomIdDTOPageUrlPattern);
      });

      it('edit button click should load edit EntityCustomIdDTO page and save', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('EntityCustomIdDTO');
        cy.get(entityCreateSaveButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', entityCustomIdDTOPageUrlPattern);
      });

      it('last delete button click should delete instance of EntityCustomIdDTO', () => {
        cy.get(entityDeleteButtonSelector).last().click();
        cy.getEntityDeleteDialogHeading('entityCustomIdDTO').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click();
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', entityCustomIdDTOPageUrlPattern);

        entityCustomIdDTO = undefined;
      });
    });
  });

  describe('new EntityCustomIdDTO page', () => {
    beforeEach(() => {
      cy.visit(`${entityCustomIdDTOPageUrl}`);
      cy.get(entityCreateButtonSelector).click();
      cy.getEntityCreateUpdateHeading('EntityCustomIdDTO');
    });

    it('should create an instance of EntityCustomIdDTO', () => {
      cy.get(entityCreateSaveButtonSelector).click();

      cy.wait('@postEntityRequest').then(({ response }) => {
        expect(response?.statusCode).to.equal(201);
        entityCustomIdDTO = response.body;
      });
      cy.wait('@entitiesRequest').then(({ response }) => {
        expect(response?.statusCode).to.equal(200);
      });
      cy.url().should('match', entityCustomIdDTOPageUrlPattern);
    });
  });
});
