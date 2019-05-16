package Controleur;

import Modele.Jeu;
import Modele.Position;
import Vue.ViewJouer;
import Vue.ViewParametre;

public class ControleurMediateur {
	Jeu jeu;
	ViewJouer vjouer;
	ViewParametre vpara;
	int joueurCourant;
	final int lenteurAttente = 50;
	int decompte;
 
	public ControleurMediateur(Jeu j, ViewJouer vj, ViewParametre vp) {
		jeu = j;
		vjouer = vj;
		vpara = vp;

	}


	
	public void redimensionnement() {
		vjouer.miseAJour();
	}
	public void clicSouris(double x, double y) {
		int l = (int) (y / vjouer.hauteurCase());
		int c = (int) (x / vjouer.largeurCase());
		Position posPlateau = new Position(l,c);
		Position posPiece = new Position(jeu.PosPieceL,jeu.PosPieceC);
		System.out.println("courant = " + jeu.pieceCourant);
		if (vpara.joueurs[joueurCourant].jeu(posPlateau,posPiece,jeu.pieceCourant)) {
			jeu.plateauPiece[vjouer.joueurCourant].enlevePiece(jeu.pieceCourant.getNum());
			vjouer.joueurCourant = jeu.joueurCourant;
			vjouer.miseAJour();
			changeJoueur();
			
		}
	}
	
	public void selectPiece(double x, double y) {
		int l = (int) (y / vjouer.hauteurCasePiece());
		int c = (int) (x / vjouer.largeurCasePiece());
		if(jeu.plateauPiece[jeu.joueurCourant].valeur(l,c)>=0 && vjouer.joueurCourant==jeu.joueurCourant) {
			jeu.setSelected(jeu.plateauPiece[jeu.joueurCourant].valeur(l,c));
			jeu.plateauAffiche.PlacerPiece(jeu.pieceCourant);
			vjouer.miseAJour();
		}
	}
	
	public void PieceAffiche(double x, double y) {
		int l = (int) (y / vjouer.hauteurCaseAffiche());
		int c = (int) (x / vjouer.largeurCaseAffiche());

		jeu.setPieceL(l);
		jeu.setPieceC(c);
	}

	public void initAffiche() {
		jeu.plateauAffiche.initPlateauAffiche();
		vjouer.miseAJour();
	}
	void changeJoueur() {
		joueurCourant = (joueurCourant + 1) % vpara.joueurs.length;
		decompte = lenteurAttente;
	}

       /* public boolean choisirNiveau(String niveau){
            if(!niv.equals(niveau)){
                System.out.println("Vous avez choisit " + niveau);
                niv=niveau;
            } 
            switch(niveau){
                case "Easy":return joueurs[joueurCourant].tempsEcoule();
                case "Medium":return joueurs[joueurCourant].tempsEcouleNonPertant();
                case "Hard": return joueurs[joueurCourant].tempsEcouleMinimax();
                default:return false;
            }
        }*/
       /* public void basculeIA(boolean value) {
        	jeuAutomatique = value;
        	System.out.println("jeuautomatique " + jeuAutomatique);
        	//f.changeBoutonIA(value);
            if (jeuAutomatique)
            	joueurs[1] = new JoueurIA(1, jeu);
            else
            	joueurs[1] = new JoueurHumain(1, jeu);
        }*/
        
        public void tictac() {
        	boolean b;
			if (jeu.enCours()) {
				if (decompte == 0) {
					//System.out.println("joueur courant " + joueurCourant);
					if((b = vpara.joueurs[joueurCourant].tempsEcoule())) {
						System.out.println("joueur " + joueurCourant + " " + b);
						vjouer.joueurCourant = jeu.joueurCourant;
						vjouer.miseAJour();
						changeJoueur();
					}
					else {
						System.out.println("On vous attend, joueur " + vpara.joueurs[joueurCourant].num());
						decompte = lenteurAttente;
					}
				}
				else{
					decompte--;
				}
			}
        }    
	}
        

