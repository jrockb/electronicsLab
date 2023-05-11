package co.com.jcd.electronicslab.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.jcd.electronicslab.request.ProyectoRequest;
import co.com.jcd.electronicslab.response.UsuarioResponse;

@RestController
@RequestMapping("/electronicslab")
public class UsuarioController {
	
	@GetMapping("/usuarios")	
	public ResponseEntity<UsuarioResponse> obtenerUsuarios(ProyectoRequest usuarioRequest){
		return null;
	}
	
	

}
