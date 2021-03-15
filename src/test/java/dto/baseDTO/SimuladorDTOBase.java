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
		simulador.setCpf("00788639022");
		simulador.setCreatedAt("14/03/2021");
		simulador.setNome("Andre Marinho");
		return simulador;
	}
	
}
