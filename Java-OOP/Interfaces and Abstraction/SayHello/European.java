package Lab_04.SayHello;

public class European extends BasePerson {


    public European(String name) {
       super(name);
    }

    @Override
    public String sayHello() {
        return "Hello";
    }
}
