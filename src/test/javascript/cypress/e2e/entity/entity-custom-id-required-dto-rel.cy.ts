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

describe('EntityCustomIdRequiredDTORel e2e test', () => {
  const entityCustomIdRequiredDTORelPageUrl = '/entity-custom-id-required-dto-rel';
  const entityCustomIdRequiredDTORelPageUrlPattern = new RegExp('/entity-custom-id-required-dto-rel(\\?.*)?$');
  const username = Cypress.env('E2E_USERNAME') ?? 'user';
  const password = Cypress.env('E2E_PASSWORD') ?? 'user';
  // const entityCustomIdRequiredDTORelSample = {};

  let entityCustomIdRequiredDTORel;
  // let entityCustomIdRequiredDTO;
  // let entityCustomIdRequiredDTOMapsId;

  beforeEach(() => {
    cy.login(username, password);
  });

  /* Disabled due to incompatibility
  beforeEach(() => {
    // create an instance at the required relationship entity:
    cy.authenticatedRequest({
      method: 'POST',
      url: '/api/entity-custom-id-required-dtos',
      body: {},
    }).then(({ body }) => {
      entityCustomIdRequiredDTO = body;
    });
    // create an instance at the required relationship entity:
    cy.authenticatedRequest({
      method: 'POST',
      url: '/api/entity-custom-id-required-dto-maps-ids',
      body: {},
    }).then(({ body }) => {
      entityCustomIdRequiredDTOMapsId = body;
    });
  });
   */

  beforeEach(() => {
    cy.intercept('GET', '/api/entity-custom-id-required-dto-rels+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/entity-custom-id-required-dto-rels').as('postEntityRequest');
    cy.intercept('DELETE', '/api/entity-custom-id-required-dto-rels/*').as('deleteEntityRequest');
  });

  /* Disabled due to incompatibility
  beforeEach(() => {
    // Simulate relationships api for better performance and reproducibility.
    cy.intercept('GET', '/api/entity-custom-id-required-dtos', {
      statusCode: 200,
      body: [entityCustomIdRequiredDTO],
    });

    cy.intercept('GET', '/api/entity-custom-id-required-dto-maps-ids', {
      statusCode: 200,
      body: [entityCustomIdRequiredDTOMapsId],
    });

  });
   */

  afterEach(() => {
    if (entityCustomIdRequiredDTORel) {
      cy.authenticatedRequest({
        method: 'DELETE',
        url: `/api/entity-custom-id-required-dto-rels/${entityCustomIdRequiredDTORel.relatedId}`,
      }).then(() => {
        entityCustomIdRequiredDTORel = undefined;
      });
    }
  });

  /* Disabled due to incompatibility
  afterEach(() => {
    if (entityCustomIdRequiredDTO) {
      cy.authenticatedRequest({
        method: 'DELETE',
        url: `/api/entity-custom-id-required-dtos/${entityCustomIdRequiredDTO.customId}`,
      }).then(() => {
        entityCustomIdRequiredDTO = undefined;
      });
    }
    if (entityCustomIdRequiredDTOMapsId) {
      cy.authenticatedRequest({
        method: 'DELETE',
        url: `/api/entity-custom-id-required-dto-maps-ids/${entityCustomIdRequiredDTOMapsId.customId}`,
      }).then(() => {
        entityCustomIdRequiredDTOMapsId = undefined;
      });
    }
  });
   */

  it('EntityCustomIdRequiredDTORels menu should load EntityCustomIdRequiredDTORels page', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('entity-custom-id-required-dto-rel');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response?.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('EntityCustomIdRequiredDTORel').should('exist');
    cy.url().should('match', entityCustomIdRequiredDTORelPageUrlPattern);
  });

  describe('EntityCustomIdRequiredDTORel page', () => {
    describe('create button click', () => {
      beforeEach(() => {
        cy.visit(entityCustomIdRequiredDTORelPageUrl);
        cy.wait('@entitiesRequest');
      });

      it('should load create EntityCustomIdRequiredDTORel page', () => {
        cy.get(entityCreateButtonSelector).click();
        cy.url().should('match', new RegExp('/entity-custom-id-required-dto-rel/new$'));
        cy.getEntityCreateUpdateHeading('EntityCustomIdRequiredDTORel');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', entityCustomIdRequiredDTORelPageUrlPattern);
      });
    });

    describe('with existing value', () => {
      /* Disabled due to incompatibility
      beforeEach(() => {
        cy.authenticatedRequest({
          method: 'POST',
          url: '/api/entity-custom-id-required-dto-rels',
          body: {
            ...entityCustomIdRequiredDTORelSample,
            oneToOne: entityCustomIdRequiredDTO,
            oneToOneMapsId: entityCustomIdRequiredDTOMapsId,
            manyToOne: entityCustomIdRequiredDTO,
            manyToOneMapsId: entityCustomIdRequiredDTOMapsId,
            manyToMany: entityCustomIdRequiredDTO,
            manyToManyMapsId: entityCustomIdRequiredDTOMapsId,
          },
        }).then(({ body }) => {
          entityCustomIdRequiredDTORel = body;

          cy.intercept(
            {
              method: 'GET',
              url: '/api/entity-custom-id-required-dto-rels+(?*|)',
              times: 1,
            },
            {
              statusCode: 200,
              body: [entityCustomIdRequiredDTORel],
            }
          ).as('entitiesRequestInternal');
        });

        cy.visit(entityCustomIdRequiredDTORelPageUrl);

        cy.wait('@entitiesRequestInternal');
      });
       */

      beforeEach(function () {
        cy.visit(entityCustomIdRequiredDTORelPageUrl);

        cy.wait('@entitiesRequest').then(({ response }) => {
          if (response?.body.length === 0) {
            this.skip();
          }
        });
      });

      it('detail button click should load details EntityCustomIdRequiredDTORel page', () => {
        cy.get(entityDetailsButtonSelector).first().click();
        cy.getEntityDetailsHeading('entityCustomIdRequiredDTORel');
        cy.get(entityDetailsBackButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', entityCustomIdRequiredDTORelPageUrlPattern);
      });

      it('edit button click should load edit EntityCustomIdRequiredDTORel page and go back', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('EntityCustomIdRequiredDTORel');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', entityCustomIdRequiredDTORelPageUrlPattern);
      });

      it('edit button click should load edit EntityCustomIdRequiredDTORel page and save', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('EntityCustomIdRequiredDTORel');
        cy.get(entityCreateSaveButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', entityCustomIdRequiredDTORelPageUrlPattern);
      });

      // Reason: cannot create a required entity with relationship with required relationships.
      it.skip('last delete button click should delete instance of EntityCustomIdRequiredDTORel', () => {
        cy.get(entityDeleteButtonSelector).last().click();
        cy.getEntityDeleteDialogHeading('entityCustomIdRequiredDTORel').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click();
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', entityCustomIdRequiredDTORelPageUrlPattern);

        entityCustomIdRequiredDTORel = undefined;
      });
    });
  });

  describe('new EntityCustomIdRequiredDTORel page', () => {
    beforeEach(() => {
      cy.visit(`${entityCustomIdRequiredDTORelPageUrl}`);
      cy.get(entityCreateButtonSelector).click();
      cy.getEntityCreateUpdateHeading('EntityCustomIdRequiredDTORel');
    });

    // Reason: cannot create a required entity with relationship with required relationships.
    it.skip('should create an instance of EntityCustomIdRequiredDTORel', () => {
      cy.get(`[data-cy="oneToOne"]`).select(1);
      cy.get(`[data-cy="oneToOneMapsId"]`).select(1);
      cy.get(`[data-cy="manyToOne"]`).select(1);
      cy.get(`[data-cy="manyToOneMapsId"]`).select(1);
      cy.get(`[data-cy="manyToMany"]`).select([0]);
      cy.get(`[data-cy="manyToManyMapsId"]`).select([0]);

      cy.get(entityCreateSaveButtonSelector).click();

      cy.wait('@postEntityRequest').then(({ response }) => {
        expect(response?.statusCode).to.equal(201);
        entityCustomIdRequiredDTORel = response.body;
      });
      cy.wait('@entitiesRequest').then(({ response }) => {
        expect(response?.statusCode).to.equal(200);
      });
      cy.url().should('match', entityCustomIdRequiredDTORelPageUrlPattern);
    });
  });
});
