package Java_Level2.Lesson3_HomeWork;

import java.util.*;

public class Frequency {
    public static void main(String[] args) {
        String[] countryArray = {"Куба", "США", "Канада", "Мексика", "Бразилия", "США", "Мексика", "Мексика", "Куба", "Аргенитна", "Куба"};

        List<String> countryArrayList = Arrays.asList(countryArray);
        System.out.println("Исходный массив: " + countryArrayList);

        Set<String> uniqueCountryArrayList = new HashSet<>(countryArrayList);
        System.out.println("Список уникальных значений: " + uniqueCountryArrayList);

        System.out.print("Результат: " + "\t");
        for (String temp : uniqueCountryArrayList) {
            System.out.print(temp + ":" + Collections.frequency(countryArrayList, temp) + "\t");
        }
    }
}
