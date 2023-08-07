package sim_pack;

public class PVector {
    double x;
    double y;

    //constructor
    public PVector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }

    public double getMagnitude() {
        double magnitude = Math.sqrt(x*x + y*y);
        return(magnitude);
    }

    public double getDirection() {
        double angle = Math.atan(y/x);
        return(angle);
    }

    // add constant to vector and replace
    public void addc(double c) {
        x += c;
        y += c;
    }

    // add vector v to current vector and replace
    public void add(PVector v) {
        x = this.x + v.x;
        y = this.y + v.y;
    }

    // subtract vector v from current vector and replace
    public void subtract(PVector v) {
        x = this.x - v.x;
        y = this.y - v.y;
    }

    // subtract two vectors
    public PVector sub(PVector v) {
        double xa = x - v.x;
        double ya = y - v.y;
        return new PVector(xa, ya);
    }

    // create a copy of the vector
    public PVector copy() {
        double xa = x;
        double ya = y;
        return new PVector(xa, ya);
    }

    // multiply by a scalar a and replace
    public void multiply(double a) {
        x = this.x*a;
        y = this.y*a;
    }

    // multiply a vector by a scalar a
    public PVector mult(double a) {
        double xa = x*a;
        double ya = y*a;
        return new PVector(xa, ya);
    }

    // divide a vector by a scalar a
    public static PVector divide(PVector f, double a) {
        double xa = f.x/a;
        double ya = f.y/a;
        return new PVector(xa, ya);
    }

    public double innerproduct(PVector vec) {
        double product = x * vec.x + y * vec.y;
        return product;
    }

    public double distance (PVector v) {
        double distance = Math.sqrt(Math.pow(Math.abs(x - v.x), 2) + Math.pow(Math.abs(y - v.y), 2));
        return distance;
    }

    public PVector normalize () {
        double xa = x/this.getMagnitude();
        double ya = y/this.getMagnitude();
        return new PVector(xa, ya);
    }
}

