package stepDefinition;

import automation.Pages;
import automation.ProjectBase;
import automation.utils.ArquivoUtils;
import cucumber.api.java.en.Given;
import session.ThreadManager;
import dto.SimuladorDTO;

import org.apache.log4j.Logger;

import Enum.Login;

public class LoginStepDefinition extends ProjectBase {
	
	Login login = null;
	SimuladorDTO processoDTO = null;
	
	private Pages getPages() {
		return ThreadManager.getSession().getPages();
	}
	final static Logger logger = Logger.getLogger(LoginStepDefinition.class);
	private void setupTest(String sys) {
		try {
			setUpSuite();
			setUpClass(sys);
			setUpMethod();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Given("^acesso site para preencher formulario$")
	public void acesso_site_para_preencher_formulario() throws Throwable {
		setupTest("");
		ArquivoUtils.tiraScreenshot("Pesquisa.jpg");
		logger.info("Acessando Site Google ");

	}

}
