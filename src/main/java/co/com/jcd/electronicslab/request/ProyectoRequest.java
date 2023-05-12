package co.com.jcd.electronicslab.request;

import java.util.Date;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProyectoRequest {
	
	@NotEmpty // valida que el String no este vacío 
	private String nombreProyecto;
	@NotEmpty(message = "debe haber una descripcion del tipo de proyecto")
	private String tipoProyecto;
	private Date fechaInicio;
	private Date fechaFin;
	private Long idUsuario;
	
	// mejoras: nombre, tipo y fechaInicio deben ser obligatorios, 
    // fecha fin debe ser opcional y tener un valor por defecto
	// validar formato de fechas
	


}
