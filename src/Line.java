
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;


public class Line extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Interval r;
	Graphics2D g2d;
	SensorNet  n;
	
	
	int LEN = 400;
	int Y = 100;
	int WIDTH  = 10;
	int xOffset = 10;
	
    public Line(Interval region, SensorNet net) {
    	r = region;
    	n = net;
        this.setPreferredSize(new Dimension(250, 250));
        this.setVisible(true);
      
    }
    
    
    

    @Override
    public void paint(Graphics g) {
    	super.paint(g);
        g.drawLine(xOffset, Y, LEN+xOffset, Y);
        g.setColor(Color.blue);
       
        
        drawSensors(g);
      
    }




	private void drawSensors(Graphics g) {
		
		for(Sensor s : n.sensors){
			
			int posX = (int)( s.position * LEN);
			 g.setColor(Color.red);
			 g.fillOval(posX-WIDTH/2+xOffset, Y-WIDTH/2, WIDTH, WIDTH);	
		
			 int rad =  2*(int)(s.radius*LEN);
			 g.drawOval(posX-rad/2+xOffset, Y-rad/2, rad, rad);	
		}
		
	}
    
 
}





