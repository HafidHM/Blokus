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
	public ArrayList<int []> coord;
	public PlateauPiece plateauPiece;
	LecteurPiece l;
	String fichierPieces = "resources/Pieces/pieces.txt";


	public Jeu(int n) {
		plateau = new Plateau(n);
		enCours = true;
		for (int i = 0; i < plateau.taille(); i++)
			for (int j = 0; j < plateau.taille(); j++)

				plateau.newVal(i, j, 0);

		plateau.newVal(plateau.taille()-1,0, 8);

		joueurCourant = 1;

		plateauPiece = new PlateauPiece();

		pieces = new ArrayList<>();
		piecesJ = new ArrayList[4]; // TODO ???
		coord = new ArrayList<>();
		initialiserPieces();
		initPiecesJoueurs();
		
		FileInputStream in = null;
		in = Configuration.charge(fichierPieces);
		l = new LecteurPiece(in);

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
				} else if ((i<plateau.p.length && j<plateau.p.length) && ((plateau.valeur(i, j) != 0) &&
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
		plateau.p = new int[23][23];
		enCours = true;
		for (int i = 0; i < plateau.p.length; i++)
			for (int j = 0; j < plateau.p[0].length; j++)
				plateau.newVal(i, j, 0);
		joueurCourant = 1;
		metAJour();
	}

	public void updateJoueurCour(){
		joueurCourant = ((joueurCourant) %4)+1;
	}

	public void initialiserPieces() {
		Piece p = new Piece(5);
		for(int i=0;i<21;i++) {

			p.ajout(true, 0, 0);

			p.num = i;
			pieces.add(p);
		}

	}

	public void initPiecesJoueurs() {
		for (int i = 0; i < 4; i++) {
			piecesJ[i] = new ArrayList<>();
			piecesJ[i].addAll(pieces);
		}


	}

	public Piece choixPiece(int num){
		return piecesJ[joueurCourant-1].get(num);

	}

	public boolean noPiecesPosées(){
		return piecesJ[((joueurCourant) %4)].size() == pieces.size();
	}

}