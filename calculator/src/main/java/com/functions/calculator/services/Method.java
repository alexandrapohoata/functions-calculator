package com.functions.calculator.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.functions.calculator.dto.PointDTO;
import com.functions.calculator.dto.Step;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import net.objecthunter.exp4j.ValidationResult;
import net.objecthunter.exp4j.tokenizer.UnknownFunctionOrVariableException;

@Component
public abstract class Method {
	private List<Step> steps;
	private Expression function;
	private double h;
	private double xi;
	private double xf;
	private int n;

	@Autowired
	public Method(String functionString, double xi, double xf, double h, int n)
			throws UnknownFunctionOrVariableException {
		if (this instanceof BiDemnsional)
			this.function = new ExpressionBuilder(functionString).variables("x", "y").build();
		else
			this.function = new ExpressionBuilder(functionString).variables("x").build();

		ValidationResult result = this.function.validate(false);
		if (result.isValid())
			System.out.println("Function is valid");

		this.xi = xi;
		this.xf = xf;
		this.h = h;
		this.n = n;
		this.steps = new ArrayList<>();
	}

	protected double getH() {
		return h;
	}

	protected double getXi() {
		return xi;
	}

	protected double getXf() {
		return xf;
	}

	protected double getN() {
		return n;
	}

	protected void addStep(Step step) {
		this.steps.add(step);
	}

	protected double dy(double x) {
		double result = this.function.setVariable("x", x).evaluate();

		return result;
	}

	protected double dy(double x, double y) {
		double result = this.function.setVariable("x", x).setVariable("y", y).evaluate();

		return result;
	}

	public List<Step> getSteps() {
		return steps;
	}

	public abstract List<PointDTO> compute();

	public static enum Type {
		EULER, HEUN, MIDPOINT, RUNGEKUTTA;

		@Override
		public String toString() {
			return this.name();
		}

		public static Type fromInteger(int ordinal) {
			switch (ordinal) {
			case 1:
				return EULER;
			case 2:
				return HEUN;
			case 3:
				return MIDPOINT;
			case 4:
				return RUNGEKUTTA;
			}
			return null;
		}
	}
}
