import Controleur.ControleurMediateur;
import Modele.Jeu;
import Vue.FenetreGraphique;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class InterfaceGraphique extends Application {
	Jeu jeu;

	@Override
	public void start(Stage primaryStage) {

		jeu = new Jeu(23);
		FenetreGraphique f = new FenetreGraphique(jeu, primaryStage);
		ControleurMediateur c = new ControleurMediateur(jeu, f);
                f.ecouteurIA(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        ToggleButton b = (ToggleButton) event.getSource();
				c.basculeIA(b.isSelected());
                    }
                });
                //System.out.println("vous avez choisi" + f.ecouterNiveau());
		f.ecouteurDeSouris(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				c.clicSouris(e.getX(), e.getY());
			}
		});
		f.ecouteurRefaire(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				c.refaire();
			}
		});
        f.ecouteurAnnuler(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) { 
                //En cours
			}
		});
		f.ecouteurDeRedimensionnement(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				c.redimensionnement();
			}
		});
		new AnimationTimer() {
			@Override
			public void handle(long now) {
				c.tictac();
			}
		}.start();
	}
}