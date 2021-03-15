package stepDefinition;

import CommonMethods.CommonMethods;
import automation.Pages;
import automation.ProjectBase;
import automation.utils.ArquivoUtils;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import paginas.PaginaPesquisa;
import session.ThreadManager;

public class FormularioContatoStepDefinition extends ProjectBase {



    private Pages getPages() {
        return ThreadManager.getSession().getPages();
    }
    private WebDriver driver;
    CommonMethods cm = new CommonMethods();


    @When("^formulario é preenchido \"([^\"]*)\"$")
    public void formulario_é_preenchido(String texto) throws Throwable {
        getPages().get(PaginaPesquisa.class).clicaSearchBox();
        getPages().get(PaginaPesquisa.class).digitaTextoPesquisa(texto);
        ArquivoUtils.tiraScreenshot("Resultado.jpg");

    }

    @Then("^valida envio do formulário$")
    public void valida_envio_do_formulário() throws Throwable {
        getPages().get(PaginaPesquisa.class).validaRespostaPesquisa();

    }
}
