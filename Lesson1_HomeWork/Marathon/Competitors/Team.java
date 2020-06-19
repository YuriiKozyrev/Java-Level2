package Java_Level2.Lesson1_HomeWork.Marathon.Competitors;

public class Team {
    public String commandName;
    public Competitor[] competitors;

    public Team(String commandName, Competitor...competitors) {
        this.commandName = commandName;
        this.competitors = competitors;
    }

    public void showInfo(){
        for (Competitor c: competitors) c.info();
    }

    public void startTeamInfo(){
        System.out.println("Выступает команда " + commandName + " в составе:");
        showInfo();
        System.out.println("\n");
    }

    public void finishTeamInfo(){
        System.out.println("\n");
        System.out.println("Результаты участников команды " + commandName);
        showInfo();
    }


}
