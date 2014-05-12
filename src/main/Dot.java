package main;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

public class Dot implements Comparable {
    private String name;
    private double x;
    private double y;


    public Dot(String name, double x_cord, double y_cord) {
        this.name = name;
        x = x_cord;
        y = y_cord;
    }

    public double getX() {
        return x;
    }

    public double getY() {

        return y;
    }


    public double getDistance(Dot d) {
        double targetX = d.getX();
        double targetY = d.getY();
        double deltaX = x-targetX;
        double deltaY = y-targetY;
        double distance = Math.hypot(deltaX, deltaY);

        return distance;

    }



    public String toString() {
        return name;
    }


    public int compare(Dot o1, Dot o2) {

        return o1.compareTo(o2);
    }

    @Override
    public int compareTo(Object o) {
        Dot a = (Dot) o;
        if (x < a.x) {
            return 1;
        }
        if (x > a.getX()) {
            return -1;
        }

        return 0;
    }

}
