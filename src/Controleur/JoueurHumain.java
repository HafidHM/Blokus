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

		if (plateau.plateau.jouable(i, j)) {
			int bound, num;
			if((bound = plateau.piecesJ[plateau.joueurCourant-1].size()) != 0){
				num = r.nextInt(bound);
			} else {
				return false;
			}
			plateau.plateau.availableCases(((plateau.joueurCourant) %4)+1, plateau.coord, plateau.noPiecesPosées());

			// Joueur ???
			if(plateau.jouer(i, j, plateau.choixPiece(num))){
				plateau.piecesJ[plateau.joueurCourant-1].remove(num);
				plateau.updateJoueurCour();
				return true;
			}else {
				return false;
			}

		} else {
			return false;
		}
	}

}
