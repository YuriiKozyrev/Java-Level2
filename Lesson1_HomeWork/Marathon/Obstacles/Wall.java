package Java_Level2.Lesson1_HomeWork.Marathon.Obstacles;


import Java_Level2.Lesson1_HomeWork.Marathon.Competitors.Competitor;
import Java_Level2.Lesson1_HomeWork.Marathon.Obstacles.Obstacle;

public class Wall extends Obstacle {
    int height;

    public Wall(int height) {
        this.height = height;
    }

    @Override
    public void doIt(Competitor competitor) {
        competitor.jump(height);
    }
}