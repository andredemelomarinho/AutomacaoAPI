Feature: Enviar uma request para a API e validar os métodos de POST, GET, PUT, DELETE
#Background:


  @validacaoAplicacao @testeAPI1
  Scenario: Enviar uma Requisição para a API validando o nome do usuário informado no request
    Given valido_endpoint "users"
   Then valido_nome_response "users" "Helena" "1"

  @validacaoAplicacao @testeAPI2
  Scenario: Enviar uma Requisição para a API validando o id do usuário informado no request
    Given valido_endpoint "users"
    Then valido_userId_response "users" "12"


  @validacaoAplicacao @testeAPIPost
  Scenario: Enviar uma Requisição para a API criando um registro
    Given valido_endpoint "pessoa"
    When criar_registro_api "pessoa" "André Marinho"
    Then valida registro existente "nome" "André Marinho"

  @validacaoAplicacao @testeAPIPUT
  Scenario: Enviar uma Requisição para a API atualizando  um registro
    Given valido_endpoint "pessoa"
    When update_registro_api "pessoa" "41"
    Then valida registro existente "nome" "André de Melo Marinho"

  @validacaoAplicacao @testeAPIDelete
  Scenario: Enviar uma Requisição para a API deletando um registro
    Given valido_endpoint "pessoa"
    When  valida registro existente "id" "41"
    Then deletar_user_ID "pessoa" "41"

  @validacaoAplicacao @testeAPIGet
  Scenario: Enviar uma Requisição para a API retornando todos os dados
    Given valido_endpoint "pessoa"
    Then  get pessoas API "pessoa"

  @validacaoAplicacao @testeAPI6
  Scenario: Enviar uma Requisição para a API validando o nome do usuário informado no request com endpoint invalido
    Given valido_endpoint "users"
    Then valido_nome_response "teste" "Andre" "1"

  @validacaoAplicacao @testeAPI7
  Scenario: Enviar uma Requisição para a API validando o nome do usuário informado no request
    Given valido_endpoint "users"
    Then valido_nome_response "users" "testexyz" "1"

  @validacaoAplicacao @testeAPI8
  Scenario: Enviar  Requisição para a API validando todos os nomes de usuários informado pelo dataprovider no request
    Given valido_endpoint "users"
    Then valido_nome_response_dataprovider "users" "dataprovider" ""

  @validacaoAplicacao @testeAPI9
  Scenario: Enviar uma Requisição para a API atualizando  vários registros usando dataprovider
    Given valido_endpoint "users"
    Then update_registro_api_dataprovider "users" "updates"