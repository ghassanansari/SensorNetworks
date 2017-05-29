import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;


public class Box extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	SensorNet  n;
	int X = 150;
	int Y = 60;
	static int HEIGHT = 300;
	static int WIDTH = 300;
	int offset = 5;
	
	Box(SensorNet  net){
		n = net;
		this.setPreferredSize(new Dimension(300, 300));
	    this.setVisible(true);
		
	}
    
	public void paintComponent(Graphics g) {
		
	    super.paintComponent(g);
	    g.setColor(Color.blue);
	    g.drawRect(X, Y, WIDTH, HEIGHT);
	  
	    drawSensors(g);
	  
	  }

	private void drawSensors(Graphics g) {
		
		for(Sensor2D s : n.sensors2d){
				
				 int posX = (int)( s.x * WIDTH)+ X;
				 int posY = (int)( s.y * HEIGHT)+ Y;
				 
				 g.setColor(Color.red);
				 
				 g.fillOval(posX- (offset/2), posY- (offset/2), offset, offset);	
			
				 int rad =  (int)(s.radius * WIDTH)* 2;
				 g.drawOval(posX-rad/2, posY-rad/2, rad, rad);	
			}
		
	}
	 
	
	
	
	
	
	
	
	
	
	/*
	 // g.drawRoundRect(100, 10, 80, 30, 15, 15);
	 
	    int thickness = 4;
	 
	 //   for (int i = 0; i <= thickness; i++)
	   //   g.draw3DRect(200 - i, 10 - i, 80 + 2 * i, 30 + 2 * i, true);
	    for (int i = 0; i < thickness; i++)
	      g.draw3DRect(200 - i, 50 - i, 80 + 2 * i, 30 + 2 * i, false);
	 
	 //   g.drawOval(10, 100, 80, 30);
	 * */
}
