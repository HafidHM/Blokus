package Vue;
/*
 * Morpion pédagogique

 * Copyright (C) 2016 Guillaume Huard

 * Ce programme est libre, vous pouvez le redistribuer et/ou le
 * modifier selon les termes de la Licence Publique Générale GNU publiée par la
 * Free Software Foundation (version 2 ou bien toute autre version ultérieure
 * choisie par vous).

 * Ce programme est distribué car potentiellement utile, mais SANS
 * AUCUNE GARANTIE, ni explicite ni implicite, y compris les garanties de
 * commercialisation ou d'adaptation dans un but spécifique. Reportez-vous à la
 * Licence Publique Générale GNU pour plus de détails.

 * Vous devez avoir reçu une copie de la Licence Publique Générale
 * GNU en même temps que ce programme ; si ce n'est pas le cas, écrivez à la Free
 * Software Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307,
 * États-Unis.

 * Contact: Guillaume.Huard@imag.fr
 *          Laboratoire LIG
 *          700 avenue centrale
 *          Domaine universitaire
 *          38401 Saint Martin d'Hères
 */

import Modele.Jeu;
import Patterns.Observateur;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class FenetreGraphique implements Observateur {
	Jeu jeu;
	Scene scene;
	Canvas canvas;
        BoutonAnnuler annuler;
        BoutonRefaire refaire;
        ToggleButton IA;
	double largeurCase, hauteurCase;
        ChoiceBox niveau;
        String niv = "Easy";
	public FenetreGraphique(Jeu j, Stage primaryStage) {
		jeu = j;
		primaryStage.setTitle("Gaufre");

		canvas = new Canvas();
		Pane vue = new Pane(canvas);
		vue.setPrefSize(650, 400);

                VBox boiteTexte = new VBox();
                boiteTexte.setAlignment(Pos.CENTER);
               

        
                Label titre = new Label("Gaufre");
                titre.setMaxHeight(Double.MAX_VALUE);
                titre.setAlignment(Pos.TOP_CENTER);
                boiteTexte.getChildren().add(titre);
                VBox.setVgrow(titre, Priority.ALWAYS);
                
                IA = new ToggleButton("IA");
		IA.setFocusTraversable(false);
                boiteTexte.getChildren().add(IA);
                
                niveau = new ChoiceBox(FXCollections.observableArrayList(
    "Easy", "Medium", "Hard"));
                niveau.getSelectionModel().selectFirst();
                
                boiteTexte.getChildren().add(niveau);
                
                refaire = new BoutonRefaire(jeu);
        		refaire.setFocusTraversable(false);
                annuler = new BoutonAnnuler(jeu);
        		annuler.setFocusTraversable(false);
                        
        		HBox  AnnulerRefaire = new HBox(refaire,annuler);
                AnnulerRefaire.setSpacing(4.0);
        		AnnulerRefaire.setAlignment(Pos.CENTER);
        		boiteTexte.getChildren().add(AnnulerRefaire);
                
                Label copyright = new Label("Copyright G. Huard, 2018");
                copyright.setMaxHeight(Double.MAX_VALUE);
                copyright.setAlignment(Pos.BOTTOM_LEFT);
                boiteTexte.getChildren().add(copyright);
                VBox.setVgrow(copyright, Priority.ALWAYS);
        
                boiteTexte.setSpacing(5.0);
                HBox boiteScene = new HBox();
		boiteScene.getChildren().add(vue);
		boiteScene.getChildren().add(boiteTexte);
		HBox.setHgrow(vue, Priority.ALWAYS);
                
                
		scene = new Scene(boiteScene);
		primaryStage.setScene(scene);
		primaryStage.show();

		canvas.widthProperty().bind(vue.widthProperty());
		canvas.heightProperty().bind(vue.heightProperty());
                primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    @Override
                    public void handle(WindowEvent we) {
                        System.out.println("Fin du jeu");
                    }
                });
                
                niveau.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue observable, String oldValue, String newValue) {
                        niv = newValue;
                    }
                });
		jeu.ajouteObservateur(this);
		miseAJour();
	}

	public void ecouteurDeRedimensionnement(ChangeListener<Number> l) {
		canvas.widthProperty().addListener(l);
		canvas.heightProperty().addListener(l);
	}

	public void ecouteurDeSouris(EventHandler<MouseEvent> h) {
		canvas.setOnMouseClicked(h);
	}

        public void ecouteurIA(EventHandler<ActionEvent> h){
                IA.setOnAction(h);
        }
        
        public void changeBoutonIA(boolean value) {
		IA.setSelected(value);
	}
	
	    public void ecouteurRefaire(EventHandler<ActionEvent> e) {
		refaire.setOnAction(e);
	}
        
	public void ecouteurAnnuler(EventHandler<ActionEvent> e) {
		annuler.setOnAction(e);
		//En cours
	}
        

        public String ecouteurNiveau(){
                niveau.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue observable, String oldValue, String newValue) {
                        niv = newValue;
                    }
                });
                return niv;
        }
	@Override
	public void miseAJour() {
            double lignes = jeu.hauteur();
            double colonnes = jeu.largeur();
            largeurCase = largeur() / colonnes;
            hauteurCase = hauteur() / lignes;
            GraphicsContext g = canvas.getGraphicsContext2D();
            g.clearRect(0, 0, largeur(), hauteur());
            // Grille
            for (int i=0; i<lignes;i++) {
                for (int j=0; j<colonnes;j++) {
                    g.setFill(Color.DARKORANGE);
                    g.fillRect(j*largeurCase, i*hauteurCase, largeurCase, hauteurCase);

                }
            }
            for (int i=1; i<lignes;i++) {
                g.strokeLine(0, i*hauteurCase, largeur(), i*hauteurCase);
            }
            for (int i=1; i<colonnes+1;i++) {
                g.strokeLine(i*largeurCase, 0, i*largeurCase, hauteur());
            }
            g.strokeOval(0*largeurCase, 0*hauteurCase, largeurCase, hauteurCase);
            // Coups
            for (int i=0; i<lignes; i++)
                for (int j=0; j<colonnes; j++)
                    switch (jeu.valeur(i, j)) {
                        case -1:
                            break;
                        case 1:
                            g.setFill(Color.WHITE);
                            g.fillRect(j*largeurCase, i*hauteurCase, largeurCase, hauteurCase);
                            break;
                    }
	}
            


	double largeur() {
		return canvas.getWidth();
	}

	double hauteur() {
		return canvas.getHeight();
	}

	public double largeurCase() {
		return largeurCase;
	}

	public double hauteurCase() {
		return hauteurCase;
	}
}