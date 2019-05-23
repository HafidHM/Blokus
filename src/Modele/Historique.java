package Modele;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Stack;

import javafx.scene.control.Tab;

public class Historique implements HistoriqueInterface{
    
           public Stack<Jeu> futur;
           public Stack<Jeu> passe;
	       public Jeu mem;
           String rep="historique/";
           String fichier;
	
	public Historique(String fic) {
            futur = new Stack<>();
            passe = new Stack<>();
            mem = null;
            fichier=fic;
	}

        public void setMem(Jeu m) {
            mem = m;
            /* 
                if(mem.getClass().getSimpleName().toString().compareTo("Jeu")==0) {
					mem=(E)copy((Jeu)mem);
		}
             */
        }
        
        
	
	@Override
	public boolean peutAnnuler() {
		return !passe.isEmpty();
	}


	@Override
	public boolean peutRefaire() {
		return !futur.isEmpty();
	}
        
     public Jeu transfert(Stack<Jeu> source, Stack<Jeu> destination) {
	    Jeu resultat = source.pop();
		destination.push(resultat);
		return resultat;
	 }


	@Override
	public Jeu annuler() {
            if (peutAnnuler()) {
            	System.out.println("Je peux annuler");
                Jeu resultat = transfert(passe, futur);
                mem = resultat;
                return resultat;
            }
            return null;
	}


	@Override
	public Jeu refaire() {
            if (peutRefaire()) {
            	System.out.println("Je peux Refaire ");
                Jeu resultat = transfert(futur, passe);
                mem = resultat;
                return resultat;
            }
            return null;		
	}
       
      /*  
        public  void save(){
                FileOutputStream fileOut;
                try {
                      fileOut = new FileOutputStream(fichier);
                      ObjectOutputStream out = new ObjectOutputStream(fileOut);
                      out.writeObject(mem);
                      out.close();
                      fileOut.close();
                      System.out.println("Enregistrer une partie");

                } catch (FileNotFoundException e) {
                       System.err.println("Le fichier "+fichier+"n'existe pas");
                } catch (IOException e) {
                        System.err.println("Probleme d'enregistrement des donnees");
                }  
        }
 
        
        public  E load(String fichier){
                fichier = rep+ fichier;
                E  obj = null;
                try {
                        FileInputStream fileIn = new FileInputStream(fichier);
                        ObjectInputStream in = new ObjectInputStream(fileIn);
                        obj = (E) in.readObject();
                        System.out.println("Charger les parties du fichier "+ fichier);

                } catch (FileNotFoundException e) {
                        System.err.println("Le fichier "+fichier+"n'existe pas");
                } catch (IOException e) {
                        System.err.println("Probleme du chargement des donnees");
                } catch (ClassNotFoundException e) {
                        System.err.println("Class non trouvee");
                }
                this.mem = (E) obj;
                return obj;
        }
       */ 
        public void add(Jeu jeu){
        	passe.push((Jeu)copyObject(jeu));
        }
        
        private Object copyObject(Object objSource) {
                Object objDest = null;
                try {
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    ObjectOutputStream oos = new ObjectOutputStream(bos);

                    oos.writeObject(objSource);
                    oos.flush();
                    oos.close();
                    bos.close();
                    byte[] byteData = bos.toByteArray();
                    ByteArrayInputStream bais = new ByteArrayInputStream(byteData);
                    try {
                        objDest = new ObjectInputStream(bais).readObject();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            return objDest;
        }
     
           
}