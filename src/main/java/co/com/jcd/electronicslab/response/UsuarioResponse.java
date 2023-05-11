package co.com.jcd.electronicslab.response;

import java.util.List;

import co.com.jcd.electronicslab.model.Usuario;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioResponse extends ResponseRest{
	
	private List<Usuario> usuario;

}
