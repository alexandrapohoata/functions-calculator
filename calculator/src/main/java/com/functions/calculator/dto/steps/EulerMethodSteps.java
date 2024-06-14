package com.functions.calculator.dto.steps;

import com.functions.calculator.dto.Step;

public class EulerMethodSteps implements Step {
	private double x;
	private double y;
	private double result;

	public EulerMethodSteps(double x, double y, double result) {
		this.x = x;
		this.y = y;
		this.result = result;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getResult() {
		return result;
	}

}
