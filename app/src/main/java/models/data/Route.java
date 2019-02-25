package models.data;

import android.util.Pair;

public class Route {
    private Integer points;
    private Enums.Color cardColor;
    private Pair<String, String> location;
    private Enums.PLAYERCOLOR ownerColor;

    public Route(Integer points, Enums.Color cardColor, Pair<String, String> location, Enums.PLAYERCOLOR ownerColor) {
        this.points = points;
        this.cardColor = cardColor;
        this.location = location;
        this.ownerColor = ownerColor;
    }
}
