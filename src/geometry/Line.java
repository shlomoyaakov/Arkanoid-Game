package geometry; /**
 * @ author Shlomo Yakov.
 */

import java.util.List;

/**
 * The type geometry.Line.
 */
public class Line {
    private Point start;
    private Point end;

    /**
     * The constructor with points, initialize a new geometry.Point.
     *
     * @param start the start point of the line.
     * @param end   the end point of the line.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * The constructor with coordinates, initialize a new geometry.Point.
     *
     * @param x1 the x1 coordinate of the start of the line.
     * @param y1 the y1 coordinate of the start of the line.
     * @param x2 the x2 coordinate of the end of the line.
     * @param y2 the y2 coordinate of the end of the line.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * Length, calculate the length of the line, using point class.
     *
     * @return the length of the line in a double variable.
     */
    public double length() {
        //Using distance in geometry.Point class.
        return start.distance(end);
    }

    /**
     * Middle point, calculate the middle point of the line.
     *
     * @return the middle point of the line.
     */
    public Point middle() {
        //find the x,y of the middle of the line.
        double midX = (this.end.getX() + this.start.getX()) / 2;
        double midY = (this.end.getY() + this.start.getY()) / 2;
        //Then make a new point.
        Point middleLine = new Point(midX, midY);
        return middleLine;
    }

    /**
     * Closest intersection to start of line point.
     *
     * The program gets a rectangle and its return the closest point,if exists, of intersection
     * of the line with rectangle,to the starts of the line.
     *
     * @param rect the rectangle that we want to return the closets intersections of the line with it.
     * @return the closest intersection point if exists otherwise null.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        //Gets the list of intersections with the rectangles
        List<Point> list = rect.intersectionPoints(this);
        //if there is no intersection.
        if (list.isEmpty()) {
            return null;
        }
        //checks whats is the shortest distance
        double distance = list.get(0).distance(this.start);
        Point point = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).distance(this.start) < distance) {
                distance = list.get(i).distance(this.start);
                point = list.get(i);
            }
        }
        return point;
    }

    /**
     * Start point,gives the start point of the line.
     *
     * @return the point start of the line.
     */
    public Point start() {
        return this.start;
    }

    /**
     * End point,gives the end point of the line.
     *
     * @return the point end of the line.
     */
    public Point end() {
        return this.end;
    }

    /**
     * Generate random line line.
     *
     * The program gets two randoms points from geometry.Point class and then generate a new line.
     *
     * @param minRangeX the min range in x coordinates.
     * @param maxRangeX the max range in x coordinates.
     * @param minRangeY the min range in y coordinates.
     * @param maxRangeY the max range in y coordinates.
     * @return random line the given range.
     */
    public static Line generateRandomLine(int minRangeX, int maxRangeX, int minRangeY, int maxRangeY) {
        //Gets two randoms points from Points class.
        Point start = Point.randomPoint(minRangeX, maxRangeX, minRangeY, maxRangeY);
        Point end = Point.randomPoint(minRangeX, maxRangeX, minRangeY, maxRangeY);
        //Generate a new line.
        Line line = new Line(start, end);
        return line;
    }

    /**
     * Between - gets 3 numbers, the program checks if the first one is between the others.
     *
     * @param check the numbers the need to be checks if it is between the the two numbers.
     * @param num1  one of the numbers that we want to verify if "check" between them.
     * @param num2  one of the numbers that we want to verify if "check" between them.
     * @return Boolean true if it is between if not return false.
     */
    private boolean between(double check, double num1, double num2) {
        //checks the two options for "check" to be between.
        if (check >= num1 && check <= num2) {
            return true;
        }
        if (check >= num2 && check <= num1) {
            return true;
        }
        return false;
    }

