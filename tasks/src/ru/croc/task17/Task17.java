package ru.croc.task17;

import ru.croc.task17.interactionWithDB.SetInfoInDB;

public class Task17 {
    public static void main(String[] args){
        String pathToCSVFile = "/home/andrew/learning/java_croc/tasks/src/ru/croc/task17/interactionCSV/table.CSV";//args[0]
        final String DB_URL = "jdbc:postgresql://localhost/testDB";//args[1]
        final String USER = "postgres";//args[2]
        final String PASSWORD = "pass";//args[3]

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        SetInfoInDB setterInfoInDB = new SetInfoInDB(DB_URL, USER, PASSWORD);
        setterInfoInDB.setInformationInDB(pathToCSVFile);
    }
}