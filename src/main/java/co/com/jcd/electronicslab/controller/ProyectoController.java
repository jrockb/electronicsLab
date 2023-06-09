package co.com.jcd.electronicslab.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.jcd.electronicslab.request.ProyectoRequest;
import co.com.jcd.electronicslab.response.ProyectoDtoResponse;
import co.com.jcd.electronicslab.response.ProyectoResponse;
import co.com.jcd.electronicslab.response.ResponseRest;
import co.com.jcd.electronicslab.service.IProyectoService;
import co.com.jcd.electronicslab.utils.ResponseUtils;

@RestController
@RequestMapping("/electronicslab")
public class ProyectoController {
	
	@Autowired
	private IProyectoService proyectoService;
	
	@PostMapping("/crearProyecto")
	public ResponseEntity<ProyectoResponse> crearProyecto(@Valid @RequestBody ProyectoRequest request, 
			BindingResult result) {
		if(result.hasErrors()) { // mejorar este metodo creando uno generico para generar el mensaje de resultado de validacion
			ResponseRest response = new ProyectoResponse();
			response = ResponseUtils.generarRespuestaValidador(result, response);
			return new ResponseEntity<>((ProyectoResponse) response, HttpStatus.BAD_REQUEST);
		}
		return proyectoService.crearProyecto(request);
	}
	
	@GetMapping("/obtenerProyectos")
	public ResponseEntity<ProyectoDtoResponse> obtenerProyectos(){
		return proyectoService.obtenerProyectos();
	}

}
