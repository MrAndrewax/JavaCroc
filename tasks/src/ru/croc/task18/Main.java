package ru.croc.task18;

import java.sql.*;
import java.util.List;


/*Таблицы те же, что и в task17*/
/*
 * 1) users - Два столбца: user_id (<номер_заказа:integer>), user_name(<логин_пользователя:string>)
 * 2) products - 3 столбца: product_id (<артикул_товара:string>), product_name (<название_товара:string>), price (<цена_в_рублях:integer>)
 * 3) orders - эта таблица отражает отношение многие ко многим. Каждому пользователю может соответствовать несколько заказов и наоборот,
 * Конкретному заказу может соотвествовать несколько пользователей.
 * 3 столбца: user_id, product_id*/

public class Main {
    public static void main(String[] args) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver is not found. Include it in your library path ");
            e.printStackTrace();
        }
    }
}