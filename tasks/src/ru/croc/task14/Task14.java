package ru.croc.task14;


import java.util.*;

class CommentsIterator <T, C extends Iterable<T>> implements Iterator<T>{
    C iterableCollection;

    @Override
    public boolean hasNext() {
        return iterableCollection.iterator().hasNext();
    }

    @Override
    public T next() {
        return iterableCollection.iterator().next();
    }

    @Override
    public void remove(){
        iterableCollection.iterator().remove();
    }
}

class Comments <T, C extends Iterable<T>>{//C - тип коллеции. T - тип комментария
    Iterator<T> iterator(){
        return new CommentsIterator<T, C>();
    }
}






public class Task14 {
    public static void main(String[] args){

    }
}
