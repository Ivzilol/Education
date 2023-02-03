package ExamPreparetion_01.easterBasket;

public class Main {
    public static void main(String[] args) {

        //Initialize the repository (Basket)
        Basket basket = new Basket("Wood", 20);
//Initialize entity (Egg)
        Egg egg = new Egg("Red", 10, "oval");
//Print Egg
        System.out.println(egg);  //Red egg, 10 strength, oval shape

//Add Egg
        basket.addEgg(egg);

//Remove Egg
        System.out.println(basket.removeEgg("Pink"));  //False

        Egg secondEgg = new Egg("Green", 9, "pointy");

//Add Egg
        basket.addEgg(secondEgg);

//Get strongest egg
        Egg strongestEgg = basket.getStrongestEgg();

        System.out.println(strongestEgg);

//Get egg
        Egg getEgg = basket.getEgg("Green"); //Green egg with 9 strength, pointy shape
        System.out.println(getEgg);


        System.out.println(basket.report());
//Wood basket contains:
//Red egg, with 10 strength and oval shape.
//Green egg, with 9 strength and pointy shape.

    }
}
