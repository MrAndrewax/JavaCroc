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


import java.io.*;
import java.util.*;



public class Main {

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
    public static Map<String, Department> getDepartmentsFromFile(String path){//"/home/andrew/learning/java_croc/tasks/src/ru/croc/task13/1.txt"
        try ( FileReader fr = new FileReader(path);
             BufferedReader reader = new BufferedReader(fr)){

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
            throw new RuntimeException(e);
        }
    }

    /*Возвраащает корневой элемент иерархии отделов*/
    public static Department getRoot(Map<String, Department> departments ){
        for (Map.Entry<String, Department> pair : departments.entrySet()) {
            if (pair.getValue().isRoot()) {
                return pair.getValue();
            }
        }
        return null;
    }

    /*Рекурсивная функция, которая обходит хэш таблицу и тем самым считает количество часов*/
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

    public static void main(String[] args){
        String path = "/home/andrew/learning/java_croc/tasks/src/ru/croc/task15/departments.txt";//args[0];
        Map<String, Department> departments = getDepartmentsFromFile(path);
        Department root = getRoot(departments);
        System.out.println(rec(departments, root, 0));
    }
}
