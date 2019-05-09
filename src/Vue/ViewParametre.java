package Vue;
import static blokus.Framework.*;

import Modele.Jeu;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class ViewParametre extends View{
	public ViewParametre(Jeu j) {
		super(j);
	}

	private Button retourBtn;
	private Button commencerBtn;
	
	@Override
	public void onLaunch() {
		retourBtn = new Button("Retour");
		retourBtn.setOnAction((event)->{
			app.gotoView("Menu");
		});
		commencerBtn = new Button("Commencer");
		commencerBtn.setOnAction((event)->{
			app.gotoView("Jouer");
		});
		
		VBox box = new VBox(retourBtn,commencerBtn);
		box.setAlignment(Pos.CENTER);//居中对齐
		box.setSpacing(20);
		getChildren().add(box);
	}

	@Override
	public void miseAJour() {
		// TODO Auto-generated method stub
		
	}
}
