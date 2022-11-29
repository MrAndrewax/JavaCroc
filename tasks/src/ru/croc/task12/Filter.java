package ru.croc.task12;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Filter implements BlackListFilter{
    public void filterComments(List<String> comments, Set<String> blackList){

        for (int i = 0; i < comments.size(); i++){
            List<String> wordsInComment = new ArrayList<>(List.of(comments.get(i).split(" ")));

            for (String word : wordsInComment){
                if (blackList.contains(word)){
                    comments.set(i, "");
                }
            }
        }
        //comments.removeIf(blackList::contains);
        comments.removeIf(comment -> comment.equals(""));
    }
}
