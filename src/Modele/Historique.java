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
	
	public Historique(String fic) {
            futur = new Stack<>();
            passe = new Stack<>();
            mem = null;
            fichier=fic;
	}
        
        
	@Override
	public boolean peutAnnuler() {
		return !(passe.isEmpty()&&passe.size()<2);
	}


	@Override
	public boolean peutRefaire() {
		return !futur.isEmpty();
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
       
 
        public void add(Jeu jeu){
        	passe.push((Jeu)copyObject(jeu));
        }

        public Jeu transfert(Stack<Jeu> source, Stack<Jeu> destination) {
	    Jeu resultat = source.pop();
		destination.push(resultat);
		return destination.peek();
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
        
        public void setMem(Jeu m) {
            mem = m;
            /* 
                if(mem.getClass().getSimpleName().toString().compareTo("Jeu")==0) {
					mem=(E)copy((Jeu)mem);
		}
             */
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
     public void affiche(){
         System.err.println("******* Historique**********");
         for (Jeu jeu : passe) {
            jeu.affiche();
         }
     }
           
}