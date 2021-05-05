package stepDefinition;

import automation.Pages;
import automation.ProjectBase;
import automation.utils.ArquivoUtils;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import paginas.PaginaCompras;
import paginas.PaginaLoginBase;
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


	@Given("^faco login no site http://automationpractice\\.com/$")
	public void faco_login_no_site_http_automationpractice_com() throws Throwable {
		setupTest("");
		ArquivoUtils.tiraScreenshot("Login.jpg");
		logger.info("Acessando Site http://automationpractice.com/ ");
	}
	@Given("^acesso pagina de cadstro$")
	public void acesso_pagina_de_cadstro() throws Throwable {
		setupTest("");
		ArquivoUtils.tiraScreenshot("Login.jpg");
		logger.info("Acessando Site http://automationpractice.com/ ");
		getPages().get(PaginaLoginBase.class).acessoLogin();
		getPages().get(PaginaLoginBase.class).enterRegister();

	}
	@When("^insiro login e senha pre existentes$")
	public void insiro_login_e_senha_pre_existentes() throws Throwable {
		getPages().get(PaginaLoginBase.class).acessoLogin();
		getPages().get(PaginaLoginBase.class).loginSite();

	}

}
