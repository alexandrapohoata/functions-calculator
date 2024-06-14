package com.functions.calculator.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.functions.calculator.dto.PointDTO;
import com.functions.calculator.dto.Step;
import com.functions.calculator.repository.CustomRepository;
import com.functions.calculator.services.Method;

import net.objecthunter.exp4j.tokenizer.UnknownFunctionOrVariableException;

@RestController
@RequestMapping(path = "calculator")
public class FunctionController {

	private CustomRepository repository;

//	public static void main(String[] args) {
//		EulerMethod eulerMethod = new EulerMethod("x+2y", 1, 5, 0.1, -1);
//		HeunMethod heunMethod = new HeunMethod("x+2y", 1, 5, 0.1, -1);
//		MidpointMethod midpointMethod = new MidpointMethod("x+2y", 1, 5, 0.1, -1);
//		RungeKuttaMethod rungeKuttaMethod = new RungeKuttaMethod("x+2y", 1, 5, 0.1, -1);
//		System.out.println("Euler" + eulerMethod.compute());
//		System.out.println("Heun" + heunMethod.compute());
//		System.out.println("Midpoint" + midpointMethod.compute());
//		System.out.println("Runge-Kutta" + rungeKuttaMethod.compute());
//	}

	@Autowired
	public FunctionController(CustomRepository repository) {
		this.repository = repository;
	}

	@GetMapping(path = "/points")
	public ResponseEntity<?> getPoints(@RequestParam(name = "func") Optional<String> func,
			@RequestParam(name = "methodid") Optional<Integer> methodid, @RequestParam(name = "xi") Optional<Double> xi,
			@RequestParam(name = "xf") Optional<Double> xf, @RequestParam(name = "h") Optional<Double> h,
			@RequestParam(name = "iterations", required = false) Optional<Integer> n) {

		if (func.isPresent() && xf.isPresent() && xi.isPresent() && h.isPresent() && methodid.isPresent()) {
			System.out.println("Getting points from repository...");

			List<PointDTO> functionPointsDTO = null;
			try {
				functionPointsDTO = repository.getFunctionPlotPoints(func, methodid, xi, xf, h, n);
			} catch (UnknownFunctionOrVariableException e) {
				System.out.println("Can't validate/parse the function...");
			} catch (IllegalArgumentException e) {
				System.out.println("Something wrong with the function... Missing paranthesis or unknown function.");
			}

			if (functionPointsDTO != null)
				return new ResponseEntity<List<PointDTO>>(functionPointsDTO, HttpStatus.OK);
		}
		return new ResponseEntity<String>("Function has errors.", HttpStatus.NOT_FOUND);
	}

	@GetMapping(path = "/steps")
	public ResponseEntity<?> getSteps(@RequestParam(name = "methodid") Optional<Integer> methodid) {

		Method lastMethod = repository.getLastMethod();
		if (methodid.isPresent() && lastMethod != null) {
			System.out.println("Getting steps from repository...");

			List<Step> steps = lastMethod.getSteps();

			if (steps != null)
				return new ResponseEntity<List<Step>>(steps, HttpStatus.OK);
		}
		return new ResponseEntity<String>("`methodid` is not specified as query param.", HttpStatus.NOT_FOUND);
	}
}
