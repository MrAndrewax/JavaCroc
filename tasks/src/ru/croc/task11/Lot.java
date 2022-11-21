package ru.croc.task11;

/*текущую стоимость, имя пользователя, предложившего ее, и время окончания торгов по этому лоту.*/
public class Lot {
    private static final Object lock = new Object();
    private String userName;
    private double currentValue;
    private long endTime;


    /*метод “ставки”, который обновляет текущую стоимость лота и сохраняет предложившего ее пользователя,
    если торги по лоту еще ведутся по времени и предложенная цена выше текущей.*/
    public  void bid(String userName, double value, long time){
        synchronized (lock){
            if (value > currentValue && time < endTime){
                this.userName = userName;
                this.currentValue = value;
            }
        }
    }

    /*Второй метод - метод получения имени пользователя победителя (итогового, не текущего лидера).*/
    public String getWinnerName(long currentTime){
        synchronized(lock){
            if (currentTime > endTime){
                return userName;
            }
            return null;
        }
    }
}

