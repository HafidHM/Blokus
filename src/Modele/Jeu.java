package Modele;
import Patterns.Observable;

import java.io.Serializable;
import java.util.ArrayList;

public class Jeu extends Observable implements Serializable {
	boolean enCours;
	public int joueurCourant;
	public ArrayList<Piece> []piecesJ;
	public Plateau plateau;
	ArrayList<Piece> pieces;
	public ArrayList<Position> coord;
	public PlateauPiece plateauPiece;
	public PlateauAffiche plateauAffiche;
	public int pieceCourant;
	public int PosPieceL;
	public int PosPieceC;

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
	public void jouer(Position posPlateau, Position posPiece, Piece p){
		int debutI = posPlateau.l-posPiece.l;
		int debutJ = posPlateau.c-posPiece.c;
		for(int i=0;i<p.taille;i++){
			for(int j=0;j<p.taille;j++) {
				if (p.carres[i][j])
					plateau.p[debutI + i][debutJ + j] = joueurCourant;
			}
		}
		updateJoueurCour();
		plateau.availableCases(joueurCourant, coord, noPiecesPosées());
		metAJour();
	}
	public void setPieceL(int num) {
		PosPieceL = num;
	}
	public void setPieceC(int num) {
		PosPieceC = num;
	}
	public void setSelected(int num) {
		pieceCourant = num;
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
		p.ajout(true, 2, 2);
		p.num = 0;
		pieces.add(p);

		p = new Piece(5);
		p.ajout(true, 2, 1);
		p.ajout(true, 2, 2);
		p.num = 1;
		pieces.add(p);

		p = new Piece(5);
		p.ajout(true, 1, 1);
		p.ajout(true, 1, 2);
		p.ajout(true, 2, 2);
		p.num = 2;
		pieces.add(p);

		p = new Piece(5);
		p.ajout(true, 2, 1);
		p.ajout(true, 2, 2);
		p.ajout(true, 2, 3);
		p.num = 3;
		pieces.add(p);

		p = new Piece(5);
		p.ajout(true, 1, 1);
		p.ajout(true, 1, 2);
		p.ajout(true, 2, 1);
		p.ajout(true, 2, 2);
		p.num = 4;
		pieces.add(p);

		p = new Piece(5);
		p.ajout(true, 1, 2);
		p.ajout(true, 2, 3);
		p.ajout(true, 2, 1);
		p.ajout(true, 2, 2);
		p.num = 5;
		pieces.add(p);

		p = new Piece(5);
		p.ajout(true, 2, 0);
		p.ajout(true, 2, 1);
		p.ajout(true, 2, 2);
		p.ajout(true, 2, 3);
		p.num = 6;
		pieces.add(p);

		p = new Piece(5);
		p.ajout(true, 2, 1);
		p.ajout(true, 2, 2);
		p.ajout(true, 2, 3);
		p.ajout(true, 1, 3);
		p.num = 7;
		pieces.add(p);

		p = new Piece(5);
		p.ajout(true, 1, 2);
		p.ajout(true, 1, 3);
		p.ajout(true, 2, 1);
		p.ajout(true, 2, 2);
		p.num = 8;
		pieces.add(p);

		p = new Piece(5);
		p.ajout(true, 1, 1);
		p.ajout(true, 2, 1);
		p.ajout(true, 2, 2);
		p.ajout(true, 2, 3);
		p.ajout(true, 2, 4);
		p.num = 9;
		pieces.add(p);

		p = new Piece(5);
		p.ajout(true, 1, 2);
		p.ajout(true, 2, 2);
		p.ajout(true, 3, 2);
		p.ajout(true, 3, 1);
		p.ajout(true, 3, 3);
		p.num = 10;
		pieces.add(p);

		p = new Piece(5);
		p.ajout(true, 1, 1);
		p.ajout(true, 2, 1);
		p.ajout(true, 3, 1);
		p.ajout(true, 3, 2);
		p.ajout(true, 3, 3);
		p.num = 11;
		pieces.add(p);

		p = new Piece(5);
		p.ajout(true, 2, 1);
		p.ajout(true, 2, 2);
		p.ajout(true, 1, 2);
		p.ajout(true, 1, 3);
		p.ajout(true, 1, 4);
		p.num = 12;
		pieces.add(p);

		p = new Piece(5);
		p.ajout(true, 3, 1);
		p.ajout(true, 2, 1);
		p.ajout(true, 2, 2);
		p.ajout(true, 2, 3);
		p.ajout(true, 1, 3);
		p.num = 13;
		pieces.add(p);

		p = new Piece(5);
		p.ajout(true, 0, 2);
		p.ajout(true, 1, 2);
		p.ajout(true, 2, 2);
		p.ajout(true, 3, 2);
		p.ajout(true, 4, 2);
		p.num = 14;
		pieces.add(p);

		p = new Piece(5);
		p.ajout(true, 1, 1);
		p.ajout(true, 2, 1);
		p.ajout(true, 2, 2);
		p.ajout(true, 3, 1);
		p.ajout(true, 3, 2);
		p.num = 15;
		pieces.add(p);

		p = new Piece(5);
		p.ajout(true, 3, 1);
		p.ajout(true, 2, 1);
		p.ajout(true, 2, 2);
		p.ajout(true, 1, 2);
		p.ajout(true, 1,3);
		p.num = 16;
		pieces.add(p);

		p = new Piece(5);
		p.ajout(true, 1, 1);
		p.ajout(true, 1, 2);
		p.ajout(true, 2, 1);
		p.ajout(true, 3, 1);
		p.ajout(true, 3,2);
		p.num = 17;
		pieces.add(p);

		p = new Piece(5);
		p.ajout(true, 2, 1);
		p.ajout(true, 1, 2);
		p.ajout(true, 2, 2);
		p.ajout(true, 2, 3);
		p.ajout(true, 1, 3);
		p.num = 18;
		pieces.add(p);

		p = new Piece(5);
		p.ajout(true, 2, 1);
		p.ajout(true, 1, 2);
		p.ajout(true, 2, 2);
		p.ajout(true, 2, 3);
		p.ajout(true, 3, 2);
		p.num = 19;
		pieces.add(p);

		p = new Piece(5);
		p.ajout(true, 1, 2);
		p.ajout(true, 2, 1);
		p.ajout(true, 2, 2);
		p.ajout(true, 2, 3);
		p.ajout(true, 2, 4);
		p.num = 20;
		pieces.add(p);
	}
	public void initPiecesJoueurs() {
		for (int i = 0; i < 4; i++) {
			piecesJ[i] = new ArrayList<>();
			piecesJ[i].addAll(pieces);
		}


	}
	public Piece choixPiece(int num){
		return pieces.get(num);
	}
	public boolean noPiecesPosées(){
   		return piecesJ[((joueurCourant+1) %4)].size() == pieces.size();
	}
	public boolean libre(Position posPlateau, Position posPiece, Piece p) {
		boolean lib = true;
		boolean dans = false;
		int debutI = posPlateau.l-posPiece.l;
		int debutJ = posPlateau.c-posPiece.c;
		for(int i=0;i<p.taille;i++){
			for(int j=0;j<p.taille;j++){
				if (p.carres[i][j] && !(plateau.p[debutI+i][debutJ+j]==-1|| plateau.p[debutI+i][debutJ+j]==8))
					lib = false;
				if(p.carres[i][j] && plateau.p[debutI+i][debutJ+j]==8)
					dans = true;
			}
		}
		return lib && dans;
	}
	public boolean connecter(Position posPlateau, Position posPiece, Piece p){
		boolean res = false;
		int debutI = posPlateau.l-posPiece.l;
		int debutJ = posPlateau.c-posPiece.c;
		for(int i=0;i<p.taille;i++){
			for(int j=0;j<p.taille;j++){
				if(p.carres[i][j]){
					if(!plateau.horsBord(new Position(debutI+i-1,debutJ+j)))
						res = res || plateau.p[debutI+i-1][debutJ+j]==joueurCourant;
					if(!plateau.horsBord(new Position(debutI+i+1,debutJ+j)))
						res = res || plateau.p[debutI+i+1][debutJ+j]==joueurCourant;
					if(!plateau.horsBord(new Position(debutI+i,debutJ+j-1)))
						res = res || plateau.p[debutI+i][debutJ+j-1]==joueurCourant;
					if(!plateau.horsBord(new Position(debutI+i,debutJ+j+1)))
						res = res || plateau.p[debutI+i][debutJ+j+1]==joueurCourant;
				}
			}
		}
		return res;
	}
	public boolean placerPossible(Position posPlateau, Position posPiece, Piece p){
		return libre(posPlateau,posPiece,p) && (!connecter(posPlateau,posPiece,p));
	}
}