package co.com.jcd.electronicslab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.jcd.electronicslab.model.Herramienta;
import co.com.jcd.electronicslab.model.dao.IHerramientaDao;
import co.com.jcd.electronicslab.request.HerramientaRequest;
import co.com.jcd.electronicslab.response.HerramientaResponse;

@Service
public class HerramientaServiceImpl implements IHerramientaService {
	
	@Autowired
	private IHerramientaDao herramientaDao;
	
	@Override
	@Transactional
	public ResponseEntity<HerramientaResponse> crearHerramienta(HerramientaRequest request) {
		HerramientaResponse response = new HerramientaResponse();
		Herramienta herramienta  = new Herramienta(request.getNombre() ,request.getCantidad(), request.getMarca(), 
				request.getFechaAdquisicion(), request.getPrestada(), request.getTipo());
		try {
			Herramienta herramientaGuardar = herramientaDao.save(herramienta);
			if(herramientaGuardar.getId() != null) {
				response.setCodigo("00");
				response.setTipo("Ejecución exitosa");
				response.setRespuesta("OK");
				response.setId(herramientaGuardar.getId());
				response.setNombreHerramienta(herramientaGuardar.getNombre());
			} else {
				response.setCodigo("-1");
				response.setTipo("No se guardó la herramienta en la base de datos");
				response.setRespuesta("NA");
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			}
		} catch(Exception ex) {
			response.setCodigo("-2");
			response.setTipo("error: "+ex.getMessage());
			response.setRespuesta("NA");
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
