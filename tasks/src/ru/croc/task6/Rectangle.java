package ru.croc.task6;

public class Rectangle extends Figure{
    private final Point point1;
    private final Point point2;

    Rectangle(){
        point1 = new Point();
        point2 = new Point();
    }
    Rectangle(double x2, double y2){
        point1 = new Point();
        point2 = new Point(x2, y2);
    }
    Rectangle(double x1, double y1, double x2, double y2){
        point1 = new Point(x1, y1);
        point2 = new Point(x2, y2);
    }

    Point getPoint1(){
        return point1;
    }
    Point getPoint2(){
        return point2;
    }

    @Override
    public void move(int dx, int dy){
        point1.setX(point1.getX() + dx);
        point1.setY(point1.getY() + dy);

        point2.setX(point2.getX() + dx);
        point2.setY(point2.getY() + dy);
    }

    @Override
    public String toString(){
        return "Rectangle " + point1.toString() + ", "+ point2.toString();
    }
}