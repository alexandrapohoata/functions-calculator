package com.functions.calculator.repository;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.functions.calculator.dto.PointDTO;
import com.functions.calculator.services.Method;
import com.functions.calculator.services.methods.EulerMethod;
import com.functions.calculator.services.methods.HeunMethod;
import com.functions.calculator.services.methods.MidpointMethod;
import com.functions.calculator.services.methods.RungeKuttaMethod;

import net.objecthunter.exp4j.tokenizer.UnknownFunctionOrVariableException;

@Repository
public class PointsRepository implements CustomRepository {
	private Method method = null;

	@Autowired
	public PointsRepository() {
	}

	@PostConstruct
	void initializeDummyPoints() {
		System.out.println("Initialize PointsRepository class...");
	}

	@Override
	public List<PointDTO> getFunctionPlotPoints(Optional<String> func, Optional<Integer> methodid, Optional<Double> xi,
			Optional<Double> xf, Optional<Double> h, Optional<Integer> n) throws UnknownFunctionOrVariableException {
		if (func.isPresent() && xi.isPresent() && xf.isPresent() && h.isPresent() && methodid.isPresent()) {
			String decodedFunction;
			try {
				decodedFunction = URLDecoder.decode(func.get(), StandardCharsets.UTF_8.name());
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				return null;
			}

			System.out.println(decodedFunction);
			Method.Type method = Method.Type.fromInteger(methodid.get());
			switch (method) {
			case EULER:
				this.method = new EulerMethod(decodedFunction, xi.get(), xf.get(), h.get(),
						n.isPresent() ? n.get() : -1);
				break;
			case HEUN:
				this.method = new HeunMethod(decodedFunction, xi.get(), xf.get(), h.get(),
						n.isPresent() ? n.get() : -1);
				break;
			case MIDPOINT:
				this.method = new MidpointMethod(decodedFunction, xi.get(), xf.get(), h.get(),
						n.isPresent() ? n.get() : -1);
				break;
			case RUNGEKUTTA:
				this.method = new RungeKuttaMethod(decodedFunction, xi.get(), xf.get(), h.get(),
						n.isPresent() ? n.get() : -1);
				break;
			default:
				this.method = null;
				return null;
			}

			return this.method.compute();
		}
		return null;
	}

	@Override
	public Method getLastMethod() {
		return this.method;
	}

}
