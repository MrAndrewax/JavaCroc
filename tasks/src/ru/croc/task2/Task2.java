




package ru.croc.task2;

/*Замечания ко второй задаче:
1) Также имя класса.
2) Смотри, решение мб и рабочее, но уезжающие вправо if`ы - обычно антипаттерн.
    Подумай, есть идеи, как можно сделать не через if`ы?*/

/*Решение замечаний
1) Поправил))
2) Я сделал дополнительный метод
    public static int recursive_implementation(BigDecimal cur, int countOfThousands);
    Который избавляет нас от бесконечных if'ов*/

import java.math.BigDecimal;
import java.math.BigInteger;

public class Task2 {
    //что такое public?
    //что такое static?
    static final BigInteger kInt = new BigInteger("1024");
    static final BigDecimal kDouble = new BigDecimal("1024");
    public static int recursiveImplementation(BigDecimal cur, int countOfThousands){
        BigInteger integerCur = cur.toBigInteger();
        integerCur = integerCur.divide(kInt);//сделать константой
        if (integerCur.compareTo(BigInteger.ZERO) > 0){
            int result = recursiveImplementation(cur.divide(kDouble), countOfThousands + 1);
            if (result > 0){
                return result;
            }
        }
        switch (countOfThousands){
            case(4):
                System.out.printf("%.1f TB", cur);
                return 4;
            case(3):
                System.out.printf("%.1f GB", cur);
                return 3;
            case(2):
                System.out.printf("%.1f МB", cur);
                return 2;
            case(1):
                System.out.printf("%.1f KB", cur);
                return 1;
            case (0):
                System.out.printf("%.1f B", cur);
                return 0;
        }
        return -1;
    }

    public static void main(String[] args) {
        String strBytes = args[0];//shift f6
        BigDecimal bytes = new BigDecimal(strBytes);
        recursiveImplementation(bytes, 0);
    }
}
