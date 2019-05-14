package Controleur;

import Controleur.Modele.Jeu;
import Controleur.Modele.Piece;
import Controleur.Modele.Position;


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

	boolean jeu(Position posPlateau, Position posPiece, Piece p) {
		return false;
	}

}