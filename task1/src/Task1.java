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
    соответственно мы не привязаны к количеству вершин (если не учитывать формулу герона)*/


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
        /*старый ввод точек
        Point a = new Point();
        System.out.print("Введите координату х вершины №1: ");
        a.x = in.nextDouble();
        System.out.print("Введите координату y вершины №1: ");
        a.y = in.nextDouble();

        Point b = new Point();
        System.out.print("Введите координату х вершины №2: ");
        b.x = in.nextDouble();
        System.out.print("Введите координату y вершины №2: ");
        b.y = in.nextDouble();

        Point c = new Point();
        System.out.print("Введите координату х вершины №3: ");
        c.x = in.nextDouble();
        System.out.print("Введите координату y вершины №3: ");
        c.y = in.nextDouble();
        */





        /*
        for (int i = 0; i < pointsCount; i++){
            System.out.printf("points[%d].x =  %.2f\n", i, points[i].x);
            System.out.printf("points[%d].y =  %.2f\n", i, points[i].y);
        }
*/

        //оптимизированная проверка на одинаковые точки
        for (int i = 0; i < pointsCount - 1; i++){
            for (int j = i + 1; j < pointsCount; j++){
                if (points[i].x == points[j].x && points[i].y == points[j].y){
                    System.out.print("Точки не удовлетворяют неравенству треугольника => они не образуют треугольник\n");
                    System.exit(2);
                }
            }
        }
        /*старая проверка на то, что мы ввели две одинаковые точки.
        if ( ((a.x == b.x) && (a.y == b.y)) ||
                ( (c.x == b.x) && (c.y == b.y)) ||
                ( (a.x == c.x) && (a.y == c.y))){
            System.out.print("Какие-то точки склеились => они не образуют треугольник\n");
            System.exit(1);
        }*/



        double [] triangleSides = new double [pointsCount];

        int triangleSidesIndex = 0;
        for (int i = 0; i < pointsCount - 1; i++){
            for (int j = i + 1; j < pointsCount; j++){
/*
                System.out.printf("points[%d].x =  %.2f\n", i, points[i].x);
                System.out.printf("points[%d].y =  %.2f\n", i, points[i].y);
                System.out.printf("points[%d].x =  %.2f\n", j, points[j].x);
                System.out.printf("points[%d].y =  %.2f\n", j, points[j].y);

 */
                //System.out.printf("triangleSides[%d] =  %.2f\n", triangleSidesIndex, Math.sqrt(Math.pow(points[i].x - points[j].x, 2) + Math.pow(points[i].y - points[j].y, 2)));


                triangleSides[triangleSidesIndex] = Math.sqrt(Math.pow(points[i].x - points[j].x, 2) + Math.pow(points[i].y - points[j].y, 2));
                triangleSidesIndex++;
            }
        }

        /*считаем длины сторон треугольника
        double ab = Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
        double bc = Math.sqrt(Math.pow(c.x - b.x, 2) + Math.pow(c.y - b.y, 2));
        double ac = Math.sqrt(Math.pow(a.x - c.x, 2) + Math.pow(a.y - c.y, 2));
        */

        for (int i = 0; i < pointsCount; i++){
            //System.out.printf("%d: triangleSides[i] = %.2f\ntriangleSides[(i+1)  3] = %.2f\ntriangleSides[(i+2)  3] = %.2f\n\n", i, triangleSides[i], triangleSides[(i+1) % 3], triangleSides[(i+2) % 3]);
            if (triangleSides[i] > (triangleSides[(i+1) % 3] + triangleSides[(i+2) % 3]) ){
                System.out.print("Точки не удовлетворяют неравенству треугольника => они не образуют треугольник\n");
                System.exit(2);
            }
        }

        /*неравенство треугольника
        if ( (ab > bc + ac) || (bc > ab + ac) || (ac > ab + bc)){//провереям образуют ли эти точки треугольник.
            System.out.print("Точки не удовлетворяют неравенству треугольника => они не образуют треугольник\n");
            System.exit(2);
        }*/


        double p = 0;

        for (int i = 0; i < pointsCount; i++){
            p += triangleSides[i];//считаем периметр треугольника, как сумму длин всех сторон
        }
        p = p / 2;//получаем полупериметр
        //System.out.printf("%.2f\n", p);

        double areaSquare = p;//площадь в квадрате

        for (int i = 0; i < pointsCount; i++){
            areaSquare *= (p - triangleSides[i]);
        }


        double area = Math.sqrt(areaSquare);//искомая площадь
        /*
        double p = (ab + bc + ac) / 2;//вычисляем полупериметр
        double s = Math.sqrt(p * (p - ab) * (p - bc) * (p - ac));//вычисляем площадь по формуле Герона
        */


        System.out.printf("%.2f", area);
    }
}
