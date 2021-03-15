package service;


import CommonMethods.CommonMethods;
import com.cucumber.listener.Reporter;
import dto.UsuariosDTO;
import dto.baseDTO.SimuladorDTOBase;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.json.JSONArray;



import static io.restassured.RestAssured.get;
import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.junit.Assert;


public class RequisicaoAPI {


	private String url ="https://604e13942a808e00177848a8.mockapi.io/api/cadastrar/";

	public static Map<String, String> map = new HashMap<String, String>();
	 CommonMethods cm = new CommonMethods();

	 //TALK API COM RESTASSURED\\
	public String getField (String iduser ,String endpoint,String field) {
		String responseString="";
try {


	 responseString =
			get(url + endpoint + "/" + iduser)
					.then()
					//.statusCode(200)
					.extract().
					path("id");
	return responseString;
}catch (Exception ex ){
	return null;
}

	}
	public void  postUserTest(String endpoint , String nome){
		//RestAssured Test\\
       /*
		SimuladorDTOBase baseDto = new SimuladorDTOBase();
		UsuariosDTO dados =baseDto.getSimuladorBaseRealizado();
		JSONObject requestParams = new JSONObject();
		RequestSpecification request = given();
		dados.setNome("André de Melo Marinho");
		requestParams.put("nome", dados.nome);
		requestParams.put("cpf", dados.cpf);
		requestParams.put("createdAt", dados.createdAt);
		request.header("Content-Type", "application/json");
		request.body(requestParams.toString());
		Response post =request.post(url+endpoint+"/");
		Reporter.addStepLog(post.prettyPrint());
       */ 

		SimuladorDTOBase baseDto = new SimuladorDTOBase();
		UsuariosDTO dados =baseDto.getSimuladorBaseRealizado();
		dados.setNome("Juviana Marinho");
		JsonPath retorno = given()
				.header("Accept", "application/json")
				.contentType("application/json")
				.body(dados)
				.when()
				.post(url+endpoint+"/")
				.andReturn()
				.then()
				.statusCode(201)
				.extract()
				.jsonPath();

		Reporter.addStepLog("Reponse:" +retorno.prettyPrint());
		String resposta = retorno.getJsonObject("nome");
		assertEquals(dados.nome, resposta);


	}
	public void putUserTest(String endpoint,String id){

		String idUser= getField(id,endpoint,"id");
		if (idUser==null){
			Assert.fail("Id não encontrado");
		}
		// REST ASSURED \\

		SimuladorDTOBase baseDto = new SimuladorDTOBase();
		UsuariosDTO dados =baseDto.getSimuladorBaseRealizado();
		JSONObject requestParams = new JSONObject();
		RequestSpecification request = given();
		dados.setNome("André de Melo Marinho");
		requestParams.put("nome", dados.nome);
		requestParams.put("cpf", dados.cpf);
		requestParams.put("createdAt", dados.createdAt);
		request.header("Content-Type", "application/json");
		request.body(requestParams.toString());
		Response response  = request.put(url+endpoint+"/"+id);
		Reporter.addStepLog(response.prettyPrint());


/*
		UsuariosDTO dados = new UsuariosDTO();
		SimuladorDTOBase baseDto = new SimuladorDTOBase();
		dados = baseDto.getSimuladorBaseRealizado();
		dados.nome ="André Melo Marinho";
		String path =urlSimulacoes +endpoint+ "/"+id;

		JsonPath retorno =
				given().
				contentType("application/json").
				body(dados).
				when().
				put(path).
				then().
				statusCode(200).and().
						body("nome",Matchers.is(dados.nome)).
						contentType("application/json").
						extract().
						jsonPath();
		Reporter.addStepLog(retorno.prettyPrint());
*/
	}
	public void deleteUserTest(String endpoint ,String id){
		String idUser= getField(id,endpoint,"id");
		if(idUser.equals(id)){

			when().
					delete(url + endpoint + "/" + idUser).
					then().
					statusCode(200);
			System.out.println(url + endpoint + "/" + idUser);
		}else{
			Assert.fail("Id não encontrado");
		}

	}
	public JsonPath getPessoas (String endpoint) {
		// Rest Assured\\
		/*
		Response pessoas = get(url+endpoint);
		pessoas.then().statusCode(200);
		Reporter.addStepLog("Pessoas:" +pessoas.prettyPrint());
		*/

		JsonPath retorno =
				get(url + endpoint + "/" )
						.then()
						.statusCode(200)
						.extract().
						jsonPath();
		Reporter.addStepLog("Reponse:" +retorno.prettyPrint());
		return retorno;
	}

////////////////////////////////////////////////////////////////////////////////////////




