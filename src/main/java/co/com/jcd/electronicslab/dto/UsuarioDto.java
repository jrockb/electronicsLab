package co.com.jcd.electronicslab.dto;

import java.util.List;

import co.com.jcd.electronicslab.model.Proyecto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDto{
	
	private String nombre;
	private String apellido;
	private String aliasUsuario;
	private String identificacion;
	private String telefono;
	private String direccion;
	private String email;
	private List<Proyecto> proyectos;
	

}
