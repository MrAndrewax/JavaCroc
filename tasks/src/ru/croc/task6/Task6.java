package ru.croc.task6;





public class Task6{
    public static void main(String[] args) {
        Annotation a1 = new Annotation(new Circle (100, 100, 10),"car");
        Annotation a2 = new Annotation(new Circle (200, 200, 20),"big car");
        Annotation a3 = new Annotation(new Circle (600, 300, 40),"shop");
        Annotation a4 = new Annotation(new Circle (10, 20, 4),"dog");
        Annotation a5 = new Annotation(new Rectangle (100, 100, 500, 500),"house");
        Annotation a6 = new Annotation(new Rectangle (1000, 1000, 2000, 2000),"large house");
        Annotation a7 = new Annotation(new Rectangle (10, 10, 20, 20),"child");
        Annotation a8 = new Annotation(new Rectangle (25, 25, 30, 30),"cat");
        Annotation[] annotations = {a1, a2, a3, a4, a5, a6, a7, a8};
        AnnotatedImage annotatedImage = new AnnotatedImage("image/path", annotations);


        Annotation b1 = annotatedImage.findByLabel("house");
        System.out.println(b1);
        Annotation b2 = annotatedImage.findByPoint(215, 215);
        System.out.println(b2);

        Annotation b3 = annotatedImage.findByPoint(26, 27);
        System.out.println(b3);

        Annotation b4 = annotatedImage.findByPoint(1500, 1500);
        System.out.println(b4);

    }
}



