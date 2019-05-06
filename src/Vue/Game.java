package blokus.Vue;

import blokus.Modele.Jeu;
import blokus.Vue.App;
import blokus.test.view.Aide;
import blokus.test.view.Jouer;
import blokus.test.view.Menu;
import blokus.test.view.Parametre;
import javafx.application.Application;
import javafx.stage.Stage;

public class Game extends Application{
	private App app;
	Jeu jeu;
	
	public void onLaunch() {
		app.setTitle("Test Game");
		app.setWidth(800);
		app.setHeight(600);
		
		app.regView("Menu", new Menu());
		app.regView("Aide", new Aide());
		app.regView("Parametre", new Parametre());
		app.regView("Jouer", new Jouer());
		app.gotoView("Menu");
	};
	
	public void onFinish() {
		
	}
	
	public boolean onExit() {
		return true;
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {

		app = new App(primaryStage);
		app.onLaunch = this::onLaunch;
		app.onFinish = this::onFinish;
		app.onExit= this::onExit;
		
		app.launch();
	}
	
	public void stop() throws Exception {
		app.finish();
	}
}
