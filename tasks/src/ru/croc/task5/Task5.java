package ru.croc.task5;

/*Задача 5
Вы разрабатываете небольшое приложение по аннотированию (разметке) изображений
 с целью последующего использования этой разметки для обучения моделей computer vision.
 В этом приложении пользователь может выделять области на изображении с помощью
 прямоугольников и окружностей и подписывать их произвольным текстом.

Прямоугольники определяются координатами левого нижнего и правого верхнего углов,
а окружности - координатами центра и радиусом.
Вся разметка для изображения представляется массивом Annotation[].

Определите класс Annotation для представления данных разметки (подпись + фигура)
и классы Figure, Rectangle, Circle для задания размеченных областей.

Переопределите метод toString класса Annotation так,
чтобы в результат выводилась информация о полях и вложенных объектах. Формата вывода:

Окружность:

“C (<X0>, <Y0>), <R>: <Подпись>”

Прямоугольник:

“R (<X1>, <Y1>), (<X2>, <Y2>): <Подпись>”

Например:

C (100, 100), 10: Tree
R (100, 100), (150, 200): Car
*/

public class Task5{
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
        for (Annotation annotation : annotatedImage.getAnnotations()){
            System.out.println(annotation.toString());
        }
    }
}

