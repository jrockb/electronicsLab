package co.com.jcd.electronicslab.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "proyectos")
@Getter
@Setter
@NoArgsConstructor
public class Proyecto implements Serializable{

	private static final long serialVersionUID = 3308084694874741599L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "nombre_proyecto")
	private String nombre;
	private String tipo;
	@Column(name = "fecha_inicio")
	private Date fechaInicio;
	@Column(name = "fecha_Fin")
	private Date fechaFin; 
	@ManyToOne(targetEntity = Usuario.class)
	@JsonBackReference
	private Usuario usuario;
	
	public Proyecto(String nombre, String tipo, 
			Date fechaInicio, Date fechaFin) {
		this.nombre = nombre;
		this.tipo = tipo;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
	}
	
	
	
	
		
}
