Feature: Enviar uma request para a API e validar os métodos GET da API restrições

  @validacaoAplicacao @testeAPI1
  Scenario: Consultar uma restrição pelo CPF válido
    Given envia request para api com cpf "97093236014"
    Then Valida se o cpf tem restricao

  @validacaoAplicacao @testeAPI2
  Scenario: Consultar uma restrição pelo CPF inválido
    Given envia request para api com cpf "970932360164"
    Then Valida se o cpf tem restricao

  @validacaoAplicacao @testeAPI3
  Scenario: Consultar uma restrição pelos CPFs no dataprovider
    Given envia request para api com cpf


