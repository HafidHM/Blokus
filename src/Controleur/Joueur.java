package Controleur;

import Modele.Jeu;

import java.util.ArrayList;


abstract class Joueur {
	Jeu jeu;
	int num;

	Joueur(int n, Jeu j) {
		num = n;
		jeu = j;
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