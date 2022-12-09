package ru.croc.project.flashCards;
//надо добавить прокерку на дубликаты

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.*;


class WorkWithWordsDb{
    static final String DB_URL = "jdbc:postgresql://localhost:5432/WordsDB";//args[0]
    static final String USER = "postgres";//args[1]
    static final String PASSWORD = "Sekret2504!";//args[2]
    public static void dropDB(){
        Connection connection;
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            Statement stmt = connection.createStatement();

            //Если таблицы нет, то создаём
            String dropTableWords = "DROP TABLE IF EXISTS words;";
            //" PRIMARY KEY (id));";
            stmt.executeUpdate(dropTableWords);

        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
        }
    }
    public static void addWordToDB(Word word){
        Connection connection;
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            Statement stmt = connection.createStatement();

            //Если таблицы нет, то создаём
            String createTableWords = "CREATE TABLE IF NOT EXISTS words " +
                    "(id int NOT NULL, " +
                    "russian VARCHAR(255)," +
                    "english VARCHAR(255), " +
                    "transcription VARCHAR(255), " +
                    "group_number int, " +
                    "right_count int, " +
                    "wrong_count int " + ");";
                    //" PRIMARY KEY (id));";
            stmt.executeUpdate(createTableWords);

            int id = WorkWithWordsDb.getMaxIndex();
            System.out.println(word);
            String insertWordInTable = String.format(
                    "INSERT INTO words (id, russian, english, transcription, group_number, right_count, wrong_count) " +
                            "VALUES (%d, '%s', '%s', '%s', %d, %d, %d)",
                    id + 1, word.getRussian(), word.getEnglish(), word.getTranscription(), word.getGroup(), word.getRight(), word.getWrong());
            stmt.executeUpdate(insertWordInTable);

        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
        }
    }
    public static void addWordsToDB(Iterable<Word> words){//тут можно заменить на Iterable
        for (Word word : words) {
            addWordToDB(word);
        }
    }
    public static void deleteWordFromDB(String word){
    }
    public static void updateRightOrWrong(int id, int rightOrWrong){//right = 0; wrong = 1
        Connection connection;
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            Statement stmt = connection.createStatement();

            String sqlWrong  = "SELECT * FROM words WHERE id = " + id;
            ResultSet resultSet = stmt.executeQuery(sqlWrong);
            resultSet.next();
            int right = resultSet.getInt("right_count");
            int wrong = resultSet.getInt("wrong_count");
            if (rightOrWrong == 0){
                right++;
            }
            else{
                wrong++;
            }
            String sql = String.format("UPDATE words SET right_count = %d, wrong_count = %d WHERE id = '%d'", right, wrong, id);
            stmt.executeUpdate(sql);
            connection.close();

        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();

        }
    }
    public static Map<Integer, Word> getGroupByNum(int groupNum){
        Connection connection;
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            Statement stmt = connection.createStatement();

            String sql  = "SELECT * FROM words WHERE group_number = " + groupNum;

            ResultSet resultSet = stmt.executeQuery(sql);

            Map<Integer, Word> words = new HashMap<>();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String russian = resultSet.getString("russian");;
                String english = resultSet.getString("english");;
                String transcription = resultSet.getString("transcription");
                int group = resultSet.getInt("group_number");;
                int right = resultSet.getInt("right_count");;
                int wrong = resultSet.getInt("wrong_count");;
                Word word = new Word(id, russian, english, transcription, group, right, wrong);
                words.put(word.getId(), word);
            }

            connection.close();
            return words;
        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
            return null;
        }
    }
    public static int getMaxIndex(){
        Connection connection;
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            Statement stmt = connection.createStatement();

            String sql  = "SELECT MAX(id) AS \"MAX\" FROM words";

            ResultSet resultSet = stmt.executeQuery(sql);
            resultSet.next();
            if (resultSet == null){
                System.out.println("AS:FLJFAS:LJFAS:LFJ");
            }
            int index = resultSet.getInt("MAX");
            System.out.println("ID = " + index);
            connection.close();
            return index;
        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
            return 1;
        }
    }
}

