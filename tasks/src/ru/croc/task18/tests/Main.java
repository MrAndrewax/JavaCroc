package ru.croc.task18.tests;

import ru.croc.task17.interactionWithDB.SetInfoInDB;
import ru.croc.task18.config.DataBaseInfo;

/*Денис, я это для себя делал. Не ругай пожалуйста за неаккуратность)00)00)*/

public class Main {
    public static void main(String[] args) throws Exception {
        Class.forName("org.postgresql.Driver");
        SetInfoInDB setterInfoInDB = new SetInfoInDB(DataBaseInfo.DB_URL, DataBaseInfo.USER, DataBaseInfo.PASSWORD);
        setterInfoInDB.setInformationInDB("/home/andrew/learning/java_croc/tasks/src/ru/croc/task17/interactionCSV/table.CSV");
        Tester tester = new Tester();
        tester.testFindProduct1();
        tester.testFindProduct2();
        tester.testCreateProduct1();
        //tester.testUpdateProduct1();
        //tester.testUpdateProduct2();
        tester.testDeleteProduct1();
        tester.testCreateOrder1();
    }
}



