package Controleur;

import Modele.Historique;
import Modele.Jeu;
import Modele.Position;
import Vue.ViewJouer;
import Vue.ViewParametre;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

public class ControleurMediateur {
    Jeu jeu;
    ViewJouer vjouer;
    ViewParametre vpara;
    public int joueurCourant;
    final int lenteurAttente = 50;
    int decompte;
    Historique h;
    boolean refaire = false;
    public ControleurMediateur(Jeu j, ViewJouer vj, ViewParametre vp) {
        jeu = j;
        vjouer = vj;
        vpara = vp;
        h = new Historique(jeu);
        h.add(jeu);
    }

    public void setDimension(int n) {
		jeu = new Jeu(n);
		vjouer.modify(jeu);
		vpara.modify(jeu);
		for(int i=0;i<4;i++) {
			vpara.joueurs[i].modify(jeu);
		}
		vjouer.onLaunch();

	}

    public void annuler() {
		jeu = h.annuler();
		h.modify(jeu);
		vjouer.modify(jeu);
		vpara.modify(jeu);
		for(int i=0;i<4;i++) {
			vpara.joueurs[i].modify(jeu);
		}
		h.affiche_passe();
		h.affiche_futur();
		joueurCourant = (joueurCourant - 1) % vpara.joueurs.length;
		vjouer.miseAJour();
	}
    
    public void refaire() {
		jeu = h.refaire();
		h.modify(jeu);
		vjouer.modify(jeu);
		vpara.modify(jeu);
		for(int i=0;i<4;i++) {
			vpara.joueurs[i].modify(jeu);
		}
		h.affiche_passe();
		h.affiche_futur();
		joueurCourant = (joueurCourant + 1) % vpara.joueurs.length;
		vjouer.miseAJour();
	}
    
    public void modifScore(int nb) {
        vjouer.Score.getChildren().clear();
        if(nb == 4)
            vjouer.Score.getChildren().addAll(vjouer.joueur_score[0],vjouer.joueur_score[1],vjouer.joueur_score[2],vjouer.joueur_score[3]);
        else if(nb == 2)
            vjouer.Score.getChildren().addAll(vjouer.joueur_score[0],vjouer.joueur_score[1]);
    }

    public void setNom(TextField t,int order) {
        t.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                t.setText(newValue);
                vpara.nom[order] = newValue;
            }
        });
    }

    public void valide(int order) {
        vjouer.joueur[order].setText(vpara.nom[order]);
        vjouer.joueur_score[order].setText(vpara.nom[order]+ ": " + jeu.Score[order]);
        if(vpara.nbJoueur==2) {
            vjouer.joueur[order+2].setText(vpara.nom[order]);
            vjouer.joueur_score[order].setText(vpara.nom[order]+ ": " + (jeu.Score[order]+jeu.Score[order+2]));
        }

    }
    public void redimensionnement() {
        vjouer.miseAJour();
    }

    public void clicSouris(double x, double y) {
        int l = (int) (y / vjouer.hauteurCase());
        int c = (int) (x / vjouer.largeurCase());

        Jeu j = jeu;
        System.out.println("lPlateau = " + l);
        System.out.println("cPlateau = " + c);
        Position posPlateau = new Position(l,c);
        Position posPiece = new Position(jeu.PosPieceL,jeu.PosPieceC);
        System.out.println("pieceCorant " + jeu.pieceCourant.getNum());
        if (vpara.joueurs[joueurCourant].jeu(posPlateau,posPiece,jeu.pieceCourant)) {
            jeu.plateauPiece[vjouer.joueurCourant].enlevePiece(jeu.pieceCourant.getNum());
            vjouer.joueurCourant = jeu.joueurCourant;
            	h.add(j);
            h.affiche_passe();
            h.affiche_futur();
            vjouer.miseAJour();
            changeJoueur();
            
        }
        refaire = false;
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

        System.out.println("lAffiche = " + l);
        System.out.println("cAffiche = " + c);
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

    public boolean choisirNiveau(String niveau){
        switch(niveau){
            case "Robot simple": return vpara.joueurs[joueurCourant].tempsEcoule();
            case "Robot Intelligent": return vpara.joueurs[joueurCourant].tempsEcouleNonPerdant();
            case "Robot Excellent": return vpara.joueurs[joueurCourant].tempsEcouleMinimax();
            default: return false;
        }
    }

    public void tictac() {
        Jeu j = jeu;

        if (jeu.enCours()) {
            if (decompte == 0) {
                if( choisirNiveau(vpara.dif[joueurCourant])) {//num() pour joueurCourant change
                    vjouer.joueurCourant = jeu.joueurCourant;
                    h.add(j);
                    vjouer.miseAJour();
                    changeJoueur();
                }
                else {
                    if(vpara.joueurs[joueurCourant].jeu.enCoursJ[joueurCourant]) {
                        System.out.println("On vous attend, joueur " + joueurCourant);
                        decompte = lenteurAttente;
                    }
                    else{
                        vpara.joueurs[joueurCourant].jeu.setupNextJoueur();
                        vjouer.joueurCourant = jeu.joueurCourant;
                        vjouer.miseAJour();
                        changeJoueur();
                    }

                }
            }
            else{
                decompte--;
            }
        }
    }
}
