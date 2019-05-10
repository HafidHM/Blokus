package Vue;
import static blokus.Framework.app;

import Modele.Jeu;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

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
		
		HBox box = new HBox(retourBtn,commencerBtn);
		box.setAlignment(Pos.BOTTOM_CENTER);//居中对齐
		box.setSpacing(100);
		getPane().setCenter(box);
		getPane().setPadding(new Insets(50));
	}

	@Override
	public void miseAJour() {
		// TODO Auto-generated method stub
		
	}

	/*@Override
	public void redimension() {
		// TODO Auto-generated method stub
		
	}*/
}
