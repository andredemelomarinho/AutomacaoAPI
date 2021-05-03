Feature: Preencher o formulário de pesquisa google

  @preencherformulario @validarSubmit
  Scenario: Preencher e enviar formulário de pesquisa
    Given acesso site para preencher formulario
    When formulario é preenchido "Dataum Ti"
    Then valida envio do formulário
