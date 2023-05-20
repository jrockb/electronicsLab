package co.com.jcd.electronicslab.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.jcd.electronicslab.request.HerramientaRequest;
import co.com.jcd.electronicslab.response.HerramientaResponse;
import co.com.jcd.electronicslab.response.ResponseRest;
import co.com.jcd.electronicslab.service.IHerramientaService;
import co.com.jcd.electronicslab.utils.ResponseUtils;

@RestController
@RequestMapping("/electronicslab")
public class HerramientaController {
	
	@Autowired
	private IHerramientaService herramientaService;
	
	// validar fechas con initBinder en vez de anotaciones
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		format.setLenient(false); // analizar los formatos de fecha de manera estricta
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format, false));
	}
	
	@PostMapping("/crearHerramienta")
	public ResponseEntity<HerramientaResponse> crearHerramienta(@Valid @RequestBody HerramientaRequest request, 
		BindingResult result){
		if(result.hasErrors()) { 
			ResponseRest response = new HerramientaResponse();			
			response = ResponseUtils.generarRespuestaValidador(result, response);
			return new ResponseEntity<>((HerramientaResponse)response, HttpStatus.BAD_REQUEST);
		}
		return herramientaService.crearHerramienta(request);
	}

}
