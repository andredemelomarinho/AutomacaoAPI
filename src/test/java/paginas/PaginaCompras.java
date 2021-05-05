package paginas;

import automation.utils.ArquivoUtils;
import com.sun.deploy.config.WinPlatform;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import session.ThreadManager;
import sun.awt.windows.WBufferStrategy;

import java.lang.reflect.GenericArrayType;

public class PaginaCompras {
    public PaginaCompras(WebDriver driver) {
        this.getDriver();

    }
    public WebDriver getDriver() {
        return ThreadManager.getSession().getDriver();
    }
    JavascriptExecutor jse = (JavascriptExecutor)getDriver();
    Actions action = new Actions(getDriver());
    WebDriverWait wait = new WebDriverWait(getDriver(), 20);
    public void limparPesquisa(){
        WebElement pesquisa = getDriver().findElement(By.id("search_query_top"));
        pesquisa.click();
        pesquisa.clear();
    }

    public void continuarCompras()
    {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@title='Continue shopping']")));
        WebElement contiue =   getDriver().findElement(By.xpath("//span[@title='Continue shopping']"));
        contiue.click();
    }

    public void pesquisoProduto(String produto)
    {
        WebElement pesquisa = getDriver().findElement(By.id("search_query_top"));
        pesquisa.click();
        pesquisa.sendKeys(produto);
        WebElement botaoPesquisa = getDriver().findElement(By.name("submit_search"));
        botaoPesquisa.submit();
    }
    public void adicionoProdutoAoCarrinho(String produto)
    {




        WebElement adicionaProduto = getDriver().findElement(By.xpath("//a[@title='"+produto+"']"));
        adicionaProduto.click();
        ArquivoUtils.tiraScreenshot("adiciona1.jpg");
        action.sendKeys(Keys.ESCAPE).perform();
        jse.executeScript("window.scrollBy(0,523)", "");
        ArquivoUtils.tiraScreenshot("adiciona2.jpg");
        action.moveToElement(adicionaProduto).click().perform();
        ArquivoUtils.tiraScreenshot("adiciona3.jpg");



        WebElement adicionaCarrinho = getDriver().findElement(By.xpath("//span[text()='Add to cart']"));
        ArquivoUtils.tiraScreenshot("adiciona4.jpg");
       adicionaCarrinho.click();
        ArquivoUtils.tiraScreenshot("adiciona5.jpg");
       continuarCompras();
        ArquivoUtils.tiraScreenshot("adiciona6.jpg");
        limparPesquisa();
        ArquivoUtils.tiraScreenshot("adiciona7.jpg");

    }
    public void checkout(){
        WebElement checkoutoption = getDriver().findElement(By.xpath("//a[@title='View my shopping cart']"));
        checkoutoption.click();
        ArquivoUtils.tiraScreenshot("adiciona8.jpg");
        WebElement proceedCheckout = getDriver().findElement(By.xpath("//span[text()='Proceed to checkout']"));
        jse.executeScript("window.scrollBy(0,800)", "");
        ArquivoUtils.tiraScreenshot("proceed0.jpg");
        action.moveToElement(proceedCheckout).click().perform();
        //proceedCheckout.click();
        ArquivoUtils.tiraScreenshot("proceed1.jpg");
        jse.executeScript("window.scrollBy(0,800)", "");
        ArquivoUtils.tiraScreenshot("proceed2.jpg");
         processAdress();
        ArquivoUtils.tiraScreenshot("proceed3.jpg");
        checkTerms();
        processCarrier();
        ArquivoUtils.tiraScreenshot("proceed4.jpg");
        paymentBnakWire();
        ArquivoUtils.tiraScreenshot("proceed5.jpg");
        jse.executeScript("window.scrollBy(0,800)", "");
        confirmOrder();
        ArquivoUtils.tiraScreenshot("proceed6.jpg");

    }
    public void processAdress(){
        WebElement addres = getDriver().findElement(By.xpath("//button[@name='processAddress']"));
        addres.click();
    }
    public void processCarrier(){
        WebElement carrier = getDriver().findElement(By.xpath("//button[@name='processCarrier']"));
        carrier.click();
    }

    public void checkTerms(){
        WebElement term = getDriver().findElement(By.xpath("//input[@name='cgv']"));
        term.click();
    }
    public void paymentBnakWire(){
        WebElement bankwirePayment = getDriver().findElement(By.xpath("//a[@class='bankwire']"));
        bankwirePayment.click();
    }
    public void confirmOrder(){
        WebElement order =getDriver().findElement(By.xpath("//button[@class='button btn btn-default button-medium']"));
        order.click();
    }


}
