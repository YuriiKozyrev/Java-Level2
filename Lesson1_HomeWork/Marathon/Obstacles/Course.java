package Java_Level2.Lesson1_HomeWork.Marathon.Obstacles;

import Java_Level2.Lesson1_HomeWork.Marathon.Competitors.Competitor;
import Java_Level2.Lesson1_HomeWork.Marathon.Competitors.Team;

public class Course {

    public Obstacle[] obstacle;

    public Course(Obstacle...obstacle){
        this.obstacle = obstacle;
    }

    public void doIt (Team team){
        for (Competitor c: team.competitors) {
            for (Obstacle o : obstacle) {
                o.doIt(c);
                if (!c.isOnDistance()) break;
            }
        }
    }


}
