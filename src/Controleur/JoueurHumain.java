package Controleur;

import Modele.Jeu;
import Modele.Piece;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

class JoueurHumain extends Joueur {
	Random r;
	JoueurHumain(int n, Jeu p) {
		super(n, p);
		r = new Random();
	}

	@Override
	boolean jeu(int i, int j) {

		if (jeu.plateau.jouable(i, j)) {
			int bound, num;
			if((bound = jeu.piecesJ[jeu.joueurCourant].size()) != 0){
				num = r.nextInt(bound);
			} else {
				return false;
			}
			jeu.plateau.availableCases(((jeu.joueurCourant+1) %4), jeu.coord, jeu.noPiecesPosées());

			// Joueur ???
			if(jeu.jouer(i, j, jeu.choixPiece(num))){
				jeu.piecesJ[jeu.joueurCourant].remove(num);
				jeu.updateJoueurCour();
				return true;
			}else {
				return false;
			}

		} else {
			return false;
		}
	}

}
