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
            if (annotation.figure instanceof Circle){
                if (Math.pow( (x - ((Circle) annotation.figure).getPoint().getX()), 2)
                        + Math.pow( (y - ((Circle) annotation.figure).getPoint().getY()), 2)
                        <=  Math.pow( ((Circle) annotation.figure).getR(), 2) ){
                    return annotation;
                }
            }
            else{
                if (x >= ((Rectangle) annotation.figure).getPoint1().getX() && x <= ((Rectangle) annotation.figure).getPoint2().getX()
                        && y >= ((Rectangle) annotation.figure).getPoint1().getY() && y <= ((Rectangle) annotation.figure).getPoint2().getY()){
                    return annotation;
                }
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