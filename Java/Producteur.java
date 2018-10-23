import java.util.*;

public class Producteur extends Thread{

    int temps;
    File file;
    boolean interrupted = false;

    public Producteur(int temps , File file){


        this.temps = temps;
        this.file = file;
    }


    public void arreter(){interrupted = true;}

    public void run(){

        while(!interrupted){
            try{
            file.ajoute();
            file.afficheFile();
            sleep(temps);
            }catch(Exception e){}

        }

    }





}