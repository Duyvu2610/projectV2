package controller;

import model.Vertex;
import view.NotifyView;

public class NotifyController {
	private NotifyView view;

	public NotifyController() {
		this.view = NotifyView.getInstance();
	}

	public void setNotify(String[][] s , Vertex st, Vertex ds) {
		view.updateNotify(s, st, ds);
	}
	public void removeNotify(){
		view.removeNotify();
	}
}
