package models.data;

public class Route {
    private Integer length;
    private Integer cardColor;
    private String[] locations;
//    private Enums.PLAYERCOLOR ownerColor;

    public Route(Integer length, Integer cardColor, String[] locations/*, Enums.PLAYERCOLOR ownerColor*/) {
        this.length = length;
        this.cardColor = cardColor;
        this.locations = locations;
//        this.ownerColor = ownerColor;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer points) {
        this.length = points;
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

    public void setLocation(String[] locations) {
        this.locations = locations;
    }
}
