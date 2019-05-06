package blokus.Vue;
import static blokus.Framework.app;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class Jouer extends View {
	private Button quitBtn;
	private Pane vue;
	@Override
	public void onLaunch() {	
		vue = getPane();
		VBox boiteTexte = new VBox();
        boiteTexte.setAlignment(Pos.CENTER);
                     
        Label titre = new Label("Gaufre");
        titre.setMaxHeight(Double.MAX_VALUE);
        titre.setAlignment(Pos.TOP_CENTER);
        boiteTexte.getChildren().add(titre);
        VBox.setVgrow(titre, Priority.ALWAYS);

        quitBtn = new Button("Quit");
        quitBtn.setOnAction((event)->{
			app.exit();
		}); 
        boiteTexte.getChildren().add(quitBtn);
        Label copyright = new Label("Copyright G. Huard, 2018");
        copyright.setMaxHeight(Double.MAX_VALUE);
        copyright.setAlignment(Pos.BOTTOM_LEFT);
        boiteTexte.getChildren().add(copyright);
        VBox.setVgrow(copyright, Priority.ALWAYS);
        boiteTexte.setAlignment(Pos.BOTTOM_RIGHT);
        getChildren().add(boiteTexte);
        
        /*HBox boiteScene = new HBox();
        boiteScene.getChildren().add(vue);
		boiteScene.getChildren().add(boiteTexte);
		getChildren().add(boiteScene);*/
	}


}
