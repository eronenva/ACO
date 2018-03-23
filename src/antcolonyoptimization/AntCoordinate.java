package antcolonyoptimization;


//"Fermon och koordinaterna för myrorna", en del av delmoment C1.

public class AntCoordinate {
      
    
    int x;
    int y;
    
    int fermon = 0;
    int counter = 0;
    
   
	public AntCoordinate(int x, int y) {
		
		this.x = x;
		this.y = y;
        
	}
	
	public void addFermon() {
		counter++;
		fermon = 100;
	}
	
	public void reduceFermon() {
		
		if (fermon > 0)
		fermon = fermon - Integer.parseInt(AntColonyOptimization.fermonPercentage);
	}
	
	
	public int getFermon() {
		return fermon;
	}
	
	public int getCounter() {
		
		return counter;
	}
    
   

   
            
          

            
               
 

}






