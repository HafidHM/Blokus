package Vue;
import static blokus.Framework.app;

import Controleur.*;
import Modele.Jeu;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ViewParametre extends View{
	public ViewParametre(Jeu j) {
		super(j);
	}
	
	private Button retourBtn;
	private Button commencerBtn;
	boolean jeuAutomatique;
	public ChoiceBox<String>[] Joueur;
	public Joueur[] joueurs;
	@SuppressWarnings("unchecked")
	@Override
	public void onLaunch() {
		//ViewJouer vj = new ViewJouer(jeu);
		//ControleurMediateur c = new ControleurMediateur(jeu,vj);
		joueurs = new Joueur[4];
		joueurs[0] = new JoueurHumain(0, jeu);
		jeuAutomatique = false;
		if (jeuAutomatique) {
			System.out.println("auto");
			joueurs[1] = new JoueurIA(1, jeu);
			joueurs[2] = new JoueurHumain(2, jeu);
			joueurs[3] = new JoueurIA(3, jeu);
		} else {
			joueurs[1] = new JoueurHumain(1, jeu);
			joueurs[2] = new JoueurHumain(2, jeu);
			joueurs[3] = new JoueurHumain(3, jeu);
		}
		retourBtn = new Button("Retour");
		retourBtn.setOnAction((event)->{
			app.gotoView("Menu");
		});
		commencerBtn = new Button("Commencer");
		commencerBtn.setOnAction((event)->{
			app.gotoView("Jouer");
		});
		Joueur = new ChoiceBox[4];
		for(int i=0;i<4;i++) {
			Joueur[i] = new ChoiceBox<String>(FXCollections.observableArrayList("Humain", "Robot simple", "Robot Intelligent","Robot Excellent"));
		    Joueur[i].getSelectionModel().selectFirst();	    
		}
		
		Label j0 = new Label("Joueur 0 (vert) :   ");
		HBox J0 = new HBox(j0,Joueur[0]);
		Label j1 = new Label("Joueur 1 (bleu) :   ");
		HBox J1 = new HBox(j1,Joueur[1]);
		Label j2 = new Label("Joueur 2 (jaune) : ");
		HBox J2 = new HBox(j2,Joueur[2]);
		Label j3 = new Label("Joueur 3 (rouge) : ");
		HBox J3 = new HBox(j3,Joueur[3]);
		
		VBox joueurbox = new VBox(J0,J1,J2,J3);
		joueurbox.setAlignment(Pos.CENTER);
		joueurbox.setSpacing(20);
		HBox box = new HBox(retourBtn,commencerBtn);
		box.setAlignment(Pos.BOTTOM_CENTER);//居中对齐
		box.setSpacing(100);
		getPane().setCenter(box);
		getPane().setTop(joueurbox);
		getPane().setPadding(new Insets(50));
		
		Joueur[0].getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(newValue.equalsIgnoreCase("Humain")) {
                	joueurs[0] = new JoueurHumain(0, jeu); 
                }else {
                	joueurs[0] = new JoueurIA(0, jeu); 
                }
            }
        });
		Joueur[1].getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            	 if(newValue.equalsIgnoreCase("Humain")) {
                 	joueurs[1] = new JoueurHumain(1, jeu); 
                 }else {
                 	joueurs[1] = new JoueurIA(1, jeu); 
                 }
            }
        });
		Joueur[2].getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            	 if(newValue.equalsIgnoreCase("Humain")) {
                 	joueurs[2] = new JoueurHumain(2, jeu);
                 }else {
                 	joueurs[2] = new JoueurIA(2, jeu); 
                 }
            }
        });
		Joueur[3].getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            	 if(newValue.equalsIgnoreCase("Humain")) {
                 	joueurs[3] = new JoueurHumain(3, jeu);
                 }else {
                 	joueurs[3] = new JoueurIA(3, jeu); 
                 }
            }
        });
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
