package model;

public class UndirectedGraph extends Graph{
    private static UndirectedGraph instance;
	public static UndirectedGraph getInstance() {
		if (instance == null) {
			synchronized (Graph.class) {
				if (instance == null) {
					instance = new UndirectedGraph();
				}
			}
		}
		return instance;
	}
}
