package ru.croc.task18.Tests;

import ru.croc.task17.SetInfoInDB;
import ru.croc.task18.Config.DataBaseInfo;

public class Main {
    public static void main(String[] args) throws Exception {
        Class.forName("org.postgresql.Driver");
        SetInfoInDB setterInfoInDB = new SetInfoInDB(DataBaseInfo.DB_URL, DataBaseInfo.USER, DataBaseInfo.PASSWORD);
        setterInfoInDB.setInformationInDB("/home/andrew/learning/java_croc/tasks/src/ru/croc/task17/table.CSV");

        Tester tester = new Tester();
        tester.testFindProduct1();
        tester.testFindProduct2();
        tester.testCreateProduct1();
        //tester.testUpdateProduct1();
        //tester.testUpdateProduct2();
        tester.testDeleteProduct1();
    }
}



