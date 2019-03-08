package models.data;

public class Route {
    private Integer points;
    private Integer cardColor;
    private String[] locations;

    public Route(Integer length, Integer cardColor, String[] locations) {
        this.points = length;
        this.cardColor = cardColor;
        this.locations = locations;
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

    public String[] getLocation() {
        return locations;
    }

    public Route() {
    }

    public void setLocation(String[] locations) {
        this.locations = locations;
    }
}
