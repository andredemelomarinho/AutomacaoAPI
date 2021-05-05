package dto.baseDTO;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import dto.UsuariosDTO;
import org.apache.commons.lang.RandomStringUtils;

//import org.apache.commons.lang3.RandomStringUtils;

import dto.SimuladorDTO;


public class SimuladorDTOBase {

	public  UsuariosDTO getSimuladorBaseRealizado() {
		UsuariosDTO simulador = new UsuariosDTO();
		simulador.setId("14");
		simulador.setFirst_name("Andre");
		simulador.setLast_name("Marinho");
		simulador.setEmail("andre@teste.com.br");
		simulador.setAvatar("https://reqres.in/img/faces/12-image.jpg");

		return simulador;
	}
	
}
