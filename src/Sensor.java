//it represents the sensors
public class Sensor {

	//need to keep track a location
	double position;
	
	//has a range/radius
	double radius;
	double distanceTraveled;

	public Sensor(){
		position = 0;
		radius = 0;
		distanceTraveled = 0;
	}
	public Sensor(double p, double r){
		position = p;
		radius = r;
		distanceTraveled = 0;
	}

	public Interval getInterval(){

		return new Interval(position-radius, position+radius);

	}

	public String toString(){

		return String.format("Sensor [@: %.2f, %s ]", position, getInterval());
	}
	
	public void moveTo(double newPosition){
		
		// we always need to save the distance traveled ..
		distanceTraveled += Math.abs(position - newPosition);
		
		position = newPosition;
		
		}
	
	
	
	//////////////////// test ////////////////////////
	public static void main(String args[]){
		System.out.println("Sensor: ");
		double start= 0.5;
		double radius = 0.2;
		
		
		Sensor s = new Sensor(start, radius);
		System.out.println(s);
		
		s.moveTo(.3);
		System.out.println(s.distanceTraveled);
		
		
	}
	
}
