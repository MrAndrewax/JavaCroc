/*  Задан массив целых чисел.
    Необходимо переставить наименьшее из этих чисел в начало массива,
    а наибольшее - в конец.*/

public class Task3 {
    static void printArr(int[] arr){
        for (int elem : arr) {
            System.out.print(elem + " ");
        }
        System.out.print("\n");
    }

    static int minInArray(int[] arr){
        //рассмотреть случай, когда length == 0
        int indexOfMin = 0;
        for (int i = 1; i < arr.length; i++){
            if (arr[i] < arr[indexOfMin]){
                indexOfMin = i;
            }
        }
        return indexOfMin;
    }
    static int maxInArray(int[] arr){
        //рассмотреть случай, когда length == 0
        int indexOfMax = 0;
        for (int i = 1; i < arr.length; i++){
            if (arr[i] > arr[indexOfMax]){
                indexOfMax = i;
            }
        }
        return indexOfMax;
    }

    public static void main(String[] args) {

        int [] arr = new int [args.length];
        for (int i =0; i < args.length; i++){
            arr[i] = Integer.parseInt(args[i]);
        }

        int indexMin = minInArray(arr);//это индекс минимального элемента в массиве
        int indexMax = maxInArray(arr);//это индекс максимального элемента в массиве

        /*Меняем первый элемент с минимальным местами.*/
        int min = arr[indexMin];
        arr[indexMin] = arr[0];
        arr[0] = min;

        if (indexMax == 0){
            /*если индекс максимального элемента равен нулю, то в массиве он поменялся местами с минимальным элементом.
                то есть если изначально indexMax == 0 => сейчас indexMax == indexMin*/
            indexMax = indexMin;
        }

        int max = arr[indexMax];
        arr[indexMax] = arr[arr.length - 1];
        arr[arr.length - 1] = max;

        printArr(arr);
    }
}