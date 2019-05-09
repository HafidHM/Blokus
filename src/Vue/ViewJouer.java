package Vue;
import static blokus.Framework.app;

import Controleur.ControleurMediateur;
import Modele.Jeu;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class ViewJouer extends View {
	
	public ViewJouer(Jeu j) {
		super(j);
	}

	private Button quitBtn;
	private Canvas canPlateau;
	private Canvas canPiece;
	private Canvas canAffiche;
	private AnchorPane panePlateau;
	private AnchorPane panePiece;
	private AnchorPane paneAffiche;
	
	@Override
	public void onLaunch() {	
		ControleurMediateur c = new ControleurMediateur(jeu,this);
		canPlateau = new Canvas(450,400);
		panePlateau = new AnchorPane(canPlateau);
		
        quitBtn = new Button("Quit");
        quitBtn.setLayoutX(150);
        quitBtn.setLayoutY(550);
        quitBtn.setOnAction((event)->{
			app.exit();
		}); 
        
        canPiece = new Canvas(560,180);
        panePiece = new AnchorPane(canPiece);
        
        canAffiche = new Canvas(200,200);
        paneAffiche = new AnchorPane(canAffiche);
        paneAffiche.setLayoutY(100);
        
        Group gPlateau = new Group();
        Group gPiece = new Group();
        Group gAffiche = new Group();
        

        gPlateau.getChildren().add(panePlateau);
        gAffiche.getChildren().addAll(quitBtn, paneAffiche);
        gPiece.getChildren().add(panePiece);
   
       
        
        AnchorPane.setTopAnchor(gPlateau, (double) 10);
        AnchorPane.setLeftAnchor(gPlateau, (double) 20);

        
        AnchorPane.setTopAnchor(gPiece, 430.0);
        AnchorPane.setLeftAnchor(gPiece, 10.0);
        AnchorPane.setBottomAnchor(gPiece, 10.0);

       // AnchorPane.setTopAnchor(gAffiche, 10.0);
        AnchorPane.setLeftAnchor(gAffiche, 480.0);
        AnchorPane.setRightAnchor(gAffiche, 10.0);
        AnchorPane.setBottomAnchor(gAffiche, 10.0);
        
        getChildren().addAll(gPlateau,gPiece,gAffiche);
        
        
		
        jeu.ajouteObservateur(this);
        miseAJour();
        
        canPlateau.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent e) {
				c.clicSouris(e.getX(), e.getY());
				System.out.println("X = " + e.getX() + " " + " Y = " + e.getY());
			}
		});
        
        
        
        new AnimationTimer() {
			@Override
			public void handle(long now) {
				c.tictac();
			}
		}.start();
	}
	
	

	
	@Override 
        public void miseAJour() {
            double lignes = plateau.taille();
            double colonnes = plateau.taille();
            largeurCase = largeurPlateau() / colonnes;
            hauteurCase = hauteurPlateau() / lignes;

            GraphicsContext gPlateau = canPlateau.getGraphicsContext2D();
            gPlateau.clearRect(0, 0, largeurPlateau(), hauteurPlateau());
            // Grille
            for (int i=0; i<lignes;i++) {
                for (int j=0; j<colonnes;j++) {
                    gPlateau.setFill(Color.LIGHTGRAY);
                    gPlateau.fillRect(j*largeurCase, i*hauteurCase, largeurCase, hauteurCase);

                }
            }
            for (int i=0; i<lignes+1;i++) {
                gPlateau.strokeLine(0, i*hauteurCase, largeurPlateau(), i*hauteurCase);
            }
            for (int i=0; i<colonnes+1;i++) {
                gPlateau.strokeLine(i*largeurCase, 0, i*largeurCase, hauteurPlateau());
            }
            // Coups
            for (int i=0; i<lignes; i++)
                for (int j=0; j<colonnes; j++)
                    switch (plateau.valeur(i, j)) {
                        case 0:
                            break;
                        case 1:
                            gPlateau.setFill(Color.DARKOLIVEGREEN);
                            gPlateau.fillRect(j*largeurCase, i*hauteurCase, largeurCase, hauteurCase);
                            break;
                        case 2:
                            gPlateau.setFill(Color.DARKSLATEBLUE);
                            gPlateau.fillRect(j*largeurCase, i*hauteurCase, largeurCase, hauteurCase);
                            break;
                        case 3:
                            gPlateau.setFill(Color.YELLOW);
                            gPlateau.fillRect(j*largeurCase, i*hauteurCase, largeurCase, hauteurCase);
                            break;
                        case 4:
                            gPlateau.setFill(Color.INDIANRED);
                            gPlateau.fillRect(j*largeurCase, i*hauteurCase, largeurCase, hauteurCase);
                            break;
                        case 8:
                            gPlateau.setFill(Color.LIGHTGREEN);
                            gPlateau.fillRect(j*largeurCase, i*hauteurCase, largeurCase, hauteurCase);
                            break;
                    }
            
            //canPiece
            GraphicsContext gPiece = canPiece.getGraphicsContext2D();
            gPiece.clearRect(0, 0, largeurPiece(), hauteurPiece());
           
            gPiece.strokeLine(0, 0, 0, hauteurPiece());
            gPiece.strokeLine(0, 0, largeurPiece(), 0);
            gPiece.strokeLine(largeurPiece(), 0, largeurPiece(), hauteurPiece());
            gPiece.strokeLine(0, hauteurPiece(), largeurPiece(), hauteurPiece());

            //canAffiche
            GraphicsContext gAffiche = canAffiche.getGraphicsContext2D();
            gAffiche.clearRect(0, 0, largeurPlateau(), hauteurPlateau());
            
            gAffiche.strokeLine(0, 0, 0, hauteurAffiche());
            gAffiche.strokeLine(0, 0, largeurAffiche(), 0);
            gAffiche.strokeLine(largeurAffiche(), 0, largeurAffiche(), hauteurAffiche());
            gAffiche.strokeLine(0, hauteurAffiche(), largeurAffiche(), hauteurAffiche());
	}


	
	double largeurPlateau() {
		return canPlateau.getWidth();
	}

	double hauteurPlateau() {
		return canPlateau.getHeight();
	}
	
	double largeurPiece() {
		return canPiece.getWidth();
	}

	double hauteurPiece() {
		return canPiece.getHeight();
	}
	
	double largeurAffiche() {
		return canAffiche.getWidth();
	}

	double hauteurAffiche() {
		return canAffiche.getHeight();
	}

	public double largeurCase() {
		
		return largeurCase;
	}

	public double hauteurCase() {
		
		return hauteurCase;
	}

	
}
