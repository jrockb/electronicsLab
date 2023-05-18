package co.com.jcd.electronicslab.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

// en ConstraintValidator<> el primer parametro es la clase anotación, 
// el segundo el tipo de elemento que se va validar, en este caso el tipo de identificacion
public class IdentificadorRegexValidador implements ConstraintValidator<IdentificadorRegex, String>{
			
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(value.matches("\\d{7}|\\d{10}")) {
			return true;
		}
		return false;
	}
	
	
	
	
}
