package models.data;

import android.util.Pair;

public class Route {
    private Integer points;
    private Integer cardColor;
    private Pair<String, String> location;
//    private Enums.PLAYERCOLOR ownerColor;

    public Route(Integer length, Integer cardColor, Pair<String, String> location/*, Enums.PLAYERCOLOR ownerColor*/) {
        this.points = length;
        this.cardColor = cardColor;
        this.location = location;
//        this.ownerColor = ownerColor;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoint(Integer points) {
        this.points = points;
    }

    public int getLength() {
        switch (points.intValue()) {
            case (1): return 1;
            case (2): return 2;
            case (4): return 3;
            case (7): return 4;
            case (10): return 5;
            case (15): return 6;
            default:
                return 0;
        }
    }

    public Integer getCardColor() {
        return cardColor;
    }

    public void setCardColor(Integer cardColor) {
        this.cardColor = cardColor;
    }

    public Pair<String, String> getLocation() {
        return location;
    }

    public void setLocation(Pair<String, String> location) {
        this.location = location;
    }
}
