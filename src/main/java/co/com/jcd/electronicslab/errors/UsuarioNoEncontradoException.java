package co.com.jcd.electronicslab.errors;

public class UsuarioNoEncontradoException extends RuntimeException{

	private static final long serialVersionUID = 1384762193096661337L;

	public UsuarioNoEncontradoException(String id) {
		super(String.format("usuario con id %s no existe",id));
	}
	
	
	
}
