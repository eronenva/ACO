package antcolonyoptimization;

import static antcolonyoptimization.parseTxtFiles.files;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class AntColonyOptimization  {
    
    static ArrayList<Ant> ants = new ArrayList<>();
    static ArrayList<Double> time = new ArrayList<>();
    static ArrayList<Double> xPos = new ArrayList<>();
    static ArrayList<Double> yPos = new ArrayList<>();
    static HashMap<String, AntCoordinate> antCoordinates = new HashMap <String, AntCoordinate>();
    public static ExecutorService executor;
    public static Grafik grafik = new Grafik();
    public static String fermonPercentage;
    
    //Main class
    public static void main(String[] args) throws IOException, InterruptedException {
  	
        buttonFrame buttonFrame = new buttonFrame();
        buttonFrame.setVisible(true);
        
        //Quick and dirty fix - we didn't have time to investigate in how to keep the main thread waiting for the radio buttons.
        //Therefore the 10 second time limit for choosing wanted simulation.
        System.out.println("You've got 10 seconds to make a choice!");
        sleep(10000);
        
        JFrame frame = new JFrame();
        fermonPercentage = JOptionPane.showInputDialog(
            frame, 
            "Ange med hur många procent fermonet skall avta för varje tick. Skriv in t.ex. '1' eller '2'", 
            
            JOptionPane.WARNING_MESSAGE
        );

        //Read all the files for chosen ant colony.
        parseTxtFiles.readDirectory();
        
        //Draw the canvas
        grafik.drawGrafik();
    
        //Loop through all the files that belong to the wanted colony and start the threads.
        for(int k = 0; k < files.size(); k++){
            parseTxtFiles.parse(k);
        }
       
        executor.shutdown();
        
        //Quick and dirty fix - we noticed too late that we constructed the threads in a stupid way...
        System.out.println("Now we wait 20sec to make sure everything is ready in order to show the results");
        sleep(20000);
        
        
        //Print the results (note that for the large colonies all results might not show in console...) This happens at least in Eclipse (Console doesn't fit all results)
        for (int i = 0; i < parseTxtFiles.getXSizeInt()+1; i++) {
        	
        	for (int j = 0; j < parseTxtFiles.getYSizeInt()+1; j++) {
        		System.out.println("Fermon value for coordinate" + Integer.toString(i)+","+ Integer.toString(j) + " is: " + antCoordinates.get(Integer.toString(i)+","+ Integer.toString(j)).getFermon()+"% and has been visited: "+ antCoordinates.get(Integer.toString(i)+","+ Integer.toString(j)).getCounter()+" times");	    	
		    }
		    
		}
        
    }
}
    

