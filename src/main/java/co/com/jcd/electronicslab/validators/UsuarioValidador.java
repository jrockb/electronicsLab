package co.com.jcd.electronicslab.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import co.com.jcd.electronicslab.request.UsuarioRequest;

@Component
public class UsuarioValidador implements Validator{

	@Override
	public boolean supports(Class<?> clazz) { // que clase se va validar	
		return UsuarioRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) { // validar campo
		UsuarioRequest usuario = (UsuarioRequest) target;
		ValidationUtils.rejectIfEmpty(errors, "nombre", "El nombre de usuario no debe estar vacío");
		if(!usuario.getIdentificacion().matches("\\d{7}|\\d{10}")) {
			errors.rejectValue("identificacion", "el número de identificación debe tener 10 o 7 digitos");
		}
		
		/**
		 * otra opcion para validar el nombre:
		 * 
		 * if(usuario.getNombre().isEmpty()){
		 * 		errors.rejectValue("nombre", "mensaje");
		 * }
		 * 
		 * 
		 */
	}

}
