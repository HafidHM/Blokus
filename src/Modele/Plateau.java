package Controleur.Modele;

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
        return valeur(i, j) == 8 ||valeur(i, j) == -1;
    }
    public int valeur(int i, int j) {
        return p[i][j];
    }
    public void newVal(int i, int j, int v){
        p[i][j] = v;
    }
    public void availableCases(int player, ArrayList<Position> coord, boolean noPieces){
        if (!noPieces) {
            for (Position pos : coord) {
                if (p[pos.l][pos.c] == 8)
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
            switch (player){
                case 0:
                    break;
                case 1:
                    p[0][0] = 8;
                    break;
                case 2:
                    p[0][taille()-1] = 8;
                    break;
                case 3:
                    p[taille()-1][taille()-1] = 8;
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
