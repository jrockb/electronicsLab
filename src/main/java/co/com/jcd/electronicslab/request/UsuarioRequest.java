package co.com.jcd.electronicslab.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioRequest {	
	
	//@NotEmpty ya no es necesaria porque se implementa un validador para este campo  
	private String nombre;
	@NotEmpty
	private String apellido;
	@NotBlank
	@Size(min =  3, max = 8) // para definir un minimo y maximo de caracteres
	private String aliasUsuario;
	@NotEmpty
	private String identificacion; // va ser validado por la clase validador
	@NotEmpty
	private String telefono;
	@NotEmpty
	private String direccion;
	@NotEmpty
	@Email(message = "Correo con formato incorrecto")
	private String email;

}
