package com.functions.calculator.dto.steps;

import com.functions.calculator.dto.Step;

public class RungeKuttaMethodSteps implements Step {
	private double x;
	private double y;
	private double k;
	private double k1;
	private double k2;
	private double k3;
	private double k4;

	public RungeKuttaMethodSteps(double x, double y, double k, double k1, double k2, double k3, double k4) {
		this.x = x;
		this.y = y;
		this.k = k;
		this.k1 = k1;
		this.k2 = k2;
		this.k3 = k3;
		this.k4 = k4;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getK() {
		return k;
	}

	public double getK1() {
		return k1;
	}

	public double getK2() {
		return k2;
	}

	public double getK3() {
		return k3;
	}

	public double getK4() {
		return k4;
	}

}
