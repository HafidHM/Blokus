package Vue;
import static blokus.Framework.app;

import Controleur.ControleurMediateur;
import Modele.Jeu;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ViewJouer extends View {
	
	public ViewJouer(Jeu j) {
		super(j);
	}
	double largeurCase, hauteurCase;
	double largeurCasePiece, hauteurCasePiece;
	double largeurCaseAffiche, hauteurCaseAffiche;
	
	private Button quitBtn;
	private Button miroirBtn;
	private Button inversBtn;
	
	private Canvas canPlateau;
	private Canvas canPiece;
	private Canvas canAffiche;
	
	private AnchorPane panePlateau;
	private AnchorPane panePiece;
	private AnchorPane paneAffiche;
	
	VBox gPlateau ;
    VBox gAffiche ;
    VBox gPiece;
	
	@Override
	public void onLaunch() {	
		ControleurMediateur c = new ControleurMediateur(jeu,this);
		canPlateau = new Canvas(450, 400);
		panePlateau = new AnchorPane(canPlateau);
		panePlateau.setPrefSize(450, 400);
		
        quitBtn = new Button("Quit");
        quitBtn.setOnAction((event)->{
			app.exit();
		}); 
        miroirBtn = new Button("Miroir");
        miroirBtn.setOnAction((event)->{
        	jeu.plateauAffiche.Miroir();
        	jeu.choixPiece(jeu.pieceCourant).Miroir();
        	miseAJour();
        });
        inversBtn = new Button("Inverser");
        inversBtn.setOnAction((event)->{
        	jeu.plateauAffiche.retationGauche();
        	jeu.choixPiece(jeu.pieceCourant).retationGauche();
        	miseAJour();
        });
        HBox actionBtn = new HBox();
        actionBtn.getChildren().addAll(miroirBtn,inversBtn);
        actionBtn.setSpacing(20);
        actionBtn.setAlignment(Pos.CENTER);
        
        canPiece = new Canvas(450, 200);
        panePiece = new AnchorPane(canPiece);
        panePiece.setPrefSize(450, 200);
        
        canAffiche = new Canvas(200,200);
        paneAffiche = new AnchorPane(canAffiche);
        paneAffiche.setPrefSize(200,200);
        
        gPlateau = new VBox();
        gPiece = new VBox();
        gAffiche = new VBox();
       
        gPlateau.getChildren().add(panePlateau);
        gPiece.getChildren().add(panePiece);
        gAffiche.getChildren().addAll(paneAffiche,actionBtn,quitBtn);
        gAffiche.setSpacing(20);
        gAffiche.setAlignment(Pos.CENTER);
        
        getPane().setPadding(new Insets(10));
        
        getPane().setLeft(gPlateau);
        getPane().setRight(gAffiche);
        getPane().setBottom(gPiece);

        jeu.ajouteObservateur(this);
        miseAJour();
       
   	 	
        canPlateau.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				c.initAffiche();
				c.clicSouris(e.getX(), e.getY());
				System.out.println("X = " + e.getX() + " " + " Y = " + e.getY());
			}
		});
        
        canPiece.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent e) {
				c.selectPiece(e.getX(), e.getY());
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
	            largeurCasePiece = largeurPiece() / 23;
	            hauteurCasePiece = hauteurPiece() / 12;
	            
	            GraphicsContext gPiece = canPiece.getGraphicsContext2D();
	            gPiece.clearRect(0, 0, largeurPiece(), hauteurPiece());
	            System.out.println("larPiece = " + largeurPiece());
	            gPiece.strokeLine(0, 0, 0, hauteurPiece());
	            gPiece.strokeLine(0, 0, largeurPiece(), 0);
	            gPiece.strokeLine(largeurPiece(), 0, largeurPiece(), hauteurPiece());
	            gPiece.strokeLine(0, hauteurPiece(), largeurPiece(), hauteurPiece());
	            /*for (int i=0; i<13;i++) {
	                gPiece.strokeLine(0, i*hauteurCasePiece, largeurPiece(), i*hauteurCasePiece);
	            }
	            for (int i=0; i<24;i++) {
	                gPiece.strokeLine(i*largeurCasePiece, 0, i*largeurCasePiece, hauteurPiece());
	            }*/
	            System.out.println("(3,15) = " + plateauPiece.valeur(3, 15));
	            for (int i=0; i<12; i++) {
	                for (int j=0; j<23; j++) {
	                   if(plateauPiece.valeur(i, j)!=0) {
	                	   	gPiece.setFill(Color.RED);
	                	   	gPiece.fillRect(j*largeurCasePiece, i*hauteurCasePiece, largeurCasePiece, hauteurCasePiece);
	                	   	gPiece.strokeLine(j*largeurCasePiece, i*hauteurCasePiece, (j+1)*largeurCasePiece, i*hauteurCasePiece);
	                	   	gPiece.strokeLine(j*largeurCasePiece, i*hauteurCasePiece, j*largeurCasePiece, (i+1)*hauteurCasePiece);
	                	   	gPiece.strokeLine(j*largeurCasePiece, (i+1)*hauteurCasePiece, (j+1)*largeurCasePiece, (i+1)*hauteurCasePiece);
	                	   	gPiece.strokeLine((j+1)*largeurCasePiece, i*hauteurCasePiece, (j+1)*largeurCasePiece, (i+1)*hauteurCasePiece);
	                   }
	                }
	            }
	            
	
	            //canAffiche
	            largeurCaseAffiche = largeurAffiche() / 5;
	            hauteurCaseAffiche = hauteurAffiche() / 5;
	            GraphicsContext gAffiche = canAffiche.getGraphicsContext2D();
	            gAffiche.clearRect(0, 0, largeurPlateau(), hauteurPlateau());
	            
	            gAffiche.strokeLine(0, 0, 0, hauteurAffiche());
	            gAffiche.strokeLine(0, 0, largeurAffiche(), 0);
	            gAffiche.strokeLine(largeurAffiche(), 0, largeurAffiche(), hauteurAffiche());
	            gAffiche.strokeLine(0, hauteurAffiche(), largeurAffiche(), hauteurAffiche());
	            
	            /*for (int i=0; i<6;i++) {
	                gAffiche.strokeLine(0, i*hauteurCaseAffiche, largeurAffiche(), i*hauteurCaseAffiche);
	            }
	            for (int i=0; i<6;i++) {
	                gAffiche.strokeLine(i*largeurCaseAffiche, 0, i*largeurCaseAffiche, hauteurAffiche());
	            }*/
	            
	            for (int i=0; i<5; i++) {
	                for (int j=0; j<5; j++) {
	                   if(plateauAffiche.valeur(i, j)) {
	                	   	gAffiche.setFill(Color.RED);
	                	   	gAffiche.fillRect(j*largeurCaseAffiche, i*hauteurCaseAffiche, largeurCaseAffiche, hauteurCaseAffiche);
	                	   	gAffiche.strokeLine(j*largeurCaseAffiche, i*hauteurCaseAffiche, (j+1)*largeurCaseAffiche, i*hauteurCaseAffiche);
	                	   	gAffiche.strokeLine(j*largeurCaseAffiche, i*hauteurCaseAffiche, j*largeurCaseAffiche, (i+1)*hauteurCaseAffiche);
	                	   	gAffiche.strokeLine(j*largeurCaseAffiche, (i+1)*hauteurCaseAffiche, (j+1)*largeurCaseAffiche, (i+1)*hauteurCaseAffiche);
	                	   	gAffiche.strokeLine((j+1)*largeurCaseAffiche, i*hauteurCaseAffiche, (j+1)*largeurCaseAffiche, (i+1)*hauteurCaseAffiche);
	                   }
	                }
	            }
	}

	/*@Override
	public void redimension() {
		System.out.println("redimensionner");
        
        InvalidationListener listener = new InvalidationListener() {
			@Override
			public void invalidated(Observable observable) {
				miseAJour();	
			}
		};
        	 canAffiche.widthProperty().addListener(listener);
        	 canAffiche.heightProperty().addListener(listener); 
        	 canPiece.widthProperty().addListener(listener);
        	 canPiece.heightProperty().addListener(listener); 
        	 canPlateau.widthProperty().addListener(listener);
        	 canPlateau.heightProperty().addListener(listener); 
	}*/
	
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

	public double largeurCasePiece() {
		return largeurCasePiece;
	}

	public double hauteurCasePiece() {
		return hauteurCasePiece;
	}
	
	public double largeurCaseAffiche() {
		return largeurCaseAffiche;
	}

	public double hauteurCaseAffiche() {
		return hauteurCaseAffiche;
	}
	
}
