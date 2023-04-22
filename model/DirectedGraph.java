package model;

public class DirectedGraph extends Graph{
    private static DirectedGraph instance;
	public static DirectedGraph getInstance() {
		if (instance == null) {
			synchronized (Graph.class) {
				if (instance == null) {
					instance = new DirectedGraph();
				}
			}
		}
		return instance;
	}
}
