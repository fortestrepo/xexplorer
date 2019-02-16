package com.utilitysoftwareservices;

import java.util.Objects;

/**
 * Point represents a unit in Cartesian coordinate system
 * 
 * (x, y) represents x unit away horizontally, and y unit away vertically, from
 * the origin (0, 0).
 * 
 */
public final class Point {
 
    private final int x;
    private final int y;

    /**
     * Factory method of Point
     * 
     * @param x horizontal unit from the origin
     * @param y vertical unit from the origin
     * @return new Position of the point
     */
    public static Point of(int x, int y){
        return new Point(x, y);
    }

    private Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Point)) {
            return false;
        }
        Point point = (Point) o;
        return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return String.format("(%s,%s)", x, y);
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

}