package model;

import java.awt.Color;
import java.awt.GradientPaint;

public class Edge {
	private Vertex source;
	private Vertex destination;
	private int weight;
	private GradientPaint gradient;

	public Edge(Vertex source, Vertex destination, int weight) {
		this.source = source;
		this.destination = destination;
		this.weight = weight;
	}

	public Vertex getSource() {
		return source;
	}

	public Vertex getDestination() {
		return destination;
	}

	public void setSource(Vertex source) {
		this.source = source;
	}

	public void setDestination(Vertex destination) {
		this.destination = destination;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getWeight() {
		return weight;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((source == null) ? 0 : source.hashCode());
		result = prime * result + ((destination == null) ? 0 : destination.hashCode());
		result = prime * result + weight;
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
		Edge other = (Edge) obj;
		if (source == null) {
			if (other.source != null)
				return false;
		} else if (!source.equals(other.source))
			return false;
		if (destination == null) {
			if (other.destination != null)
				return false;
		} else if (!destination.equals(other.destination))
			return false;
		if (weight != other.weight)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "source=" + source.getName() + ", destination=" + destination.getName() + ", weight=" + weight;
	}


	public GradientPaint getGradient() {
		return gradient;
	}

	public void setGradient(GradientPaint g) {
		this.gradient = g;
	}
	public GradientPaint getDefaultColor(){
		return new GradientPaint(
            source.getLocation().x, source.getLocation().y, new Color(127, 126, 127),
            destination.getLocation().x, destination.getLocation().y, new Color(127, 126, 127)
        );
	}
	// nhận màu của đường đi
	public GradientPaint getPathColor(){
		return new GradientPaint(
            source.getLocation().x, source.getLocation().y, new Color(254, 185, 0),
            destination.getLocation().x, destination.getLocation().y, new Color(251, 115, 0)
        );
	}

    public void setDefaultColor() {
		setGradient(getDefaultColor());
    }


}
