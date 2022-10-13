import java.util.Scanner;

public class Main {

    static class Point {
        double x;
        double y;
    }

    public static void main(String[] args) {

        //ввод точек
        //провереям образуют ли эти точки треугольник.
        //проверяем не делим ли мы на ноль
        //сделать проверку на то, что мы ввели две одинаковые точки.
        Scanner in = new Scanner(System.in);

        Point a = new Point();
        System.out.print("Введите координату х вершины №1:");
        a.x = in.nextDouble();
        System.out.print("Введите координату y вершины №1:");
        a.y = in.nextDouble();

        Point b = new Point();
        System.out.print("Введите координату х вершины №2:");
        b.x = in.nextDouble();
        System.out.print("Введите координату y вершины №2:");
        b.y = in.nextDouble();

        Point c = new Point();
        System.out.print("Введите координату х вершины №3:");
        c.x = in.nextDouble();
        System.out.print("Введите координату y вершины №3:");
        c.y = in.nextDouble();

        double ab = Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
        double bc = Math.sqrt(Math.pow(c.x - b.x, 2) + Math.pow(c.y - b.y, 2));
        double ac = Math.sqrt(Math.pow(a.x - c.x, 2) + Math.pow(a.y - c.y, 2));
        //неравенство треугольника
        if ( (ab > bc + ac) || (bc > ab + ac) || (ac > ab + bc)){
            System.out.print("Точки не удовлетворяют неравенству треугольника => они не образуют треугольник");//exception
        } else {
            double p = (ab + bc + ac) / 2;

            //вычисляем площадь по формуле Герона
            double s = Math.sqrt(p * (p - ab) * (p - bc) * (p - ac));

            System.out.println(s);
            //надо разобраться с точностью вывода
        }
    }
}
