package Controleur;
import Modele.Jeu;
import Modele.Piece;
import Modele.Position;

import java.util.Random;

public class JoueurHumain extends Joueur {
	public JoueurHumain(int n, Jeu p) {
		super(n, p);
	}
	boolean jeu(Position posPlateau,Position posPiece,Piece choix) {
		if (jeu.placerPossible(posPlateau, posPiece, choix)) {
			jeu.jouer(posPlateau, posPiece, choix);
			jeu.piecesJ[jeu.joueurCourant][choix.getNum()] = false;
			return true;
		}

		return false;
	}
}