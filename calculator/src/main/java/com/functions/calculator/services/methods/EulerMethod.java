package com.functions.calculator.services.methods;

import java.util.ArrayList;
import java.util.List;

import com.functions.calculator.dto.PointDTO;
import com.functions.calculator.dto.steps.EulerMethodSteps;
import com.functions.calculator.services.BiDemnsional;
import com.functions.calculator.services.Method;

public class EulerMethod extends Method implements BiDemnsional {

	public EulerMethod(String functionString, double xi, double xf, double h, int n) {
		super(functionString, xi, xf, h, n);
	}

	@Override
	public List<PointDTO> compute() {
		double h = getH();
		double x = getXi();
		double y = getXf();
		double n = getN() == -1 ? (y - x) / h : getN();
		double result;

		List<PointDTO> points = new ArrayList<PointDTO>();

		int i = 0;
		while (i != n) {
			points.add(new PointDTO(x, y));

			result = dy(x, y);

			addStep(new EulerMethodSteps(x, y, result));

			y += result * h;
			x += h;

			i++;
		}

		return points;
	}
}
