import java.util.*;



public class File {

    private ArrayList<Integer> file = new ArrayList<Integer>();




    public static int alea(int min , int max){

        int nombreAleatoire = min + (int)(Math.random() * ((max - min) + 1));

        return (nombreAleatoire);




    }

    public synchronized String ajoute(){
        int nb_alea = alea(0,100);
        String resultat="";

        if(file.size() ==20){
            try{
            wait();
            }catch(Exception e){}
        }

        else {

            file.add(nb_alea);
            notifyAll();
            resultat="Ajout de l'entier "+nb_alea+" ...";
            return resultat;
            

        }

        return resultat;
        

        
}

    public synchronized String supprime(){
        String resultat="";

        if (file.isEmpty()){
            try{
            wait();
            }catch(Exception e){}
        }
        
        else {
            file.remove(0);
            notifyAll();
            resultat = "Retrait de "+file.get(0)+"effectue ! ";
            return resultat;


        }
        return resultat;

    }
    
   

    public String fileToString(){

        String fileString="";

        fileString+= "File : <";
        for (int i=0 ; i<file.size()-1 ; i++){

            fileString += (file.get(i)).toString() + "," ;


        }

        fileString+= (file.get(file.size()-1)).toString() + "<" ;
        
        
        return fileString;
        
    }



}