package com.functions.calculator.repository;

import java.util.List;
import java.util.Optional;

import com.functions.calculator.dto.PointDTO;
import com.functions.calculator.services.Method;

public interface CustomRepository {
	List<PointDTO> getFunctionPlotPoints(Optional<String> func, Optional<Integer> methodid, Optional<Double> xi,
			Optional<Double> xf, Optional<Double> h, Optional<Integer> n);

	Method getLastMethod();
}
