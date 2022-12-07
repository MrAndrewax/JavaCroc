package ru.croc.project.flashCards;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static Map<Integer, Word> getWords(){
        return null;
    }
    public static int getDay(){
        return 1;
    }
    public static void getGroupByNum(int groupNum){}
}

class Word{
    private int id;
    private String russian;
    private String english;
    private String transcription;
    private int right;
    private int wrong;

    Word(int id, String russian, String english, String transcription, int right, int wrong){
        this.id = id;
        this.russian = russian;
        this.english = english;
        this.transcription = transcription;
        this.right = right;
        this.wrong = wrong;
    }

    int getKnowledgeIndex(){
        return right - wrong;
    }

}