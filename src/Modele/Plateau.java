package Modele;

import java.util.ArrayList;

public class Plateau implements PlateauInterface {
    public int[][] p;
    public boolean[][]pB;

    public Plateau (int largeur, int hauteur){
        p = new int[largeur][hauteur];
        pB = new boolean[largeur][hauteur];
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
            Position pos;
            switch (player){
                case 0:
                    pos = new Position(taille()-1, 0);
                    coord.add(pos);
                    break;
                case 1:
                    p[0][0] = 8;

                    pos = new Position(0, 0);
                    coord.add(pos);
                    break;
                case 2:
                    p[0][taille()-1] = 8;

                    pos = new Position(0, taille()-1);
                    coord.add(pos);
                    break;
                case 3:
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

    public void initPlateauPiece() {
        for(int i =0;i<p.length;i++) {
            for(int j = 0;j<p[0].length;j++) {
                p[i][j]=-1;
            }
        }
        p[0][3]=0;

        p[0][7]=1;
        p[0][8]=1;

        p[0][12]=2;
        p[0][13]=2;
        p[1][13]=2;

        p[0][17]=3;
        p[0][18]=3;
        p[0][19]=3;

        p[2][0]=4;
        p[2][1]=4;
        p[3][0]=4;
        p[3][1]=4;

        p[2][5]=5;
        p[3][4]=5;
        p[3][5]=5;
        p[3][6]=5;

        p[3][9]=6;
        p[3][10]=6;
        p[3][11]=6;
        p[3][12]=6;

        p[3][15]=7;
        p[3][16]=7;
        p[3][17]=7;
        p[2][17]=7;

        p[3][20]=8;
        p[3][21]=8;
        p[2][21]=8;
        p[2][22]=8;

        p[6][0]=9;
        p[7][0]=9;
        p[7][1]=9;
        p[7][2]=9;
        p[7][3]=9;

        p[7][5]=10;
        p[7][6]=10;
        p[7][7]=10;
        p[6][6]=10;
        p[5][6]=10;

        p[7][9]=11;
        p[7][10]=11;
        p[7][11]=11;
        p[6][9]=11;
        p[5][9]=11;

        p[7][13]=12;
        p[7][14]=12;
        p[6][14]=12;
        p[6][15]=12;
        p[6][16]=12;

        p[6][18]=13;
        p[6][19]=13;
        p[6][20]=13;
        p[5][20]=13;
        p[7][18]=13;

        p[5][22]=14;
        p[6][22]=14;
        p[7][22]=14;
        p[8][22]=14;
        p[9][22]=14;

        p[9][0]=15;
        p[10][0]=15;
        p[11][0]=15;
        p[10][1]=15;
        p[11][1]=15;

        p[9][4]=16;
        p[9][5]=16;
        p[10][3]=16;
        p[10][4]=16;
        p[11][3]=16;

        p[9][7]=17;
        p[9][8]=17;
        p[10][7]=17;
        p[11][7]=17;
        p[11][8]=17;

        p[9][12]=18;
        p[9][13]=18;
        p[10][11]=18;
        p[10][12]=18;
        p[11][12]=18;

        p[9][16]=19;
        p[10][15]=19;
        p[10][16]=19;
        p[10][17]=19;
        p[11][16]=19;

        p[11][19]=20;
        p[11][20]=20;
        p[11][21]=20;
        p[11][22]=20;
        p[10][20]=20;


    }

    public void enlevePiece(int num) {
        for(int i =0;i<p.length;i++) {
            for(int j = 0;j<p[0].length;j++) {
                if(p[i][j]==num)
                    p[i][j]=-2;
            }
        }
    }

    public void initPlateauAffiche() {
        for(int i =0;i<pB.length;i++) {
            for(int j = 0;j<pB[0].length;j++) {
                pB[i][j]=false;
            }
        }
    }

    public void AffichePlateauP() {
        System.out.println("Afficher PlateauPiece");
        for(int i =0;i<p.length;i++) {
            for(int j = 0;j<p[0].length;j++) {
                System.out.print(p[i][j] + " ");
            }
            System.out.println();
        }
    }
    public void AffichePlateau() {
        System.out.println("Afficher PlateauAffiche");
        for(int i =0;i<pB.length;i++) {
            for(int j = 0;j<pB[0].length;j++) {
                if(pB[i][j]==false)
                    System.out.print("0");
                else
                    System.out.print("1");
            }
            System.out.println();
        }
    }
    public void PlacerPiece(Piece piece) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                pB[i][j] = piece.carres [i][j];
            }
        }
    }

    public boolean valeurB(int i, int j) {
        return pB[i][j];
    }

    public void setPiece(boolean [][] carres) {
        this.pB = carres;
    }

    public void retationGauche() {
        boolean[][] carres = new boolean[5][5];

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                carres [i][j] = pB[j][5-i-1];
            }
        }
        setPiece(carres);
    }

    public void Miroir() {

        boolean[][] carres = new boolean[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                carres[i][j] = pB[i][5- j - 1];
            }
        }
        setPiece(carres);
    }

}
