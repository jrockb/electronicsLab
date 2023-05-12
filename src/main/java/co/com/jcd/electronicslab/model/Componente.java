package co.com.jcd.electronicslab.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "componentes")
@Getter
@Setter
public class Componente  implements Serializable{

	private static final long serialVersionUID = -2933794184701041149L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
	private String nombre;
	@Column(name = "cantidad_estimada")
	private Long cantidad;
	@Column(name = "tipo_componente")
	private String tipo;
	private String descripcion;
	
	

}
