package co.com.jcd.electronicslab.request;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProyectoRequest {
	
	private String nombreProyecto;
	private String tipoProyecto;
	private Date fechaInicio;
	private Date fechaFin;
	private Long idUsuario;

}
