package ru.croc.task12;

import java.util.List;
import java.util.Set;

public class Filter implements BlackListFilter{
    public void filterComments(List<String> comments, Set<String> blackList){
        comments.removeIf(blackList::contains);
    }
}
