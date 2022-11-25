package ru.croc.task15;

/*Государственная организация принимает заявки, на которые необходимо формировать ответ. В формировании ответа участвуют разные отделы организации по следующему принципу:
сначала заявка рассматривается корневым отделом;
затем она переводится во все его дочернии отделы, в которых рассматривается параллельно;
и т.д., пока заявка не пройдет через все отделы.

Для каждого отдела известно количество часов, необходимых для рассмотрения заявки.
У каждого отдела может быть только один родительский, после которого начинается работа над заявкой.

Реализовать метод, вычисляющий время,
необходимое для обработки заявки в заданной конфигурации
(при условии что все отделы начинают обработку заявки без задержек сразу после предшествующих).

Конфигурация отделов задается файлом со следующим форматом строк <код отдела>,
<код родительского отдела или “-”, если отдел корневой>, <время обработки заявки в часах>. Например:

A,-,1
B1,A,3
C11,B1,1
C12,B1,1
B2,A,1
C21,B2,4
B3,A,2
C31,B3,1
D311,C31,1

Время обработки заявки для этой конфигурации: 6 часов
*/



//ВОПРОСЫ
//обязательно ли родителя определяют перед ребёнком?


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

class Department{
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

public class Main {

    public static Department stringToDepartment(String line){
        String[] departmentComponents = line.split(",");
        return new Department(
                departmentComponents[0],
                departmentComponents[1],
                Integer.parseInt(departmentComponents[2])
        );
    }

    public static Map<String, Department> getDepartmentsFromFile(String path){//"/home/andrew/learning/java_croc/tasks/src/ru/croc/task13/1.txt"
        try {
            File file = new File(path);
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);

            Map<String, Department> departments = new HashMap<>();



            String line = reader.readLine();
            while (line != null) {
                Department department = stringToDepartment(line);

                String parentName = department.getParent();
                if (!parentName.equals("-")){
                    Department parent = departments.get(parentName);
                    parent.addChildren(department);
                }


                departments.put(department.getName(), department);
                line = reader.readLine();
            }
            return departments;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Department getRoot(Map<String, Department> departments ){
        for (Map.Entry<String, Department> pair : departments.entrySet()) {
            if (pair.getValue().isRoot()) {
                return pair.getValue();
            }
        }
        return null;
    }

    public static int rec(Map<String, Department> departments, Department department, int counter){
        List<Department> children = department.getChildren();

        int childrenMaxHours = 0;

        if (children.isEmpty()){
            return department.getHours();
        }

        for (Department child : children){
            //System.out.println(child.getName());
             int childHours= rec(departments, child, childrenMaxHours);
             if (childHours > childrenMaxHours){
                 childrenMaxHours = childHours;
             }
        }
        //System.out.println(counter + childrenMaxHours);
        return counter + childrenMaxHours;
    }


    /* int sumHours(Department dep)
     * counter = start;
     * childrenList;
     * int max = 0;
     * foreach(child: childrenList){
     *   int childHours = sumHours(child)
     *   if (childHours > max) max = childHours;
     * }
     * return counter + childHours;
     * */

    public static void main(String[] args){
        String path = "/home/andrew/learning/java_croc/tasks/src/ru/croc/task15/departments.txt";
        Map<String, Department> departments = getDepartmentsFromFile(path);
        //System.out.println(departments);
        Department root = getRoot(departments);
        //System.out.println(root);
        int result = rec(departments, root, 0);
        System.out.println(result);
    }

}
