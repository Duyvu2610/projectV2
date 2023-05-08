package controller;

import model.Vertex;
import view.NotifyView;

public class NotifyController {
	private NotifyView view;

	public NotifyController() {
		this.view = NotifyView.getInstance();
	}

	public void setPathNotify(Vertex[] s, Vertex st, Vertex ds, int pathCost, boolean negativeCycle) {
		view.updatePathNotify(s, st, ds, pathCost, negativeCycle);
	}

	public void removeNotify() {
		view.removeNotify();
	}

    public void setConnectedNotify(boolean connected) {
		view.updateConnectedNotify(connected);
    }
}
