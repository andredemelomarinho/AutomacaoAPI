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


	private String url ="https://reqres.in/api/";
	 String respostaId;
	 String respostaNome;
     String endpoint ="users";
	 CommonMethods cm = new CommonMethods();

	 //TALK API COM RESTASSURED\\
	public String getField (String iduser ,String endpoint,String field) {

	try {
	String path =url + endpoint + "/" + iduser;

		JsonPath responseString =
			get(path)
					.then()
					//.statusCode(200)
					.extract().
					jsonPath();
		Reporter.addStepLog(responseString.prettyPrint());
		return responseString.getString("data."+field);
}	catch (Exception ex ){
		return null;
}
}
	public void validaRespostaApi(String campo, String valor){
		if(respostaId==null){
			respostaId =valor;
		}
		String resposta= getField(respostaId,endpoint,campo);
		assertEquals(valor, resposta);
	}
	public  void postUserTest(String endpoint , String nome){
		//RestAssured Test\\

		SimuladorDTOBase baseDto = new SimuladorDTOBase();
		UsuariosDTO dados =baseDto.getSimuladorBaseRealizado();
		JSONObject requestParams = new JSONObject();
		RequestSpecification request = given();
		requestParams.put("first_name", dados.first_name);
		requestParams.put("id", dados.id);
		requestParams.put("last_name", dados.last_name);
		requestParams.put("email", dados.email);
		requestParams.put("avatar",dados.avatar);
		request.header("Content-Type", "application/json");
		request.body(requestParams.toString());
		Response post =request.post(url+endpoint+"/");
		respostaId =post.path("id");
		Reporter.addStepLog("Endpoint utilizado:" +url+endpoint+"/");
		Reporter.addStepLog(post.prettyPrint());


/*
		SimuladorDTOBase baseDto = new SimuladorDTOBase();
		UsuariosDTO dados =baseDto.getSimuladorBaseRealizado();
		String path =url+endpoint+"/";
		JsonPath retorno = given()
				.header("Accept", "application/json")
				.contentType("application/json")
				.body(dados)
				.when()
				.post(path)
				.andReturn()
				.then()
				.statusCode(201)
				.extract()
				.jsonPath();

		Reporter.addStepLog("Reponse:" +retorno.prettyPrint());
		respostaNome = retorno.getJsonObject("first_name");
		respostaId = retorno.getJsonObject("id");
		System.out.println("id "+respostaId+ " - nome "+respostaNome);
		System.out.println("URL: "+path);


*/
	}
	public void putUserTest(String endpoint,String id){

		String idUser= getField(id,endpoint,"id");
		if (idUser==null){
			Assert.fail("Id não encontrado");
		}
		// REST ASSURED \\
/*
		SimuladorDTOBase baseDto = new SimuladorDTOBase();
		UsuariosDTO dados =baseDto.getSimuladorBaseRealizado();
		JSONObject requestParams = new JSONObject();
		RequestSpecification request = given();
		dados.setNome("André Marinho");
		requestParams.put("nome", dados.nome);
		requestParams.put("cpf", dados.cpf);
		requestParams.put("createdAt", dados.createdAt);
		request.header("Content-Type", "application/json");
		request.body(requestParams.toString());
		Response response  = request.put(url+endpoint+"/"+id);
		respostaNome= response.path("nome");
		respostaId= response.path("id");
		Reporter.addStepLog(response.prettyPrint());
		*/



		UsuariosDTO dados = new UsuariosDTO();
		SimuladorDTOBase baseDto = new SimuladorDTOBase();
		dados = baseDto.getSimuladorBaseRealizado();
		String path =url +endpoint+ "/"+id;

		JsonPath retorno =
				given().
				contentType("application/json").
				body(dados).
				when().
				put(path).
				then().
				statusCode(200).and().
						body("nome",Matchers.is(dados.first_name)).
						contentType("application/json").
						extract().
						jsonPath();
		Reporter.addStepLog(retorno.prettyPrint());

	}
	public void patchUserTest(String endpoint,String id){
		RestAssured.baseURI = "https://reqres.in/";
		RestAssured.basePath = "api";
					Response res =
					given()
							.header("Content-Type","application/json")
							.body("{\n" +
									"    \"name\": \"Andre Melo\",\n" +
									"    \"job\": \"QA\"\n" +
									"}")
							.when()
							.patch(endpoint+"/"+id)
							.then()
							.statusCode(200)
							.extract().response();
			Reporter.addStepLog(res.body().prettyPrint());
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
	public void valida_endpoint(String endpoint){
		when().
				get(url + endpoint).
				then().
				statusCode(200);
		System.out.println(url + endpoint);

	}
	public String getNameUserTest(String endpoint, String name,String id){
		String  path =url + endpoint +"/"+id;
		String responseString =get(path).
				then()
				//.body("first_name", equalTo(name))
				.extract()
				.path("first_name");
		Reporter.addStepLog("Reponse:" +responseString);
		if(responseString.equals(name)){
			return "Nome encontrado na API com sucesso:"+responseString;
		}else {
			return "Nome não encontrado na API:" + responseString;
		}
	}
	public void GetUserName(String campo, String valor)	{
		RestAssured.baseURI = "https://reqres.in/";
		RestAssured.basePath = "api";
		Response res = given()
				.queryParam(campo,valor)
				.when()
				.get("/users/");

		JsonPath js = new JsonPath(res.asString());

		//Retrieve name of user
		String name = js.getString("data.first_name");
		// Retrieve email of first user
		String firstUserEmail = js.getString("data.email");
		Reporter.addStepLog("User name is: "+name);
		Reporter.addStepLog("User email is: "+firstUserEmail);

	}

	}








