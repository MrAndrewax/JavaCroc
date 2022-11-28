package ru.croc.task15;

import java.util.ArrayList;
import java.util.List;

public class Department{
    private final String name;
    private final String parent;
    private final int hours;
    private List<Department> children;

    Department(String name, String parent, int hours){
        this.name = name;
        this.parent = parent;
        this.hours = hours;
        children = new ArrayList<>();
    }

    public String getName(){
        return name;
    }

    public String getParent(){
        return parent;
    }

    public int getHours(){
        return hours;
    }

    public List<Department> getChildren(){
        return children;
    }

    public void addChildren(Department child){
        children.add(child);
    }

    public boolean isRoot(){
        return parent.equals("-");
    }

    public String childrenToString(){
        StringBuilder result = new StringBuilder();
        for (Department dep : children){
            result.append(dep.getName()).append(" ");
        }
        return result.toString();
    }

    @Override
    public String toString() {
        return (
                "name: " + name + " parentName: " + parent
                        + " hours: " + hours + "\n" + childrenToString() + "\n"
        );
    }
}