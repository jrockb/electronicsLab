package co.com.jcd.electronicslab.model.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import co.com.jcd.electronicslab.model.Proyecto;

public interface IProyectoDao extends CrudRepository<Proyecto, Long> {
	
	@Query("select u.id from Proyecto u where u.id = ?1 and u.usuario.id = ?2")
	public Long findProyectoByIdusuario(Long idProyecto, Long idUsuario);

}
