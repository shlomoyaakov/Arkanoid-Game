package geometry;
/**
 * @ author Shlomo Yakov.
 */

import java.util.ArrayList;
import java.util.List;

/**
 * The type geometry.Rectangle.
 */
public class Rectangle {
    private Point upperLeft;
    private Double width;
    private Double height;
    private Line[] lines;


    /**
     * Instantiates a new geometry.Rectangle.
     *
     * create a new rectangle with a specific location and size.
     *
     * @param upperLeft the upper left point of the rectangle.
     * @param width     the width of the rectangle.
     * @param height    the height of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.lines = lines();
    }

    /**
     * Makes an array of lines from the rectangle.
     *
     * create an array of lines from each of the rectangle side.
     *
     * @return geometry.Line[] the array of lines.
     */
    private Line[] lines() {
        Line[] recLines = new Line[4];
        Point lowerLeft, upperRight, lowerRight;
        lowerLeft = new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.height);
        recLines[0] = new Line(this.upperLeft, lowerLeft);
        upperRight = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY());
        recLines[1] = new Line(upperRight, this.upperLeft);
        lowerRight = new Point(upperRight.getX(), lowerLeft.getY());
        recLines[2] = new Line(lowerRight, upperRight);
        recLines[3] = new Line(lowerRight, lowerLeft);
        return recLines;
    }

    /**
     * Makes a list of intersection points of the rectangle with line.
     *
     * @param line the line that we want the intersection points with.
     * @return List<geometry.Point> of intersection possibly empty.
     */
    public List<Point> intersectionPoints(Line line) {
        List<Point> list = new ArrayList<Point>();
        Point intersection = null;
        //checks intersection with each side of the rectangle.
        for (int i = 0; i < 4; i++) {
            intersection = line.intersectionWith(this.lines[i]);
            if (intersection != null) {
                list.add(intersection);
            }
        }
        return list;
    }

    /**
     * Gets width.
     *
     * @return the width
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Gets height.
     *
     * @return the height
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Gets the upper left point.
     *
     * @return the upper left point.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }
}