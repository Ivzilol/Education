package Lab_03.SingleInheritance_01;

public class Main {
    public static void main(String[] args) {

        Puppy puppy = new Puppy();
        puppy.eat();
        puppy.bark();
        puppy.weep();

        Cat cat = new Cat();
        cat.eat();
        cat.meow();
    }
}
