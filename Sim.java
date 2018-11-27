package sim_pack;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Sim extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
    private static final int FRAMES_PER_SECOND = 80;
          
    // main window dimensions
    public static final int WIDTH = 1200;
    public static final int HEIGHT = 800;
  
    public static int frame;
      
    //Mover[] ball = new Mover[1];
           
    Mover ball1, ball2;
  
    PVector gravity = new PVector(0, 7);
    PVector wind = new PVector(3*(Math.random()-0.5), -3*Math.random());
    
    public static void main(String[] args) {
                        JFrame window = new JFrame("Physics Simulation");
                        Sim drawingArea = new Sim();
                        Timer timer = new Timer(1000/FRAMES_PER_SECOND, drawingArea);
                        drawingArea.setBackground(Color.WHITE);
                        drawingArea.setPreferredSize(new Dimension(WIDTH, HEIGHT));
                        window.setContentPane(drawingArea);
                        window.pack();
                        window.setLocationRelativeTo(null);
                        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        window.setResizable(false);
                        window.setVisible(true);
                        timer.start(); // triggers actionPerformed method     
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    	repaint();
    }
    
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
               
        /*create and move the balls
        for (int i=0; i<ball.length; i++) {
             if (ball[i] == null)
                         ball[i] = new Mover(10*Math.random(), 0, 0, WIDTH, HEIGHT);
            ball[i].applyForce(gravity);
            ball[i].applyForce(wind);
            ball[i].update();
            ball[i].checkEdges();
            ball[i].display(g); */
           
        if (ball1 == null)
        	ball1 = new Mover(3, WIDTH*Math.random(), 0, WIDTH, HEIGHT);
        
        ball1.applyForce(gravity);
        ball1.applyForce(wind);
        ball1.update();
        ball1.checkEdges();
        ball1.display(g);
           
        if (ball2 == null)
        	ball2 = new Mover(4, WIDTH*Math.random(), 0, WIDTH, HEIGHT);
        
        ball2.applyForce(gravity);
        ball2.applyForce(wind);
        ball2.update();
        ball2.checkEdges();
        ball2.display(g);
        
        // handle collisions
        ball1.collision(ball2);
        ball2.collision(ball1);
               
        frame++;
             
        // wind changes direction every 100 frames
        if (frame == 100) {
        	frame = 0;
            wind = null;
            wind = new PVector(1*(Math.random()-0.5), -6*(Math.random()));
        }
    }
}
