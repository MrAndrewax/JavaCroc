package ru.croc.task14;

import java.util.Iterator;
import java.util.Set;
import java.util.function.Predicate;

interface BlackListFilter{
    //C - тип коллекции, Т - тип элементов коллекции.
    default <T, C extends Iterable<T>> void filterComments(C collection, Set<String> blackList, Predicate<T> filterCondition){
        Iterator<T> commentsIterator = collection.iterator();
        while (commentsIterator.hasNext()){
            boolean isBadWordInComment = filterCondition.test(commentsIterator.next());
            if (isBadWordInComment){
                commentsIterator.remove();
            }
        }
    }
}