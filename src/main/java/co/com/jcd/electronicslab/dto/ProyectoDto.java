package co.com.jcd.electronicslab.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProyectoDto {
	
	private String nombre;
	private String tipo;
	private Date fechaInicio;
	private Date fechaFin;
	private String usuario;

}
