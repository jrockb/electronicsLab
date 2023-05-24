package co.com.jcd.electronicslab.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.jcd.electronicslab.dto.UsuarioDto;
import co.com.jcd.electronicslab.model.Proyecto;
import co.com.jcd.electronicslab.model.Usuario;
import co.com.jcd.electronicslab.model.dao.IProyectoDao;
import co.com.jcd.electronicslab.model.dao.IUsuarioDao;
import co.com.jcd.electronicslab.request.UsuarioRequest;
import co.com.jcd.electronicslab.response.UsuarioDtoResponse;
import co.com.jcd.electronicslab.response.UsuarioResponse;

@Service
public class UsuarioServiceImpl implements IUsuarioService {
	
	private static final Logger log = LoggerFactory.getLogger(UsuarioServiceImpl.class);
	
	@Autowired
	private IUsuarioDao usuarioDao;
	
	@Autowired
	private IProyectoDao proyectoDao;

	@Override
	@Transactional
	public ResponseEntity<UsuarioResponse> crearUsuario(UsuarioRequest request) {
		log.info("Inicio de metodo crearUsuario");
		UsuarioResponse response = new UsuarioResponse();
		Optional<Usuario> usuarioGuardado = usuarioDao
				.findByIdentificacion(request.getIdentificacion());
		if(usuarioGuardado.isEmpty()) {			
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
					log.info("Usuario creado correctamente");
					if(request.getIdProyecto() != null) {
						log.info("Creando asociación de proyecto");
						Optional<Proyecto> proyecto = proyectoDao
								.findById(Long.valueOf(request.getIdProyecto()));
						if(proyecto.isPresent()) {
							Proyecto proyectoActualizar = proyecto.get();
							proyectoActualizar.setUsuario(usuarioGuardar);
							proyectoDao.save(proyectoActualizar);						
						}
					}
				} else {
					log.error("No se guardó el proyecto en la base de datos");
					response.setCodigo("-1");
					response.setTipo("No se guardó el proyecto en la base de datos");
					response.setRespuesta("NA");
					return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
				}
			} catch(Exception ex) {
				log.error("Error creando el usuario: "+ex.getMessage());
				response.setCodigo("-1");
				response.setTipo("Error creando el proyecto");
				response.setRespuesta("NA");
				return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			log.error("El usuario con ese número de identificación ya existe");
			response.setCodigo("-1");
			response.setTipo("NOK");
			response.setRespuesta("El usuario con ese número de identificación ya existe");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	// mejorar este metodo
	@Override
	@Transactional
	public ResponseEntity<UsuarioResponse> asignarProyectoUsuario(Long idProyecto, Long idUsuario) {
		log.info("Inicio de metodo asignarProyectoUsuario");
		UsuarioResponse response = new UsuarioResponse();
		try {
			Optional<Usuario> usuarioBuscar = usuarioDao.findById(idUsuario);
			if(usuarioBuscar.isPresent()) {
				Long idproyectoBuscar = proyectoDao.findProyectoByIdusuario(idProyecto, idUsuario);
				if(idproyectoBuscar == null) {
					Optional<Proyecto> proyecto = proyectoDao.findById(idProyecto);
					if(proyecto.isPresent()) {
						Proyecto proyectoActualizar = proyecto.get();
						proyectoActualizar.setUsuario(usuarioBuscar.get());
						proyectoDao.save(proyectoActualizar);
						log.info("proyecto asignado exitosamente al usuario");
						response.setCodigo("00");
						response.setTipo("Ejecución exitosa");
						response.setRespuesta("OK");
						response.setId(idUsuario);
					} else {
						log.info("proyecto no existe");
						response.setCodigo("-2");
						response.setTipo("proyecto no existe");
						response.setRespuesta("NOK");
						response.setId(idUsuario);
						return new ResponseEntity<>(response, HttpStatus.OK);	
					}
				} else {
					log.info("el proyecto ya está asignado al usuario");
					response.setCodigo("00");
					response.setTipo("el proyecto ya está asignado al usuario");
					response.setRespuesta("OK");
					response.setId(idUsuario);
					return new ResponseEntity<>(response, HttpStatus.OK);	
				}			
			} else {
				log.info("usuario no existe");
				response.setCodigo("-2");
				response.setTipo("usuario no existe");
				response.setRespuesta("NOK");
				response.setId(idUsuario);
				return new ResponseEntity<>(response, HttpStatus.OK);	
			}
		}catch(Exception ex) {
			log.error("Error creando el usuario: "+ex.getMessage());
			response.setCodigo("-1");
			response.setTipo("Error creando el proyecto");
			response.setRespuesta("NA");
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);		
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<UsuarioDtoResponse> buscarUsuarios() {
		UsuarioDtoResponse response = new UsuarioDtoResponse();
		List<Usuario> usuarios = (List<Usuario>) usuarioDao.findAll();
		List<UsuarioDto> listUsuario = new ArrayList<>();
		if(!usuarios.isEmpty()) {			
			for(Usuario usr : usuarios) {
				UsuarioDto usrDto = new UsuarioDto();
				usrDto.setNombre(usr.getNombre());
				usrDto.setApellido(usr.getApellido());
				usrDto.setAliasUsuario(usr.getAliasUsuario());
				usrDto.setIdentificacion(usr.getIdentificacion());
				usrDto.setDireccion(usr.getDireccion());
				usrDto.setTelefono(usr.getTelefono());
				usrDto.setEmail(usr.getEmail());
				usrDto.setProyectos(usr.getProyectos());
				listUsuario.add(usrDto);
			}
			response.setUsuarios(listUsuario);
			response.setCodigo("00");
			response.setTipo("Ejecución exitosa");
			response.setRespuesta("OK");
			return new ResponseEntity<>(response, HttpStatus.OK);	
		} else {
			response.setCodigo("-1");
			response.setTipo("OK");
			response.setRespuesta("No se encontraron usuarios");
			return new ResponseEntity<>(response, HttpStatus.OK);	
		}
			
	}

}
