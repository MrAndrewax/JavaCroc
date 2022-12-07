package ru.croc.task14;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Predicate;

interface BlackListFilter{
    /**
     * From the given list of comments removes ones
     * that contain words from the black list.
     *
     * @param collectionWithComments - Произовольная коллекция элементов типа T,
     *                               которая имплементирует интерфейс Iterable
     * @param filterCondition - Предикат, который возвращает True в случае,
     *                        если комментарий содержит запрещенное слово,
     *                        иначе False.
     * @return Произвольная коллекция типа T
     */
    default <T> Collection<T> filterComments(Iterable<T> collectionWithComments, Predicate<T> filterCondition){

        Collection<T> clearComments = new ArrayList<>();

        for (T comment : collectionWithComments) {
            boolean isBadWordInComment = filterCondition.test(comment);
            if (!isBadWordInComment) {
                clearComments.add(comment);
            }
        }
        return clearComments;
    }
}