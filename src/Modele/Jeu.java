package Modele;

import Patterns.Observable;

public class Jeu extends Observable {
	boolean enCours;
	public int joueurCourant; // Mis en public pour pouvoir y acceder depuis JoueurIA
	boolean [][] pieceJoueurs = new boolean[4][21]; // 4 joueurs max et 21 pièces au total
	public Plateau plateau;
			
	public Jeu(int n) {
		plateau = new Plateau(n);
		enCours = true;
		for (int i = 0; i < plateau.p.length; i++)
			for (int j = 0; j < plateau.p[0].length; j++)
				plateau.newVal(i, j, 0);
		joueurCourant = 1;
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

}
