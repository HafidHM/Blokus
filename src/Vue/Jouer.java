package Vue;
import static blokus.Framework.app;

import Modele.Jeu;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

public class ViewJouer extends View {
	public ViewJouer(Jeu j) {
		super(j);
	}

	private Button quitBtn;
	private Canvas canvas;
	private StackPane pane;
	
	@Override
	public void onLaunch() {	
		setPrefSize(800, 600);
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
        double lignes = jeu.hauteur();
        double colonnes = jeu.largeur();
        largeurCase = largeur() / colonnes;
        hauteurCase = hauteur() / lignes;
        largeurCase = Math.max(largeurCase,hauteurCase);
        hauteurCase = Math.max(largeurCase,hauteurCase);
        System.out.println("lig = " + lignes + "col = " + colonnes);
        System.out.println("lar = " + largeur() + "hau = " + hauteur());
        System.out.println("ligC = " + largeurCase+ "colC = " + hauteurCase);
        GraphicsContext g = canvas.getGraphicsContext2D();
        g.clearRect(0, 0, largeur(), hauteur());
        // Grille
        for (int i=0; i<lignes+1;i++) {
            g.strokeLine(0, i*hauteurCase, largeur(), i*hauteurCase);
        }
        for (int i=0; i<colonnes+1;i++) {
            g.strokeLine(i*largeurCase, 0, i*largeurCase, hauteur());
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
