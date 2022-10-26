/* Условие:
    В текстах программ на Java могут использоваться многострочные и однострочные (// ...) комментарии.
    Реализовать метод, принимающий на вход строковую переменную - исходный текст программы на Java,
    вырезающий из этой строки все комментарии и возвращающий результат в виде строки.
 */

/*В качестве исходного текста для задачи я взял этот код*/

public class Main{
    public static void main(String[] args) {
        //String sourceText = "hard code\n//small comment \n very very hard code \n /*bigger \n comment \n */ \n lol";
        String sourceText = """
                /* Условие:
                    В текстах программ на Java могут использоваться многострочные и однострочные (// ...) комментарии.
                    Реализовать метод, принимающий на вход строковую переменную - исходный текст программы на Java,
                    вырезающий из этой строки все комментарии и возвращающий результат в виде строки.
                 */
                                
                /*В качестве исходного текста для задачи я взял этот код*/
                                
                //comment
                
                /*Big comment*///small comment
                
                <code>
                
                // strange comment /* nice
                                        comment
                                                !*///small comment
                
                <hard code>
                
                /*sad,jshd skjdh*/
                """;


        int start = -1;
        int end = -1;
        String tempStr;

        /*сначала удаляем многострочные комментарии*/
        while (true){
            start = sourceText.indexOf("/*");
            if (start == -1){
                break;
            }
            tempStr = sourceText.substring(start);
            end = tempStr.indexOf("*/");
            tempStr = sourceText.substring(start, end + start + 2);//Сейчас tempStr ссылается на многострочный комментарий. Добавляем к концу строку 2, чтобы захватить */
            sourceText = sourceText.replace(tempStr, "");
        }


        /*После того как все многострочные комментарии удалены, удаяем однострочные*/
        
        while (true){
            start = sourceText.indexOf("//");//находим начало комментария.
            if (start == -1){
                break;
            }
            end = sourceText.substring(start).indexOf("\n");//находим индекс первого вхождения "\n" после начала комментария.
            if (end == -1){//если конец строки не найден, то это последняя строка. Тогда за конец комментария возьмём конец текста.
                end = sourceText.length();
                tempStr = sourceText.substring(start, end);
            }
            else{
                tempStr = sourceText.substring(start, end + start);// Сейчас tempStr ссылается на однострочный комментарий
            }

            sourceText = sourceText.replace(tempStr, "");//удаляем найденный комментарий.
        }



        System.out.println(sourceText);
    }
}