    /**
     * Is intersecting boolean,checks if two line are intersecting.
     *
     * The program gets the intersecting point of the line equation, if exist,
     * and then checks if the intersecting point is in the given line and in this line.
     *
     * @param other the other line that we want to check intersection.
     * @return true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        //the x,y coordinates of the start,end point of this line.
        double sLineX = this.start.getX(), eLineX = this.end.getX();
        double sLineY = this.start.getY(), eLineY = this.end.getY();
        //the x,y coordinates of the start,end point of the other line.
        double sOtherX = other.start.getX(), eOtherX = other.end.getX();
        double sOtherY = other.start.getY(), eOtherY = other.end.getY();
        //the changes in the x coordinate of this line.
        double deltaX = eLineX - sLineX;
        //the changes in the y coordinate of the this line.
        double deltaY = eLineY - sLineY;
        //the changes in the x coordinate of the other line.
        double otherDeltaX = eOtherX - sOtherX;
        //the changes in the y coordinate of the the other line.
        double otherDeltaY = eOtherY - sOtherY;
        double lineM, otherM, checkY, checkX, deltaM;
        // if they are both parallel to the y axis there is not intersection point.
        if (deltaX == 0 && otherDeltaX == 0) {
            return false;
        }
        if (deltaY == 0 && otherDeltaY == 0) {
            return false;
        }
        //if this line is parallel to the y axis.
        if (deltaX == 0) {
            //checks if this line x coordinate is between the other line x coordinates.
            if (!between(sLineX, sOtherX, eOtherX)) {
                return false;
            }
            //if it is then place its x coordinate in the other line equation.
            otherM = otherDeltaY / otherDeltaX;
            checkY = otherM * (sLineX - sOtherX) + sOtherY;
            //checks if the y coordinate that we get is between the two line y coordinates.
            if (!between(checkY, sOtherY, eOtherY)) {
                return false;
            }
            if (!between(checkY, sLineY, eLineY)) {
                return false;
            }
            return true;
        }
        //if the other line is parallel to the y axis.
        if (otherDeltaX == 0) {
            //checks if this line x coordinate is between this line coordinates.
            if (!between(sOtherX, sLineX, eLineX)) {
                return false;
            }
            //if it is then place its x coordinate in this line equation.
            lineM = deltaY / deltaX;
            checkY = lineM * (sOtherX - sLineX) + sLineY;
            //checks if the y coordinate that we get is between the two line y coordinates.
            if (!between(checkY, sLineY, eLineY)) {
                return false;
            }
            if (!between(checkY, sOtherY, eOtherY)) {
                return false;
            }
            return true;
        }
        //now we can calculate the slope of the two lines, without dividing in zero.
        lineM = deltaY / deltaX;
        otherM = otherDeltaY / otherDeltaX;
        // if they are both parallel, there is no intersection.
        if (lineM == otherM) {
            return false;
        }
        deltaM = lineM - otherM;
        //gets the x intersection by the two equations.
        checkX = (lineM * sLineX - sLineY + sOtherY - otherM * sOtherX) / deltaM;
        //checks if the x coordinate that we get is between the two line x coordinates.
        if (!between(checkX, sLineX, eLineX)) {
            return false;
        }
        if (!between(checkX, sOtherX, eOtherX)) {
            return false;
        }
        return true;
    }

    /**
     * intersectionWith return the point of the intersection, if exist, with the other line.
     *
     * The program gets the intersecting point of the line equation, if exist,
     * and then checks if the intersecting point is in the given line and in this line, if
     * it is it return a new geometry.Point with the intersection coordinates, otherwise null.
     *
     * @param other the other line that we want the point of intersection with.
     * @return geometry.Point with the intersection value .if there is no intersection,null.
     */
    public Point intersectionWith(Line other) {
        //if there is no intersection.
        if (!isIntersecting(other)) {
            return null;
        }
        double deltaX = this.end.getX() - this.start.getX();
        double deltaY = this.end.getY() - this.start.getY();
        double otherDeltaX = other.end.getX() - other.start.getX();
        double otherDeltaY = other.end.getY() - other.start.getY();
        double interX, interY, lineM, otherM, deltaM;
        double x1 = this.end.getX(), x2 = other.end.getX();
        double y1 = this.end.getY(), y2 = other.end.getY();
        //if this line is parallel to the axis y.
        if (deltaX == 0) {
            //we place this line x coordinate in the equation of the other line to get the y.
            otherM = otherDeltaY / otherDeltaX;
            interX = x1;
            interY = otherM * (x1 - x2) + y2;

            //if the other line is parallel to the axis y.
        } else if (otherDeltaX == 0) {
            //we place other line x coordinate in the equation of the other line to get the y.
            lineM = deltaY / deltaX;
            interX = x2;
            interY = lineM * (x2 - x1) + y1;
            //if they both have a line equation.
        } else {
            //we gets the combine x coordinate from the two equations.
            lineM = deltaY / deltaX;
            otherM = otherDeltaY / otherDeltaX;
            deltaM = lineM - otherM;
            interX = (lineM * x1 - y1 + y2 - otherM * x2) / deltaM;
            //place the x coordinate that we found in one of the equation and get the y.
            if (deltaY == 0) {
                interY = y1;
            } else if (otherDeltaY == 0) {
                interY = y2;
            } else {
                interY = lineM * (interX - x1) + y1;
            }

        }
        Point interPoint = new Point(interX, interY);
        return interPoint;
    }

    /**
     * Equals boolean, checks if this line is equal to other line.
     *
     * @param other the other line the we want to compare to this line
     * @return boolean. true is the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        //checks the two possible ways to line to be equals
        if (this.start == other.start) {
            if (this.end == other.end) {
                return true;
            }
        }
        if (this.start == other.end) {
            if (this.end == other.end) {
                return true;
            }
        }
        return false;
    }
}