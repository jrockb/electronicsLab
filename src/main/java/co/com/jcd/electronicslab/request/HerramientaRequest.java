package co.com.jcd.electronicslab.request;

import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HerramientaRequest {
	
	private String nombre;
	@NotNull // para validar objetos, para validar el tipo primitivo int se usa @Min
	@Min(1)
	@Max(5000)
	private Integer cantidad;
	private String marca;
	private Date fechaAdquisicion;
	private Boolean prestada;
	private String tipo;

}
