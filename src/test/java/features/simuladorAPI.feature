Feature: Enviar uma request para a API e validar os métodos de POST, GET, PUT, DELETE

  @validacaoAplicacao @testeAPISimulacoes
  Scenario: Realizar uma simulação válida
    Given realiza uma simulacao valida
    Then  valida a simulacao realizada

  @validacaoAplicacao @testeAPISimulacoes1
  Scenario: Realizar uma simulação com cpf existente
    Given consulta cpf simulado
    When  realiza uma simulacao com cpf existente
    Then  valida a simulacao realizada

  @validacaoAplicacao @testeAPISimulacoes2
  Scenario: Realizar uma simulação com cpf no formato incorreto
    When  realiza uma simulacao com cpf incorreto "007.886.250-68"
    Then  valida a simulacao realizada

  @validacaoAplicacao @testeAPISimulacoes3
  Scenario: Realizar update de uma simulação com cpf
    Given consulta cpf simulado
    When  update_registro_api "André Melo Marinho"
    Then  valida a simulacao realizada
  @validacaoAplicacao @testeAPISimulacoes31
  Scenario: Realizar update de uma simulação com cpf
    Given consulta cpf informado "66414919004"
    When  update_registro_api "André M.. Marinho"
    Then  valida update realizado "André M.. Marinho"

  @validacaoAplicacao @testeAPISimulacoes4
  Scenario: Realizar consulta de simulação retornando todas as simulações
   Given  envia o request para consulta
   When   conta quantidade de dados retornados
   Then   valida a simulacao realizada

  @validacaoAplicacao @testeAPISimulacoes5
  Scenario: Realizar consulta de uma simulação por CPF
    Given  envia o request para consulta com "00788639021"
    Then   valida a simulacao realizada com "00788639021 "

  @validacaoAplicacao @testeAPISimulacoes6
  Scenario: Realizar consulta de uma simulação por CPF inválido
    Given  envia o request para consulta com "007886390210000"
    Then   valida a simulacao realizada com "0078863902100 "

  @validacaoAplicacao @testeAPISimulacoes7
  Scenario: Delete uma simulação existente
    Given encontra um id de simulacao cadastrada
    When  envia request para o delete
    Then  valida se o id foi deletado

  @validacaoAplicacao @testeAPISimulacoes8
  Scenario: Delete uma simulação não existente
    Given  envia request para o delete 999
    Then   valida se o id foi deletado






