package co.com.jcd.electronicslab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.jcd.electronicslab.model.Usuario;
import co.com.jcd.electronicslab.model.dao.IUsuarioDao;
import co.com.jcd.electronicslab.request.UsuarioRequest;
import co.com.jcd.electronicslab.response.UsuarioResponse;

@Service
public class UsuarioServiceImpl implements IUsuarioService {
	
	@Autowired
	private IUsuarioDao usuarioDao;

	@Override
	@Transactional
	public ResponseEntity<UsuarioResponse> crearUsuario(UsuarioRequest request) {
		UsuarioResponse response = new UsuarioResponse();
		Usuario usuario = new Usuario(request.getNombre(), 
				request.getApellido(), 
				request.getAliasUsuario(),
				request.getIdentificacion(),
				request.getTelefono(), 
				request.getDireccion(), 
				request.getEmail());
		try {
			Usuario usuarioGuardar = usuarioDao.save(usuario);
			if(usuarioGuardar.getId() != null) {
				response.setCodigo("00");
				response.setTipo("Ejecución exitosa");
				response.setRespuesta("OK");
				response.setId(usuarioGuardar.getId());
			} else {
				response.setCodigo("-1");
				response.setTipo("No se guardó el proyecto en la base de datos");
				response.setRespuesta("NA");
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			}
		} catch(Exception ex) {
			response.setCodigo("-1");
			response.setTipo("Error creando el proyecto");
			response.setRespuesta("NA");
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
