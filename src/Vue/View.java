package Vue;


import Modele.Jeu;
import Modele.Plateau;
import Patterns.Observateur;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

public abstract class View implements Observateur {
	
	double largeurCase, hauteurCase;
	private final AnchorPane pane;
	//private final StackPane pane;
	Jeu jeu;
	Plateau plateau;
	public View(Jeu j) {
		jeu = j;
		plateau = jeu.plateau;
		pane = new AnchorPane();
		//pane = new StackPane();
	}
	
	public AnchorPane getPane() {
		return pane;
	}

	/*public StackPane getPane() {
			return pane;
	}*/

	
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

