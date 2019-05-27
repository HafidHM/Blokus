package Modele;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Stack;

public class Historique implements HistoriqueInterface, Serializable{
    
    public Stack<Jeu> futur;
    public Stack<Jeu> passe;
	public Jeu mem;
    public Jeu ancien;
    String rep="historique/";
    String fichier;
	
	public Historique(Jeu j) {
            futur = new Stack<>();
            passe = new Stack<>();
            ancien = j;
	}
        
        
	@Override
	public boolean peutAnnuler() {
		return passe.size()>1;
	}


	@Override
	public boolean peutRefaire() {
		return !futur.isEmpty();
	}
        
	@Override
	public Jeu annuler() {
         if (peutAnnuler()) {
            System.out.println("Je peux annuler");
            transfert(passe, futur);
            System.out.println("passe taille " +passe.size());
            return passe.peek();
          }
         System.out.println("Je ne peux pas annuler");
          return ancien;
	}


	@Override
	public Jeu refaire() {
            if (peutRefaire()) {
            	System.out.println("Je peux Refaire ");
                transfert(futur, passe);
                System.out.println("passe taille " +passe.size());
                return passe.peek();
            }
            System.out.println("Je ne peux pas refaire");
            return ancien;		
	}


    public void transfert(Stack<Jeu> source, Stack<Jeu> destination) {
	    Jeu resultat = source.pop();
	    destination.push(resultat);
		
	}
        
        Object copyObject(Object src) {
                Object dest = null;
                try {
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    ObjectOutputStream oos = new ObjectOutputStream(bos);
                    oos.writeObject(src);
                    oos.flush();
                    oos.close();
                    bos.close();
                    byte[] byteData = bos.toByteArray();
                    ByteArrayInputStream bais = new ByteArrayInputStream(byteData);
                    try {
                        dest = new ObjectInputStream(bais).readObject();
                    } catch (ClassNotFoundException e) {
                        System.err.println("Class non trouvee");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            return dest;
        }
        
       
     public void affiche_passe(){
         System.out.println("******* Historique**********");
         for (Jeu jeu : passe) {
            jeu.affiche();
         }
        
     }

     public void affiche_futur(){
         System.out.println("******* Historique Futur**********");
         for (Jeu jeu : futur) {
             jeu.affiche();
          }
     }
	@Override
	public void add(Jeu jeu) {
		passe.push((Jeu)copyObject(jeu));
		
	}
	
	public void modify(Jeu j) {
		ancien = j;
	}

	 @Override
     public  void save(Jeu j){
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