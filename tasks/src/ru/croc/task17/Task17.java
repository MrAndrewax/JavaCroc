package ru.croc.task17;

public class Task17 {
    public static void main(String[] args){
        final String DB_URL = "jdbc:postgresql://localhost/testDB";//args[0]
        final String USER = "postgres";//args[1]
        final String PASSWORD = "pass!";//args[2]

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver is not found. Include it in your library path ");
            e.printStackTrace();
            return;
        }
        SetInfoInDB setterInfoInDB = new SetInfoInDB(DB_URL, USER, PASSWORD);
        setterInfoInDB.setInformationInDB();
    }
}