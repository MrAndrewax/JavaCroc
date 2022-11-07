package ru.croc.task9;

import java.util.Objects;

public class Task9{
    public static String[] remove(String[] values, int index) {
        String[] result = new String[values.length - 1];

        for (var i = 0; i < values.length; i++) {
            if (i != index) {
                int newIndex = i < index ? i : i - 1;
                result[newIndex] = values[i];
            }
        }
        return result;
    }
    public static void main(String[] args){
        String path = args[0];//"КРОК/работа/src/./../../универ/../../../мемы/котики/";


        String[] files = path.split("/");


        int j = 0;
        for (String file: files){
            if (Objects.equals(file, ".")){
                j++;
            }
        }

        String[] commonFiles = new String [files.length - j];

        int k = 0;
        for (String file: files){
            if (!Objects.equals(file, ".")){
                commonFiles[k] = file;
                k++;
            }
        }

        int l = 0;
        while (l < commonFiles.length){
            if (l != 0 && Objects.equals(commonFiles[l], "..") && !Objects.equals(commonFiles[l-1], "..")){
                commonFiles = remove(commonFiles, l);
                commonFiles = remove(commonFiles, l-1);
                l--;
            }
            else{
                l++;
            }
        }

        StringBuilder resultSB = new StringBuilder(commonFiles[0]);
        for (int y = 1; y < commonFiles.length; y++){
            resultSB.append("/").append(commonFiles[y]);
        }
        String result = String.valueOf(resultSB);
        System.out.println(result);
    }
}