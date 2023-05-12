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
@Table(name = "instrumentos")
@Getter
@Setter
public class Instrumento implements Serializable{

	private static final long serialVersionUID = 203011659949123810L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer cantidad;
	@Column(name = "marca_instrumento")
	private String marca;
	@Column(name = "fecha_adquisicion")
	private Date fechaAdquisicion;
	private Boolean prestada;
	private String tipo;

}
