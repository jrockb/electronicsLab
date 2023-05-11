package co.com.jcd.electronicslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.jcd.electronicslab.request.ProyectoRequest;
import co.com.jcd.electronicslab.response.ProyectoResponse;
import co.com.jcd.electronicslab.service.IProyectoService;

@RestController
@RequestMapping("/electronicslab")
public class ProyectoController {
	
	@Autowired
	private IProyectoService proyectoService;
	
	@PostMapping("/crearProyecto")
	public ResponseEntity<ProyectoResponse> crearProyecto(@RequestBody ProyectoRequest request){
		return proyectoService.crearProyecto(request);
	}

}
