package models.data;

import android.util.Pair;

public class DestinationCard {
    private Pair<String,String> locations;
    private Integer points;

    public Pair<String, String> getLocations() {
        return locations;
    }

    public Integer getPoints() {
        return points;
    }

    public DestinationCard(Pair<String, String> locations, Integer points) {
        this.locations = locations;
        this.points = points;
    }
}
