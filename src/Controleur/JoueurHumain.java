package Controleur;
import Controleur.Modele.Jeu;
import Controleur.Modele.Piece;
import Controleur.Modele.Position;

class JoueurHumain extends Joueur {
	JoueurHumain(int n, Jeu p) {
		super(n, p);
	}
	boolean jeu(Position posPlateau,Position posPiece,Piece choix) {
		if (jeu.placerPossible(posPlateau,posPiece,choix)) {
			jeu.jouer(posPlateau,posPiece,choix);
			jeu.piecesJ[jeu.joueurCourant].remove(choix.getNum());
			return true;
		} else {
			return false;
		}
	}
}