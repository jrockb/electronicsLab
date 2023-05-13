package co.com.jcd.electronicslab.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "herramientas")
@Getter
@Setter
public class Herramienta implements Serializable{
	
	private static final long serialVersionUID = 7014281360718487415L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "nombre_herramienta")
	private String nombre;
	private Integer cantidad;
	@Column(name = "marca_herramienta")
	private String marca;
	@Column(name = "fecha_adquisicion")
	private Date fechaAdquisicion;
	private Boolean prestada;
	private String tipo;
	
	public Herramienta(String nombre, Integer cantidad, String marca, Date fechaAdquisicion, 
			Boolean prestada, String tipo) {
		this.nombre = nombre;
		this.cantidad = cantidad;
		this.marca = marca;
		this.fechaAdquisicion = fechaAdquisicion;
		this.prestada = prestada;
		this.tipo = tipo;
	}
	
	

}
