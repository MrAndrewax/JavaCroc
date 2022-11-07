package ru.croc.task8;
import java.io.*;

import java.io.FileReader;

public class Task8 {
    public static void main(String[] args){
        String path = args[0];
        try(FileReader reader = new FileReader(path))
        {
            StringBuilder textFromFile = new StringBuilder("");
            int c;
            while( (c = reader.read()) != -1 ){
                textFromFile.append((char) c);
            }
            String[] words = String.valueOf(textFromFile).trim().replaceAll(" +", " ").split("\\s");
            System.out.println(words.length);
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}
