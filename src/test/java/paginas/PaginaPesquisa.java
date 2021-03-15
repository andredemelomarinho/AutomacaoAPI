package paginas;
import com.cucumber.listener.Reporter;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import automation.AbstractPage;
import session.ThreadManager;

import org.openqa.selenium.WebDriver;
import session.ThreadManager;

public class PaginaPesquisa {

    public PaginaPesquisa(WebDriver driver) {
        this.getDriver();
    }



    public WebDriver getDriver() {
        return ThreadManager.getSession().getDriver();
    }
    public void clicaSearchBox(){
    WebElement pesquisarClick =getDriver().findElement(By.name("q"));
    pesquisarClick.click();
    }

    public void digitaTextoPesquisa(String texto){
        WebElement digitaTexto =getDriver().findElement(By.name("q"));
        digitaTexto.sendKeys(texto);
        digitaTexto.submit();
    }
    public void validaRespostaPesquisa(){
        WebElement resultado =getDriver().findElement(By.xpath("//span[text()=\"Exibindo resultados para\"]"));
        WebElement pesquisa =getDriver().findElement(By.xpath("//h3[text()=\"Datum TI\"]"));
        resultado.isDisplayed();
        pesquisa.isDisplayed();
        Reporter.addStepLog("Resultado: "+resultado.getText() +pesquisa.getText());
    }


}
