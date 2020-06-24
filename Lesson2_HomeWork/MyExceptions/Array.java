package Java_Level2.Lesson2_HomeWork.MyExceptions;

import java.util.Scanner;

public class Array {

    static final int actualSize = 4;
    int size;
    String [][] testArray;

    public void setArraySize(){
        size = readArraySize("Введите размер двумерного массива, равный " + actualSize + ": ");
        testArray = new String[size][size];
    }

    public void checkArraySize(String [][] stringArray)  {
        try{
            if (stringArray.length != actualSize)
                throw new MyArraySizeException("Это не " + actualSize + ". Вы ввели: " + stringArray.length);
        } catch (MyArraySizeException e){
            e.printStackTrace();
        }
    }

    public void checkArrayElements(String [][] stringArray) {
        int sum = 0;
        for (int i = 0; i < stringArray.length; i++) {
            for (int j = 0; j < stringArray[i].length; j++) {

                try {
                    if (!stringArray[i][j].chars().allMatch(Character::isDigit))
                        throw new MyArrayDataException("Ошибка! Строка вместо числа в ячейке " + "[" + i + "]" + " [" + j + "]");
                    if (stringArray[i][j].isEmpty())
                        throw new MyArrayDataException("Ошибка! Пустой элемент вместо числа в ячейке " + "[" + i + "]" + " [" + j + "]");
                    else sum += Integer.parseInt(stringArray[i][j]);

                } catch (MyArrayDataException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("Сумма элементов массива: " + sum);
    }


    public void setArrayElements(String [][] stringArray) {

        System.out.println("Заполним массив числами");

        for (int i = 0; i < stringArray.length; i++) {
            for (int j = 0; j < stringArray[i].length; j++) {
                String element = readArrayElements("Введите элемент для ячейки [" + i + "] " + "[" + j + "] :");
                stringArray[i][j] = element;
            }
        }
    }

    public void showArrayElements(String [][] stringArray){
        for (int i = 0; i < stringArray.length; i++) {
            System.out.print("\n");
            for (int j = 0; j < stringArray[i].length; j++) {
                System.out.print(stringArray[i][j] + "\t");
            }
        }
    }

    static int readArraySize (String userText){
        Scanner userInput = new Scanner(System.in);
        System.out.print(userText);
        return userInput.nextInt();
    }

    static String readArrayElements (String userText) {
        Scanner userInput = new Scanner(System.in);
        System.out.print(userText);
        return userInput.nextLine();
    }
}
