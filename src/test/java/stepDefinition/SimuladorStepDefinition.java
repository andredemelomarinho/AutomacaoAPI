package stepDefinition;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import automation.Pages;
import automation.utils.TestData;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import service.RequisicaoAPI;
import session.ThreadManager;
import com.cucumber.listener.Reporter;

public class SimuladorStepDefinition {
	RequisicaoAPI req = new RequisicaoAPI();
	private Pages getPages() {
		return ThreadManager.getSession().getPages();
	}
	String cpf="";
	Integer id=0;
	



	//-----------------------Start API tests definition here-------------------------//
	@And("^valido_endpoint \"([^\"]*)\"$")
	public void valido_endpoint(String endpoint) throws Throwable {
		req.valida_endpoint(endpoint);
	}

	@And("^deletar_user_ID \"([^\"]*)\" \"([^\"]*)\"$")
	public void deletar_user_by_id(String endpoint, String iduser) throws Throwable {
		req.deleteUserTest(endpoint,iduser);
		//Reporter.addStepLog(result);
	}
	@And("^criar_registro_api \"([^\"]*)\" \"([^\"]*)\"$")
	public void request_post_api(String endpoint, String nome) throws Throwable {
		req.postUserTest(endpoint, nome);
	}
	@And("^update_registro_api \"([^\"]*)\" \"([^\"]*)\"$")
	public void update_registro_api(String endpoint, String id) throws Throwable {
		req.putUserTest(endpoint,id);

	}
	@When("^PATCH registro_api \"([^\"]*)\" \"([^\"]*)\"$")
	public void patch_registro_api(String endpoint, String id) throws Throwable {
		req.patchUserTest( endpoint,  id);

	}
	@Then("^valida registro existente \"([^\"]*)\" \"([^\"]*)\"$")
	public void valida_nome_criado(String campo,String valor) throws Throwable {
		req.validaRespostaApi(campo, valor);
		//req.GetUserName(campo, valor);

	}


	@And("^update_registro_api_dataprovider \"([^\"]*)\" \"([^\"]*)\"$")
	public void update_registro_api_dataprovider(String endpoint, String plan) throws Throwable {
		//req.putUserFromDataProvider(endpoint,plan);

	}
	@Then("^valido_nome_response_dataprovider \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
	public void valido_nome_response_dataprovider(String endpoint ,String planilha,String nome) throws Throwable {
		//req.validateUsersFromDataProvider(endpoint,planilha,nome);

	}
	@Then("^get pessoas API \"([^\"]*)\"$")
	public void get_pessoas_API(String endpoint) throws Throwable {
		req.getPessoas(endpoint);

	}
	@And("^valido_nome_response \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
	public void valido_nome_response(String endpoint, String nome,String id) throws Throwable {
		String result =req.getNameUserTest(endpoint,nome,id);
		Reporter.addStepLog(result);

	}
	@And("^valido_userId_response \"([^\"]*)\" \"([^\"]*)\"$")
	public void valido_user_id_response(String endpoint, String id) throws Throwable {
		String body = req.getField(id, endpoint, "id");
		Reporter.addStepLog("Reponse:" + body);
	}
	@Then("^get users API \"([^\"]*)\"$")
	public void get_users_API(String endpoint) throws Throwable {
			req.getPessoas(endpoint);
		}



	}
	//-----------------------End definition here------------------------//


