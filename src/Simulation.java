import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;


public class Simulation extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static int 		numSensors;
	static double 	radius;
	Interval 		region = new Interval(0.0, 1.0);
	View 			view;
	SensorNet 		net;
	Coverage 		coverage;
	ArrayList<Graphics> arrayOfDots; 
	Graphics 		g;
	String 			flag = "";
	
	
	public Simulation (String title){
        super(title);
        setSize(430, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        net = new SensorNet();
        view = new View(region, net);
      
        getContentPane().add(view);
        
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar (menuBar);
        
        final  JMenuItem  lineSim, boxSim, boxPSim;
        
        final JMenu simOptions = new JMenu("Simulation");
        simOptions.setMnemonic('S');
        simOptions.add(lineSim = new JMenuItem("Start Line Simulation", 'L'));
        simOptions.add(new JSeparator());
        simOptions.add(boxSim = new JMenuItem("Start Box Simulation", 'B'));
        simOptions.add(new JSeparator());
        simOptions.add(boxPSim = new JMenuItem("Start Box-Perimeter Simulation", 'P'));
        menuBar.add(simOptions); 
        
        
        lineSim.addActionListener(new ActionListener (){
            @Override
			public void actionPerformed(ActionEvent e) { 
            	
            	flag  = "Line";
            	
            	if(view.noteLabel != null)view.noteLabel.setVisible(false);
            	if(view.box != null)view.box.setVisible(false);
            	
            	setSize(440, 500);
            	
            	boxSim.setEnabled(false);
            	boxPSim.setEnabled(false);
            	
            	view.line = new Line(region, net);
            	view.constraints.gridx = 0;
            	view.constraints.gridy = 0;
            	view.constraints.gridwidth = 0;
            	view.constraints.gridheight = 1;
            	view.constraints.fill =  GridBagConstraints.BOTH;
            	view.constraints.insets = new Insets (2,2,2,2);
            	view.constraints.weightx = 10;
            	view.constraints.weighty = 10;
            	view.layout.setConstraints(view.line, view.constraints);
                view.add(view.line);
                getContentPane().add(view);
                
               
                
               
         } 
     });
        
        boxSim.addActionListener(new ActionListener (){
            @Override
			public void actionPerformed(ActionEvent e) { 
            	
            	flag = "Box";
            	
            	if(view.noteLabel != null)view.noteLabel.setVisible(false);
            	if(view.line != null)view.line.setVisible(false);
            	if(view.box != null)view.box.setVisible(false);
            	
            	setSize(650,700);
            	
            	boxPSim.setEnabled(false);
            	lineSim.setEnabled(false);
            	
            	view.box = new Box(net);
            	view.constraints.gridx = 0;
            	view.constraints.gridy = 0;
            	view.constraints.gridwidth = 0;
            	view.constraints.gridheight = 1;
            	view.constraints.fill =  GridBagConstraints.BOTH;
            	view.constraints.insets = new Insets (2,2,2,2);
            	view.constraints.weightx = 10;
            	view.constraints.weighty = 10;
            	view.layout.setConstraints(view.box, view.constraints);
                view.add(view.box);
                getContentPane().add(view);
               
         } 
     });
        
        boxPSim.addActionListener(new ActionListener (){
            @Override
			public void actionPerformed(ActionEvent e) { 
            	
            	flag = "Box2";
            	
            	if(view.noteLabel != null)view.noteLabel.setVisible(false);
            	if(view.box != null)view.box.setVisible(false);
            	if(view.line != null)view.line.setVisible(false);
            	
            	boxSim.setEnabled(false);
            	lineSim.setEnabled(false);
            	
            	setSize(650, 700);
            	
            	view.box = new Box(net);
            	view.constraints.gridx = 0;
            	view.constraints.gridy = 0;
            	view.constraints.gridwidth = 0;
            	view.constraints.gridheight = 1;
            	view.constraints.fill =  GridBagConstraints.BOTH;
            	view.constraints.insets = new Insets (2,2,2,2);
            	view.constraints.weightx = 10;
            	view.constraints.weighty = 10;
            	view.layout.setConstraints(view.box, view.constraints);
                view.add(view.box);
                getContentPane().add(view);
               
         } 
     });
        
        
        
        view.placeSensorsButton.addActionListener(new ActionListener (){
            @Override
            public void actionPerformed(ActionEvent ae) {
                if(ae.getActionCommand().equals("Place Sensors Randomly")){
                
                	view.placeSensorsButton.setEnabled(false);
                	numSensors = Integer.parseInt(view.sensorNoText.getText());
                	radius     = Double.parseDouble(view.sensorRangeText.getText());
                	
                	view.sensorNoText.setEditable(false);
                	view.sensorRangeText.setEditable(false);
                	
                	if(flag == "Line"){
                		
	                    net.clear();
	                    coverage = new Coverage();
	                    initRandomNetworkForLine();
	                    addCoverage();
	                 
                    
                	}
                	
                	else if (flag == "Box"){
                		net.clear();
                		coverage = new Coverage();
                		initRandomNetworkForBox();
                		addCoverage2D();
                		
                	}
                	else if (flag == "Box2"){
                		net.clear();
                		coverage = new Coverage();
                		initRandomNetworkForBox();
                		addCoverage2D();
                		
                	}
                    
                    repaint();
                }
            }
        });
        
        view.startSimButton.addActionListener(new ActionListener (){
            @Override
            public void actionPerformed(ActionEvent ae) {
            	
            	view.placeSensorsButton.setEnabled(false);
            	view.startSimButton.setEnabled(false);
            	
            	if (flag == "Line"){
            		runAndrewAlgorithm();
            		repaint();
            		JOptionPane.showMessageDialog( null, "Total Movement: " + String.format("%.02f",net.totalMovement()) + ", and Average Movement: " + 
            		String.format("%.02f",net.averageMovement()));
                	
            	}else if(flag == "Box"){
            		runAlgorithmFor2D();
            		repaint();
            		JOptionPane.showMessageDialog( null, "Total Movement: " + String.format("%.02f",net.totalMovementFor2D()) + ", and Average Movement: " + 
                    		String.format("%.02f",net.averageMovementFor2D()));
            		
            		System.out.println(hasTotalCoverage());
            	} else if(flag == "Box2"){
            		runAlgorithmFor2DP();
            		repaint();
            		JOptionPane.showMessageDialog( null, "Total Movement: " + String.format("%.02f",net.totalMovementFor2D()) + ", and Average Movement: " + 
                    		String.format("%.02f",net.averageMovementFor2D()));
            	}
            	
            	
            
            }
        
        });
            
        view.endSimButton.addActionListener(new ActionListener (){
            @Override
            public void actionPerformed(ActionEvent ae) {
            	
            	view.sensorNoText.setEditable(true);
            	view.sensorRangeText.setEditable(true);
            	boxSim.setEnabled(true);
            	boxPSim.setEnabled(true);
            	lineSim.setEnabled(true);
            	
            	view.placeSensorsButton.setEnabled(true);
            	view.startSimButton.setEnabled(true);
            	
            
            }
        
        });
	}
	
	
	
	public static void main(String args[]){
		Simulation sim = new Simulation ("The Simulation");
		sim.setVisible(true);
		
	}
	


	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	private void runAndrewAlgorithm() {
		
		int numNeeded = (int) Math.ceil(1.0/(radius * 2.0));
		
		if((numNeeded <= net.sensors.size())){
			// first we have to rearrange the sensors in ascending order ..
			rearrangeSensors(net);
			
			// then each sensor.position = previous sensor.end + L/2N
			List<Sensor> sensors = net.sensors;
			
			double newPos = radius;
			
			for(int i=0; i<sensors.size() &&  newPos < 1.0; ++i){
				
				sensors.get(i).moveTo(newPos);
				
				newPos += 2.0*radius;
			
			}
			
			
		}
		
		else{
			JOptionPane.showMessageDialog(null, "Not enough sensors. Please change either number of sensors or radius.");
			view.sensorNoText.setEditable(true);
        	view.sensorRangeText.setEditable(true);
        	/*boxSim.setEnabled(true);
        	boxPSim.setEnabled(true);
        	lineSim.setEnabled(true);*/
        	
        	view.placeSensorsButton.setEnabled(true);
        	view.startSimButton.setEnabled(true);
		}
	}

	private void rearrangeSensors(SensorNet net2) {
		for(int x = 0; x < net2.sensors.size(); x++){
			for(int y = 1; y < net2.sensors.size(); y++){
				
				if(net2.sensors.get(y-1).position > net2.sensors.get(y).position){
					
					Sensor temp = new Sensor();
					
					temp.position = net2.sensors.get(y-1).position;
					temp.radius = net2.sensors.get(y-1).radius;
					temp.getInterval().start = net2.sensors.get(y-1).getInterval().start;
					temp.getInterval().end = net2.sensors.get(y-1).getInterval().end;
					
					net2.sensors.get(y-1).position = net2.sensors.get(y).position;
					net2.sensors.get(y-1).radius = net2.sensors.get(y).radius;
					net2.sensors.get(y-1).getInterval().start = net2.sensors.get(y).getInterval().start;
					net2.sensors.get(y-1).getInterval().end = net2.sensors.get(y).getInterval().end;
					
					net2.sensors.get(y).position = temp.position;
					net2.sensors.get(y).radius = temp.radius;
					net2.sensors.get(y).getInterval().start = temp.getInterval().start;
					net2.sensors.get(y).getInterval().end = temp.getInterval().end;
					
				}
			}
		}
		
	}
	
	
	public void runAlgorithmFor2D(){
		
		int numNeeded = (int) Math.ceil(1.0/(radius * 2.0));
		
		if(numNeeded * 4 <= net.sensors2d.size()){
			System.out.println("======================");
			for(Sensor2D s: net.sensors2d){
				
				int X = (int)(s.x * Box.WIDTH);
				int Y = (int)(s.y * Box.HEIGHT);
				//System.out.println("X: " + X +", Y: " + Y);
				// left side of Rec 
				boolean TOP_RIGHT 		= (X > Y );
				boolean BOTTOM_LEFT 	= (X <= Y );
				boolean BOTTOM_RIGHT 	= (Y > Box.HEIGHT - X );
				boolean TOP_LEFT 		= (Y <= Box.HEIGHT - X );
				
				
				
				if( TOP_RIGHT && BOTTOM_RIGHT) 	{ s.x = 1.0;  }
	
				if( TOP_RIGHT && TOP_LEFT ) 	{ s.y = 0.0;  }
	
				if( TOP_LEFT && BOTTOM_LEFT) 	{ s.x = 0.0;  }
	
				if( BOTTOM_LEFT && BOTTOM_RIGHT) { s.y = 1.0;  }
				
			} 
				
				
					List<Sensor2D> sensors = net.sensors2d;
					List<Sensor2D> leftSensors = new ArrayList<Sensor2D>();
					
					double  CaseX0 = 0.0, CaseX1 = 1.0, CaseY0 = 0.0, CaseY1 = 1.0;
					double leftPos = radius, rightPos = radius, topPos = radius, bottomPos = radius;
					boolean rightConnected = false, leftConnected = false, topConnected = false, bottomConnected = false;
					
					net.printSensors2d();
					
					
					
					// left side
					int leftCounter = 0, rightCounter = 0, topCounter = 0, bottomCounter = 0;
					
					for(int i=0; i<sensors.size(); ++i){
						
						if(sensors.get(i).x == CaseX0){
							
							if( leftCounter >=  numNeeded ){
								
								leftSensors.add(sensors.get(i));
								leftConnected = true;
				
							} else {
								
								sensors.get(i).moveTo(CaseX0, leftPos);
								leftPos += 2.0*radius;
								leftCounter++;
							}
						}
					}
					
					// right side 
					//newPos = radius;
					for(int i=0; i<sensors.size(); ++i){
						
						if(sensors.get(i).x == CaseX1){
							
							if(rightCounter >= numNeeded){
								
								leftSensors.add(sensors.get(i));
								rightConnected = true;
								
							} else {
							
							sensors.get(i).moveTo(CaseX1, rightPos);
							rightPos += 2.0*radius;
							rightCounter++;
						}
						
					}
				}
					
					// top side 
					//newPos = radius;
					for(int i=0; i<sensors.size(); ++i){
						
						if(sensors.get(i).y == CaseY0){
							
							if(topCounter >= numNeeded){
								
								leftSensors.add(sensors.get(i));
								topConnected = true;
								
							}
							
							else {
							
							sensors.get(i).moveTo(topPos, CaseY0);
							topPos += 2.0*radius;
							topCounter++;
						}
					}
				}
					
					// bottom side 
				//	newPos = radius;
					for(int i=0; i<sensors.size() ; ++i){
						
						if(sensors.get(i).y == CaseY1) {
							
							if(bottomCounter >= numNeeded){
								
								leftSensors.add(sensors.get(i));
								bottomConnected = true;
							}
						
						else {
			
								sensors.get(i).moveTo(bottomPos, CaseY1);
								bottomPos += 2.0*radius;
								bottomCounter++;
						}
					}
				}
					
					// now check for other unconnected edges .. 
					
					// left side
					int counter = 0;
					for(int i=0; i<sensors.size() ; ++i){
						
						if(!leftConnected){
							if(sensors.get(i).x == CaseX0){
								
								if(sensors.get(i).y == leftPos){
									leftPos+= 2.0*radius;
									
								} else if(leftCounter < numNeeded){
									
									leftSensors.get(counter++).moveTo(CaseX0, leftPos);
									leftPos += 2.0*radius;
									leftCounter++;
									
								}	
						}// end if for the case ..
					} // end the if connection .. 
						
				}// end for loop .. 
					
					/*********************************/
					
					
					for(int i=0; i<sensors.size() ; ++i){
						
						if(!rightConnected){
							if(sensors.get(i).x == CaseX1){
								
								if(sensors.get(i).y == rightPos){
									rightPos+= 2.0*radius;
									
								} else if(rightCounter < numNeeded){
									
									leftSensors.get(counter++).moveTo(CaseX1, rightPos);
									rightPos += 2.0*radius;
									rightCounter++;
									
								}	
						}// end if for the case ..
					} // end the if connection .. 
						
				}// end for loop .. 
					
					
					for(int i=0; i<sensors.size() ; ++i){
						
						if(!topConnected){
							if(sensors.get(i).y == CaseY0){
					
								if(sensors.get(i).x == topPos){
									
									topPos += 2.0*radius;
									
									
								} else if(topCounter < numNeeded){
									
									leftSensors.get(counter++).moveTo(topPos, CaseY0);
									topPos += 2.0*radius;
									topCounter++;
									
								}	
						}// end if for the case ..
					} // end the if connection .. 
						
				}// end for loop ..
					
					
					for(int i=0; i<sensors.size() ; ++i){
						
						if(!bottomConnected){
							if(sensors.get(i).y == CaseY1){
					
								if(sensors.get(i).x == bottomPos){
									
									bottomPos += 2.0*radius;
									
									
								} else if(bottomCounter < numNeeded){
									
									
									leftSensors.get(counter++).moveTo(bottomPos, CaseY1);
									bottomPos += 2.0*radius;
									bottomCounter++;
									
								}	
						}// end if for the case ..
					} // end the if connection .. 
						
				}// end for loop ..
					
		} else{
			
			JOptionPane.showMessageDialog(null, "Not enough sensors. Please change either number of sensors or radius.");
			view.sensorNoText.setEditable(true);
        	view.sensorRangeText.setEditable(true);
        	/*boxSim.setEnabled(true);
        	boxPSim.setEnabled(true);
        	lineSim.setEnabled(true);*/
        	
        	view.placeSensorsButton.setEnabled(true);
        	view.startSimButton.setEnabled(true);
        	
		}
	}
	
	
	public void runAlgorithmFor2DP(){
		
		
		int numNeeded = (int) Math.ceil(1.0/(radius * 2.0));
		int counter = 0, bigCounter = 0;; 
		
		List<Sensor2D> sensors = net.sensors2d;
		
				double position = 0,  Y = 0.0;
				
						if(counter < sensors.size()){
						
						
						
						for(int i=0; i < sensors.size(); i++){
							
							if(bigCounter < numNeeded+1){
							
								sensors.get(i).moveTo(position, Y);
								
								position += 2 * radius;
								counter++;
								
							if(counter  == numNeeded + 1){
								position = 0;
								Y += 2 * radius;
								counter = 0;
								bigCounter++;
								
							}
						
						
								
								
							}
						}
					}
				}
	

	private Sensor2D getClosestSensor(List<Sensor2D> sensors, double position,
			double Y) {
	
		
		
		Sensor2D closestSensor = sensors.get(0);
		
		double distanceFromPosToPoint = getDistance(closestSensor.x, closestSensor.y, position, Y);
		double DistanceFromPosTo2edPoint;
		
		for(int i = 1; i < sensors.size(); i++){
			
			DistanceFromPosTo2edPoint = getDistance(sensors.get(i).x, sensors.get(i).y, position, Y);
			
			if(distanceFromPosToPoint > DistanceFromPosTo2edPoint && (sensors.get(i).alreadyPicked == false && closestSensor.alreadyPicked == false)){
			
				closestSensor = sensors.get(i);
			}
			
		}
		
		
		//System.out.println("i got picked: " + closestSensor.x + ", and my Y: " + closestSensor.y);
		return closestSensor;
	}



	private double getDistance(double x, double y, double x1, double y2) {
		
		double distX = Math.abs(x - x1);
		double distY = Math.abs(y - y2);
		
		return Math.sqrt(distX*distX + distY*distY);
	}



	private void addCoverage() {
		
		for(Sensor s : net.sensors){
			
			coverage.addInterval( s.getInterval());
			
		}
		
	}
	
	private void addCoverage2D() {
			
		for(Sensor2D s : net.sensors2d){
			
			coverage.addInterval( s.getInterval());
			
		}
		
	}

	
	public void initRandomNetworkForLine() {
		//make network have numSensors sensors each with radius radius
		//located at random position
		for (int i = 0; i<numSensors; i++)addRandomSensor();
		
	}
	public void initRandomNetworkForBox(){
		for (int i = 0; i<numSensors; i++)addRandomSensorForBox();
	}

	public boolean hasTotalCoverage(){
	
		return coverage.contains(region);
	}
	
	public boolean hasTotalCoverageFor2D(){
		// we need to take care of all intervals 
		// (0,0) -> (1,0)
		// (1,0) -> (1,1)
		// (0,1) -> (1,1)
		// (0,0) -> (0,1)
		
		return coverage.contains(region);
	}

	private void addRandomSensor() {
		// generate a random position from 0 to 1
		double pos = Math.random();
		
		//create a sensor at that position
		Sensor s = new Sensor(pos, radius);
		
		//add that sensor to the sensornet
		net.addSensor(s);
		
		
	} 
	
	private void addRandomSensorForBox(){
		// generate a random position from 0 to 1
		double x = Math.random();
		double y = Math.random();
		
		Sensor2D s = new Sensor2D(x, y, radius);
		net.addSensor2d(s);
	}
	
}
