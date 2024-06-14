package com.functions.calculator.dto.steps;

import com.functions.calculator.dto.Step;

public class MidpointMethodSteps implements Step {
	private double x0;
	private double y0;
	private double x1;
	private double y1;
	private double x_2;
	private double y_2;
	private double result1;
	private double result2;

	public MidpointMethodSteps(double x0, double y0, double x1, double y1, double x_2, double y_2, double result1,
			double result2) {
		this.x0 = x0;
		this.y0 = y0;
		this.x1 = x1;
		this.y1 = y1;
		this.x_2 = x_2;
		this.y_2 = y_2;
		this.result1 = result1;
		this.result2 = result2;
	}

	public double getX0() {
		return x0;
	}

	public double getY0() {
		return y0;
	}

	public double getX1() {
		return x1;
	}

	public double getY1() {
		return y1;
	}

	public double getX_2() {
		return x_2;
	}

	public double getY_2() {
		return y_2;
	}

	public double getResult1() {
		return result1;
	}

	public double getResult2() {
		return result2;
	}

}
