package sim_pack;
import java.awt.Color;
import java.awt.Graphics;

public class Mover {
    // position, velocity, and acceleration
    PVector position;
    PVector velocity;
    PVector acceleration;
    double mass;

    public static double DAMPING = 0.9;

    int width, height; // the window size

    int radius;
    Color color = new Color((int)(256*Math.random()), (int)(256*Math.random()), (int)(256*Math.random()) );
    // constructor
    Mover(double m, double x, double y, int width_, int height_) {
        width = width_;
        height = height_;
        position = new PVector(x+radius, y+radius);
        velocity = new PVector(0, 0);
        acceleration = new PVector(0, 0);
        mass = m;
        radius = (int) (5*mass);
    }

    void setColor(Color col) {
        color = col;
    }

    // Newton's 2nd law: F = M * A or A = F / M
    void applyForce(PVector force) {
        // Divide by mass
        PVector f = PVector.divide(force, mass);
        //Accumulate all forces in acceleration
        acceleration.add(f);
    }

    void update() {
        // Velocity changes according to acceleration
        velocity.add(acceleration);
        // position changes by velocity
        position.add(velocity);
        // We must clear acceleration each frame
        acceleration.multiply(0);
    }

    // Draw Mover
    void display(Graphics g) {
        g.setColor(color);
        g.fillOval((int) position.x-radius, (int) position.y-radius, 2*radius, 2*radius);
    }

    // Bounce off bottom of window
    void checkEdges() {
        if (position.x - radius < 0) {
            velocity.x *= -DAMPING;  // A little dampening when hitting the side
            position.x = radius;
        }

        if (position.x + radius > width) {
            velocity.x *= -DAMPING;  // A little dampening when hitting the side
            position.x = width - radius;
        }

        if (position.y - radius < 0) {
            velocity.y *= -DAMPING;  // A little dampening when hitting the side
            position.y = radius;
        }

        if (position.y + radius > height) {
            velocity.y *= -DAMPING;  // A little dampening when hitting the bottom
            position.y = height - radius;
        }
    }

    void collision(Mover mover) {
        if ( position.distance(mover.position) < (radius + mover.radius) ) {
            PVector dist = position.sub(mover.position);
            position.add(dist.mult(1.01)); // we do that to avoid strange effects when colliding

            PVector v1minusv2 = velocity.sub(mover.velocity); // v1-v2
            PVector pos1minuspos2 = position.sub(mover.position); // x1-x2
            double in_prod = v1minusv2.innerproduct(pos1minuspos2); // <v1-v2, x1-x2>
            double in_prod_div_dist_sqr = in_prod/Math.pow(this.position.distance(mover.position), 2); // <v1-v2, x1-x2>/||x1-x2||^2
            double mass_factor = 2*this.mass/(this.mass+mover.mass); // 2m2/m1+m2
            double mult = mass_factor * in_prod_div_dist_sqr;
            pos1minuspos2.multiply(mult);
            velocity.subtract(pos1minuspos2); // changes the velocity vector, so now velocity has the new value
        }
    }
}