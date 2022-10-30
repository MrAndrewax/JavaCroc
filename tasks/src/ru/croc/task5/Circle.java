package ru.croc.task5;

public class Circle extends Figure{
    Point point;
    private final double r;

    Circle(){
        point = new Point();
        this.r = 1;
    }
    Circle(double x, double y, double r){
        point = new Point(x, y);
        this.r = r;
    }
    Circle(double x, double y){
        point = new Point(x, y);
        this.r = 1;
    }
    Circle(double r){
        point = new Point();
        this.r = r;
    }

    @Override
    public String toString(){
        return "Circle" + point.toString() +  ", " + r;
    }
}
