
package antcolonyoptimization;

import static antcolonyoptimization.AntColonyOptimization.antCoordinates;
import static antcolonyoptimization.AntColonyOptimization.grafik;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;


public class Ant implements Runnable  {
    int id;
    ArrayList<Double> time = new ArrayList<>();
    ArrayList<Double> xPos = new ArrayList<>();
    ArrayList<Double> yPos = new ArrayList<>();
    public int[][] matrix = new int[45][35];
          
    int xInt = 0;
    int yInt = 0;    
    
    public Ant(int id, ArrayList<Double> time , ArrayList<Double> xPos, ArrayList<Double> yPos) {
          
        this.id = id+1;
        this.time = time;
        this.xPos = xPos;
        this.yPos = yPos;

    }
    
    public ArrayList<Double> getxPos() {
     
        return this.xPos;
    }

    @Override
    public void run() {
                       
        Graphics g = grafik.image.getGraphics();
        g.setColor(Color.white);
        
        //Loop through all ant-files
                    
        for (int x = 0; x < time.size(); x++) {
                
            xInt = xPos.get(x).intValue();
            yInt = yPos.get(x).intValue();
	            
	        //Draw the paths for the ant in question  
            if (x >0) {
            g.drawLine(xPos.get(x).intValue()*10, yPos.get(x).intValue()*10, xPos.get(x-1).intValue()*10, yPos.get(x-1).intValue()*10);
	            }
            g.drawRect(xPos.get(x).intValue()*10, yPos.get(x).intValue()*10, 1, 1);
	            
            //This second for-loop adds fermon to the coordinate it currently stands on, and removes one unit of fermon for the other coordinates.          
	        for (String key : antCoordinates.keySet()) {     
	 		   
	 		   if (key.equals(Integer.toString(xInt)+","+Integer.toString(yInt))) {
	 			  antCoordinates.get(key).addFermon();
	 		   }
	 		   if (!key.equals(Integer.toString(xInt)+","+Integer.toString(yInt))) {
	 		   antCoordinates.get(key).reduceFermon();
 		   }
        }
    }
        //Vårt försök att få opacityn att ändra beroende på fermonvärdet
                /*for (String key : antCoordinates.keySet()) {
                	
                	
                   if (key.equals(Integer.toString(xInt)+","+Integer.toString(yInt))) {
                	   antCoordinates.get(Integer.toString(xInt)+","+Integer.toString(yInt)).addFermon();
                	   System.out.println("Fermon value for "+ Integer.toString(xInt)+","+Integer.toString(yInt)+ "is: " +antCoordinates.get(Integer.toString(xInt)+","+Integer.toString(yInt)).getFermon());
                	   g.setColor(Color.WHITE);
                	   g.drawRect(xPos.get(x).intValue()*10, yPos.get(x).intValue()*10, 1, 1);
                	   
                	  
                	   
                   } else {
                	   
                	   
            	   }
                   
                   }
                     if (antCoordinates.get(Integer.toString(xInt)+","+Integer.toString(yInt)).getFermon() == 0) {
                   		
                   		g.setColor(Color.BLACK);
                   		
                   	  } else {
                   	  Color color = new Color(0,0,0, antCoordinates.get(Integer.toString(xInt)+","+Integer.toString(yInt)).getFermon()/100);
                   	  g.setColor(color);
                   	  
                   	  }
                      grafik.canvas.repaint();
                   	  g.drawRect(xPos.get(x).intValue()*10, yPos.get(x).intValue()*10, 1, 1);
                   	 
                  
                   
                }*/
                
        grafik.canvas.repaint();
                     
        g.dispose();
                       
    }

}





