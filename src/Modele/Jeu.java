package Modele;

import Patterns.Observable;

import java.io.Serializable;
import java.util.ArrayList;


public class Jeu extends Observable implements Serializable {
	boolean enCours;
	public int joueurCourant; // Mis en public pour pouvoir y acceder depuis JoueurIA
	public ArrayList<Piece> []piecesJ;
	public Plateau plateau;
	ArrayList<Piece> pieces;
	public ArrayList<Position> coord;
	public PlateauPiece plateauPiece;
	public PlateauAffiche plateauAffiche;
	public int pieceCourant;


	public Jeu(int n) {
		plateau = new Plateau(n);
		plateauPiece = new PlateauPiece();
		plateauAffiche = new PlateauAffiche();
		enCours = true;
		for (int i = 0; i < plateau.p.length; i++)
			for (int j = 0; j < plateau.p[0].length; j++)
				plateau.newVal(i, j, -1);
		plateau.newVal(plateau.taille() - 1, 0, 8);
		joueurCourant = 0;

		pieces = new ArrayList<>();
		piecesJ = new ArrayList[4];
		coord = new ArrayList<>();
		initialiserPieces();
		initPiecesJoueurs();

		plateauPiece.initPlateauPiece();
		plateauAffiche.initPlateauAffiche();

	}

	public boolean jouer(int l, int c, Piece piece) {
/*
		if(plateau.valeur(l, c) != 8){ // Le joueur ne peut jouer que sur des cases vertes
			return false;
		}*/

		for(int i=l;i<piece.taille+l;i++) {
			for (int j = c; j < piece.taille+c; j++) {
				if((i>=plateau.p.length || j>=plateau.p.length) && (piece.carres[i-l][j-c])){
					return false; // Cas où la pièce dépasse du plateau
				} else if ((i<plateau.p.length && j<plateau.p.length) && ((plateau.valeur(i, j) != -1) &&
						(plateau.valeur(i, j) != 8)) && ((piece.carres[i-l][j-c]))){
					return false; // Cas où la pièce est en collision avec une autre pièce du plateau
				}
			}
		}

		for(int i=l;i<piece.taille+l;i++) {
			for (int j = c; j < piece.taille+c; j++) {
				if(piece.carres[i-l][j-c]){
					plateau.newVal(i, j, joueurCourant);
				}
			}
		}

		metAJour();

		return true;

	}

	public boolean enCours() {
		return enCours;
	}

	public void refaire() {
		plateau.p = new int[9][9];
		enCours = true;
		for (int i = 0; i < plateau.p.length; i++)
			for (int j = 0; j < plateau.p[0].length; j++)
				plateau.newVal(i, j, -1);

		plateau.newVal(plateau.taille()-1,0, 8);

		joueurCourant = 0;

		pieces = new ArrayList<>();
		piecesJ = new ArrayList[4]; // TODO ???
		coord = new ArrayList<>();
		initialiserPieces();
		initPiecesJoueurs();


		metAJour();
	}

	public void updateJoueurCour(){
		joueurCourant = ((joueurCourant+1) %4);
	}