	private String urlSimulacoes = "https://604e13942a808e00177848a8.mockapi.io/api/cadastrar/";
	public String  valida_endpoint_restricao(){
		String responseString =get(urlSimulacoes).
				then().
				statusCode(200).
				extract().
				path("mensagem");

		System.out.println(urlSimulacoes );
		return responseString;
	}
	public void valida_endpoint_cpf(String cpf){
				when().
				get(url + cpf).
				then().
				statusCode(200).
				extract();

		System.out.println(url + cpf);

	}
	public void validaRestricaoCpf(String mensagem){
		if(mensagem.contains("tem problema")){
			Reporter.addStepLog("CPF com restrição");
		}else{
			Reporter.addStepLog("CPF sem  restrição");
		}
	}
	public String getRestricaoByCPF (String cpf) {
		String path =urlSimulacoes+cpf;
		Reporter.addStepLog("Reponse:" +path);
		String responseString =get(path).
				then().
				statusCode(200).
				extract().
				path("mensagem");

		Assert.assertNotNull(responseString);
		return responseString;
	}
	public String getCPFSimulacao(){
	String path =urlSimulacoes;
	Reporter.addStepLog("Reponse:" +path);
	JsonPath retorno = given()
			.header("Accept", "application/json")
			.get(path)
			.andReturn().jsonPath();

	List<String> UsuariosDTO = retorno.getJsonObject("cpf");
	String cpf =UsuariosDTO.get(0).toString();
	return cpf;
}
	public Integer getIdSimulacao(){
		List<String> simulacoes =getListSimulacao();
		int registros =simulacoes.size();
		String path =urlSimulacoes;
		Reporter.addStepLog("Reponse:" +path);
		JsonPath retorno = given()
				.header("Accept", "application/json")
				.get(path)
				.andReturn().jsonPath();

		List<Integer> UsuariosDTO = retorno.getJsonObject("id");
		Integer id =UsuariosDTO.get(registros-1);
		Reporter.addStepLog("id encontrado na simulação : "+id);
		return id;
	}
	public String getSimulacaoByCPF(String cpf){
		String path =urlSimulacoes+cpf;
		Reporter.addStepLog("Reponse:" +path);
		JsonPath retorno = given()
				.header("Accept", "application/json")
				.get(path)
				.andReturn().jsonPath();
		String cpfs = retorno.getJsonObject("cpf");
		String nome = retorno.getJsonObject("nome");
		Reporter.addStepLog("CPF encontrado : "+cpfs);
		Reporter.addStepLog("Nome encontrado : "+nome);
		return cpfs;
	}
	public List<String> getListSimulacao(){
		String path =urlSimulacoes;
		JsonPath retorno = given()
				.header("Accept", "application/json")
				.get(path)
				.andReturn().jsonPath();
				List<String> UsuariosDTO = retorno.getJsonObject("cpf");
		return UsuariosDTO;
	}
	public void realiza_uma_simulacao_com_cpf_existente(String cpf){
		RestAssured.baseURI = "http://localhost:8888/api/v1/";
		UsuariosDTO dados = new UsuariosDTO();
		SimuladorDTOBase baseDto = new SimuladorDTOBase();
		dados = baseDto.getSimuladorBaseRealizado();
		dados.cpf =cpf;

		JsonPath retorno = given()
				.header("Accept", "application/json")
				.contentType("application/json")
				.body(dados)
				.expect()
				.statusCode(409)
				.when()
				.post("/simulacoes")
				.andReturn()
				.jsonPath();

	}
	public void  postSimualacaoCpfIncorreto(String cpf){
		RestAssured.baseURI = urlSimulacoes;
		UsuariosDTO dados ;
		SimuladorDTOBase baseDto = new SimuladorDTOBase();
		dados = baseDto.getSimuladorBaseRealizado();
		dados.cpf=cpf;
		JsonPath retorno = given()
				.header("Accept", "application/json")
				.contentType("application/json")
				.body(dados)
				.expect()
				.statusCode(409)
				.when()
				.post("/simulacoes")
				.andReturn()
				.jsonPath();
		Reporter.addStepLog("Reponse:" +retorno.prettyPrint());
		String resposta = retorno.getJsonObject("nome");
		assertEquals(dados.nome, resposta);
	}
	public void  postSimualacaoDadosValidos(){
		RestAssured.baseURI = "http://localhost:8888/api/v1/";
		UsuariosDTO dados = new UsuariosDTO();
		SimuladorDTOBase baseDto = new SimuladorDTOBase();
		dados = baseDto.getSimuladorBaseRealizado();
		JsonPath retorno = given()
						.header("Accept", "application/json")
						.contentType("application/json")
						.body(dados)
						//.expect()
						//.statusCode(201)
						.when()
						.post("/simulacoes")
						.andReturn()
						.jsonPath();
		Reporter.addStepLog("Reponse:" +retorno.prettyPrint());
		UsuariosDTO resposta =  retorno.getJsonObject("nome");
		assertEquals(dados.nome, resposta.getNome());



	}
	public void putUserTestCPF(String nome,String cpf){
		UsuariosDTO dados = new UsuariosDTO();
		SimuladorDTOBase baseDto = new SimuladorDTOBase();
		dados = baseDto.getSimuladorBaseRealizado();
		dados.nome =nome;
		dados.cpf =cpf;
		String path =urlSimulacoes +cpf;

		JsonPath retorno =	given().
					contentType("application/json").
					body(dados).
					when().
					put(path).
					then().
					statusCode(200).and().
					body("nome",Matchers.is(nome)).
					contentType("application/json").
					extract().
					response().
					jsonPath();
					Reporter.addStepLog(retorno.prettyPrint());

	}
	public int getQuantidadeSimulacoes(List<String> simulacoes){
		int registros=0;
		if(!simulacoes.isEmpty()) {
			registros = simulacoes.size();
			for (String simulacao:simulacoes) {
				Reporter.addStepLog("cpf : "+simulacao);
			}
			Reporter.addStepLog("Registros encontrados :" +simulacoes.size());
		}else{
			Reporter.addStepLog("Não foram encontrados simulações");
			Assert.fail("Não foram encontrados simulações");
		}

		return registros;

	}
	public void validaUltmimaSimulacao(){
		List<String> simulacoes =getListSimulacao();
		int registros =simulacoes.size();
		String path =urlSimulacoes;
		Reporter.addStepLog("Reponse:" +path);
		JsonPath retorno = given()
				.header("Accept", "application/json")
				.get(path)
				.andReturn().jsonPath();

		List<String> UsuariosDTO = retorno.getJsonObject("cpf");
		String cpf =UsuariosDTO.get(registros-1).toString();
		Reporter.addStepLog("CPF encontrado na simulação : "+cpf);
	}
	public void validaSimulacaoByCPF(String cpf){
		List<String> simulacoes =getListSimulacao();
		int registros =simulacoes.size();
		String path =urlSimulacoes+cpf;
		Reporter.addStepLog("Reponse:" +path);
		JsonPath retorno = given()
				.header("Accept", "application/json")
				.expect()
				.statusCode(200)
				.when()
				.get(path)
				.andReturn().jsonPath();
		String UsuariosDTO = retorno.getJsonObject("cpf");
		String cpfs =UsuariosDTO;
		Reporter.addStepLog("CPF encontrado na simulação : "+cpfs);
	}
	public void deleteUserTest(Integer id){
					when().
					delete(urlSimulacoes+id).
					then().
					statusCode(204);
			Reporter.addStepLog(urlSimulacoes+id);

	}
	public void confirmIdDeletado(Integer id){
		Reporter.addStepLog(urlSimulacoes+id);
		when().
				delete(urlSimulacoes+id).
				then().
				statusCode(404);

	}
	public void confirmCpfUpdated(String nome, String cpf){
		String path =urlSimulacoes+cpf;
		Reporter.addStepLog("Reponse:" +path);
		JsonPath retorno = given()
				.header("Accept", "application/json")
				.expect()
				.statusCode(200).and()
				.body("nome",Matchers.is(nome))
				.when()
				.get(path)
				.andReturn().jsonPath();
		String UsuariosDTO = retorno.getJsonObject("nome");
		  nome =UsuariosDTO;
		  Reporter.addStepLog("Nome atualizado para : "+nome);

	}
	public void consultarRestricao(){
		String path ="src/test/resources/Arquivos/restricao.json";

		String arquivo =cm.lerArquivo(path);
		JSONArray array = new JSONArray(arquivo);
		int i = 0;
		String cpf =array.getString(0);

		while(i < array.length()){
			cpf =array.getString(i);
			String urli =urlSimulacoes+cpf;
			Reporter.addStepLog("Reponse:" +urli);
			String responseString =get(urli).
					then().
					statusCode(200).
					extract().
					path("mensagem");

			Assert.assertNotNull(responseString);
			i++;
		}

	}
	public void valida_endpoint(String endpoint){
		when().
				get(url + endpoint).
				then().
				statusCode(200);
		System.out.println(url + endpoint);

	}
	public String getNameUserTest(String endpoint, String name,String id){

		String responseString =get(url + endpoint +"/"+id).
				then().
				body("name", equalTo(name))
				.extract()
				.path("name");
		System.out.println(url + endpoint);
		if(responseString.equals(name)){
			return "Nome encontrado na API com sucesso:"+responseString;
		}else {
			return "Nome não encontrado na API:" + responseString;
		}
	}
	public String getUserTest(String id ,String endpoint) {

		String responseString = get(url + endpoint + "/" + id).
				then().
				body("id", equalTo(id))
				.extract()
				.path("name");
		System.out.println(url + endpoint);
		if (responseString.equals(id)) {
			return "Nome encontrado na API com sucesso:" + responseString;
		} else {
			return "Nome não encontrado na API:" + responseString;
		}


	}




	}




	
		


