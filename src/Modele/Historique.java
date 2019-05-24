package Modele;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Stack;

public class Historique implements HistoriqueInterface, Serializable{
    
           public Stack<Jeu> futur;
           public Stack<Jeu> passe;
	   public Jeu mem;
           
           String rep="historique/";
           String fichier;
	
	public Historique() {
            futur = new Stack<>();
            passe = new Stack<>();
            fichier="";
	}
        
        
        @Override
        public  void save(Jeu j,String nomFic){
                fichier = nomFic;
                FileOutputStream fileOut;
                try {
                      fileOut = new FileOutputStream(fichier);
                      ObjectOutputStream out = new ObjectOutputStream(fileOut);
                      out.writeObject(j);
                      out.close();
                      fileOut.close();
                      System.out.println("Enregistrer une partie");

                } catch (FileNotFoundException e) {
                       System.err.println("Le fichier "+fichier+" n'existe pas");
                } catch (IOException e) {
                        System.err.println("Probleme d'enregistrement des donnees");
                }  
        }
 
        @Override
        public  Jeu load(String fichier){
                fichier = rep+ fichier;
                Jeu  obj = null;
                try {
                        FileInputStream fileIn = new FileInputStream(fichier);
                        ObjectInputStream in = new ObjectInputStream(fileIn);
                        obj = (Jeu) in.readObject();
                        System.out.println("Charger les parties du fichier "+ fichier);

                } catch (FileNotFoundException e) {
                        System.err.println("Le fichier "+fichier+"n'existe pas");
                } catch (IOException e) {
                        System.err.println("Probleme du chargement des donnees");
                } catch (ClassNotFoundException e) {
                        System.err.println("Class non trouvee");
                }
                return obj;
        }

           
}