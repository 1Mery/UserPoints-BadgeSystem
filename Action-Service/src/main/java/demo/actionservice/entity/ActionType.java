package demo.actionservice.entity;

public enum ActionType {
    READ_ARTICLE(5),
    ADD_COMMENT(10),
    COMPLETE_TASK(20),
    DAILY_LOGIN(2);

    private final int points;

    ActionType(int points) {
        this.points = points;
    }
    public int getPoints() {
        return points;
    }
}
