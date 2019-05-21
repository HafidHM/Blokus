package Modele;
import Patterns.Observable;

import java.io.Serializable;
import java.util.ArrayList;

public class Jeu extends Observable implements Serializable {
	public boolean enCours;
	public boolean[] enCoursJ;
	public int joueurCourant;
	public ArrayList<Piece> []piecesJ;
	public Plateau plateau;
	public ArrayList<Piece> pieces;
	public ArrayList<Position> coord;
	public Plateau[] plateauPiece;
	public Plateau plateauAffiche;
	public Piece pieceCourant;
	public int PosPieceL;
	public int PosPieceC;
	public int [] Score;
	public int ScoreT;

	public Jeu(int n) {
		plateau = new Plateau(n,n);
		plateauPiece = new Plateau[4];
		for (int i = 0; i < 4; i++) {
			plateauPiece[i] = new Plateau(12, 23);
			plateauPiece[i].initPlateauPiece();
		}
		plateauAffiche = new Plateau(5,5);
		enCours = false;
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
        initEnCoursJ();
		plateauAffiche.initPlateauAffiche();
		availableCases();
		initScores_Joueurs_Jeu();
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
		ScoreT += pieceCourant.getNbCarres();
		Score[joueurCourant]+= pieceCourant.getNbCarres();

		updateJoueurCour();
		availableCases();
		updateEncours();
		metAJour();
	}
	public void setPieceL(int num) {
		PosPieceL = num;
	}
	public void setPieceC(int num) {
		PosPieceC = num;
	}
	public void setSelected(int num) {
		pieceCourant = new Piece(5);
		for(int i=0;i<5;i++) {
			for(int j=0;j<5;j++) {
				if(pieces.get(num).carres[i][j])
					pieceCourant.ajout(true, i, j);
			}
		}
		pieceCourant.num = pieces.get(num).num;
	}
	public boolean enCours() {
		return enCours;
	}
	public void refaire() {
		plateau.p = new int[plateau.taille()][plateau.taille()];
		for (int i = 0; i < 4; i++) {
			plateauPiece[i].initPlateauPiece();
		}
		enCours = false;
		for (int i = 0; i < plateau.p.length; i++)
			for (int j = 0; j < plateau.p[0].length; j++)
				plateau.newVal(i, j, -1);

		plateau.newVal(plateau.taille()-1,0, 8);

		joueurCourant = 0;

		coord = new ArrayList<>();
		initialiserPieces();
		initPiecesJoueurs();
		initScores_Joueurs_Jeu();

		plateauAffiche.initPlateauAffiche();
		availableCases();
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
		p.ajout(true, 3, 2);
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
    public void initEnCoursJ(){
	    enCoursJ = new boolean[4];
	    for(int i=0;i<4;i++) {
            enCoursJ[i] = true;
        }
    }
    public boolean noPiecesPosées(){
		return piecesJ[joueurCourant].size() == pieces.size();
	}
	public boolean libre(Position posPlateau, Position posPiece, Piece p) {
		boolean lib = true;
		boolean dans = false;
		int debutI = posPlateau.l-posPiece.l;
		int debutJ = posPlateau.c-posPiece.c;
		for(int i=0;i<p.taille;i++){
			for(int j=0;j<p.taille;j++){
				if (p.carres[i][j]) {
					if(plateau.horsBord(new Position(debutI+i,debutJ+j)))
						return false;
					else {
						if(!(plateau.p[debutI+i][debutJ+j]==-1|| plateau.p[debutI+i][debutJ+j]==8))
							lib = false;
						if(plateau.p[debutI+i][debutJ+j]==8)
							dans = true;
					}
				}
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
	public Piece choixPiece(int num) {
		return pieces.get(num);
	}
    public void availableCases(){
        if (!noPiecesPosées()) {
            for (Position pos : coord) {
                if (plateau.p[pos.l][pos.c] == 8)
                    plateau.p[pos.l][pos.c] = -1;
            }
            coord.clear();
            for (int i = 0; i < plateau.taille(); i++) {
                for (int j = 0; j < plateau.taille(); j++) {
                    if (plateau.placeNearby(i, j, joueurCourant)) {
                        Position pos = new Position(i, j);
                        plateau.p[i][j] = 8;
                        if(vraiCaseVerte(pos))
                            coord.add(pos);
                        else
							plateau.p[i][j] = -1;
                    }
                }
            }
        } else {
            coord.clear();
            Position pos;
            switch (joueurCourant){
                case 0:
                    coord.add(new Position(plateau.taille() - 1,0));
                    break;
                case 1:
                    plateau.p[0][0] = 8;
                    pos = new Position(0, 0);
                    coord.add(pos);
                    break;
                case 2:
                    plateau.p[0][plateau.taille()-1] = 8;
                    pos = new Position(0, plateau.taille()-1);
                    coord.add(pos);
                    break;
                case 3:
                    plateau.p[plateau.taille()-1][plateau.taille()-1] = 8;
                    pos = new Position(plateau.taille()-1, plateau.taille()-1);
                    coord.add(pos);
                    break;
            }
        }

    }
    public boolean vraiCaseVerte(Position p){
	    boolean res = false;
        for(Piece buf :piecesJ[joueurCourant]){
        	int tourner=0;	int inverser=0;
        	while(!(tourner==4 && inverser==1)) {
				for (int i = 0; i < 5; i++) {
					for (int j = 0; j < 5; j++) {
						if (buf.carres[i][j]) {
							Position posPiece = new Position(i, j);
							if (placerPossible(p, posPiece, buf))
								res = true;
						}
					}
				}
				buf.retationGauche();tourner++;
				if(tourner==4 && inverser==0){
					buf.Miroir();	inverser=1;	tourner=0;
				}
			}
        }
        return res;
    }
    public boolean jouable(){
        boolean res = true;
        res = res && (coord.size()>0) && (piecesJ[joueurCourant].size()>0);
        return res;
    }
    public void updateEncours(){
	    if(!jouable()){
	        enCoursJ[joueurCourant]=false;
	        System.out.println("Joueur "+joueurCourant+" est perdu------pass!");
        }
	    int compte = 0;int gagne=0;
	    for(int i=0;i<4;i++){
	        if(enCoursJ[i]) {
	        	gagne=i;
				compte++;
			}
        }
	    if(!(compte>=2)){
	    	enCours=false;
	    	System.out.println("joueur "+gagne+" est gagne!!!!!!!!!!!");
		}
    }
    public void changerJoueurSansJeuer(){
		updateJoueurCour();
		availableCases();
		updateEncours();
		metAJour();
	}
	public void  initScores_Joueurs_Jeu(){
		Score = new int[4];
		for (int i = 0; i < 4; i++)
			Score[i] = 0;
		ScoreT = 0;
	}

}


