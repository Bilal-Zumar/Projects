public class Falcon9 {
    // Constants
    private static final double G = 6.6743e-11;  // Gravitational constant
    private static final double ME = 5.9722e24;  // Mass of Earth
    private static final double RE = 6.371e6;    // Radius of Earth

    // Rocket data
    private static final double M0 = 541300.0;  // Starting mass in kg
    private static final double MF = 142400.0;  // Ending mass in kg
    private final double TBURN = 162.0;  // Burn time in seconds
    private static final double F1 = 6806000.0; // Thrust of first stage in N
    private static final double M1 = 398900.0;  // Fuel consumption of first stage in kg

    // Variables
    private double time;    // Time in seconds
    private double mass;    // Mass of rocket in kg
    private double height;  // Height of rocket above ground in m
    private double velocity;  // Velocity of rocket in m/s
    private double deltaTime;       // Time step in seconds

    public Falcon9() {
        this.time = 0.0;
        this.mass = M0;
        this.height = 0.0;
        this.velocity = 0.0;
        this.deltaTime = 0;
    }

    public double calcThrust() {
        double t = 0.0;
        if (this.time < TBURN) {
            t = F1;
        }
        return t;
    }

    public double calcGravity() {
        double r = RE + this.height;
        return G * ME * this.mass / (r * r);
    }

    public double calcFuelBurn() {
        double burnRate = M1 / TBURN;
        double deltaM = burnRate * deltaTime;
        if (this.time + deltaTime > TBURN) {
            deltaM = (TBURN - this.time) * burnRate;
        }
        return deltaM;
    }

    public double getDeltaTime() {
        return deltaTime;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public double getVelocity() {
        return velocity;
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setDeltaTime(double deltaTime) {
        this.deltaTime = deltaTime;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public double getTBURN() {
        return TBURN;
    }
}
