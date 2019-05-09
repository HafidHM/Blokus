package Vue;
import static blokus.Framework.*;

import Modele.Jeu;
import Modele.Plateau;
import javafx.scene.control.Button;

public class ViewAide extends View{
	public ViewAide(Jeu j, Plateau p) {
		super(j, p);
	}

	private Button retourBtn;
	
	@Override
	public void onLaunch() {
		retourBtn = new Button("Retour");
		retourBtn.setOnAction((event)->{
			app.gotoView("Menu");
		});
		getChildren().add(retourBtn);
	}

	@Override
	public void miseAJour() {
		
	}
	
}
