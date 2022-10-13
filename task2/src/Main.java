import java.math.BigDecimal;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
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
                        String.format("%.1f TB", terabytes);
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
/*
        int terabyte;
        int petabyte;
        Scanner in = new Scanner(System.in);
        System.out.print("Введите координату х вершины №3:");
        in.nextDouble();
        System.out.println();
        */
    }
}
