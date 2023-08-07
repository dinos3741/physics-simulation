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
    private static final int FRAMES_PER_SECOND = 50;

    // main window dimensions
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    public static int frame;

    Mover[] ball = new Mover[2];

    PVector gravity = new PVector(0, 3);
    PVector wind = new PVector(Math.random(), Math.random());

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

        //create and move the balls
        for (int i=0; i<ball.length; i++) {
            if (ball[i] == null)
                ball[i] = new Mover(5*Math.random(), WIDTH*Math.random(), 0, WIDTH, HEIGHT);
            ball[i].applyForce(gravity);
            ball[i].applyForce(wind);
            ball[i].update();
            ball[i].checkEdges();
            ball[i].display(g);

            for (int j=0; (j<ball.length && j!= i); j++) {
                ball[i].collision(ball[j]);
                ball[j].collision(ball[i]);
            }
        }

        frame++;
        // wind changes direction every 100 frames
        if (frame == 100) {
            frame = 0;
            wind = null;
            wind = new PVector(3*(Math.random()-0.5), -7*(Math.random()-0.5) );
            // -6 because I need strong upwards wind to lift the ball up
        }
    }
}
