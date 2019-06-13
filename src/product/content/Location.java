package product.content;

import java.io.Serializable;

/**
 * Holds Content location on the DigitalMap
 *
 * @version 1
 * @author Yaad Nahshon
 */
public class Location implements Serializable
{
    private double coordinateX;
    private double coordinateY;

    public Location(double newX, double newY)
    {
        this.coordinateX = newX;
        this.coordinateY = newY;
    }

    public double getCoordinateX()
    {
        return coordinateX;
    }

    public void setCoordinateX(double coordinateX)
    {
        this.coordinateX = coordinateX;
    }

    public double getCoordinateY()
    {
        return coordinateY;
    }

    public void setCoordinateY(double coordinateY)
    {
        this.coordinateY = coordinateY;
    }
}
