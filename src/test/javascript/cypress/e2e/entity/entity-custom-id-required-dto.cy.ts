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

describe('EntityCustomIdRequiredDTO e2e test', () => {
  const entityCustomIdRequiredDTOPageUrl = '/entity-custom-id-required-dto';
  const entityCustomIdRequiredDTOPageUrlPattern = new RegExp('/entity-custom-id-required-dto(\\?.*)?$');
  const username = Cypress.env('E2E_USERNAME') ?? 'user';
  const password = Cypress.env('E2E_PASSWORD') ?? 'user';
  const entityCustomIdRequiredDTOSample = {};

  let entityCustomIdRequiredDTO;

  beforeEach(() => {
    cy.login(username, password);
  });

  beforeEach(() => {
    cy.intercept('GET', '/api/entity-custom-id-required-dtos+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/entity-custom-id-required-dtos').as('postEntityRequest');
    cy.intercept('DELETE', '/api/entity-custom-id-required-dtos/*').as('deleteEntityRequest');
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

  it('EntityCustomIdRequiredDTOS menu should load EntityCustomIdRequiredDTOS page', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('entity-custom-id-required-dto');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response?.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('EntityCustomIdRequiredDTO').should('exist');
    cy.url().should('match', entityCustomIdRequiredDTOPageUrlPattern);
  });

  describe('EntityCustomIdRequiredDTO page', () => {
    describe('create button click', () => {
      beforeEach(() => {
        cy.visit(entityCustomIdRequiredDTOPageUrl);
        cy.wait('@entitiesRequest');
      });

      it('should load create EntityCustomIdRequiredDTO page', () => {
        cy.get(entityCreateButtonSelector).click();
        cy.url().should('match', new RegExp('/entity-custom-id-required-dto/new$'));
        cy.getEntityCreateUpdateHeading('EntityCustomIdRequiredDTO');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', entityCustomIdRequiredDTOPageUrlPattern);
      });
    });

    describe('with existing value', () => {
      beforeEach(() => {
        cy.authenticatedRequest({
          method: 'POST',
          url: '/api/entity-custom-id-required-dtos',
          body: entityCustomIdRequiredDTOSample,
        }).then(({ body }) => {
          entityCustomIdRequiredDTO = body;

          cy.intercept(
            {
              method: 'GET',
              url: '/api/entity-custom-id-required-dtos+(?*|)',
              times: 1,
            },
            {
              statusCode: 200,
              body: [entityCustomIdRequiredDTO],
            },
          ).as('entitiesRequestInternal');
        });

        cy.visit(entityCustomIdRequiredDTOPageUrl);

        cy.wait('@entitiesRequestInternal');
      });

      it('detail button click should load details EntityCustomIdRequiredDTO page', () => {
        cy.get(entityDetailsButtonSelector).first().click();
        cy.getEntityDetailsHeading('entityCustomIdRequiredDTO');
        cy.get(entityDetailsBackButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', entityCustomIdRequiredDTOPageUrlPattern);
      });

      it('edit button click should load edit EntityCustomIdRequiredDTO page and go back', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('EntityCustomIdRequiredDTO');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', entityCustomIdRequiredDTOPageUrlPattern);
      });

      it('edit button click should load edit EntityCustomIdRequiredDTO page and save', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('EntityCustomIdRequiredDTO');
        cy.get(entityCreateSaveButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', entityCustomIdRequiredDTOPageUrlPattern);
      });

      it('last delete button click should delete instance of EntityCustomIdRequiredDTO', () => {
        cy.get(entityDeleteButtonSelector).last().click();
        cy.getEntityDeleteDialogHeading('entityCustomIdRequiredDTO').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click();
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', entityCustomIdRequiredDTOPageUrlPattern);

        entityCustomIdRequiredDTO = undefined;
      });
    });
  });

  describe('new EntityCustomIdRequiredDTO page', () => {
    beforeEach(() => {
      cy.visit(`${entityCustomIdRequiredDTOPageUrl}`);
      cy.get(entityCreateButtonSelector).click();
      cy.getEntityCreateUpdateHeading('EntityCustomIdRequiredDTO');
    });

    it('should create an instance of EntityCustomIdRequiredDTO', () => {
      cy.get(entityCreateSaveButtonSelector).click();

      cy.wait('@postEntityRequest').then(({ response }) => {
        expect(response?.statusCode).to.equal(201);
        entityCustomIdRequiredDTO = response.body;
      });
      cy.wait('@entitiesRequest').then(({ response }) => {
        expect(response?.statusCode).to.equal(200);
      });
      cy.url().should('match', entityCustomIdRequiredDTOPageUrlPattern);
    });
  });
});
