package demo.badgeservice.entity;

public enum BadgeType {
    BRONZE,
    SILVER,
    GOLD,
    PLATINUM;

    public static BadgeType fromPoints(int points) {
        if (points >= 20) {
            return PLATINUM;
        } else if (points >= 15) {
            return GOLD;
        } else if (points >= 5) {
            return SILVER;
        } else {
            return BRONZE;
        }
    }
}
