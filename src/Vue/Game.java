package Vue;

import Modele.Jeu;
import Vue.App;
import javafx.application.Application;
import javafx.stage.Stage;

public class Game extends Application{
	private App app;
	Jeu jeu;
	
	public void onLaunch() {
		
		app.setTitle("Test Game");
		app.setWidth(800);
		app.setHeight(600);
		
		app.regView("Menu", new ViewMenu(jeu));
		app.regView("Aide", new ViewAide(jeu));
		app.regView("Parametre", new ViewParametre(jeu));
		app.regView("Jouer", new ViewJouer(jeu));
		app.gotoView("Menu");
	};
	
	public void onFinish() {
		
	}
	
	public boolean onExit() {
		return true;
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		jeu = new Jeu(20,20);
		app = new App(jeu,primaryStage);
		
		app.onLaunch = this::onLaunch;
		app.onFinish = this::onFinish;
		app.onExit= this::onExit;
		
		app.launch();
	}
	
	public void stop() throws Exception {
		app.finish();
	}
}
