package Controleur;

import Modele.Jeu;
import Vue.ViewJouer;

public class ControleurMediateur {
	Jeu jeu;
	ViewJouer vjouer;
	JoueurHumain[] joueurs;
    boolean jeuAutomatique;
	int joueurCourant;
	final int lenteurAttente = 50;
	int decompte;
    String niv;
	public ControleurMediateur(Jeu j, ViewJouer v) {
		jeu = j;
		vjouer = v;
        jeuAutomatique = false;
		joueurs = new JoueurHumain[2];
        joueurs[0] = new JoueurHumain(0, jeu);
		if (jeuAutomatique)
			joueurs[1] = new JoueurIA(1, jeu);
		else
			joueurs[1] = new JoueurHumain(1, jeu);   
	}

	public void redimensionnement() {
		vjouer.miseAJour();
	}

	public void clicSouris(double x, double y) {
		int l = (int) (y / vjouer.hauteurCase());
		int c = (int) (x / vjouer.largeurCase());
		
		if (joueurs[joueurCourant].jeu(l, c))
			changeJoueur();
	}

	void changeJoueur() {
		joueurCourant = (joueurCourant + 1) % joueurs.length;
		decompte = lenteurAttente;
	}

       /* public boolean choisirNiveau(String niveau){
            if(!niv.equals(niveau)){
                System.out.println("Vous avez choisit " + niveau);
                niv=niveau;
            } 
            switch(niveau){
                case "Easy":return joueurs[joueurCourant].tempsEcoule();
                case "Medium":return joueurs[joueurCourant].tempsEcouleNonPertant();
                case "Hard": return joueurs[joueurCourant].tempsEcouleMinimax();
                default:return false;
            }
        }*/
        public void basculeIA(boolean value) {
        	jeuAutomatique = value;
        	System.out.println("jeuautomatique " + jeuAutomatique);
        	//f.changeBoutonIA(value);
            if (jeuAutomatique)
            	joueurs[1] = new JoueurIA(1, jeu);
            else
            	joueurs[1] = new JoueurHumain(1, jeu);
        }
        
        public void tictac() {
			if (jeu.enCours()) {
				if (decompte == 0) {
					//if (choisirNiveau(f.ecouteurNiveau())) {
					if(joueurs[joueurCourant].tempsEcoule())
						changeJoueur();
				} else {
						//System.out.println("On vous attend, joueur " + joueurs[joueurCourant].num());
						decompte = lenteurAttente;
	                                        
				}
			} else {
					decompte--;
				}          
		}
            
	}
        

