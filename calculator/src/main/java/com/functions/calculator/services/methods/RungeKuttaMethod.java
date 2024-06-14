package com.functions.calculator.services.methods;

import java.util.ArrayList;
import java.util.List;

import com.functions.calculator.dto.PointDTO;
import com.functions.calculator.dto.steps.RungeKuttaMethodSteps;
import com.functions.calculator.services.BiDemnsional;
import com.functions.calculator.services.Method;

public class RungeKuttaMethod extends Method implements BiDemnsional {

	public RungeKuttaMethod(String functionString, double xi, double xf, double h, int n) {
		super(functionString, xi, xf, h, n);
	}

	@Override
	public List<PointDTO> compute() {
		double h = getH();
		double x = getXi(), y = getXf();
		double n = getN() == -1 ? (y - x) / h : getN();

		List<PointDTO> points = new ArrayList<PointDTO>();

		int i = 0;
		double k1, k2, k3, k4, k;
		while (i != n) {
			points.add(new PointDTO(x, y));

			k1 = dy(x, y);
			k2 = dy(x + h / 2.0, y + k1 / 2.0 * h);
			k3 = dy(x + h / 2.0, y + k2 / 2.0 * h);
			k4 = dy(x + h, y + k3 * h);
			k = 1 / 6.0 * (k1 + 2 * k2 + 2 * k3 + k4) * h;

			x += h;
			y += k;

			addStep(new RungeKuttaMethodSteps(x, y, k, k1, k2, k3, k4));
			i++;
		}

		return points;
	}

}
