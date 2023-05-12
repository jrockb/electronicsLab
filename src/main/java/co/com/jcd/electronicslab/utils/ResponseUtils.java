package co.com.jcd.electronicslab.utils;

import org.springframework.validation.BindingResult;

import co.com.jcd.electronicslab.response.ResponseRest;

public class ResponseUtils {
	
	public static ResponseRest generarRespuestaValidador(BindingResult result){		
		ResponseRest response = new ResponseRest();
		StringBuilder errores = new StringBuilder();
		errores.append("Error validando campos: ");
		if (result.getErrorCount() > 1) {
			result.getFieldErrors().forEach(err -> {
				String msj = err.getDefaultMessage() != null 
						? err.getDefaultMessage() : err.getCode();
				errores.append(err.getField().concat(" ").concat(msj).concat("\n"));
			});
		} else {
			if(result.getFieldError() != null) {
				String msj = result.getFieldError().getDefaultMessage() != null 
						? result.getFieldError().getDefaultMessage() : 
							result.getFieldError().getCode();
				errores.append(result.getFieldError().getField().concat(" ")
						.concat(msj));
			}
		}
		response.setRespuesta(errores.toString());		
		response.setCodigo("-2");		
		response.setTipo("NA");
		return response;
	}
	
	

}
