package uaic.functions.calculator.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uaic.functions.calculator.dto.PointDTO;
import uaic.functions.calculator.repository.CustomRepository;

@RestController
@RequestMapping(path = "calculator")
public class FunctionController {
	
	private CustomRepository repository;

	@Autowired
	public FunctionController(CustomRepository repository) {
		this.repository = repository;
	}
	
	@GetMapping(path = "/points")
	public ResponseEntity<List<PointDTO>> getPoints() {
		System.out.println("Getting points from repository...");
		return new ResponseEntity<List<PointDTO>>(repository.getFunctionPlotPoints(), HttpStatus.OK);
	}
}
