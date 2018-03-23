/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antcolonyoptimization;

import static antcolonyoptimization.AntColonyOptimization.ants;
import static antcolonyoptimization.AntColonyOptimization.antCoordinates;

import static antcolonyoptimization.AntColonyOptimization.executor;
import static antcolonyoptimization.AntColonyOptimization.time;
import static antcolonyoptimization.AntColonyOptimization.xPos;
import static antcolonyoptimization.AntColonyOptimization.yPos;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.Executors;

//Delmoment A och B lösta i denna fil.
public class parseTxtFiles {

    static ArrayList<String> files = new ArrayList<>();
    

public static void readDirectory() throws FileNotFoundException, IOException {
	
	String path = new File (".").getCanonicalPath();
	File dir = new File (path + "\\src\\ants\\"+buttonFrame.selected);
    try{
        for (File file : dir.listFiles()) {
        
        Scanner s = new Scanner(file);
        
        files = new ArrayList(Arrays.asList(dir.list()));
        executor  = Executors.newFixedThreadPool(files.size());
       
        s.close();
  
        }
    }catch(NullPointerException ec){
        System.out.println(" You did not choose quick enough, try again");
        System.exit(0);
    }

    //Creates all coordinates and adds them to the HashMap.
    for (int i = 0; i < getXSizeInt()+5; i++) {
    	
    	for (int j = 0; j < getYSizeInt()+5; j++) {
	    	AntCoordinate test = new AntCoordinate(i,j);
	    	antCoordinates.put(Integer.toString(i)+","+Integer.toString(j), test);
    	}
    
    }           
}

//Used for getting the size of the colonies
public static int getXSizeInt() {
	
	 String txt;
	 txt = files.get(0);
	 String[] words = txt.split("\\-");
	 String size = words[1];
	 String[] a = size.split("x");
	 String xSize = a[0];
	 
	 int xSizeInt = Integer.parseInt(xSize);
	 return xSizeInt;
}
//Used for getting the size of the colonies
public static int getYSizeInt() {
	
	  String txt;
	  txt = files.get(0);
	  String[] words = txt.split("\\-");
	  String size = words[1];
	  String[] a = size.split("x");
	  String ySize = a[1];
	 
	  int ySizeInt = Integer.parseInt(ySize);
	  return ySizeInt;
}
      
//Adds all the ants to an ArrayList and then starts a new thread for all the ants.
public static void parse(int k) throws FileNotFoundException, IOException, InterruptedException {
    
      String strLine;
      String[] temp = null;
      String timeValue = "";
      String yPosValue = "";
      String xPosValue = "";

      String path = new File (".").getCanonicalPath();
      FileInputStream fstream = new FileInputStream(path + "\\src\\ants\\"+buttonFrame.selected+"\\" +files.get(k));
      DataInputStream in = new DataInputStream(fstream);
      BufferedReader br = new BufferedReader(new InputStreamReader(in));
      
      ArrayList<Double> tempTime = (ArrayList<Double>)time.clone();
      ArrayList<Double> tempXpos = (ArrayList<Double>)xPos.clone();
      ArrayList<Double> tempYpos = (ArrayList<Double>)yPos.clone();
           
	   while((strLine = br.readLine()) != null){
	
	   temp = strLine.split("\\s+");
	
	   timeValue = temp[temp.length-3];
	   tempTime.add(new Double(timeValue));
	
	   xPosValue = temp[temp.length-2];
	   tempXpos.add(new Double(xPosValue));
	    
	   yPosValue = temp[temp.length-1];
	   tempYpos.add(new Double(yPosValue));
	
	   }
	   br.close();
    
       Ant ant = new Ant(k ,tempTime, tempXpos , tempYpos); 

       ants.add(ant);
        
       executor.submit(new Ant(k ,tempTime, tempXpos , tempYpos));
        
       time.clear();
       xPos.clear();
       yPos.clear();

   }

}
