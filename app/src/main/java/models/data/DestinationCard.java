package models.data;

public class DestinationCard {
    private String[] locations;
    private Integer points;

    public DestinationCard() {
    }

    public String[] getLocations() {
        return locations;
    }

    public Integer getPoints() {
        return points;
    }

    public DestinationCard(String[] locations, Integer points) {
        this.locations = locations;
        this.points = points;
    }
}
