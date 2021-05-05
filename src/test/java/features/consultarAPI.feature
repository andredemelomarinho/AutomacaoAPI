Feature: Enviar uma request para a API e validar os métodos de POST, GET, PUT, DELETE
#Background:

########### Cenários esperados + adicionais###################
  @validacaoAplicacao @testeAPIPost
  Scenario: Enviar uma Requisição para a API criando um registro usando o método POST
    Given valido_endpoint "users"
    When criar_registro_api "users" "André Marinho"
    Then valida registro existente "first_name" "Andre"

  @validacaoAplicacao @testeAPIGetLIST
  Scenario: Enviar uma Requisição para a API retornando todos os dados do o método GET LIST USER
    Given valido_endpoint "users"
    Then  get users API "users"

  @validacaoAplicacao @testeAPIGetSINGLE
  Scenario: Enviar uma Requisição para a API retornando todos os dados do o método GET SINGLE USER
    Given valido_endpoint "users"
    Then valida registro existente "id" "12"

  @validacaoAplicacao @testeAPIPATCH
  Scenario: Enviar uma Requisição para a API atualizando  um registro
    Given valido_endpoint "users"
    When PATCH registro_api "users" "11"
##############FIM###############################

 ##################Cenários complementares######################
  @validacaoAplicacao @testeAPIPUT
  Scenario: Enviar uma Requisição para a API atualizando  um registro
    Given valido_endpoint "users"
    When update_registro_api "users" "11"
    Then valida registro existente "first_name" "André Marinho"


  @validacaoAplicacao @testeAPIDelete
  Scenario: Enviar uma Requisição para a API deletando um registro
    Given valido_endpoint "users"
    When  valida registro existente "id" "11"
    Then deletar_user_ID "users" "11"

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
 ##################FIM###################################