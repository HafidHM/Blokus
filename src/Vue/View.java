package Vue;


import Modele.Jeu;
import Modele.Plateau;
import Patterns.Observateur;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

public abstract class View implements Observateur{
	double largeurCase, hauteurCase;
	//private final AnchorPane pane;
	private final StackPane pane;
	Jeu jeu;
	Plateau plateau;
	public View(Jeu j, Plateau p) {
		jeu = j;
		plateau = p;
		//pane = new AnchorPane();
		pane = new StackPane();
	}
	
	//public AnchorPane getPane() {
	//	return pane;
	//}

	public StackPane getPane() {
			return pane;
	}
	public void setTopAnchor(Node child, double value) {
		AnchorPane.setTopAnchor(child, value);
	}
	
	public void setLeftAnchor(Node child, double value) {
		AnchorPane.setLeftAnchor(child, value);
	}
	public void setPrefSize(double w, double h) {
		pane.setPrefSize(w, h);
	}

	
	public ObservableList<Node> getChildren(){
		return pane.getChildren();
	}
	
	public void setAlignment(Node child, Pos value) {
		StackPane.setAlignment(child, value);
	}
	public abstract void onLaunch();
	

	
	public void onEnter() {
		
	}
	
	public void onLeave() {
		
	}
	
	public void onFinish() {
		
	}
}

