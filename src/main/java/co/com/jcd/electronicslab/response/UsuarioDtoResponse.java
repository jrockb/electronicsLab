package co.com.jcd.electronicslab.response;

import java.util.List;

import co.com.jcd.electronicslab.dto.UsuarioDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDtoResponse extends ResponseRest{
	
	private List<UsuarioDto> usuarios;

}
