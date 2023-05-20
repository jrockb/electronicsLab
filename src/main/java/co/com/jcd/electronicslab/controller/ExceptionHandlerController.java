package co.com.jcd.electronicslab.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

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

}
