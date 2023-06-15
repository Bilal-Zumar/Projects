package Balls;

import java.awt.Color;

public class JumpingBall extends Ball {//Jumping ball is a subclass of Ball

    //Default Constructor
    public JumpingBall() {//0 parameter constructor
        super();//call the base class default constructor - there are no new private data members to initialize
    }

    //4 Parameter Constructor
    public JumpingBall(double x, double y, double diameter, Color color) {//definition of this constructor for jumping ball
        super(x, y, diameter, color);//there are no new data members here, so call the base-class 4-parameter constructor
    }

    public void move(int WIDTH, int HEIGHT) {//move for a jumping ball
        setX(Math.random() * (WIDTH - this.getDiameter()));//randomly reset the x position x: 0 to width-diameter
        setY(Math.random() * (HEIGHT - this.getDiameter()));//randomly reset the y position y: 0 to height-diameter
    }

    public double findDistance(double x, double y) {//calculate distance of jumping ball from position (x,y)
        double distance = Math.sqrt(Math.pow((x - getX()), 2) + Math.pow((y - getY()), 2));//distance formula - getX and getY give balls position
        return distance;//return the calculated distance
    }

    public boolean intersectsWith(Ball otherBall) {//this - the jumping ball, otherBall is a regular ball - check if the ball images overlap - intersect
        if (findDistance(otherBall.getX(), otherBall.getY()) < (otherBall.getRadius() + this.getRadius())) {//find distance of this ball from other
//ball. if the distance is < the sum of the radii, then an intersection is true
            return true;//return that there was an intersection
        } else {
            return false;//return no intersection
        }
    }
}