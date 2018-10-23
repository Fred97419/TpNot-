import java.util.*;



public class File {

    private ArrayList<Integer> file = new ArrayList<Integer>();




    public static int alea(int min , int max){

        int nombreAleatoire = min + (int)(Math.random() * ((max - min) + 1));

        return (nombreAleatoire);




    }

    public synchronized void ajoute(){

        if(file.size() ==20){
            try{
            wait();
            }catch(Exception e){}
        }

        else {

            file.add(alea(0,100));
            notifyAll();

        }
        

        
}

    public synchronized void supprime(){

        if (file.isEmpty()){
            try{
            wait();
            }catch(Exception e){}
        }
        
        else {
            file.remove(0);
            notifyAll();

        }

    }
    
    public void afficheFile(){

        System.out.print("File : <");
        for (int i=0 ; i<file.size()-1 ; i++){

            System.out.print(file.get(i) + ",");


        }

        System.out.print(file.get(file.size()-1) + "<");

    }



}