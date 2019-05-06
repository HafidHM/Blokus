package Modele;

public class Plateau implements PlateauInterface {
    public int[][] p;


    public Plateau (int taille){
        p = new int[taille][taille];
    }

    public int taille() {
        return p.length;
    }

    public boolean libre(int i, int j) {
        return valeur(i, j) == 0;
    }

    public int valeur(int i, int j) {
        return p[i][j];
    }

    public void newVal(int i, int j, int v){
        p[i][j] = v;
    }


}
