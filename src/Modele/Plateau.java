package Modele;

import javafx.geometry.Pos;

import java.util.ArrayList;

public class Plateau implements PlateauInterface {
    public int[][] p;


    public Plateau (int taille){
        p = new int[taille][taille];
    }

    public int taille() {
        return p.length;
    }

    public boolean jouable(int i, int j) {
        return valeur(i, j) == 8 ||  valeur(i, j) == 0;
    }

    public int valeur(int i, int j) {
        return p[i][j];
    }

    public void newVal(int i, int j, int v){
        p[i][j] = v;
    }

    // Marque les cases où une pièce peut être posée. // TODO // INCOMPLET : CAS OU LA PIECE EST A 1 DU BORD
    public void availableCases(int player, ArrayList<Position> coord, boolean noPieces){

        if (!noPieces) {

            for (Position pos : coord) { // On enlève toutes les cases disponibles du joueur précédent
                p[pos.l][pos.c] = -1;

            }
            coord.clear();

            for (int i = 0; i < taille(); i++) {
                for (int j = 0; j < taille(); j++) {
                    if (placeNearby(i, j, player)) {
                        p[i][j] = 8;
                        Position pos = new Position(i, j);
                        coord.add(pos);
                    }
                }
            }

        } else {
            Position pos;
            switch (player){
                case 0:
                    p[taille()-1][taille()-1] = -1;
                    break;
                case 1:
                    coord.clear();
                    p[taille()-1][0] = -1;
                    p[0][0] = 8;

                    pos = new Position(0, 0);
                    coord.add(pos);
                    break;
                case 2:
                    coord.clear();
                    p[0][0] = -1;
                    p[0][taille()-1] = 8;

                    pos = new Position(0, taille()-1);
                    coord.add(pos);
                    break;
                case 3:
                    coord.clear();
                    p[0][taille()-1] = -1;
                    p[taille()-1][taille()-1] = 8;

                    pos = new Position(taille()-1, taille()-1);
                    coord.add(pos);
                    break;
            }
        }

    }

    public boolean placeNearby(int i, int j, int player) {
        if (p[i][j]!=-1)
            return false;
        else if(caseNearby(i,j,player))
            return false;
        else{
            boolean res=false;
            if(!(horsBord(new Position(i+1,j+1)))) res=res||p[i+1][j+1] == player;
            if(!(horsBord(new Position(i+1,j-1)))) res=res||p[i+1][j-1] == player;
            if(!(horsBord(new Position(i-1,j+1)))) res=res||p[i-1][j+1] == player;
            if(!(horsBord(new Position(i-1,j-1)))) res=res||p[i-1][j-1] == player;
            return res;
        }
    }
    public boolean caseNearby(int i, int j, int player){
        boolean res = false;
        if(!horsBord(new Position(i+1,j))) res = res || (p[i+1][j] == player);
        if(!horsBord(new Position(i-1,j))) res = res || (p[i-1][j] == player);
        if(!horsBord(new Position(i,j+1))) res = res || (p[i][j+1] == player);
        if(!horsBord(new Position(i,j-1))) res = res || (p[i][j-1] == player);
        return res;
    }

    public boolean horsBord(Position p){

        return p.l<0 || p.l>=taille() || p.c<0 || p.c>=taille();
    }

}
