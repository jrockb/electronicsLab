package co.com.jcd.electronicslab.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.com.jcd.electronicslab.request.UsuarioRequest;
import co.com.jcd.electronicslab.response.ResponseRest;
import co.com.jcd.electronicslab.response.UsuarioResponse;
import co.com.jcd.electronicslab.service.IUsuarioService;
import co.com.jcd.electronicslab.utils.ResponseUtils;
import co.com.jcd.electronicslab.validators.UsuarioValidador;

@RestController
@RequestMapping("/electronicslab")
public class UsuarioController {
	
	@Autowired
	private UsuarioValidador validador;
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(validador);
	}
	
	@PostMapping("/crearUsuario")	
	public ResponseEntity<UsuarioResponse> crearUsuarios(@Valid @RequestBody UsuarioRequest request, 
			BindingResult result) {
		if(result.hasErrors()) { 
			ResponseRest response = new UsuarioResponse();			
			response = ResponseUtils.generarRespuestaValidador(result, response);
			return new ResponseEntity<>((UsuarioResponse)response, HttpStatus.BAD_REQUEST);
		}
		return usuarioService.crearUsuario(request);
	}
	
	@PutMapping("/asignarProyectoUsuario")
	public ResponseEntity<UsuarioResponse> asignarProyecto(@RequestParam String idProyecto, 
			@RequestParam String idUsuario){
		return usuarioService.asignarProyectoUsuario(Long.valueOf(idProyecto), Long.valueOf(idUsuario));		
	}
	
	

}
