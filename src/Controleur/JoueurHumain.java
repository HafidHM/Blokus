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

		if (plateau.plateau.libre(i, j)) {
			int num = r.nextInt(3);
			//plateau.plateau.availableCases(plateau.joueurCourant);

			/*try {
				Scanner keyboard = new Scanner(System.in);
				System.out.println("Entrez un entier");
				while(num < 1 || num > 21) {
					num = keyboard.nextInt();
				}
			} catch (InputMismatchException e){
				System.err.println("Entrez un entier !!!");
			}*/
			plateau.jouer(i, j, plateau.choixPiece(num));

			return true;
		} else {
			return false;
		}
	}

}
