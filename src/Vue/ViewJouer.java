package Vue;
import static blokus.Framework.app;

import Modele.Jeu;
import Modele.Plateau;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class ViewJouer extends View {
	public ViewJouer(Jeu j, Plateau plateau) {
		super(j,plateau);
	}

	private Button quitBtn;
	private Canvas canvas;
	private StackPane pane;
	
	@Override
	public void onLaunch() {	
		setPrefSize(900, 800);
		canvas = new Canvas(400,400);
		pane = new StackPane(canvas);
        quitBtn = new Button("Quit");
        quitBtn.setOnAction((event)->{
			app.exit();
		}); 

        /*setTopAnchor(pane, hauteurCase);
        setLeftAnchor(pane, hauteurCase);
        setLeftAnchor(quitBtn,750);
        setTopAnchor(quitBtn, 550);*/
        getChildren().add(pane);
        getChildren().add(quitBtn);
        miseAJour();
	}
	
	@Override 
        public void miseAJour() {
            double lignes = plateau.taille();
            double colonnes = plateau.taille();
            largeurCase = largeur() / colonnes;
            hauteurCase = hauteur() / lignes;
            GraphicsContext g = canvas.getGraphicsContext2D();
            g.clearRect(0, 0, largeur(), hauteur());
            // Grille
            for (int i=0; i<lignes;i++) {
                for (int j=0; j<colonnes;j++) {
                    g.setFill(Color.LIGHTGRAY);
                    g.fillRect(j*largeurCase, i*hauteurCase, largeurCase, hauteurCase);

                }
            }
            for (int i=1; i<lignes;i++) {
                g.strokeLine(0, i*hauteurCase, largeur(), i*hauteurCase);
            }
            for (int i=1; i<colonnes+1;i++) {
                g.strokeLine(i*largeurCase, 0, i*largeurCase, hauteur());
            }
            // Coups
            for (int i=0; i<lignes; i++)
                for (int j=0; j<colonnes; j++)
                    switch (plateau.valeur(i, j)) {
                        case 0:
                            break;
                        case 1:
                            g.setFill(Color.DARKOLIVEGREEN);
                            g.fillRect(j*largeurCase, i*hauteurCase, largeurCase, hauteurCase);
                            break;
                        case 2:
                            g.setFill(Color.DARKSLATEBLUE);
                            g.fillRect(j*largeurCase, i*hauteurCase, largeurCase, hauteurCase);
                            break;
                        case 3:
                            g.setFill(Color.YELLOW);
                            g.fillRect(j*largeurCase, i*hauteurCase, largeurCase, hauteurCase);
                            break;
                        case 4:
                            g.setFill(Color.INDIANRED);
                            g.fillRect(j*largeurCase, i*hauteurCase, largeurCase, hauteurCase);
                            break;
                        case 8:
                            g.setFill(Color.LIGHTGREEN);
                            g.fillRect(j*largeurCase, i*hauteurCase, largeurCase, hauteurCase);
                            break;
                    }
	}


	
	double largeur() {
		return canvas.getWidth();
	}

	double hauteur() {
		return canvas.getHeight();
	}

	public double largeurCase() {
		return largeurCase;
	}

	public double hauteurCase() {
		return hauteurCase;
	}
	
}
