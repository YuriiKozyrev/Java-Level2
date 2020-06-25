package Java_Level2.Lesson3_HomeWork;

import java.util.HashMap;
import java.util.HashSet;

public class Phone_Book {

    static HashMap<String, HashSet<String>> phoneBook = new HashMap<>();

    public static void add (String sureName, String number){
        if(phoneBook.containsKey(sureName)){
            HashSet<String> currentHashSet = phoneBook.get(sureName);
            currentHashSet.add(number);
            phoneBook.put(sureName, currentHashSet);
        } else {
            HashSet<String> hs = new HashSet<>();
            hs.add(number);
            phoneBook.put(sureName, hs);
        }
        System.out.println("Добавлен: " + sureName + "\t и его номер: " + number);
    }


    public static void get (String sureName){
        System.out.println("\n По имени " + sureName + " найдены номера: " + phoneBook.get(sureName));

    }

    public static void main(String[] args) {
        add("Вася", "111111111");
        add("Петя", "2222222222");
        add("Вася", "33333333333");
        add("Коля", "44444444444");
        add("Дима", "55555555555");

        System.out.println("\n Весь справочник: " + phoneBook);


        get("Петя");
        get("Вася");

    }

}
