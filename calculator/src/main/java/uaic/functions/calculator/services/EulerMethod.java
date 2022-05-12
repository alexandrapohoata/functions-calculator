package uaic.functions.calculator.services;

import java.util.ArrayList;
import java.util.List;

import uaic.functions.calculator.dto.PointDTO;

public class EulerMethod {
	public List<PointDTO> getPoints() {
		double h = 0.02;
		double xi = 1;
		double xf = 5.5;
		double n = (xf - xi) / h;
		double x = 1;
		double y = 1;

		List<PointDTO> points = new ArrayList<PointDTO>();

		for (int i = 1; i < n + 1; i++) {
			y = y + h * dy(x, y);
			x = x + h;
			System.out.print(x + ", ");
			points.add(new PointDTO(x, y));
		}
		System.out.println();
		for (int i = 1; i < n + 1; i++) {
			y = y + h * dy(x, y);
			x = x + h;
			System.out.print(y + ", ");
		}

		return points;
	}

	public double dy(double x, double y) {
		return 2 * x;
	}

	public double y_final(double x) {
		return x * x;
	}
}
