package dto.baseDTO;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import dto.UsuariosDTO;
import org.apache.commons.lang.RandomStringUtils;

//import org.apache.commons.lang3.RandomStringUtils;

import dto.SimuladorDTO;


public class SimuladorDTOBase {

	public  UsuariosDTO getSimuladorBaseRealizado() {
		UsuariosDTO simulador = new UsuariosDTO();
		simulador.setCpf("00788639022");
		simulador.setEmail("andretestesicredi@gmail.com");
		simulador.setNome("Andre Marinho");
		simulador.setParcela(10);
		simulador.setValor(25000);
		simulador.setSeguro(false);
		return simulador;
	}
	
}
