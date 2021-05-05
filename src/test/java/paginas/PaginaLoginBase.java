package paginas;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import automation.Pages;
import org.openqa.selenium.*;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import org.testng.Assert;

import session.ThreadManager;

import automation.utils.PropertiesUtils;
import automation.AbstractPage;

import webcomponents.Button;
import webcomponents.TextBox;

public class PaginaLoginBase extends AbstractPage{
	
	public PaginaLoginBase(WebDriver driver) {
	}
	private Pages getPages() {
		return ThreadManager.getSession().getPages();
	}
	public WebDriver getDriver() {
		return ThreadManager.getSession().getDriver();
	}
	UUID uuid = UUID.randomUUID();
	String myRandom = uuid.toString();



	/**
	 * Recebe URL como parametro para abrir pagina
	 * @param url
	 */
	//TODO: colocar como privado e ir criando os métodos para abrir páginas especificas
	public void open(String url) {
		if (ThreadManager.getSession() == null){
			System.out.println("++++++++++++++++++++++++++++++++Sessão NULA+++++++++++++++++++++++++++++++++++++++++");
		}
		if (ThreadManager.getSession().getDriver() == null){
			System.out.println("++++++++++++++++++++++++++++++++Driver NULO+++++++++++++++++++++++++++++++++++++++++");
		}
		try{
		ThreadManager.getSession().getDriver().get(url);//navigate().to(url);
		 ThreadManager.getSession().getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		}catch(TimeoutException toe){
		ThreadManager.getSession().getDriver().navigate().to(url);
		 ThreadManager.getSession().getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		}catch(Exception ex){

		}
	}

	public void openLogin(String baseUrl) {
		System.out.print(baseUrl);
		
    	open(baseUrl);
		
	}
	public void acessoLogin(){
		WebElement login =getDriver().findElement(By.xpath("//a[@class='login']"));
		login.click();

	}
	public void loginSite(){
		WebElement email = getDriver().findElement(By.xpath("//input[@id='email']"));
		email.click();
		email.sendKeys("datumqatest@soprock.com");
		WebElement passwd = getDriver().findElement(By.id("passwd"));
		passwd.click();
		passwd.sendKeys("datum2021");
		WebElement submit = getDriver().findElement(By.id("SubmitLogin"));
		submit.click();
	}
	public void enterRegister(){
		fillMailtoRegister();
		register();
	}
	public void fillMailtoRegister(){
		String random =myRandom.substring(0,10);
		WebElement mail = getDriver().findElement(By.id("email_create"));
		mail.sendKeys("andre@andremarinho.com.br"+random);
	}
	public void register(){
		WebElement register = getDriver().findElement(By.id("SubmitCreate"));
		register.submit();
	}

}
