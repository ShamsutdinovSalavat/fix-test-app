package ru.fix.algorithm.service.hsalg;

import java.util.Objects;

public class Position {

	private final Integer x;
	private final Integer y;

	public Position(Integer x, Integer y) {
		this.x = x;
		this.y = y;
	}

	public static Position from(String pos) {
		String strX = pos.substring(0, 1);
		String strY = pos.substring(1);
		Integer x = strX.toLowerCase().codePointAt(0) % 96;
		Integer y = Integer.valueOf(strY);

		return new Position(x, y);
	}

	public Integer getX() {
		return x;
	}

	public Integer getY() {
		return y;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Position)) return false;
		Position position = (Position) o;
		return x.equals(position.x) &&
				y.equals(position.y);
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}

	@Override
	public String toString() {
		return "Position{" +
				"x=" + x +
				", y=" + y +
				'}';
	}
}
