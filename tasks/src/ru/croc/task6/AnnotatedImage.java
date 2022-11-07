package ru.croc.task6;

public class AnnotatedImage {

    private final String imagePath;

    private final Annotation[] annotations;

    public AnnotatedImage(String imagePath, Annotation... annotations) {
        this.imagePath = imagePath;
        this.annotations = annotations;
    }

    Annotation findByPoint(int x, int y){
        for (Annotation annotation: annotations){
            if (annotation.figure.isPointInFigure(x, y)){
                return annotation;
            }
        }
        return null;
    }

    Annotation findByLabel(String label){
        for (Annotation annotation: annotations){
            if (annotation.label.equals(label)){
                return annotation;
            }
        }
        return null;
    }




    public String getImagePath() {
        return this.imagePath;
    }

    public Annotation[] getAnnotations() {
        return this.annotations;
    }
}