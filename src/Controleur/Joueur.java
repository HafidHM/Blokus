package Controleur;

import Modele.Jeu;

abstract class Joueur {
	Jeu plateau;
	int num;

	Joueur(int n, Jeu p) {
		num = n;
		plateau = p;
	}

	int num() {
		return num;
	}

	boolean tempsEcoule() {
		return false;
	}
	boolean tempsEcouleNonPerdant() {
		return false;
	}
	boolean tempsEcouleMinimax() {return false;}

	boolean jeu(int i, int j) {
		return false;
	}

}