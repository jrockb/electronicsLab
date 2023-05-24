package co.com.jcd.electronicslab.model.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import co.com.jcd.electronicslab.model.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario, Long>{
	
	public Optional<Usuario> findByIdentificacion(String identifiacion);

}
