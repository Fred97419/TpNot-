import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;


public class Exo2_3 extends JFrame {

        //------------------[PRODUCTEURS]-------------------------//

        public class Producteur extends Thread{

            int temps;
            File file;
            boolean interrupted = false;
            String texte;
        
            public Producteur(int temps , File file){
        
        
                this.temps = temps;
                this.file = file;
            }
        
        
            public void arreter(){interrupted = true;}
        
            public void run(){
        
                while(!interrupted){
                    try{
                    texte = file.ajoute();
                    
                    sleep(temps);
                    }catch(Exception e){}
        
                }
        
            }
            public String getText(){return texte;}
        }
    
        //------------------------------------------------//
    
        //--------------[CONSOMMATEUR]-------------------//
    
        public class Consommateur extends Thread{
    
            int temps;
            File file;
            String texte;
            boolean interrupted = false;
        
            public Consommateur(int temps , File file){
        
        
                this.temps = temps;
                this.file = file;
            }
        
        
            public void arreter(){interrupted = true;}
        
            public void run(){
        
                while(!interrupted){
                    try{
                    texte = file.supprime();
                    
                    sleep(temps);
                    }catch(Exception e){}
        
                }
        
            }

            public String getText(){return texte;}
        }

        //--------------------------------------------------------------------//


       protected JPanel panel ;
       protected JLabel file_text;
       protected JLabel producteur;

       
       protected JLabel c1;
       protected JLabel c2;
       protected JButton start;
       
       protected File file;

       

       protected Producteur prod;
       protected Consommateur cons1;
       protected Consommateur cons2;

       protected boolean started;
       

    public Exo2_3(){

        setTitle("Exo File");
        setSize(800, 500);



        panel = new JPanel();
        file_text = new JLabel();
        producteur = new JLabel();
        c1 = new JLabel();
        c2 = new JLabel();
        start = new JButton("Start");

        start.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
              
                file = new File();
                prod = new Producteur(100,file);
                cons1 = new Consommateur(400,file);
                cons2 = new Consommateur(400, file);

                started = true;
                prod.setDaemon(true);
                cons1.setDaemon(true);
                cons2.setDaemon(true);
                prod.start();
                cons1.start();
                cons2.start();

              
            } 
      });

        panel.setLayout(new GridLayout(6, 1));
		panel.add(file_text);
        panel.add(producteur);
        panel.add(c1);
        panel.add(c2);
        panel.add(start);

        
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setContentPane(panel);            
        setVisible(true);

        started = false;


    
  }

  public void update(){
		if(started)
		{
			file_text.setText(file.fileToString());
            producteur.setText(prod.getText());
            c1.setText(cons1.getText());
            c2.setText(cons2.getText());
            
			
		}
	}








    






    public static void main (String[] args){


        Exo2_3 exo = new Exo2_3();

        while(true)
		{
			exo.update();
			try{Thread.sleep(200);}catch(Exception e){System.out.println(e.toString());}
		}



    }
    









}