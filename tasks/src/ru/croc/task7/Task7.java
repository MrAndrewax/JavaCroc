package ru.croc.task7;


import java.util.Scanner;






public class Task7 {

    public static void main(String[] args){


        Scanner in = new Scanner(System.in);
        String input = in.nextLine().replace(",", "").replace("\"", "");;

        String[] strPositions = input.split(" ");
        ChessPosition[] positions = new ChessPosition[strPositions.length];

        for (int i = 0; i < strPositions.length; i++){
            try{
                positions[i] = ChessPosition.parse(strPositions[i]);
            }
            catch (IllegalPositionException e2){
                System.out.println("Вы ввели некорректные компоненты шахматного поля.");
                System.exit(0);
            }
        }
        try{
            ChessPosition.check(positions);
            System.out.println("ok");
        }
        catch (IllegalMoveException e2){
            System.out.println("Конь так не ходит: " + e2.message);
        }

    }
}
