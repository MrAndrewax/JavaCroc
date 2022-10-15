/*Замечания к первой задаче*/
/*1)Имя класса. Оно с маленькой буквы.
2) норм решение, но такой вопрос, если бы задача была не про треугольники, а, например, про восьмигранники,
    то как бы твой код отличался?
    Интересует момент ввода вершин и расчета (формула расчета площади восмигранников не важна, допустим,
    тебе так же нужно было бы вычислять расстояния между каждыми двумя вершинами).
*/

/* Решение замечаний
1) поправил)))
2) Теперь все точки я храню в массиве Point [] points;
    все стороны треугольника в массиве double [] triangleSides;
    и обращаюсь каждому отдельному объекту через индексы в цикле.
    соответственно мы не привязаны к количеству вершин (если не учитывать формулу герона)
*/


package ru.croc.task1;

import java.util.Scanner;

public class Task1 {
    static class Point {
        double x;
        double y;
    }
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        int pointsCount = 3;
        Point[] points = new Point [pointsCount];//точки будем хранить как массив объектов класса Point
        for (int i = 0; i < pointsCount; i++){
            points[i] = new Point();
        }


        //оптимизированный ввод точек
        for (int i = 0; i < pointsCount; i++){
            System.out.print("Введите координату х вершины №" + (i+1) + " ");
            points[i].x = in.nextDouble();
            System.out.print("Введите координату х вершины №" + (i+1) + " ");
            points[i].y = in.nextDouble();
        }
        for (int i = 0; i < pointsCount - 1; i++){
            for (int j = i + 1; j < pointsCount; j++){
                if (points[i].x == points[j].x && points[i].y == points[j].y){
                    System.out.print("Вы ввели одинаковые точки => треугольника не существует\n");
                    System.exit(2);
                }
            }
        }

        double [] triangleSides = new double [pointsCount];

        int triangleSidesIndex = 0;
        for (int i = 0; i < pointsCount - 1; i++){
            for (int j = i + 1; j < pointsCount; j++){
                triangleSides[triangleSidesIndex] = Math.sqrt(Math.pow(points[i].x - points[j].x, 2) + Math.pow(points[i].y - points[j].y, 2));
                triangleSidesIndex++;
            }
        }

        for (int i = 0; i < pointsCount; i++){
            if (triangleSides[i] > (triangleSides[(i+1) % 3] + triangleSides[(i+2) % 3]) ){
                System.out.print("Точки не удовлетворяют неравенству треугольника => они не образуют треугольник\n");
                System.exit(2);
            }
        }

        double p = 0;

        for (int i = 0; i < pointsCount; i++){
            p += triangleSides[i];//считаем периметр треугольника, как сумму длин всех сторон
        }

        p = p / 2;//получаем полупериметр

        double areaSquare = p;//площадь в квадрате

        for (int i = 0; i < pointsCount; i++){
            areaSquare *= (p - triangleSides[i]);
        }

        double area = Math.sqrt(areaSquare);//искомая площадь

        System.out.printf("%.2f", area);
    }
}
