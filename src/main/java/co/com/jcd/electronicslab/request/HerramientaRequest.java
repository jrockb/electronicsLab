package co.com.jcd.electronicslab.request;

import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import com.fasterxml.jackson.annotation.JsonFormat;

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
	@NotNull
	@JsonFormat
    (shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
	@PastOrPresent
	private Date fechaAdquisicion;
	private Boolean prestada;
	private String tipo;

}
