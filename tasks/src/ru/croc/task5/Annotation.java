package ru.croc.task5;

public class Annotation{

    Figure figure;
    String note;

    Annotation(Figure figure, String note){
        this.figure = figure;
        this.note = note;
    }

    @Override
    public String toString(){
        return figure.toString() + ": " + note;
    }
}