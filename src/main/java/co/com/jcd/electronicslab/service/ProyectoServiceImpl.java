package co.com.jcd.electronicslab.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.jcd.electronicslab.dto.ProyectoDto;
import co.com.jcd.electronicslab.model.Proyecto;
import co.com.jcd.electronicslab.model.dao.IProyectoDao;
import co.com.jcd.electronicslab.request.ProyectoRequest;
import co.com.jcd.electronicslab.response.ProyectoDtoResponse;
import co.com.jcd.electronicslab.response.ProyectoResponse;

@Service
public class ProyectoServiceImpl implements IProyectoService {
	
	@Autowired
	private IProyectoDao proyectoDao;
	
	// mejoras: validar si se puede crear el objeto a persistir usando un mapper.
	// agregar a la entidad el campo fechaCreacion y agregar el valor en este metodo.

	@Override
	@Transactional
	public ResponseEntity<ProyectoResponse> crearProyecto(ProyectoRequest request) {
		ProyectoResponse response = new ProyectoResponse();
		Proyecto proyecto  = new Proyecto(request.getNombreProyecto(), request.getTipoProyecto(), 
				request.getFechaInicio(), request.getFechaFin());
		try {
			Proyecto proyectoGuardar = proyectoDao.save(proyecto);
			if(proyectoGuardar.getId() != null) {
				response.setCodigo("00");
				response.setTipo("Ejecución exitosa");
				response.setRespuesta("OK");
				response.setCodigoProyecto(proyectoGuardar.getId());
				response.setNombreProyecto(proyectoGuardar.getNombre());
			} else {
				response.setCodigo("-1");
				response.setTipo("No se guardó el proyecto en la base de datos");
				response.setRespuesta("NA");
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			}
		} catch(Exception ex) {
			response.setCodigo("-1");
			response.setTipo("Error creando el proyecto");
			response.setRespuesta("NA");
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<ProyectoDtoResponse> obtenerProyectos() {
		List<Proyecto> proyectos = (List<Proyecto>) proyectoDao.findAll();
		ProyectoDtoResponse response = new ProyectoDtoResponse();		
		if(!proyectos.isEmpty()) {
			List<ProyectoDto> listaProyectoDto = new ArrayList<>();
			for(Proyecto pro : proyectos) {
				ProyectoDto proyectoDto = new ProyectoDto();
				proyectoDto.setNombre(pro.getNombre());
				proyectoDto.setTipo(pro.getTipo());
				proyectoDto.setFechaInicio(pro.getFechaInicio());
				proyectoDto.setFechaFin(pro.getFechaFin());
				if(pro.getUsuario() != null) {
					proyectoDto.setUsuario(pro.getUsuario().getAliasUsuario());
				}
				listaProyectoDto.add(proyectoDto);			}
			response.setCodigo("00");
			response.setTipo("Ejecución exitosa");
			response.setRespuesta("OK");
			response.setListProyectos(listaProyectoDto);
		} else {
			response.setCodigo("-1");
			response.setTipo("NOK");
			response.setRespuesta("No se encontraron proyectos");			
		}
		return new ResponseEntity<>(response, HttpStatus.OK);		
	}

}
