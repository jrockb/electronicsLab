package co.com.jcd.electronicslab.request;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HerramientaRequest {
	
	private String nombre;
	private Integer cantidad;
	private String marca;
	private Date fechaAdquisicion;
	private Boolean prestada;
	private String tipo;

}
