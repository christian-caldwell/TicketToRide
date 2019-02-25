package models.data;

public class TrainCard {
    public Enums.Color getCardColor() {
        return CardColor;
    }

    public void setCardColor(Enums.Color cardColor) {
        CardColor = cardColor;
    }

    private Enums.Color CardColor;

}
