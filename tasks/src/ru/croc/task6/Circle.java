package ru.croc.task6;

public class Circle extends Figure{
    private final Point point;
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

    Point getPoint(){
        return point;
    }

    double getR(){
        return r;
    }

    @Override
    public void move(int dx, int dy){
        point.setX(point.getX() + dx);
        point.setY(point.getY() + dy);
    }

    @Override
    public String toString(){
        return "Circle" + point.toString() +  ", " + r;
    }


    @Override
    public boolean isPointInFigure(int x, int y){
        return Math.pow((x - point.getX()), 2)
                + Math.pow((y - point.getY()), 2)
                <= Math.pow(r, 2);
    }
}
