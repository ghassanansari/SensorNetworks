import java.util.ArrayList;


//represent a collection of sensors
//along with useful methods dealing with the sensors
public class SensorNet {

		ArrayList<Sensor> sensors;
		ArrayList<Sensor2D> sensors2d;
		
		
		public SensorNet(){
			sensors = new ArrayList<Sensor>();
			sensors2d = new ArrayList<Sensor2D>();
		}
		
		public String toString(){
			String output = "";
			if(sensors.size()<1)output =  "SensorNet: " + sensors.size() + " sensors";  
			else output =  "SensorNet: " + sensors2d.size() + " sensors"; 
			return output;
		}

		public void addSensor(Sensor s) {
			// create a sensor at random position and add it to the sensor network
			sensors.add(s);
			
		}
		
		public void addSensor2d(Sensor2D s) {
			// create a sensor at random position and add it to the sensor network
			sensors2d.add(s);
			
		}
		
		public void printSensors(){
			
			for(Sensor s : sensors){
				System.out.println(s);
			}
			
		}
		
		public void printSensors2d(){
			
			for(Sensor2D s : sensors2d){
				System.out.println(s);
			}
			
		}

		public void clear() {
			// TODO Auto-generated method stub
			sensors.clear();
			sensors2d.clear();
		}
		
		public double totalMovementFor2D(){
			
			double total = 0;
			
			for(Sensor2D s: sensors2d){
				total += s.distanceTraveled;
			}
			return total;
		}
		
		
		public double averageMovementFor2D(){
			
			return totalMovementFor2D() / sensors2d.size();
		}
		
		
		///////////////////////
		public double totalMovement(){
			
			double total = 0;
			
			for(Sensor s: sensors){
				total += s.distanceTraveled;
			}
			return total;
		}
		public double averageMovement(){
			
			return totalMovement() / sensors.size();
		}
	
	
}
