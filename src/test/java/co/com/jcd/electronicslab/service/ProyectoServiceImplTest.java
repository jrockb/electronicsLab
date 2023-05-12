package co.com.jcd.electronicslab.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import co.com.jcd.electronicslab.model.Proyecto;
import co.com.jcd.electronicslab.model.dao.IProyectoDao;
import co.com.jcd.electronicslab.request.ProyectoRequest;
import co.com.jcd.electronicslab.response.ProyectoResponse;

class ProyectoServiceImplTest {
	
	@InjectMocks
	ProyectoServiceImpl proyectoService;
	
	@Mock
	IProyectoDao proyectoDao;
	
	Proyecto proyectoMock;
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
		setProyecto();
	}
	
	private void setProyecto() {
		proyectoMock = new Proyecto();
		proyectoMock.setId(1L);
		proyectoMock.setNombre("proyectoPrueba");
		proyectoMock.setTipo("Descripcion proyecto");
		try {
			proyectoMock.setFechaInicio(new SimpleDateFormat("dd/MM/yyyy").parse("2023/05/11"));
			proyectoMock.setFechaFin(new SimpleDateFormat("dd/MM/yyyy").parse("2023/05/12"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Test
	void crearProyectoCorrectoTest() {
		when(proyectoDao.save(any(Proyecto.class))).thenReturn(proyectoMock);
		ProyectoRequest request = new ProyectoRequest();
		ResponseEntity<ProyectoResponse> response  = proyectoService.crearProyecto(request);
		assertEquals(1L, response.getBody().getCodigoProyecto());
	}

}
