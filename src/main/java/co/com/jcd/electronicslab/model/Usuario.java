package co.com.jcd.electronicslab.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable{

	private static final long serialVersionUID = -2153929132113604367L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "nombre_usuario")
	private String nombre;
	@Column(name = "apellido_usuario")
	private String apellido;
	private String telefono;
	private String direccion;
	private String email;

}
