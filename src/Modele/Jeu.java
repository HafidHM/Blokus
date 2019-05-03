package Modele;

import Patterns.Observable;

public class Jeu extends Observable {
	boolean enCours;
	public int[][] plateau; // Mis en public pour pouvoir y acceder depuis JoueurIA
	int libre;
	public int joueurCourant; // Mis en public pour pouvoir y acceder depuis JoueurIA

	public Jeu(int m,int n) {
		plateau = new int[m][n];
		libre = m*n;
		enCours = true;
		for (int i = 0; i < plateau.length; i++)
			for (int j = 0; j < plateau[0].length; j++)
				plateau[i][j] = -1;
		joueurCourant = 0;
	}

	public void jouer(int l, int c) {
		if (enCours && (plateau[l][c] == -1) ) {
			//plateau[l][c] = joueurCourant;
			//libre--;
			/*boolean vertical = true, horizontal = true, slash = true, antiSlash = true;
			for (int p = 0; p < plateau.length; p++) {
				horizontal = horizontal && (plateau[l][p] == joueurCourant);
				vertical = vertical && (plateau[p][c] == joueurCourant);
				slash = slash && (plateau[p][p] == joueurCourant);
				antiSlash = antiSlash && (plateau[p][plateau.length - p - 1] == joueurCourant);
			}*/
			for (int i = 0 ;i<hauteur();i++){
				for(int j =0;j<largeur();j++){
					if(i>=l && j>=c && libre(i,j)){
						plateau[i][j] = 1;
						libre--;
					}
				}
			}
			//enCours = !(horizontal || vertical || slash || antiSlash) && (libre > 0);
			enCours = !coupPoison(l, c);
			joueurCourant = 1 - joueurCourant;
			if(enCours==false){
				System.out.println("Fin du jeu, joueur gagnant : "+joueurCourant);
			}

			metAJour();
		}
	}

	public boolean libre(int i, int j) {
		return valeur(i, j) == -1;
	}

	public int valeur(int i, int j) {
		return plateau[i][j];
	}

	public boolean enCours() {
		return enCours;
	}

	public int largeur() {
		return plateau[0].length;
	}

	public int hauteur() {
		return plateau.length;
	}

	public boolean coupPoison(int i, int j) {
		return (i==0 && j ==0) ;
	}

	public boolean nonPerdrePossible(){
		return libre!=1;
	}


	public boolean estFeuille(){
		return libre==0;
	}
	
    public void refaire() {
		plateau = new int[5][7];
		libre = 5*7;
		enCours = true;
		for (int i = 0; i < plateau.length; i++)
			for (int j = 0; j < plateau[0].length; j++)
				plateau[i][j] = -1;
		joueurCourant = 0;
        metAJour();
   }
}
