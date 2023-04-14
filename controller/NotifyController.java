package controller;

import model.Edge;
import view.EdgeView;
import view.NotifyView;

public class NotifyController {
	private NotifyView view;

	public NotifyController() {
		this.view = NotifyView.getInstance();
	}
	public void setNotify(String[] s) {
		view.updateNotify(s);
	}
}
