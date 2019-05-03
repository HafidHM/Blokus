package Controleur;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import Modele.Jeu;

class JoueurIA extends Joueur {
	Random r;
	int MAXPROF = 5;

	JoueurIA(int n, Jeu p) {
		super(n, p);
		r = new Random();
	}

	@Override
	boolean tempsEcoule() {
		int i, j;

		i = r.nextInt(plateau.hauteur());
		j = r.nextInt(plateau.largeur());
		while (!plateau.libre(i, j)) {
			i = r.nextInt(plateau.hauteur());
			j = r.nextInt(plateau.largeur());
		}
		plateau.jouer(i, j);
		return true;
	}

	boolean tempsEcouleNonPerdant(){
		int i,j;
		i = r.nextInt(plateau.hauteur());
		j = r.nextInt(plateau.largeur());
		while (!plateau.libre(i, j) || (plateau.coupPoison(i,j) && plateau.nonPerdrePossible()) ){
			i = r.nextInt(plateau.hauteur());
			j = r.nextInt(plateau.largeur());
		}
		plateau.jouer(i, j);
		return true;
	}

	boolean tempsEcouleMinimax(){
		int iandj [] = new int [2];

		int[][] p =  plateau.plateau;
		nextStep(iandj, p);

		System.out.println("Chosen next step : " + iandj[0] + " " + iandj[1]);

		plateau.jouer(iandj[0], iandj[1]);

		return true;

	}





	void nextStep(int[] iandj, int[][] p){

		int Profondeur = MAXPROF;

		int player = plateau.joueurCourant;

		int values [] = Minimax(p,Profondeur, player);

		iandj[0] = values[1];
		iandj[1] = values[2];

	}  // TODO

	int [] Minimax(int[][] p, int profondeur, int player){


		//System.out.println("IN IA : " + profondeur);

		List<int[]> allSteps = nextPossibleSteps(p);

		int nextScore=0;

		int finiteIJ [] = new int [2];
		int finiteScore = 0;

		if(profondeur == 0 || end(p)){
			finiteScore = evaluation(p, player);
		} else {
			if (player == 0) {
				int value = -10000;

				for (int[] iandj : allSteps) {
					//System.out.println("P0 "+iandj[0]+" "+iandj[1]);
					int[][] config = nextConfig(p, iandj, OtherPlayer(player));
					if (value < (nextScore = Minimax(config, profondeur - 1, OtherPlayer(player))[0]) ){
						value = nextScore;
						finiteIJ = iandj;
					}
				}
				finiteScore =  value;

			} else {
				int value = 10000;

				for (int[] iandj : allSteps) {
					//System.out.println("P1 "+iandj[0]+" "+iandj[1]);
					int[][] config = nextConfig(p, iandj, OtherPlayer(player));
					if (value > (nextScore = Minimax(config, profondeur - 1, OtherPlayer(player))[0])) {
						value = nextScore;
						finiteIJ = iandj;
					}
				}
				finiteScore = value;

			}
		}

		return new int[] {finiteScore, finiteIJ[0], finiteIJ[1]};
	}

	List<int[]> nextPossibleSteps(int [][] p){
		List<int[]> Steps = new ArrayList<int[]>();


		if(end(p)){
			return Steps;
		}

		for(int i=0; i<plateau.hauteur();i++){
			for(int j=0; j<plateau.largeur();j++){
				if((p[i][j] == -1) && (!plateau.coupPoison(i, j))){
					Steps.add(new int[] {i, j});
				}
			}
		}

		return Steps;

	}

	int [][] nextConfig(int[][] p, int [] iandj, int OPlayer) {
		int[][] Config = new int[plateau.hauteur()][plateau.largeur()];

		for (int i = 0 ;i<plateau.hauteur();i++){
			for(int j =0;j<plateau.largeur();j++){
				if(!plateau.coupPoison(i, j)) {
					if ((i == iandj[0] && j == iandj[1]) && p[i][j] != 1) {
						if (OPlayer == 0) {
							Config[i][j] = 2;
						} else {
							Config[i][j] = 6;
						}

					} else if ((i >= iandj[0] && j >= iandj[1]) && p[i][j] != 1) {
						if(p[i][j] == 4 || p[i][j] == 8){
							Config[i][j] = 10;
						}else {
							if (OPlayer == 0) {
								Config[i][j] = 4;
							} else {
								Config[i][j] = 8;
							}
						}

					} else {
						Config[i][j] = p[i][j];
					}
				}
			}
		}

		return Config;



	} // TODO // DONE

	int evaluation(int [][] p, int player){
		int score = 0;

		if((player == 0) && (plateau.joueurCourant == 1)) {
			score = -10;
		} else if((player == 0) && (plateau.joueurCourant == 0)) {
			score = 10;
		} else if((player == 1) && (plateau.joueurCourant == 0)) {
			score = -10;
		} else if((player == 1) && (plateau.joueurCourant == 1)) {
			score = 10;
		}

		return score;
	} // TODO // DONE

	int OtherPlayer(int player){
		if(player == 0){
			return 1;
		} else {
			return 0;
		}
	} // TODO // DONE

	boolean end(int[][] p) {
		return ((p[0][1] != -1) && (p[1][0] != -1));

	} // TODO // DONE


}
