
import java.math.BigDecimal;

public class task2 {
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
    }
}
