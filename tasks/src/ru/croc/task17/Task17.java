package ru.croc.task17;

/*
* Я решил сделать 3 таблицы.
* 1) users - Два столбца: user_id (<номер_заказа:integer>), user_name(<логин_пользователя:string>)
* 2) products - 3 столбца: product_id (<артикул_товара:string>), product_name (<название_товара:string>), price (<цена_в_рублях:integer>)
* 3) orders - эта таблица отражает отношение многие ко многим. Каждому пользователю может соответствовать несколько заказов и наоборот,
* Конкретному заказу может соотвествовать несколько пользователей.
* 3 столбца: user_id, product_id*/


public class Task17 {
    public static void main(String[] args){
        final String DB_URL = "jdbc:postgresql://localhost/testDB";//args[0]
        final String USER = "postgres";//args[1]
        final String PASSWORD = "pass!";//args[2]
        String path = "/home/andrew/learning/java_croc/tasks/src/ru/croc/task17/table.CSV";//args[3]

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver is not found. Include it in your library path ");
            e.printStackTrace();
            return;
        }

        SetInfoInDB setterInfoInDB = new SetInfoInDB(DB_URL, USER, PASSWORD);
        setterInfoInDB.setInformationInDB(path);
    }
}