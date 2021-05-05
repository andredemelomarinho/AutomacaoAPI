package stepDefinition;

import automation.Pages;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import paginas.PaginaCompras;
import paginas.PaginaPesquisa;
import session.ThreadManager;

public class ComprasStepDefinition {
    private Pages getPages() {
        return ThreadManager.getSession().getPages();
    }
    String produtos ="";


    @When("^pequiso produto \"([^\"]*)\"$")
    public void pequiso_produto_Printed_Chiffon_Dress(String produto)  throws Throwable {
        produtos =produto;
        getPages().get(PaginaCompras.class).pesquisoProduto(produtos);

    }

    @When("^adiciono produto ao carrinho$")
    public void adiciono_produto_ao_carrinho() throws Throwable {
        getPages().get(PaginaCompras.class).adicionoProdutoAoCarrinho(produtos);
    }


    @Then("^realizo o checkout da compra$")
    public void realizo_o_checkout_da_compra() throws Throwable {
       getPages().get(PaginaCompras.class).checkout();
        
    }

    @Then("^realizo o pagamento$")
    public void realizo_o_pagamento() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        
    }
}
