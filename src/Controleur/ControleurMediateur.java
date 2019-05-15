package Controleur;

import Modele.Jeu;
import Modele.Piece;
import Modele.Position;
import Vue.ViewJouer;
import Vue.ViewParametre;

public class ControleurMediateur {
	Jeu jeu;
	ViewJouer vjouer;
	ViewParametre vPara;
	public Joueur[] joueurs;
    boolean jeuAutomatique;
	int joueurCourant;
	final int lenteurAttente = 50;
	int decompte;
    String niv;
	public ControleurMediateur(Jeu j, ViewJouer vj, ViewParametre vp) {
		jeu = j;
		vjouer = vj;
		vPara = vp;
        jeuAutomatique = true;
		joueurs = new Joueur[4];
		joueurs[0] = new JoueurHumain(0, jeu);
		niv = "Easy";
		if (jeuAutomatique) {
			joueurs[1] = new JoueurIA(1, jeu);
			joueurs[2] = new JoueurIA(2, jeu);
			joueurs[3] = new JoueurIA(3, jeu);
		} else {
			joueurs[1] = new JoueurHumain(1, jeu);
			joueurs[2] = new JoueurHumain(2, jeu);
			joueurs[3] = new JoueurHumain(3, jeu);
		}
	}
	
	public void setHumain(int order) {
		joueurs[order] = new JoueurHumain(order, jeu); 
		System.out.println("joueur " + order + " est humain");
	}
	
	public void setIA(int order) {
		joueurs[order] = new JoueurIA(order, jeu); 
		System.out.println("joueur " + order + " est IA");
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
		if (joueurs[joueurCourant].jeu(posPlateau,posPiece,jeu.pieceCourant)) {
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
		joueurCourant = (joueurCourant + 1) % joueurs.length;
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
        public void basculeIA(boolean value) {
        	jeuAutomatique = value;
        	System.out.println("jeuautomatique " + jeuAutomatique);
        	//f.changeBoutonIA(value);
            if (jeuAutomatique)
            	joueurs[1] = new JoueurIA(1, jeu);
            else
            	joueurs[1] = new JoueurHumain(1, jeu);
        }
        
        public void tictac() {
			if (jeu.enCours()) {
				if (decompte == 0) {
					System.out.println("joueur courant " + joueurCourant);
					System.out.println("joueurs[joueurCourant].tempsEcoule()" + joueurs[joueurCourant].tempsEcoule());
					if(joueurs[joueurCourant].tempsEcoule()) 
						changeJoueur();
					else {
						System.out.println("On vous attend, joueur " + joueurs[joueurCourant].num());
						decompte = lenteurAttente;
					}
				}
				else{
					decompte--;
				}
			}
        }    
	}
        

