package com.functions.calculator.services.methods;

import java.util.ArrayList;
import java.util.List;

import com.functions.calculator.dto.PointDTO;
import com.functions.calculator.dto.steps.HeunMethodSteps;
import com.functions.calculator.services.BiDemnsional;
import com.functions.calculator.services.Method;

public class HeunMethod extends Method implements BiDemnsional {

	public HeunMethod(String functionString, double xi, double xf, double h, int n) {
		super(functionString, xi, xf, h, n);
	}

	@Override
	public List<PointDTO> compute() {
		double h = getH();
		double x0 = getXi();
		double y0 = getXf();
		double n = getN() == -1 ? (y0 - x0) / h : getN();

		double result, result1, y1_0, x1, y1;
		List<PointDTO> points = new ArrayList<PointDTO>();

		int i = 0;
		while (i != n) {
			result = dy(x0, y0);

			y1_0 = y0 + result * h;
			x1 = x0 + h;
			result1 = dy(x1, y1_0);
			y1 = y0 + (result + result1) / 2.0 * h;

			points.add(new PointDTO(x0, y0));

			addStep(new HeunMethodSteps(x0, y0, result, result1, x1, y1, y1_0));

			x0 = x1;
			y0 = y1;

			i++;
		}

		return points;
	}
}
