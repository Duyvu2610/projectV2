package model;

import java.awt.Color;
import java.awt.Point;

public class Vertex {
	private String name;
	private Point location;
	private Color color;
	public static final int R = 20;

	public Vertex(String name, Point location) {
		this.name = name;
		this.location = location;
		this.color = Color.GREEN;
	}

	public String getName() {
		return name;
	}

	public Point getLocation() {
		return location;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setLocation(Point location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "Vertex [name=" + name + ", location=" + location + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vertex other = (Vertex) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		return true;
	}

	public boolean isClickAt(Point point) {
		return ((point.x > location.x && point.x < location.x + R * 2)
				&& (point.y > location.y && point.y < location.y + R * 2));
	}

	public void move(int x, int y) {
		setLocation(new Point((int) (getLocation().getX() + x), (int) (getLocation().getY() + y)));
	}

	public void setColor(Color red) {
		this.color = red;
	}

	public Color getColor() {
		return this.color;
	}
	public boolean getVertex(String name){
		return this.name.equals(name);
	}

    public void setDefaultColor() {
		setColor(Color.GREEN);
    }

}
