/*Замечания ко второй задаче:
1) Также имя класса.
2) Смотри, решение мб и рабочее, но уезжающие вправо if`ы - обычно антипаттерн.
    Подумай, есть идеи, как можно сделать не через if`ы?*/

/*Решение замечаний
1) Поправил))
2) Я сделал дополнительный метод
    public static void recursive_implementation(BigDecimal cur, int countOfThousands);
    Который избавляет нас от бесконечных if'ов*/

import java.math.BigDecimal;
import java.math.BigInteger;

public class Task2 {
    public static void recursive_implementation(BigDecimal cur, int countOfThousands){
        BigInteger integerCur = cur.toBigInteger();
        integerCur = integerCur.divide(new BigInteger("1024"));
        if (integerCur.compareTo(BigInteger.ZERO) > 0){
            recursive_implementation(cur.divide(new BigDecimal("1024")), countOfThousands + 1);
        }
        switch (countOfThousands){
            case(4):
                System.out.printf("%.1f TB", cur);
                System.exit(0);
            case(3):
                System.out.printf("%.1f GB", cur);
                System.exit(0);
            case(2):
                System.out.printf("%.1f МB", cur);
                System.exit(0);
            case(1):
                System.out.printf("%.1f KB", cur);
                System.exit(0);
            case (0):
                System.out.printf("%.1f B", cur);
                System.exit(0);
        }
    }

    public static void main(String[] args) {
        String strBytes = args[0];
        BigDecimal bytes = new BigDecimal(strBytes);
        recursive_implementation(bytes, 0);

    }
}
