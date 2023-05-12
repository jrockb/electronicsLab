package co.com.jcd.electronicslab.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.jcd.electronicslab.request.UsuarioRequest;
import co.com.jcd.electronicslab.response.UsuarioResponse;
import co.com.jcd.electronicslab.service.IUsuarioService;
import co.com.jcd.electronicslab.validators.UsuarioValidador;

@RestController
@RequestMapping("/electronicslab")
public class UsuarioController {
	
	@Autowired
	private UsuarioValidador validador;
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@PostMapping("/crearUsuario")	
	public ResponseEntity<UsuarioResponse> obtenerUsuarios(@Valid @RequestBody UsuarioRequest request, 
			BindingResult result) {
		validador.validate(request, result);
		if(result.hasErrors()) { // mejorar este metodo creando uno generico para generar el mensaje de resultado de validacion
			StringBuilder errores = new StringBuilder();
			UsuarioResponse response = new UsuarioResponse();
			errores.append("Error validando campos: ");
			result.getFieldErrors().forEach(err -> {
				String msj = err.getDefaultMessage() != null ? err.getDefaultMessage() : err.getCode();
				errores.append("el campo "+err.getField()+" "+
						msj+" ");
			});
			response.setCodigo("-2");
			response.setRespuesta(errores.toString());
			response.setTipo("NA");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		return usuarioService.crearUsuario(request);
	}
	
	

}
