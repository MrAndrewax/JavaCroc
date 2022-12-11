package ru.croc.task17;

public class Task17 {
    public static void main(String[] args){
        String pathToCSVFile = "/home/andrew/learning/java_croc/tasks/src/ru/croc/task17/table.CSV";//args[0]
        final String DB_URL = "jdbc:postgresql://localhost/testDB";//args[1]
        final String USER = "postgres";//args[2]
        final String PASSWORD = "Sekret2504!";//args[3]

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        SetInfoInDB setterInfoInDB = new SetInfoInDB(DB_URL, USER, PASSWORD);
        setterInfoInDB.setInformationInDB(pathToCSVFile);
    }
}