package Modele;

public interface PlateauInterface {

    public int taille();

    public boolean jouable(int i, int j);

    public int valeur(int i, int j);

    public void newVal(int i, int j, int v);
}
