package co.com.jcd.electronicslab.service;

import org.springframework.http.ResponseEntity;

import co.com.jcd.electronicslab.request.UsuarioRequest;
import co.com.jcd.electronicslab.response.UsuarioDtoResponse;
import co.com.jcd.electronicslab.response.UsuarioResponse;

public interface IUsuarioService {
	
	public ResponseEntity<UsuarioResponse> crearUsuario(UsuarioRequest request);
	
	public ResponseEntity<UsuarioResponse> asignarProyectoUsuario(Long idProyecto, Long idUsuario);
	
	public ResponseEntity<UsuarioDtoResponse> buscarUsuarios();
	
	public ResponseEntity<UsuarioDtoResponse> buscarUsuarioPorId(String id);

}
