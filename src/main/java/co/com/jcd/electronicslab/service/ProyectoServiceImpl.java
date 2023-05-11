package co.com.jcd.electronicslab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.jcd.electronicslab.model.Proyecto;
import co.com.jcd.electronicslab.model.dao.IProyectoDao;
import co.com.jcd.electronicslab.request.ProyectoRequest;
import co.com.jcd.electronicslab.response.ProyectoResponse;

@Service
public class ProyectoServiceImpl implements IProyectoService {
	
	@Autowired
	private IProyectoDao proyectoDao;

	@Override
	@Transactional
	public ResponseEntity<ProyectoResponse> crearProyecto(ProyectoRequest request) {
		ProyectoResponse response = new ProyectoResponse();
		Proyecto proyecto  = new Proyecto(); // mejorar la creacion del objeto a persistir usando un mapper
		proyecto.setNombre(request.getNombreProyecto());
		proyecto.setTipo(request.getTipoProyecto());
		proyecto.setFechaInicio(request.getFechaInicio());
		proyecto.setFechaFin(proyecto.getFechaFin());
		try {
			Proyecto proyectoGuardar = proyectoDao.save(proyecto);
			if(proyectoGuardar.getId() != null) {
				response.setCodigo("00");
				response.setTipo("Ejecución exitosa");
				response.setRespuesta("OK");
				response.setCodigoProyecto(proyectoGuardar.getId());
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

}