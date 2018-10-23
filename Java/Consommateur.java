import java.util.*;

public class Conssomateur extends Thread{

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
            file.supprime();
            file.afficheFile();
            sleep(temps);
            }catch(Exception e){}

        }

    }





}