	public void initialiserPieces() {
		Piece p = new Piece(5);
		for(int i=0;i<21;i++) {

			p.ajout(true, 0, 0);

			p.num = i;
			pieces.add(p);
		}


		/*
		Piece p = new Piece(5);

		p.ajout(true, 2, 2);
		p.num = 1;
		pieces.add(p);

		p = new Piece(5);
		p.ajout(true, 2, 1);
		p.ajout(true, 2, 2);
		p.num = 2;
		pieces.add(p);

		p = new Piece(5);
		p.ajout(true, 1, 1);
		p.ajout(true, 1, 2);
		p.ajout(true, 2, 2);
		p.num = 3;
		pieces.add(p);

		p = new Piece(5);
		p.ajout(true, 1, 1);
		p.ajout(true, 1, 2);
		p.ajout(true, 2, 2);
		p.num = 3;
		pieces.add(p);

		p = new Piece(5);
		p.ajout(true, 2, 1);
		p.ajout(true, 2, 2);
		p.ajout(true, 2, 3);
		p.num = 4;
		pieces.add(p);

		p = new Piece(5);
		p.ajout(true, 1, 1);
		p.ajout(true, 1, 2);
		p.ajout(true, 2, 1);
		p.ajout(true, 2, 2);
		p.num = 5;
		pieces.add(p);

		p = new Piece(5);
		p.ajout(true, 1, 2);
		p.ajout(true, 2, 3);
		p.ajout(true, 2, 1);
		p.ajout(true, 2, 2);
		p.num = 6;
		pieces.add(p);

		p = new Piece(5);
		p.ajout(true, 2, 0);
		p.ajout(true, 2, 1);
		p.ajout(true, 2, 2);
		p.ajout(true, 2, 3);
		p.num = 7;
		pieces.add(p);

		p = new Piece(5);
		p.ajout(true, 2, 1);
		p.ajout(true, 2, 2);
		p.ajout(true, 2, 3);
		p.ajout(true, 1, 3);
		p.num = 8;
		pieces.add(p);

		p = new Piece(5);
		p.ajout(true, 1, 2);
		p.ajout(true, 1, 3);
		p.ajout(true, 2, 1);
		p.ajout(true, 2, 2);
		p.num = 9;
		pieces.add(p);

		p = new Piece(5);
		p.ajout(true, 1, 1);
		p.ajout(true, 2, 1);
		p.ajout(true, 2, 2);
		p.ajout(true, 2, 3);
		p.ajout(true, 2, 4);
		p.num = 10;
		pieces.add(p);

		p = new Piece(5);
		p.ajout(true, 1, 2);
		p.ajout(true, 2, 2);
		p.ajout(true, 3, 2);
		p.ajout(true, 3, 1);
		p.ajout(true, 3, 3);
		p.num = 11;
		pieces.add(p);

		p = new Piece(5);
		p.ajout(true, 1, 1);
		p.ajout(true, 2, 1);
		p.ajout(true, 3, 1);
		p.ajout(true, 3, 2);
		p.ajout(true, 3, 3);
		p.num = 12;
		pieces.add(p);

		p = new Piece(5);
		p.ajout(true, 2, 1);
		p.ajout(true, 2, 2);
		p.ajout(true, 1, 2);
		p.ajout(true, 1, 3);
		p.ajout(true, 1, 4);
		p.num = 13;
		pieces.add(p);

		p = new Piece(5);
		p.ajout(true, 3, 1);
		p.ajout(true, 2, 1);
		p.ajout(true, 2, 2);
		p.ajout(true, 2, 3);
		p.ajout(true, 1, 3);
		p.num = 14;
		pieces.add(p);

		p = new Piece(5);
		p.ajout(true, 0, 2);
		p.ajout(true, 1, 2);
		p.ajout(true, 2, 2);
		p.ajout(true, 3, 2);
		p.ajout(true, 4, 2);
		p.num = 15;
		pieces.add(p);

		p = new Piece(5);
		p.ajout(true, 1, 1);
		p.ajout(true, 2, 1);
		p.ajout(true, 2, 2);
		p.ajout(true, 3, 1);
		p.ajout(true, 3, 2);
		p.num = 16;
		pieces.add(p);

		p = new Piece(5);
		p.ajout(true, 3, 1);
		p.ajout(true, 2, 1);
		p.ajout(true, 2, 2);
		p.ajout(true, 1, 2);
		p.ajout(true, 1,3);
		p.num = 17;
		pieces.add(p);

		p = new Piece(5);
		p.ajout(true, 1, 1);
		p.ajout(true, 1, 2);
		p.ajout(true, 2, 1);
		p.ajout(true, 3, 1);
		p.ajout(true, 3,2);
		p.num = 18;
		pieces.add(p);

		p = new Piece(5);
		p.ajout(true, 2, 1);
		p.ajout(true, 1, 2);
		p.ajout(true, 2, 2);
		p.ajout(true, 2, 3);
		p.ajout(true, 1, 3);
		p.num = 19;
		pieces.add(p);

		p = new Piece(5);
		p.ajout(true, 2, 1);
		p.ajout(true, 1, 2);
		p.ajout(true, 2, 2);
		p.ajout(true, 2, 3);
		p.ajout(true, 3, 2);
		p.num = 20;
		pieces.add(p);

		p = new Piece(5);
		p.ajout(true, 1, 2);
		p.ajout(true, 2, 1);
		p.ajout(true, 2, 2);
		p.ajout(true, 2, 3);
		p.ajout(true, 2, 4);
		p.num = 21;
		pieces.add(p);

*/
	}

	public void setSelected(int num) {
		pieceCourant = num;
	}
	public void initPiecesJoueurs() {
		for (int i = 0; i < 4; i++) {
			piecesJ[i] = new ArrayList<>();
			piecesJ[i].addAll(pieces);
		}


	}

	public Piece choixPiece(int num){
		return piecesJ[joueurCourant].get(num);

	}

	public boolean noPiecesPosées(){
		return piecesJ[((joueurCourant+1) %4)].size() == pieces.size();
	}

}
