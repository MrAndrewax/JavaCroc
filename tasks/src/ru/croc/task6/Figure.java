package ru.croc.task6;

public abstract class Figure implements Movable {
    @Override
    public String toString(){
        return "Figure";
    }

    public boolean isPointInFigure(int x, int y){
        return true;
    }

}