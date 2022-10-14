package Exercises_01.CardsWithPower_03;

public enum CardSuit {
    CLUBS(0), DIAMONDS(13), HEARTS(26), SPADES(39);

    private int suitPower;

    CardSuit(int power){
        this.suitPower = power;

    }

    public int getSuitPower(){
        return this.suitPower;
    }
}
