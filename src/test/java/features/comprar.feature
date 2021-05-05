Feature: Preencher o formulário de pesquisa google

  @comprar @validarcompras
  Scenario: Realiza login e efetiva compra no site
    Given faco login no site http://automationpractice.com/
    When insiro login e senha pre existentes
    And  pequiso produto "Printed Chiffon Dress"
    And  adiciono produto ao carrinho
    And  pequiso produto "Faded Short Sleeve T-shirts"
    And  adiciono produto ao carrinho
    Then realizo o checkout da compra
    And realizo o pagamento

  @comprar @validarcadastroecompras
  Scenario: Realiza cadastro e efetiva compra no site
    Given acesso pagina de cadstro
    When  realizo cadastro no site
    And  pequiso produto "Printed Chiffon Dress"
    And  adiciono produto ao carrinho
    And  pequiso produto "Faded Short Sleeve T-shirts"
    And  adiciono produto ao carrinho
    Then realizo o checkout da compra
    And realizo o pagamento



