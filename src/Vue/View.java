package Vue;


import Modele.Jeu;
import Modele.Plateau;
import Modele.PlateauPiece;
import Patterns.Observateur;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

public abstract class View implements Observateur {
	
	double largeurCase, hauteurCase;
	double largeurCasePiece, hauteurCasePiece;
	private final BorderPane pane;
	//private final StackPane pane;
	Jeu jeu;
	Plateau plateau;
	PlateauPiece plateauPiece;
	public View(Jeu j) {
		jeu = j;
		plateau = jeu.plateau;
		plateauPiece = jeu.plateauPiece;
		pane = new BorderPane();
		//pane = new StackPane();
	}
	
	public BorderPane getPane() {
		return pane;
	}

	public double getWidth() {
		return pane.getWidth();
	}
	
	public double getHeight() {
		return pane.getHeight();
	}
	
	/*public StackPane getPane() {
			return pane;
	}*/

	
	public ObservableList<Node> getChildren(){
		return pane.getChildren();
	}
	
	//public abstract void redimension();
	
	public abstract void onLaunch();
	

	
	public void onEnter() {
		
	}
	
	public void onLeave() {
		
	}
	
	public void onFinish() {
		
	}


}

