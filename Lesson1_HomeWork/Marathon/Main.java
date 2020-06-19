package Java_Level2.Lesson1_HomeWork.Marathon;


import Java_Level2.Lesson1_HomeWork.Marathon.Competitors.*;
import Java_Level2.Lesson1_HomeWork.Marathon.Obstacles.*;

public class Main {
    public static void main(String[] args) {


        Team team = new Team("HTF", new Dog("Дружок"), new Cat("Мурзик"), new Human("Дарт Сидиус"));
        Course c = new Course( new Wall(4), new Cross(100), new Water(4));


        team.startTeamInfo();
        c.doIt(team);
        team.finishTeamInfo();
    }
}