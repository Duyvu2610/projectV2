package controller;

import model.Vertex;
import view.NotifyView;

public class NotifyController {
	private NotifyView view;

	public NotifyController() {
		this.view = NotifyView.getInstance();
	}

	public void setNotify(Vertex[] s, Vertex st, Vertex ds, int pathCost) {
		view.updateNotify(s, st, ds, pathCost);
	}

	public void removeNotify() {
		view.removeNotify();
	}
}
