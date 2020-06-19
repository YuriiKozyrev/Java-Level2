package Java_Level2.Lesson1_HomeWork.Marathon.Obstacles;


import Java_Level2.Lesson1_HomeWork.Marathon.Competitors.Competitor;

public class Cross extends Obstacle {
    int length;

    public Cross(int length) {
        this.length = length;
    }

    @Override
    public void doIt(Competitor competitor) {
        competitor.run(length);
    }
}
