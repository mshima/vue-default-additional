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

describe('EntityCustomSequence e2e test', () => {
  const entityCustomSequencePageUrl = '/entity-custom-sequence';
  const entityCustomSequencePageUrlPattern = new RegExp('/entity-custom-sequence(\\?.*)?$');
  const username = Cypress.env('E2E_USERNAME') ?? 'user';
  const password = Cypress.env('E2E_PASSWORD') ?? 'user';
  const entityCustomSequenceSample = {};

  let entityCustomSequence;

  beforeEach(() => {
    cy.login(username, password);
  });

  beforeEach(() => {
    cy.intercept('GET', '/api/entity-custom-sequences+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/entity-custom-sequences').as('postEntityRequest');
    cy.intercept('DELETE', '/api/entity-custom-sequences/*').as('deleteEntityRequest');
  });

  afterEach(() => {
    if (entityCustomSequence) {
      cy.authenticatedRequest({
        method: 'DELETE',
        url: `/api/entity-custom-sequences/${entityCustomSequence.id}`,
      }).then(() => {
        entityCustomSequence = undefined;
      });
    }
  });

  it('EntityCustomSequences menu should load EntityCustomSequences page', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('entity-custom-sequence');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response?.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('EntityCustomSequence').should('exist');
    cy.url().should('match', entityCustomSequencePageUrlPattern);
  });

  describe('EntityCustomSequence page', () => {
    describe('create button click', () => {
      beforeEach(() => {
        cy.visit(entityCustomSequencePageUrl);
        cy.wait('@entitiesRequest');
      });

      it('should load create EntityCustomSequence page', () => {
        cy.get(entityCreateButtonSelector).click();
        cy.url().should('match', new RegExp('/entity-custom-sequence/new$'));
        cy.getEntityCreateUpdateHeading('EntityCustomSequence');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', entityCustomSequencePageUrlPattern);
      });
    });

    describe('with existing value', () => {
      beforeEach(() => {
        cy.authenticatedRequest({
          method: 'POST',
          url: '/api/entity-custom-sequences',
          body: entityCustomSequenceSample,
        }).then(({ body }) => {
          entityCustomSequence = body;

          cy.intercept(
            {
              method: 'GET',
              url: '/api/entity-custom-sequences+(?*|)',
              times: 1,
            },
            {
              statusCode: 200,
              body: [entityCustomSequence],
            },
          ).as('entitiesRequestInternal');
        });

        cy.visit(entityCustomSequencePageUrl);

        cy.wait('@entitiesRequestInternal');
      });

      it('detail button click should load details EntityCustomSequence page', () => {
        cy.get(entityDetailsButtonSelector).first().click();
        cy.getEntityDetailsHeading('entityCustomSequence');
        cy.get(entityDetailsBackButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', entityCustomSequencePageUrlPattern);
      });

      it('edit button click should load edit EntityCustomSequence page and go back', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('EntityCustomSequence');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', entityCustomSequencePageUrlPattern);
      });

      it('edit button click should load edit EntityCustomSequence page and save', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('EntityCustomSequence');
        cy.get(entityCreateSaveButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', entityCustomSequencePageUrlPattern);
      });

      it('last delete button click should delete instance of EntityCustomSequence', () => {
        cy.get(entityDeleteButtonSelector).last().click();
        cy.getEntityDeleteDialogHeading('entityCustomSequence').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click();
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', entityCustomSequencePageUrlPattern);

        entityCustomSequence = undefined;
      });
    });
  });

  describe('new EntityCustomSequence page', () => {
    beforeEach(() => {
      cy.visit(`${entityCustomSequencePageUrl}`);
      cy.get(entityCreateButtonSelector).click();
      cy.getEntityCreateUpdateHeading('EntityCustomSequence');
    });

    it('should create an instance of EntityCustomSequence', () => {
      cy.get(`[data-cy="name"]`).type('vibration putrid silently');
      cy.get(`[data-cy="name"]`).should('have.value', 'vibration putrid silently');

      cy.get(entityCreateSaveButtonSelector).click();

      cy.wait('@postEntityRequest').then(({ response }) => {
        expect(response?.statusCode).to.equal(201);
        entityCustomSequence = response.body;
      });
      cy.wait('@entitiesRequest').then(({ response }) => {
        expect(response?.statusCode).to.equal(200);
      });
      cy.url().should('match', entityCustomSequencePageUrlPattern);
    });
  });
});
