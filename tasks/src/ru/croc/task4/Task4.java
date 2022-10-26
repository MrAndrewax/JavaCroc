public class Task4{
    public static void main(String[] args) {
        //String sourceText = "hard code\n//small comment \n very very hard code \n /*bigger \n comment \n */ \n lol";
        String sourceText = """
                           
                <code>
                //single line comment
                /*multi line comment*/
                <hard code>
                /*another
                    multi
                        line
                            comment*/         
                <very hard code>
                                
                /*multi line 
                comment*///single comment after multi line comment//another single line comment
                <very very hard code>
                                
                /*
                 * My first ever program in Java!
                 */
                class Hello { // class body starts here\s
                 \s
                  /* main method */
                  public static void main(String[] args/* we put command line arguments here*/) {
                    // this line prints my first greeting to the screen
                    System.out.println("Hi!"); // :)
                  }
                } // the end
                // to be continued...
                                
                """;


        int start = -1;
        int end = -1;
        String tempStr;
        //System.out.println(sourceText);
        /*Проводим аналогичную операцию
            с многострочными комментариями */
        while (true){
            start = sourceText.indexOf("/*");
            if (start == -1){
                break;
            }
            tempStr = sourceText.substring(start);


            int end1 = tempStr.substring(2).indexOf("/*");
            int end2 = tempStr.indexOf("*/");

            if (end1 != -1 && end1 < end2){
                tempStr = sourceText.substring(start, start + end1 + 2);
                System.out.println("temp_str = " + tempStr);
            }
            else{
                tempStr = sourceText.substring(start, end2 + start + 2);//Сейчас tempStr ссылается на многострочный комментарий. Добавляем к концу строку 2, чтобы захватить */
            }

            sourceText = sourceText.replace(tempStr, "");
        }
        //System.out.println(sourceText);

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