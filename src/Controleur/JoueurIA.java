package Controleur;

import java.util.Random;
import Modele.Jeu;
import Modele.Piece;
import Modele.Position;


public class JoueurIA extends Joueur {
	Random r;
	int MAXPROF = 5;

	public JoueurIA(int n, Jeu j) {
		super(n, j);
		r = new Random();
	}
	boolean tempsEcoule(){
		if(!jeu.jouable()){
			jeu.changerJoueurSansJeuer();
			return true;
		}
		else{
			int bound = jeu.piecesJ[jeu.joueurCourant].size();
			Position posPlateau;
			boolean trouver=false;
			while(!trouver){
				num = r.nextInt(bound);
				posPlateau = jeu.coord.get(r.nextInt(jeu.coord.size()));
				jeu.setSelected(jeu.piecesJ[jeu.joueurCourant].get(num).getNum());
				getFormePiece();
				Piece choix = jeu.pieceCourant;
				Position posPiece = getPosPiece(posPlateau, choix);
				if (jeu.placerPossible(posPlateau, posPiece, choix)) {
					jeu.piecesJ[jeu.joueurCourant].remove(jeu.pieces.get(choix.getNum()));
					jeu.plateauPiece[jeu.joueurCourant].enlevePiece(choix.getNum());
					jeu.jouer(posPlateau, posPiece, choix);
					trouver = true;
				}
			}
			return true;
		}
	}
	boolean tempsEcouleeee() {
		int bound;
		Position posPlateau;
		if(jeu.coord.size()>0) {
			posPlateau = jeu.coord.get(r.nextInt(jeu.coord.size()));
		}else{
			return false;
		}
		if((bound = jeu.piecesJ[jeu.joueurCourant].size()) != 0) {
			num = r.nextInt(bound);
			jeu.setSelected(jeu.piecesJ[jeu.joueurCourant].get(num).getNum());
			Piece choix = jeu.pieceCourant;
			Position posPiece = getPosPiece(posPlateau, choix);
			if (jeu.placerPossible(posPlateau, posPiece, choix)) {
				jeu.piecesJ[jeu.joueurCourant].remove(jeu.pieces.get(choix.getNum()));
				jeu.plateauPiece[jeu.joueurCourant].enlevePiece(choix.getNum());
				jeu.jouer(posPlateau, posPiece, choix);
				return true;
			}
		}
		return false;
	}
	void getFormePiece(){
		int tourner = r.nextInt(4);
		int inverser = r.nextInt(2);
		for(int i=0;i<=tourner;i++){
			jeu.pieceCourant.retationGauche();
		}
		if(inverser==0){
			jeu.pieceCourant.Miroir();
		}
	}
	Position getPosPiece(Position posPl, Piece p) {
		Position pos = new Position(0, 0);
		for (int i = 0; i < p.taille; i++) {
			for (int j = 0; j < p.taille; j++) {
				pos = new Position(i, j);
				if (p.carres[i][j]) {
					if (jeu.placerPossible(posPl, pos, p)) {
						return pos;
					}
				}
			}
		}
		return pos;
	}
	boolean tempsEcouleNonPerdant() {
		int bound;
		Position posPlateau;
		if(jeu.coord.size()>0) {
			posPlateau = jeu.coord.get(r.nextInt(jeu.coord.size()));
		}else{
			return false;
		}
		if((bound = jeu.piecesJ[jeu.joueurCourant].size()) != 0) {
			num = r.nextInt(bound);

			Piece choix = jeu.choixPiece(num);
			Position posPiece = closestToMiddle();
			//Position posPiece = nextPiece(posPlateau, choix);

			if (jeu.placerPossible(posPlateau, posPiece, choix)) {
				jeu.jouer(posPlateau, posPiece, choix);
				jeu.piecesJ[jeu.joueurCourant].remove(jeu.pieces.get(choix.getNum()));
				return true;
			}
		}
		return false;
	}
	Position closestToMiddle(){
		Position closest = basePos();

		for(Position pos : jeu.coord){
			if((jeu.joueurCourant == 1) && ((pos.l > closest.l) ||  ((pos.c > closest.c)))){
				closest = pos;
			}
			if((jeu.joueurCourant == 2) && ((pos.l > closest.l) || ((pos.c < closest.c)))){
				closest = pos;
			}
			if((jeu.joueurCourant == 3) && ((pos.l < closest.l) || ((pos.c < closest.c)))){
				closest = pos;
			}
			if((jeu.joueurCourant == 0) && ((pos.l < closest.l) || ((pos.c > closest.c)))){
				closest = pos;
			}
		}

		return closest;

	}
	Position basePos(){
		switch(jeu.joueurCourant){
			case 0:
				return new Position(jeu.plateau.taille()-1,0);
			case 1:
				return new Position(0,0);
			case 2:
				return new Position(0,jeu.plateau.taille()-1);
			case 3:
				return new Position(jeu.plateau.taille()-1,jeu.plateau.taille()-1);
		}
		return new Position(0,0);
	}
	boolean tempsEcouleMinimax(){
		int iandj [] = new int [2];

		int[][] p =  jeu.plateau.p;
		//nextStep(iandj, p);

		System.out.println("Chosen next step : " + iandj[0] + " " + iandj[1]);

		//plateau.jouer(iandj[0], iandj[1], piece);

		return true;

	}
}
