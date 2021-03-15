Feature: Preencher o formulário de contato no site datum.inf.br

  @preencherformulario @validarSubmit
  Scenario: Preencher e enviar formulário de pesquisa
    Given acesso site para preencher formulario
    When formulario é preenchido "Dataum Ti"
    Then valida envio do formulário
