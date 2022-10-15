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
import java.math.RoundingMode;
import java.util.Scanner;

public class Task2 {


    //BigDecimal k1 = new BigDecimal("1024");
    public static void recursive_implementation(BigDecimal cur, int countOfThousands){

        //System.out.printf("cur = %.4f, count of thousands = %d\n", cur, countOfThousands);

        //BigDecimal temp = cur.divide(new BigDecimal("1024"), RoundingMode.DOWN);
        //System.out.printf("temp = %.1f\n temp.compareTo(new BigDecimal(\"0\")): %d\n\n", temp, temp.compareTo(new BigDecimal("0")));
        BigInteger integerCur = cur.toBigInteger();
        integerCur = integerCur.divide(new BigInteger("1024"));

        //System.out.printf("integerCur = %d\n temp.compareTo(new BigDecimal(\"0\")): %d\n\n", integerCur, temp.compareTo(new BigDecimal("0")));
        if (integerCur.compareTo(BigInteger.ZERO) > 0){//проблема в условии
            recursive_implementation(cur.divide(new BigDecimal("1024")), countOfThousands + 1);
        }
        switch (countOfThousands){
            case(4)://terabytes
                System.out.printf("%.1f TB", cur);
                System.exit(0);
            case(3)://gigabytes
                System.out.printf("%.1f GB", cur);
                System.exit(0);
            case(2)://megabytes
                System.out.printf("%.1f МB", cur);
                System.exit(0);
            case(1)://kilobytes
                System.out.printf("%.1f KB", cur);
                System.exit(0);
            case (0)://bytes
                System.out.printf("%.1f B", cur);
                System.exit(0);
        }
    }

    public static void main(String[] args) {

        //String strBytes = args[0];
        /*
        BigDecimal k1 = new BigDecimal("1024");
        String strBytes = args[0];

        BigDecimal bytes = new BigDecimal(strBytes);
        if (bytes.compareTo(k1) > 0){
            BigDecimal kilobytes = bytes.divide(k1);

            if (kilobytes.compareTo(k1) > 0){
                BigDecimal megabytes = kilobytes.divide(k1);
                if (megabytes.compareTo(k1) > 0){
                    BigDecimal gigabytes = megabytes.divide(k1);
                    if (gigabytes.compareTo(k1) > 0){
                        BigDecimal terabytes = gigabytes.divide(k1);
                        System.out.printf("%.1f TB", terabytes);

                    }
                    else{
                        System.out.printf("%.1f GB", gigabytes);
                    }
                }
                else{
                    System.out.printf("%.1f MB", megabytes);
                }
            }
            else{
                System.out.printf("%.1f KB", kilobytes);
            }
        }
        else{
            System.out.printf("%.1f B", bytes);
        }
        */
        /*
        BigInteger k = new BigInteger("1024");
        BigInteger bytes = new BigInteger("123213");
        BigInteger modulo = bytes.mod(k);
         */
        /*
        Scanner in = new Scanner(System.in);
        System.out.print("Input count of bytes: ");
        String strBytes = in.nextLine();
        */

        String strBytes = args[0];
        BigDecimal bytes = new BigDecimal(strBytes);
        recursive_implementation(bytes, 0);

    }
}
