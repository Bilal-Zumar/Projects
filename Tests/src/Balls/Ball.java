package Balls;

import java.awt.Color; //import the awt Color

import java.awt.Graphics;//import the graphics



//class Ball. It is public and gives the attributes and behavior of a ball object. The private data members

//give the attributes, and the methods of ball give the behavior of a ball object.

public class Ball {


// 1. Declaration of Variables

    private double x; //x-coordinate of the center of the ball

    private double y; //y-coordinate of the center of the ball

    private double diameter; //diameter of the ball

    private double radius; //radius of the ball = diameter/2

    private Color color; //color of the ball

    private double xSpeed; //x-speed = change in x-position

    private double ySpeed; //y-speed = change in y-position


// 2. Create a default constructor



    /* Default Constructor

     * Creates a red ball at (0, 0) with a diameter of 25.

     * The default speed is 0.

     */

    public Ball() {

        this.x = 0;//attribute x is initialized to 0

        this.y = 0;//attribute y is initialized to 0

        this.diameter = 25;//the ball object diameter is initialized to 25 pixels

        this.radius = diameter / 2;//the radius is half of the ball diameter

        this.color = Color.RED;//the default ball has the color red

        xSpeed = 0;//the default speed in the x direction is 0

        ySpeed = 0;//the default speed in the y direction is 0

    }


// 3. Write a constructor that allows the user to input the parameters (x, y, diameter, Color)

// and sets the x and y-speed = 0.

    public Ball(double x, double y, double diameter, Color color) {//a 4-parameter constructor for a ball object

        this.x = x;//the x position in input through this constructor

        this.y = y;//the y position of the ball is input

        this.diameter = diameter;//the diameter of the ball is input

        this.color = color;//the color of the ball object is input

        xSpeed = 0;//default value for the speed of the ball in the x direction

        ySpeed = 0;//default value for the speed of the ball in the y direction

        radius = diameter / 2;//the radius is calculated from the diameter input value

    }


// 4. Create getters and setters for all private variables

//setters

    public void setX(double amount) {//this public setter sets the x position of the ball to the value in amount

        x = amount;//x is set to amount

    }


    public void setY(double amount) {//this public setter sets the y position of the ball to the value in amount

        y = amount;//y is set to amount

    }


    public void setColor(Color color) {//this public setter sets the color of the ball to the input value in color

        this.color = color;//color data member is set to color

    }


    public void setXSpeed(double amount) {//this public setter sets the x speed of the ball to the value in amount

        xSpeed = amount;//xSpeed is set to amount

    }


    public void setYSpeed(double amount) {//this public setter sets the y speed of the ball to the value in amount

        ySpeed = amount;//ySpeed is set to amount

    }


    public void setDiameter(double diameterx) {//this public setter sets the diameter of the ball to the value in diameterx

        diameter = diameterx;//the diameter data member  is set to diameterx

        radius = diameterx / 2;//the radius data member is calculated from diamterx

    }


    public void setRadius(double radiusx) {//this public setter sets the radius of the ball to the value in radiusx

        radius = radiusx;//the radius data member is set to radiusx

        diameter = radiusx * 2;//the diameter data member is calculated from radiusx

    }


//getters

    public double getX() {//this public getter returns the value of the data member x

        return x;//x is returned as a double

    }


    public double getY() {//this public getter returns the value of the data member y

        return y;//y is returned as a double

    }


    public double getDiameter() {//this public getter returns the value of the data member diameter

        return diameter;//diameter is returned as a double

    }


    public double getRadius() {//this public getter returns the value of the data member radius

        return radius;//radius is returned as a double

    }


    public Color getColor() {//this public getter returns the value of the data member color

        return color;//color is returned as a Color object

    }


    public double getXSpeed() {//this public getter returns the value of the data member xSpeed

        return xSpeed;//xSpeed is returned as a double

    }


    public double getYSpeed() {//this public getter returns the value of the data member ySpeed

        return ySpeed;//ySpeed is returned as a double

    }


// 5. Finish the following methods

// 6. Test using BouncingBallTester.java

    //This method returns nothing. It draws an oval (ball) using the graphics object g and some of the

    //ball data members: color, x, y, and diameter

