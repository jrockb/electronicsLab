package co.com.jcd.electronicslab.service;

import org.springframework.http.ResponseEntity;

import co.com.jcd.electronicslab.request.HerramientaRequest;
import co.com.jcd.electronicslab.response.HerramientaResponse;

public interface IHerramientaService {
	
	public ResponseEntity<HerramientaResponse> crearHerramienta(HerramientaRequest request);

}
