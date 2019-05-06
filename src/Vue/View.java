package blokus.Vue;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public abstract class View {
	private final Pane pane;
	private final Canvas canvas;
	
	public View() {
		canvas = new Canvas();
		pane = new StackPane(canvas);
	}
	
	public Pane getPane() {
		return pane;
	}

	
	public ObservableList<Node> getChildren(){
		return pane.getChildren();
	}
	
	public abstract void onLaunch();
	
	public void onEnter() {
		
	}
	
	public void onLeave() {
		
	}
	
	public void onFinish() {
		
	}
}

