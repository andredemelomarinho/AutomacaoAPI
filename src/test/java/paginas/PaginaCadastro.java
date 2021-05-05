package paginas;

import cucumber.api.java.de.Wenn;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import session.ThreadManager;
import sun.awt.windows.WBufferStrategy;

public class PaginaCadastro {
        public PaginaCadastro(WebDriver driver) {
            this.getDriver();

        }
        public WebDriver getDriver() {
            return ThreadManager.getSession().getDriver();
        }
        public void fillRegister(){
            fillGender();
            fillFirtName();
            fillLastName();
            fillPasswd();
            fillBirth();
            addressFirstName();
            addressLastName();
            fillAddress();
            fillCity();
            selectCity();
            fillZipcode();
            fillMobile();
            fillAlias();
            buttonRegister();
        }

        public void fillGender(){
            WebElement gender = getDriver().findElement(By.xpath("//input[@name='id_gender'][@value='1']"));
            gender.click();
        }
        public void fillFirtName(){
            WebElement name =getDriver().findElement(By.id("customer_firstname"));
            name.sendKeys("André");
        }
        public void fillLastName(){
            WebElement lastName = getDriver().findElement(By.id("customer_lastname"));
            lastName.sendKeys("Marinho");
        }
        public void fillPasswd(){
            WebElement password = getDriver().findElement(By.id("passwd"));
            password.sendKeys("123456");
        }
        public void fillBirth(){
            WebElement day = getDriver().findElement(By.name("days"));
            day.sendKeys("8");
            WebElement month = getDriver().findElement(By.name("months"));
            month.sendKeys("12");
            WebElement year = getDriver().findElement(By.name("years"));
            year.sendKeys("1984");

        }
        public void addressFirstName(){
            WebElement name =getDriver().findElement(By.id("firstname"));
            name.sendKeys("André");
        }
        public void addressLastName(){
            WebElement lastName =getDriver().findElement(By.id("lastname"));
            lastName.sendKeys("Marinho");
        }
        public void fillAddress(){
            WebElement address = getDriver().findElement(By.id("address1"));
            address.sendKeys("1001, test street");
        }
        public void fillCity(){
            WebElement city = getDriver().findElement(By.id("city"));
            city.sendKeys("Porto Alegre");
        }
        public void selectCity(){
            WebElement state = getDriver().findElement(By.id("id_state"));
            Select combobox = new Select(state);
            //combobox.selectByVisibleText("Arizona");
            combobox.selectByValue("3");
        }
        public void fillZipcode(){
            WebElement zip = getDriver().findElement(By.id("postcode"));
            zip.sendKeys("11111");
        }
        public void fillMobile(){
            WebElement phone =getDriver().findElement(By.id("phone_mobile"));
            phone.sendKeys("5599669966");
        }
        public void fillAlias()  {
            WebElement alias = getDriver().findElement(By.id("alias"));
            alias.sendKeys("Teste");
        }
        public void buttonRegister(){
            WebElement register =getDriver().findElement(By.id("submitAccount"));
            register.submit();
        }
    }

