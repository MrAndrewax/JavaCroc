package ru.croc.task5;

public class Rectangle extends Figure{
    Point point1;
    Point point2;

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

    @Override
    public String toString(){
        return "Rectangle " + point1.toString() + ", "+ point2.toString();
    }
}