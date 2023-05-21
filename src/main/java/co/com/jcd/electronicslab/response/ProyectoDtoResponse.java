package co.com.jcd.electronicslab.response;

import java.util.List;

import co.com.jcd.electronicslab.dto.ProyectoDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProyectoDtoResponse extends ResponseRest{
	
	private List<ProyectoDto> listProyectos;

}