    public void draw(Graphics g) {

        g.setColor(color);//the graphic object displays the color color

        double value = x;//value is a copy of the x position of the ball

        int num = (int) value;//num is an integer found by casting a double to an int, dropping the numbers after the decimal point

        double value2 = y;//value2 is a copy of the y position of the ball

        int num2 = (int) value2;//num2 is an integer found by casting a double to an int, dropping the numbers after the decimal point

        double value3 = diameter;//value3 is a copy of the diameter of the ball

        int num3 = (int) value3;//num3 is an integer found by casting a double to an int, dropping the numbers after the decimal point

        g.fillOval(num - num3 / 2, num2 - num3 / 2, num3, num3);//the method of the graphics class called fillOval is called.

        //It has 4 input parameters. The value of the first is the x position - the radius. The value of the second is the y position

        //minus the radius. The third is the diameter, and the fourth is also the diameter. Making the last 2 parameters identical

        //causes a perfect circle to be displayed. The first 2 input parameters define the location of the center of the circle.

    }


// 7. Sets the center location of the ball

    public void setLocation(double x, double y) {//this method acts as a setter for the center of the ball: (x,y)

        this.x = x;//the x data member is set to the input x

        this.y = y;//the y data member is set to the input y

    }


// 8. Generates a speed between maxSpeed

// and max Speed

    public void setRandomSpeed(double maxSpeed) {//This method uses the random function of the math class to

        //set random speeds for the x direction and y direction for a ball object.

        double setXspeed = (Math.random() * (maxSpeed - maxSpeed * -1 + 1) + maxSpeed * -1);//Math.random() has a value

        //between 0 and 0.99999999... The value of  (maxSpeed - maxSpeed * -1 + 1)  is essentially 2*maxSpeed + 1.

        //That gives 2*maxSpeed+1 times a value between 0 and 1. Adding -maxSpeed then gives about maxSpeed+1 for a random value of

        //0.9999999. For a random value of 0, it gives a speed of -maxSpeed. So the range is (-maxSpeed, maxSpeed+1).

        this.xSpeed = setXspeed;//the data member xSpeed is set to setXspeed.

        double setYspeed = (Math.random() * (maxSpeed - maxSpeed * -1 + 1) + maxSpeed * -1);//setYspeed has the same range as setXspeed.

        this.ySpeed = setYspeed;//the data member ySpeed is set to setYspeed.

    }


//9. Write the move method to make the ball move around the screen

// and bounce of the edges.

    public void move(int rightEdge, int bottomEdge) {//this public method moves the ball. The rightEdge and bottomEdge of the

        //screen are input values. These values are used to contain the ball within the displayed window.

        x += xSpeed;//first, the x position is increased by xSpeed (a 1 second change in x)

        y += ySpeed;//also, the y position is increased by ySpeed (a 1 second change in y)

        //Note that xSpeed and ySpeed could be positive, negative, or 0.

//right side of screen

        if (x >= rightEdge - radius) {//if the x position of the ball center is at or > than rightEdge - radius, which means

            //that the ball is touching or beyond the right screen edge, then:

            x = rightEdge - radius;//x is set so that the ball touches the right screen edge.

            xSpeed = xSpeed * -1;//the xSpeed of the ball is reversed in direction, so that the ball will move away from the rightScreen to the left.

        }

//left side of screen

        if (x - radius < 1) {//if the x position of the ball center - the ball radius is less than 1 pixel , which means

            //that the ball is touching or beyond the left screen edge, then:

            x = x + radius;//x is set so that the ball touches the left screen edge.

            xSpeed = xSpeed * -1;//the xSpeed of the ball is reversed in direction, so that the ball will move away from the leftScreen to the right.

        }

//top side of screen

        if (y - radius < 1) {//if the y position of the ball center - the ball radius is less than 1 pixel , which means

            //that the ball is touching or beyond the top screen edge, then:

            y = y + radius;//x is set so that the ball touches the top screen edge.

            ySpeed = ySpeed * -1;//the ySpeed of the ball is reversed in direction, so that the ball will move away from the topScreen (down).

        }

//bottom side of screen

        if (y >= bottomEdge - radius) {//if the y position of the ball center is at or > than bottomEdge - radius, which means

            //that the ball is touching or beyond the bottom screen edge, then:

            y = bottomEdge - radius;//y is set so that the ball touches the bottom screen edge.

            ySpeed = ySpeed * -1;//the ySpeed of the ball is reversed in direction, so that the ball will move away from the bottomScreen (up).
        }
    }
}
