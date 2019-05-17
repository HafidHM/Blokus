package Vue;
import static blokus.Framework.app;

import Controleur.ControleurMediateur;
import Modele.Jeu;
import Modele.Plateau;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.input.MouseDragEvent;
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
	public int joueurCourant = jeu.joueurCourant;
	
	private Button jouerBtn;
	private Button quitBtn;
	private Button miroirBtn;
	private Button inversBtn;
	private Button joueur0;
	private Button joueur1;
	private Button joueur2;
	private Button joueur3;
	
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
		ViewParametre vp = (ViewParametre) app.getView("Parametre");
		ControleurMediateur c = new ControleurMediateur(jeu,this,vp);
		canPlateau = new Canvas(450, 400);
		panePlateau = new AnchorPane(canPlateau);
		panePlateau.setPrefSize(450, 400);
		
		jouerBtn = new Button("Jouer");
		jouerBtn.setOnAction((event)->{
			jeu.enCours = true;
		});
        quitBtn = new Button("Quit");
        quitBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Dialog<ButtonType> dialog = new Dialog<ButtonType>();
				dialog.getDialogPane().getButtonTypes().add(ButtonType.YES);
				dialog.getDialogPane().getButtonTypes().add(ButtonType.NO);
				Button yes = (Button)dialog.getDialogPane().lookupButton(ButtonType.YES);
				yes.setText("Oui");
				Button no = (Button)dialog.getDialogPane().lookupButton(ButtonType.NO);
				no.setText("Non");
				
				yes.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						app.exit();
						
					}
				});
				no.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						dialog.close();
						
					}
				});
				
				dialog.setContentText("Voulez vous terminer le jeu ?");
				dialog.show();
				
			}
		});
        miroirBtn = new Button("Miroir");
        miroirBtn.setOnAction((event)->{
        	jeu.plateauAffiche.Miroir();
        	jeu.pieceCourant.Miroir();
        	jeu.pieceCourant.AffichePiece();
        	miseAJour();
        });
        
        inversBtn = new Button("Inverser");
        inversBtn.setOnAction((event)->{
        	jeu.plateauAffiche.retationGauche();
        	jeu.pieceCourant.retationGauche();
        	jeu.pieceCourant.AffichePiece();
        	miseAJour();
        });
        
        HBox actionBtn = new HBox();
        actionBtn.getChildren().addAll(miroirBtn,inversBtn);
        actionBtn.setSpacing(20);
        actionBtn.setAlignment(Pos.CENTER);
        
        HBox JoueurBtn = new HBox();
        joueur0 = new Button("Joueur0");
        joueur0.setOnAction((event)->{
        	joueurCourant = 0;
        	miseAJour();
        });
        joueur1 = new Button("Joueur1");
        joueur1.setOnAction((event)->{
        	joueurCourant = 1;
        	miseAJour();
        });
        joueur2 = new Button("Joueur2");
        joueur2.setOnAction((event)->{
        	joueurCourant = 2;
        	miseAJour();
        });
        joueur3 = new Button("Joueur3");
        joueur3.setOnAction((event)->{
        	joueurCourant = 3;
        	miseAJour();
        });
        JoueurBtn.getChildren().addAll(joueur0,joueur1,joueur2,joueur3);
        
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
        gPiece.getChildren().addAll(JoueurBtn,panePiece);
        gAffiche.getChildren().addAll(paneAffiche,actionBtn,jouerBtn,quitBtn);
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
				try {
					c.initAffiche();
					c.clicSouris(e.getX(), e.getY());
					jeu.pieceCourant = null;
				}catch(ArrayIndexOutOfBoundsException exception) {
					System.out.println("select une piece!");
				}catch(NullPointerException exception) {
					System.out.println("select une piece!");
				}
			}
		});
        
        canPiece.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent e) {
				c.selectPiece(e.getX(), e.getY());
			}
		});
        
        canAffiche.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent e) {
				c.PieceAffiche(e.getX(), e.getY());
			}
		});

        canAffiche.setOnDragDetected(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent e) {
				canAffiche.startFullDrag();
				System.out.println("detect");
				
			}
		});
        
        canAffiche.setOnMouseDragEntered(new EventHandler<MouseDragEvent>() {

			@Override
			public void handle(MouseDragEvent e) {
				System.out.println("drag");
				c.PieceAffiche(e.getX(), e.getY());
				
			}
		});
        
        canPlateau.setOnMouseDragReleased(new EventHandler<MouseDragEvent>() {

			@Override
			public void handle(MouseDragEvent e) {
				try {
					System.out.println("released");
					c.initAffiche();
					c.clicSouris(e.getX(), e.getY());
					jeu.pieceCourant = null;
				}catch(ArrayIndexOutOfBoundsException exception) {
					System.out.println("select une piece!");
				}catch(NullPointerException exception) {
					System.out.println("select une piece!");
				}
				
				
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
	            draw(hauteurCase, largeurCase, plateau, gPlateau);
	            
	            //canPiece
	            largeurCasePiece = largeurPiece() / 23;
	            hauteurCasePiece = hauteurPiece() / 12;
	            
	            GraphicsContext gPiece = canPiece.getGraphicsContext2D();
	            line(gPiece,largeurPiece(),hauteurPiece());
	            draw(hauteurCasePiece, largeurCasePiece, plateauPiece[joueurCourant], gPiece);
	            
	
	            //canAffiche
	            largeurCaseAffiche = largeurAffiche() / 5;
	            hauteurCaseAffiche = hauteurAffiche() / 5;
	            GraphicsContext gAffiche = canAffiche.getGraphicsContext2D();
	            
	            line(gAffiche,largeurAffiche(),hauteurAffiche());
	            draw(hauteurCaseAffiche, largeurCaseAffiche, plateauAffiche, gAffiche);
	            
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
	void line(GraphicsContext g, double largeur, double hauteur) {
		g.clearRect(0, 0, largeur, hauteur);
        
        g.strokeLine(0, 0, 0, hauteur);
        g.strokeLine(0, 0, largeur, 0);
        g.strokeLine(largeur, 0, largeur, hauteur);
        g.strokeLine(0, hauteur, largeur, hauteur);
	}
	void draw(double hauteur, double largeur, Plateau p, GraphicsContext g) {
		Color color = null;
		
		for (int i=0; i<p.p.length; i++) {
            for (int j=0; j<p.p[0].length; j++) {
               if(p==plateau) {
            	   switch (p.valeur(i, j)) {
	            	   case -1:
	                       break;
	                   case 0:
	                       g.setFill(Color.DARKOLIVEGREEN);
	                       g.fillRect(j*largeurCase, i*hauteurCase, largeurCase, hauteurCase);
	                       break;
	                   case 1:
	                       g.setFill(Color.DARKSLATEBLUE);
	                       g.fillRect(j*largeurCase, i*hauteurCase, largeurCase, hauteurCase);
	                       break;
	                   case 2:
	                       g.setFill(Color.YELLOW);
	                       g.fillRect(j*largeurCase, i*hauteurCase, largeurCase, hauteurCase);
	                       break;
	                   case 3:
	                       g.setFill(Color.INDIANRED);
	                       g.fillRect(j*largeurCase, i*hauteurCase, largeurCase, hauteurCase);
	                       break;
	                   case 8:
	                       g.setFill(Color.LIGHTGREEN);
	                       g.fillRect(j*largeurCase, i*hauteurCase, largeurCase, hauteurCase);
	                       break;

            	   }
               }
               if(p==plateauPiece[joueurCourant]) {
            	   switch (joueurCourant) {
		       	   		case 0:
		       	   			color = Color.DARKOLIVEGREEN;
		       	   			break;
		       	   		case 1:
		       	            color = Color.DARKSLATEBLUE;
		       	            break;
		       	        case 2:
		       	            color = Color.YELLOW;
		       	            break;
		       	        case 3:
		       	             color = Color.INDIANRED;
		       	            break;
		       		}
            	   switch (p.valeur(i, j)) {
            	   		case -1:
            	   			break;
            	   		case -2:
		            	   	g.setFill(Color.LIGHTGREY);
		            	   	g.fillRect(j*largeur, i*hauteur, largeur, hauteur);
		            	   	g.strokeLine(j*largeur, i*hauteur, (j+1)*largeur, i*hauteur);
			           	   	g.strokeLine(j*largeur, i*hauteur, j*largeur, (i+1)*hauteur);
			           	   	g.strokeLine(j*largeur, (i+1)*hauteur, (j+1)*largeur, (i+1)*hauteur);
			           	   	g.strokeLine((j+1)*largeur, i*hauteur, (j+1)*largeur, (i+1)*hauteur);
			           	   	break;
            	   		default:
            	   			g.setFill(color);
		            	   	g.fillRect(j*largeur, i*hauteur, largeur, hauteur);
		            	   	g.strokeLine(j*largeur, i*hauteur, (j+1)*largeur, i*hauteur);
			           	   	g.strokeLine(j*largeur, i*hauteur, j*largeur, (i+1)*hauteur);
			           	   	g.strokeLine(j*largeur, (i+1)*hauteur, (j+1)*largeur, (i+1)*hauteur);
			           	   	g.strokeLine((j+1)*largeur, i*hauteur, (j+1)*largeur, (i+1)*hauteur);
			           	   	break;
		            	   	
            	   }     
            	    
               }
               if(p==plateauAffiche && p.valeurB(i, j)) {
            	   switch (jeu.joueurCourant) {
		       	   		case 0:
		       	   			color = Color.DARKOLIVEGREEN;
		       	   			break;
		       	   		case 1:
		       	            color = Color.DARKSLATEBLUE;
		       	            break;
		       	        case 2:
		       	            color = Color.YELLOW;
		       	            break;
		       	        case 3:
		       	             color = Color.INDIANRED;
		       	            break;
		       		}
            	    g.setFill(color);
	           	   	g.fillRect(j*largeur, i*hauteur, largeur, hauteur);
	           	   	g.strokeLine(j*largeur, i*hauteur, (j+1)*largeur, i*hauteur);
	           	   	g.strokeLine(j*largeur, i*hauteur, j*largeur, (i+1)*hauteur);
	           	   	g.strokeLine(j*largeur, (i+1)*hauteur, (j+1)*largeur, (i+1)*hauteur);
	           	   	g.strokeLine((j+1)*largeur, i*hauteur, (j+1)*largeur, (i+1)*hauteur);
               }
            }
        }
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
