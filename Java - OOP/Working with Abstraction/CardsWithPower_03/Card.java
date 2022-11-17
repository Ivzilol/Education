package Exercises_01.CardsWithPower_03;



public class Card {
    private CardSuit cardSuit;
    private CardRanks cardRanks;
    private int power;

    public Card(CardSuit cardSuit, CardRanks cardRanks) {
        this.cardSuit = cardSuit;
        this.cardRanks = cardRanks;
    }

    public CardSuit getCardSuit() {
        return cardSuit;
    }

    public void setCardSuit(CardSuit cardSuit) {
        this.cardSuit = cardSuit;
    }

    public CardRanks getCardRanks() {
        return cardRanks;
    }

    public void setCardRanks(CardRanks cardRanks) {
        this.cardRanks = cardRanks;
    }

    public int getPower() {
        return this.cardSuit.getSuitPower() + this.cardRanks.getPowerRank();
    }

    public void setPower(int power) {
        this.power = power;
    }
}
