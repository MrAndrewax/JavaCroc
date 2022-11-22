package ru.croc.task13;

import java.util.ArrayList;
import java.util.List;

public class User {
    private List<Integer> history;

    User(int... films) {
        history = new ArrayList<>();
        for (int film : films) {
            history.add(film);
        }
    }

    public List<Integer> getHistory() {
        return history;
    }

    public void setHistory(List<Integer> history) {
        this.history = history;
    }

    @Override
    public String toString() {
        return history.toString();
    }
}
