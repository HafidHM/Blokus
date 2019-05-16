package Controleur;
import Modele.Jeu;
import Modele.Piece;
import Modele.Position;

public class JoueurHumain extends Joueur {
	public JoueurHumain(int n, Jeu p) {
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