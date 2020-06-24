package Java_Level2.Lesson2_HomeWork;

import Lesson2.HW.DayOfWeekMain2;

public class DayOfWeekMain {

    enum DayOfWeek {
        MONDAY(8), TUESDAY(8), WEDNESDAY(8), THURSDAY(8), FRIDAY(8), SATURDAY(0), SUNDAY(0);

        private int workingHours;

        DayOfWeek(int workingHours){
            this.workingHours = workingHours;
        }

        public int getWorkingHours(){
            return workingHours;
        }

    }

    public static String getAllWorkingHours(DayOfWeek day){
        String result;
        int sum = 0;

        for(int i = day.ordinal(); i < DayOfWeekMain.DayOfWeek.values().length; i++){
            sum += DayOfWeekMain.DayOfWeek.values()[i].getWorkingHours();
        }

        if(sum == 0) result = "Это выходной! Количество рабочих часов до конца недели: " + sum;
        else result = "Количество рабочих часов до конца недели: " + sum;

        return result;

    }


    public static void main(String[] args) {
        System.out.println(getAllWorkingHours(DayOfWeek.MONDAY));
    }
}
