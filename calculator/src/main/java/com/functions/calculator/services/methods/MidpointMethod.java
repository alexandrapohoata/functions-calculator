package com.functions.calculator.services.methods;

import java.util.ArrayList;
import java.util.List;

import com.functions.calculator.dto.PointDTO;
import com.functions.calculator.dto.steps.MidpointMethodSteps;
import com.functions.calculator.services.BiDemnsional;
import com.functions.calculator.services.Method;

public class MidpointMethod extends Method implements BiDemnsional {

	public MidpointMethod(String functionString, double xi, double xf, double h, int n) {
		super(functionString, xi, xf, h, n);
	}

	@Override
	public List<PointDTO> compute() {
		double h = getH();
		double x0 = getXi(), y0 = getXf();
		double n = getN() == -1 ? (y0 - x0) / h : getN();
		double y_2, x1, x_2, y1, result1, result2;

		List<PointDTO> points = new ArrayList<PointDTO>();

		int i = 0;
		while (i != n) {
			result1 = dy(x0, y0);
			y_2 = y0 + result1 * (h / 2);
			x1 = x0 + h;
			x_2 = (x0 + x1) / 2;
			result2 = dy(x_2, y_2);
			y1 = y0 + result2 * h;

			points.add(new PointDTO(x0, y0));

			addStep(new MidpointMethodSteps(x0, y0, x1, y1, x_2, y_2, result1, result2));

			x0 = x1;
			y0 = y1;

			i++;
		}

		return points;
	}

}
