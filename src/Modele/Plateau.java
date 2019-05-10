package Modele;

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
        return valeur(i, j) == 8;
    }

    public int valeur(int i, int j) {
        return p[i][j];
    }

    public void newVal(int i, int j, int v){
        p[i][j] = v;
    }

    // Marque les cases où une pièce peut être posée. // TODO // INCOMPLET : CAS OU LA PIECE EST A 1 DU BORD
    public void availableCases(int player, ArrayList<int []> coord, boolean noPieces){

        if (!noPieces) {

            for (int[] iandj : coord) { // On enlève toutes les cases disponibles du joueur précédent
                p[iandj[0]][iandj[1]] = 0;

            }
            coord.clear();

            for (int i = 1; i < taille() - 1; i++) {
                for (int j = 1; j < taille() - 1; j++) {
                    if (placeNearby(i, j, player)) {
                        p[i][j] = 8;
                        int[] c = {i, j};
                        coord.add(c);
                    }
                }
            }

        } else {
            int[] c = new int[2];
            switch (player){
                case 1:
                    p[taille()-1][taille()-1] = 0;
                    break;
                case 2:
                    coord.clear();
                    p[taille()-1][0] = 0;
                    p[0][0] = 8;

                    c[0] = 0;
                    c[1] = 0;
                    coord.add(c);
                    break;
                case 3:
                    coord.clear();
                    p[0][0] = 0;
                    p[0][taille()-1] = 8;

                    c[0] = 0;
                    c[1] = taille()-1;
                    coord.add(c);
                    break;
                case 4:
                    coord.clear();
                    p[0][taille()-1] = 0;
                    p[taille()-1][taille()-1] = 8;

                    c[0] = taille()-1;
                    c[1] = taille()-1;
                    coord.add(c);
                    break;
            }
        }

    }

    // Cherche une case de pièce sur les angles
    public boolean placeNearby(int i, int j, int player) {
        if (p[i][j] != 0 ) {
            return false;

        } else if (!caseNearby(i, j, player)){
            if((p[i+1][j+1] == player) || (p[i+1][j-1] == player) || (p[i-1][j+1] == player) || (p[i-1][j-1] == player)){
                return true;
            }
        }

        return false;
    }

    // Cherche si il y a une case libre "connectée" a une case de pièce.
    public boolean caseNearby(int i, int j, int player){

        if ((p[i+1][j] == player)  ||
                (p[i-1][j] == player) ||
                (p[i][j+1] == player) ||
                (p[i][j-1] == player)) {
            return true;
        } else {
            return false;
        }


    }

}
