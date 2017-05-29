import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class View extends JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton           placeSensorsButton, startSimButton, endSimButton;
    JLabel            sensorNoLabel, sensorRangeLabel, noteLabel;
    JTextField        sensorNoText, sensorRangeText;
    JFrame  		  frame;
    Line 			  line;
    Interval          r;
    SensorNet 		  n;
    GridBagConstraints  constraints;
    GridBagLayout  layout;
    Box            box;
   
    View (Interval region, SensorNet net){
    	r = region;
    	n = net;
    	
    	this.setPreferredSize(new Dimension(100, 100));
	    this.setVisible(true);
	    
        layout = new GridBagLayout (); 
        constraints = new GridBagConstraints ();
        setLayout(layout);
    
    // creating the placeSensorsButton JButton ..
    placeSensorsButton = new JButton ("Place Sensors Randomly");
    constraints.gridx = 1;
    constraints.gridy = 5;
    constraints.gridwidth = 1;
    constraints.gridheight = 1;
    constraints.fill =  GridBagConstraints.BOTH;
    constraints.insets = new Insets (10, 10, 0, 10);
    constraints.weightx = 0;
    constraints.weighty = 0;
    layout.setConstraints(placeSensorsButton, constraints);
    add(placeSensorsButton);
    
 // creating the startSimButton JButton ..
    startSimButton = new JButton ("Start Simulation");
    constraints.gridx = 1;
    constraints.gridy = 6;
    constraints.gridwidth = 1;
    constraints.gridheight = 1;
    constraints.fill =  GridBagConstraints.BOTH;
    constraints.insets = new Insets (10, 10, 0, 10);
    constraints.weightx = 0;
    constraints.weighty = 0;
    layout.setConstraints(startSimButton, constraints);
    add(startSimButton);
    
 // creating the endSimButton JButton ..
    endSimButton = new JButton ("End Simulation");
    constraints.gridx = 1;
    constraints.gridy = 7;
    constraints.gridwidth = 1;
    constraints.gridheight = 1;
    constraints.fill =  GridBagConstraints.BOTH;
    constraints.insets = new Insets (10, 10, 0, 10);
    constraints.weightx = 0;
    constraints.weighty = 0;
    layout.setConstraints(endSimButton, constraints);
    add(endSimButton);
    
    // creating the SCORE JLabel ..
    sensorNoLabel = new JLabel ("Number of Sensors:");
    constraints.gridx = 1;
    constraints.gridy = 1;
    constraints.gridwidth = 1;
    constraints.gridheight = 1;
    constraints.fill =  GridBagConstraints.BOTH;
    constraints.insets = new Insets (15, 10, 0, 10);
    constraints.weightx = 0;
    constraints.weighty = 0;
    layout.setConstraints(sensorNoLabel, constraints);
    add(sensorNoLabel);
    
    // creating the TIME REMAINING JLabel ..
    sensorRangeLabel = new JLabel ("Radius of Each Sensor: ");
    constraints.gridx = 1;
    constraints.gridy = 3;
    constraints.gridwidth = 1;
    constraints.gridheight = 1;
    constraints.fill =  GridBagConstraints.BOTH;
    constraints.insets = new Insets (15, 10, 0, 10);
    constraints.weightx = 0;
    constraints.weighty = 0;
    layout.setConstraints(sensorRangeLabel, constraints);
    add(sensorRangeLabel);
    
 // creating the SCORE TEXT FIELD ..
    sensorNoText = new JTextField ("50");
    constraints.gridx = 1;
    constraints.gridy = 2;
    constraints.gridwidth = 1;
    constraints.gridheight = 1;
    constraints.fill =  GridBagConstraints.BOTH;
    constraints.insets = new Insets (5,10,0,10);
    constraints.weightx = 0;
    constraints.weighty = 0;
    layout.setConstraints(sensorNoText, constraints);
    add(sensorNoText);
    
     // creating the TIME TEXT FIELD ..
    sensorRangeText = new JTextField (".1");
    constraints.gridx = 1;
    constraints.gridy = 4;
    constraints.gridwidth = 1;
    constraints.gridheight = 1;
    constraints.fill =  GridBagConstraints.BOTH;
    constraints.insets = new Insets (5,10,5,10);
    constraints.weightx = 0;
    constraints.weighty = 0;
    layout.setConstraints(sensorRangeText, constraints);
    add(sensorRangeText);
    
    noteLabel = new JLabel("");
    constraints.gridx = 0;
    constraints.gridy = 0;
    constraints.gridwidth = 0;
    constraints.gridheight = 1;
    constraints.fill =  GridBagConstraints.BOTH;
    constraints.insets = new Insets (2,2,2,2);
    constraints.weightx = 10;
    constraints.weighty = 10;
    layout.setConstraints(noteLabel, constraints);
    add(noteLabel);
    
 /*   line = new Line(r,n);
    constraints.gridx = 0;
    constraints.gridy = 0;
    constraints.gridwidth = 0;
    constraints.gridheight = 1;
    constraints.fill =  GridBagConstraints.BOTH;
    constraints.insets = new Insets (2,2,2,2);
    constraints.weightx = 10;
    constraints.weighty = 10;
    layout.setConstraints(line, constraints);
    add(line);
    */
    }
}

