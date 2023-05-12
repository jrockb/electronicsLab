package co.com.jcd.electronicslab.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
public class Usuario implements Serializable{

	private static final long serialVersionUID = -2153929132113604367L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotEmpty
	@Column(name = "nombre_usuario")
	private String nombre;
	@NotEmpty
	@Column(name = "apellido_usuario")
	private String apellido;
	@NotEmpty
	@Size(min =  3, max = 8) // para definir un minimo y maximo de caracteres
	private String aliasUsuario;
	@NotEmpty
	private String telefono;
	@NotEmpty
	private String direccion;
	@NotEmpty
	@Email(message = "Correo con formato incorrecto")
	private String email;
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name="usuario_instrumento", 
		joinColumns = @JoinColumn(name = "usuario_id"),
		inverseJoinColumns = @JoinColumn(name = "instrumento_id"),
		uniqueConstraints = {@UniqueConstraint(columnNames = {"usuario_id","instrumento_id"})})	
	private List<Instrumento> instrumentos;
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name="usuario_herramienta", 
		joinColumns = @JoinColumn(name = "usuario_id"),
		inverseJoinColumns = @JoinColumn(name = "herramienta_id"),
		uniqueConstraints = {@UniqueConstraint(columnNames = {"usuario_id","herramienta_id"})})	
	private List<Herramienta> herramientas;
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name="usuario_componente", 
		joinColumns = @JoinColumn(name = "usuario_id"),
		inverseJoinColumns = @JoinColumn(name = "componente_id"),
		uniqueConstraints = {@UniqueConstraint(columnNames = {"usuario_id","componente_id"})})	
	private List<Componente> componentes;
	@OneToMany(mappedBy = "usuario")	
	private List<Proyecto> proyectos;

}
