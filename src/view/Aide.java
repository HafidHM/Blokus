package blokus.test.view;
import static blokus.Framework.*;
import blokus.Vue.View;
import javafx.scene.control.Button;

public class Aide extends View{
	private Button retourBtn;
	
	@Override
	public void onLaunch() {
		retourBtn = new Button("Retour");
		retourBtn.setOnAction((event)->{
			app.gotoView("Menu");
		});
		getChildren().add(retourBtn);
	}
	
}
