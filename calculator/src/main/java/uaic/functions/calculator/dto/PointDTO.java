package uaic.functions.calculator.dto;

public class PointDTO {
	private double x, y;

	public PointDTO(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return "x = " + x + "; y = " + y + "\n";
	}
}
