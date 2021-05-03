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
	

	//-----------------------Star definition here-------------------------//


	@Given("^valido endpoint restricoes$")
	public void valido_endpoint_restricoes() throws Throwable {
		req.valida_endpoint_restricao();

	}

	@When("^envia request para api com cpf \"([^\"]*)\"$")
	public String envia_request_para_api_com_cpf(String cpfRestricao) throws Throwable {
		req.consultarRestricao();
		cpf =cpfRestricao;
		String body =req.getRestricaoByCPF(cpf);
		Reporter.addStepLog("Reponse:" +body);
		return body;
	}
	@Given("^envia request para api com cpf$")
	public void envia_request_para_api_com_cpf() throws Throwable {
		req.consultarRestricao();
	}

	@Then("^Valida se o cpf tem restricao$")
	public void valida_se_o_cpf_tem_restricao() throws Throwable {
		String mensagem =envia_request_para_api_com_cpf(cpf);
		req.validaRestricaoCpf(mensagem);

	}

	@Given("^realiza uma simulacao valida$")
	public void realiza_uma_simulacao_valida() throws Throwable {
		req.postSimualacaoDadosValidos();
	}

	@Then("^valida a simulacao realizada$")
	public void valida_a_simulacao_realizada() throws Throwable {
		req.validaUltmimaSimulacao();

	}
	@Given("^consulta cpf simulado$")
	public void consulta_cpf_simulado() throws Throwable {
		 cpf =req.getCPFSimulacao();

	}

	@When("^realiza uma simulacao com cpf existente$")
	public void realiza_uma_simulacao_com_cpf_existente() throws Throwable {
		req.realiza_uma_simulacao_com_cpf_existente(cpf);
	}

	@When("^realiza uma simulacao com cpf incorreto \"([^\"]*)\"$")
	public void realiza_uma_simulacao_com_cpf_incorreto(String cpf) throws Throwable {
		req.postSimualacaoCpfIncorreto(cpf);
	}
	@Given("^monta request para consulta$")
	public void monta_request_para_consulta() throws Throwable {
		// Write code here that turns the phrase above into concrete actions

	}

	@When("^envia o request para consulta$")
	public List<String> envia_o_request_para_consulta() throws Throwable {
		List<String> simulacoes =req.getListSimulacao();
		//Reporter.addStepLog(simulacoes);
		return simulacoes;
	}

	@Then("^conta quantidade de dados retornados$")
	public void conta_quantidade_de_dados_retornados() throws Throwable {
		List<String> qtdSimulacoes =envia_o_request_para_consulta();
		req.getQuantidadeSimulacoes(qtdSimulacoes);
	}
	@Given("^envia o request para consulta com \"([^\"]*)\"$")
	public void envia_o_request_para_consulta_com(String cpf) throws Throwable {
		req.getSimulacaoByCPF(cpf);
	}
	@Then("^valida a simulacao realizada com \"([^\"]*)\"$")
	public void valida_a_simulacao_realizada_com(String cpf) throws Throwable {
		req.validaSimulacaoByCPF(cpf);

	}
	@Given("^encontra um id de simulacao cadastrada$")
	public Integer encontra_um_id_de_simulacao_cadastrada() throws Throwable {
		Integer id =req.getIdSimulacao();
		return id;
	}

	@When("^envia request para o delete$")
	public void envia_request_para_o_delete() throws Throwable {
         id =encontra_um_id_de_simulacao_cadastrada();
		req.deleteUserTest(id);
	}

	@Then("^valida se o id foi deletado$")
	public void valida_se_o_id_foi_deletado() throws Throwable {
		req.confirmIdDeletado(id);
	}
	@Given("^envia request para o delete (\\d+)$")
	public void envia_request_para_o_delete(int id) throws Throwable {
		req.deleteUserTest(id);
	}
	@Given("^consulta cpf informado \"([^\"]*)\"$")
	public String consulta_cpf_informado(String cpfUser) throws Throwable {
		cpf =req.getSimulacaoByCPF(cpfUser);
		return cpf;
	}
	@When("^update_registro_api \"([^\"]*)\"$")
	public void update_registro_api(String nome) throws Throwable {
		req.putUserTest(nome,cpf);

	}
	@Then("^valida update realizado \"([^\"]*)\"$")
	public void valida_update_realizado(String nome) throws Throwable {
		req.confirmCpfUpdated(nome,cpf);
	}

	//-----------------------End definition here------------------------//

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
	@Then("^valida registro existente \"([^\"]*)\" \"([^\"]*)\"$")
	public void valida_nome_criado(String campo,String valor) throws Throwable {
		req.validaRespostaApi(campo, valor);
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
		String body =req.getField(id,endpoint,"id");
		Reporter.addStepLog("Reponse:" +body);


	}
	//-----------------------End definition here------------------------//

}
