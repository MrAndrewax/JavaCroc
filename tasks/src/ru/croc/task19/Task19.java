package ru.croc.task19;

import java.io.FileWriter;
import java.io.IOException;

public class Task19 {
    public static void main(String[] args) {
        try(FileWriter writer = new FileWriter("Task19Output.txt"))
        {
            String text = "Hello, world!";
            writer.write(text);
            writer.flush();
        }
        catch(IOException ex){
            throw new RuntimeException();
        }
    }
}