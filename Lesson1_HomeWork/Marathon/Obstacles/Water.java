package Java_Level2.Lesson1_HomeWork.Marathon.Obstacles;


import Java_Level2.Lesson1_HomeWork.Marathon.Competitors.Competitor;
import Java_Level2.Lesson1_HomeWork.Marathon.Obstacles.Obstacle;

public class Water extends Obstacle {
    int length;

    public Water(int length) {
        this.length = length;
    }

    @Override
    public void doIt(Competitor competitor) {
        competitor.swim(length);
    }
}
