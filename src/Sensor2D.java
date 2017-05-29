
public class Sensor2D {

	//need to keep track a location
		double x = 0, y = 0;
		
		//has a range/radius
		double radius;
		double distanceTraveled;
		boolean alreadyPicked = false;

		public Sensor2D(){
			x = 0;
			y = 0;
			radius = 0;
			distanceTraveled = 0;
			alreadyPicked = false;
		}
		public Sensor2D(double X,double Y, double r){
			x = X;
			y = Y;
			radius = r;
			distanceTraveled = 0;
		}

		public Interval getInterval(){

			return new Interval(x, y);

		} 

		public String toString(){

			return String.format("Sensor At pos: (" +  (int)(x*Box.WIDTH) + "," + (int)(y*Box.HEIGHT) + ")");
		}
		
		public void moveTo(double newX, double newY){
			
			double distX = Math.abs(x - newX);
			double distY = Math.abs(y - newY);
			
			// we always need to save the distance traveled ..
			distanceTraveled += Math.sqrt(distX*distX + distY*distY);
			
			x = newX;
			y = newY;
			
		}
	
}
