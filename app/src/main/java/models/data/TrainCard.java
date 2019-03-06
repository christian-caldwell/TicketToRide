package models.data;

public class TrainCard {
    private Enums.Color CardColor;

    public TrainCard(Enums.Color cardColor) {
        this.CardColor = cardColor;
    }

    public Enums.Color getCardColor() {
        return CardColor;
    }

    public void setCardColor(Enums.Color cardColor) {
        CardColor = cardColor;
    }


}
