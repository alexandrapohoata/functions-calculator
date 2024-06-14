package com.functions.calculator.dto.steps;

import com.functions.calculator.dto.Step;

public class HeunMethodSteps implements Step {
	private double x0;
	private double y0;
	private double result;
	private double result1;
	private double x1;
	private double y1;
	private double y1_0;

	public HeunMethodSteps(double x0, double y0, double result, double result1, double x1, double y1, double y1_0) {
		this.x0 = x0;
		this.y0 = y0;
		this.result = result;
		this.result1 = result1;
		this.x1 = x1;
		this.y1 = y1;
		this.y1_0 = y1_0;
	}

	public double getX0() {
		return x0;
	}

	public double getY0() {
		return y0;
	}

	public double getResult() {
		return result;
	}

	public double getResult1() {
		return result1;
	}

	public double getX1() {
		return x1;
	}

	public double getY1() {
		return y1;
	}

	public double getY1_0() {
		return y1_0;
	}
}
