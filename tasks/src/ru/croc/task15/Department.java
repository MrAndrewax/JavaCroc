package ru.croc.task15;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /*Этот метод принимает на вход строку типа "departmentName,parentName,numberOfHours"
        и возвращает экземпляр класса Department*/
    public static Department stringToDepartment(String line){
        String[] departmentComponents = line.split(",");
        return new Department(
                departmentComponents[0],
                departmentComponents[1],
                Integer.parseInt(departmentComponents[2])
        );
    }

    /*Этот метод читает из файла строки определнного типа,
создаёт соответствующие им экзмепляры класса Department
и добавляет их в Map*/
    public static Department getDepartmentsFromFile(String path){//"/home/andrew/learning/java_croc/tasks/src/ru/croc/task13/1.txt"
        Department root = null;
        try (FileReader fr = new FileReader(path);
             BufferedReader reader = new BufferedReader(fr) ){

            Map<String, Department> departmentsMap = new HashMap<>();

            String line = reader.readLine();
            while (line != null) {
                Department department = Department.stringToDepartment(line);

                String parentName = department.getParent();

                if (parentName.equals("-")){
                    root = department;
                }

                if (!parentName.equals("-")){
                    Department parent = departmentsMap.get(parentName);
                    parent.addChildren(department);
                }

                departmentsMap.put(department.getName(), department);
                line = reader.readLine();
            }
            return root;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int calculateTime(){
        List<Department> children = this.getChildren();

        int childrenMaxHours = 0;
        if (children.isEmpty()){
            return this.getHours();
        }
        for (Department child : children){
            int childHours = child.calculateTime();
            if (childHours > childrenMaxHours){
                childrenMaxHours = childHours;
            }
        }
        return this.getHours() + childrenMaxHours;
    }
}