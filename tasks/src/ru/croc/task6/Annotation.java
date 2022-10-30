package ru.croc.task6;

public class Annotation{

    Figure figure;
    String label;

    Annotation(Figure figure, String label){
        this.figure = figure;
        this.label = label;
    }

    @Override
    public String toString(){
        return figure.toString() + ": " + label;
    }
}