package Controleur;

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
import Vue.FenetreGraphique;

public class ControleurMediateur {
	Jeu jeu;
	FenetreGraphique f;
	Joueur[] joueurs;
        boolean jeuAutomatique;
	int joueurCourant;
	final int lenteurAttente = 50;
	int decompte;
        String niv;
	public ControleurMediateur(Jeu j, FenetreGraphique fen) {
		jeu = j;
		f = fen;
                jeuAutomatique = false;
                f.changeBoutonIA(jeuAutomatique);
		joueurs = new Joueur[2];
                joueurs[0] = new JoueurHumain(0, jeu);
		if (jeuAutomatique)
			joueurs[1] = new JoueurIA(1, jeu);
		else
			joueurs[1] = new JoueurHumain(1, jeu);
                niv = f.ecouteurNiveau();      
	}

	public void redimensionnement() {
		f.miseAJour();
	}

	public void clicSouris(double x, double y) {
		int l = (int) (y / f.hauteurCase());
		int c = (int) (x / f.largeurCase());

		if (joueurs[joueurCourant].jeu(l, c))
			changeJoueur();
	}

	void changeJoueur() {
		joueurCourant = (joueurCourant + 1) % joueurs.length;
		decompte = lenteurAttente;
	}

        public boolean choisirNiveau(String niveau){
            if(!niv.equals(niveau)){
                System.out.println("Vous avez choisit " + niveau);
                niv=niveau;
            } 
            switch(niveau){
                case "Easy":return joueurs[joueurCourant].tempsEcoule();
                case "Medium":return joueurs[joueurCourant].tempsEcouleNonPerdant();
                case "Hard":return joueurs[joueurCourant].tempsEcouleMinimax();
                default:return false;
            }
        }
        public void basculeIA(boolean value) {
		jeuAutomatique = value;
		System.out.println("jeuautomatique " + jeuAutomatique);
		f.changeBoutonIA(value);
                if (jeuAutomatique)
			joueurs[1] = new JoueurIA(1, jeu);
		else
			joueurs[1] = new JoueurHumain(1, jeu);
	}
        
	public void tictac() {
		if (jeu.enCours()) {
			if (decompte == 0) {
				if (choisirNiveau(f.ecouteurNiveau())) {
					changeJoueur();
				} else {
					System.out.println("On vous attend, joueur " + joueurs[joueurCourant].num());
					decompte = lenteurAttente;
                                        
				}
			} else {
				decompte--;
			}
                       
		}
            
	}
	
    public void refaire() {
            jeu.refaire();
	}
        
}
