package ru.croc.task11;

import java.util.Date;

public class Lot {
    //текущая стоимость
    private volatile double currentValue;

    //имя пользователя, предложившего ее
    private volatile String userName;

    //время окончания торгов по этому лоту
    private final long endTime;

    Lot(long endTime){
        currentValue = 0;
        this.endTime = endTime;
    }

    /*метод “ставки”, который обновляет текущую стоимость лота и сохраняет предложившего ее пользователя,
    если торги по лоту еще ведутся по времени и предложенная цена выше текущей.*/
    public synchronized void bid(String userName, double value, long time){
        if (value > currentValue && time < endTime){
            this.userName = userName;
            this.currentValue = value;
        }
    }

    /*Второй метод - метод получения имени пользователя победителя (итогового, не текущего лидера).*/

    public String getWinnerName(){
        if (new Date().getTime() < endTime){
            return null;//Аукцион еще не закончился
        } else{
            return userName;//Если аукцион закончен, то мы можен узнать победителя
        }
    }
}

