package stepDefinition;

import automation.Pages;
import cucumber.api.java.en.Given;
import paginas.PaginaCadastro;
import session.ThreadManager;

public class CadastroStepDefinition {
    private Pages getPages() {
        return ThreadManager.getSession().getPages();
    }

    @Given("^realizo cadastro no site$")
    public void realizo_cadastro_no_site() throws Throwable {
       getPages().get(PaginaCadastro.class).fillRegister();

    }
}
