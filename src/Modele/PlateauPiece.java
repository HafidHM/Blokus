package Modele;

public class PlateauPiece {
	public int[][] p;
	
	public PlateauPiece(){
        p = new int[12][23];
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
    
    public int valeur(int i, int j) {
        return p[i][j];
    }

}
