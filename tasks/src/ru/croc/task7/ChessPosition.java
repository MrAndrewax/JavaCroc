package ru.croc.task7;

public class ChessPosition{
    private int x;
    private int y;
    ChessPosition(){
        x = 0;
        y = 0;
    }
    ChessPosition(int x, int y) throws IllegalPositionException {
        if (x < 0 || x > 8 || y < 0 || y > 8){
            throw new IllegalPositionException();
        }
        else {
            this.x = x;
            this.y = y;
        }
    }

    double getX(){
        return x;
    }
    double getY(){
        return y;
    }
    void setX(int x) throws IllegalPositionException {
        if (x < 0 || x > 8){
            throw new IllegalPositionException();
        } else{
            this.x = x;
        }
    }
    void setY(int y) throws IllegalPositionException {
        if (y < 0 || y > 8){
            throw new IllegalPositionException();
        } else{
            this.y = y;
        }
    }

    @Override
    public String toString(){
        String result = "";
        switch (x) {
            case (1) -> result += "a";
            case (2) -> result += "b";
            case (3) -> result += "c";
            case (4) -> result += "d";
            case (5) -> result += "e";
            case (6) -> result += "f";
            case (7) -> result += "g";
            case (8) -> result += "h";
        }
        result += (y + 1);
        return result;
    }

    static ChessPosition parse(String position) throws IllegalPositionException {
        String s1 = position.substring(0, 1);
        String s2 = position.substring(1);
        char c = s1.charAt(0);

        int x = switch (c) {
            case ('a') -> 0;
            case ('b') -> 1;
            case ('c') -> 2;
            case ('d') -> 3;
            case ('e') -> 4;
            case ('f') -> 5;
            case ('g') -> 6;
            case ('h') -> 7;
            default -> -1;
        };

        int y = Integer.parseInt(s2);

        if (x == -1 || y < 0 || y > 9){
            throw new IllegalPositionException();
        }
        else{
            return new ChessPosition(x, y);
        }
    }

    static void check(ChessPosition[] positions) throws IllegalMoveException {
        for (int i = 0; i < positions.length - 1; i++){
            double dx = Math.abs(positions[i].getX() - positions[i + 1].getX());
            double dy = Math.abs(positions[i].getY() - positions[i + 1].getY());
            if ( !((dx == 1 && dy == 2) || (dx == 2 && dy == 1)) ){
                throw new IllegalMoveException(positions[i].toString() + " -> " + positions[i+1].toString());
            }
        }
    }
}