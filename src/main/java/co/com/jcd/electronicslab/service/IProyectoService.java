package co.com.jcd.electronicslab.service;

import org.springframework.http.ResponseEntity;

import co.com.jcd.electronicslab.request.ProyectoRequest;
import co.com.jcd.electronicslab.response.ProyectoDtoResponse;
import co.com.jcd.electronicslab.response.ProyectoResponse;

public interface IProyectoService {
	
	public ResponseEntity<ProyectoResponse> crearProyecto(ProyectoRequest request);
	
	public ResponseEntity<ProyectoDtoResponse> obtenerProyectos();

}