class RecommendationSystem{
    public static void getRecommendation(){
        int day = getDay();
        //смотрим остатки при делении на 2 и 3.
    }
    public static int getDay(){
        return 1;
    }
}

class MyInput{
    public static Word stringToWord(String line){
        System.out.println(line);
        String[] arr = line.split(";");

        int id = -1;//GetMaxIndexFromDB
        String russian = arr[1].trim();
        String english = arr[0].trim();
        String transcription = "";
        int group = 1;
        int right = 0;
        int wrong = 0;
        Word word = new Word(id, russian, english, transcription, group, right, wrong);
        return word;
    }
    public static List<Word> parseFileWithUsers(String path){
        try (FileReader fr = new FileReader(path);
             BufferedReader reader = new BufferedReader(fr)){

            List<Word> words = new ArrayList<>();
            String line = reader.readLine();

            while (line != null) {
                Word word = stringToWord(line);
                words.add(word);
                line = reader.readLine();
            }
            return words;
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
    public static String getWordFromInput(){
        return new Scanner(System.in).nextLine();
    }
}

//The Leitner system

class Menu{//По сути это и есть интерфейс микросервиса
    public static void printMenu(){
        String menuStr = "1) Посмотреть состав группы" +
                "2) Узнать рекомендацию на сегодня" +
                "3) Поиграть в группу" +
                "4) Добавить новые слова в систему" +
                "5) Удалить слово из системы";
        System.out.println();
    }

    public static void show(){}
    public static void getRec(){}//есть отдельный клас
    public static void play(){}
}
class Game{
    public static void playGroupByNum(int groupNum){
        Map<Integer, Word> words = WorkWithWordsDb.getGroupByNum(groupNum);
        System.out.println(words);
        assert words != null;
        for (Map.Entry<Integer, Word> entry : words.entrySet()) {
            //hard code
            System.out.println("Введите перевод для " + entry.getValue().getEnglish());
            String translatedWord = MyInput.getWordFromInput().toLowerCase();
            if (translatedWord.equals(entry.getValue().getRussian().toLowerCase())){
                System.out.println("Правильно!");
                //увеличиваем right на 1
                WorkWithWordsDb.updateRightOrWrong(entry.getValue().getId(), 0);
            } else{
                System.out.println("Неправильно!");
                //увеличиваем wrong на 1
                WorkWithWordsDb.updateRightOrWrong(entry.getValue().getId(), 1);
            }
        }
        //ещё надо перетаскивать в другую группу
    }
}
public class Main {
    public static void main(String[] args){
        String path = "/home/andrew/learning/java_croc/tasks/src/ru/croc/project/flashCards/newWords.txt";
        WorkWithWordsDb.dropDB();
        List<Word> words = MyInput.parseFileWithUsers(path);
        WorkWithWordsDb.addWordsToDB(words);
        Map<Integer, Word> wordsMap = WorkWithWordsDb.getGroupByNum(1);
        Game.playGroupByNum(1);
    }
}

class Word{
    private final int id;
    private final String russian;
    private final String english;
    private final String transcription;
    private final int group;
    private int right;
    private int wrong;

    Word(int id, String russian, String english, String transcription, int group, int right, int wrong){
        this.id = id;
        this.russian = russian;
        this.english = english;
        this.transcription = transcription;
        this.group = group;
        this.right = right;
        this.wrong = wrong;
    }

    int getKnowledgeIndex(){
        return right - wrong;
    }

    void setRight(int right){
        this.right = right;
    }

    void setWrong(int wrong){
        this.wrong = wrong;
    }

    public int getId() {
        return id;
    }

    public String getRussian() {
        return russian;
    }

    public String getEnglish() {
        return english;
    }

    public String getTranscription() {
        return transcription;
    }

    public int getGroup() {
        return group;
    }

    public int getRight() {
        return right;
    }

    public int getWrong() {
        return wrong;
    }

    @Override
    public String toString() {
        return "id " + id + "\n" +
                "russian " + russian + "\n" +
                "english " + english + "\n" +
                "transcription " + transcription + "\n" +
                "group " + group + "\n" +
                "right " + right + "\n" +
                "wrong " + wrong;

    }
}

//words = Map<Integer, Word>