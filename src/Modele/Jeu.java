package Modele;

import java.util.ArrayList;

import Patterns.Observable;

public class Jeu extends Observable {
	boolean enCours;
	public int joueurCourant; // Mis en public pour pouvoir y acceder depuis JoueurIA
	boolean [][] pieceJoueurs = new boolean[4][21]; // 4 joueurs max et 21 pièces au total
	public Plateau plateau;
	public PlateauPiece plateauPiece;
	ArrayList<Piece> pieces;

	public Jeu(int n) {
		plateau = new Plateau(n);
		plateauPiece = new PlateauPiece();
		enCours = true;
		for (int i = 0; i < plateau.p.length; i++)
			for (int j = 0; j < plateau.p[0].length; j++)
				plateau.newVal(i, j, 0);
		joueurCourant = 1;

		this.pieces = new ArrayList<>();
		initialiserPieces();
		plateauPiece.initPlateauPiece();
		

	}

	public boolean jouer(int l, int c, Piece piece) {

		for(int i=l;i<piece.taille+l;i++) {
			for (int j = c; j < piece.taille+c; j++) {
				if((i>=plateau.p.length || j>=plateau.p.length) && (piece.carres[i-l][j-c])){
					return false; // Cas où la pièce dépasse du plateau
				} else if ((i<plateau.p.length && j<plateau.p.length) && (plateau.valeur(i, j) != 0) && ((piece.carres[i-l][j-c]))){
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

		/*for(int i=0;i<plateau.p.length;i++) {
			for (int j = 0; j < plateau.p.length; j++) {
				System.out.print(plateau.valeur(i, j));
			}
			System.out.println();
		}
*/
		updateJoueurCour();
		metAJour();
		
		return true;

	}

	public boolean enCours() {
		return enCours;
	}

    public void refaire() {
		plateau.p = new int[8][8];
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

   /*public void initPieces(){
	   FileInputStream in;

	   String fichier = Configuration.instance().lis("FichierPieces");
	   in = Configuration.charge(fichier);

	   if (in == null) {
		   System.err.println("ERREUR : impossible de trouver le fichier de niveaux nommé " + fichier);
		   System.exit(1);
	   } else {
		   l = new LecteurPiece(in);
	   }

	   for(int i=0;i<4;i++) {
		   Arrays.fill(pieceJoueurs[i], Boolean.TRUE);
	   }


   }*/

   public Piece choixPiece(int num){
		Piece piece = pieces.get(num);
		return piece;
   }

	public void initialiserPieces() {
		Piece p = new Piece(5);
		p.ajout(true, 0, 0);
		p.ajout(true, 0, 1);
		p.ajout(true, 0, 2);
		p.ajout(true, 0, 3);
		p.num = 1;
		pieces.add(p);

		p = new Piece(5);
		p.ajout(true, 0, 1);
		p.ajout(true, 1, 1);
		p.ajout(true, 2, 0);
		p.ajout(true, 2, 1);
		p.ajout(true, 2, 2);
		p.num = 2;
		pieces.add(p);
		p = new Piece(5);
		p.ajout(true, 0, 0);
		p.ajout(true, 0, 1);
		p.ajout(true, 1, 1);
		p.num = 3;
		pieces.add(p);
	}
}
