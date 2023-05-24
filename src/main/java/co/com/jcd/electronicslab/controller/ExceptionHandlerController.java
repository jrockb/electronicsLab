package co.com.jcd.electronicslab.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import co.com.jcd.electronicslab.errors.UsuarioNoEncontradoException;
import co.com.jcd.electronicslab.response.ResponseRest;

@ControllerAdvice
public class ExceptionHandlerController {
	
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<ResponseRest> badRequestHandler(){
		ResponseRest response = new ResponseRest();
		response.setCodigo("-1");
		response.setTipo("Error");
		response.setRespuesta("Error en el request");
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}	
	
	@ExceptionHandler(InvalidFormatException.class)
	public ResponseEntity<ResponseRest> incorrectFormatDateHandler(InvalidFormatException ex){		
		ResponseRest response = new ResponseRest();
		response.setCodigo("-1");
		response.setTipo("Error");
		response.setRespuesta("Error en el request: "+ex.getMessage());
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(UsuarioNoEncontradoException.class)
	public ResponseEntity<ResponseRest> usuarioNoEncontradoHandler(UsuarioNoEncontradoException ex){		
		ResponseRest response = new ResponseRest();
		response.setCodigo("-1");
		response.setTipo("Error");
		response.setRespuesta("Error en el request: "+ex.getMessage());
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
