package Lab_08.Example;

import java.util.Iterator;

public class Main {
    public static void main(String[] args) {

        Supermarket supermarket = new Supermarket("Apple", "Banana", "Orange");

        Iterator<String> shopAssist = supermarket.iterator();
        while (shopAssist.hasNext()){
            System.out.println(shopAssist.next());
        }
    }

}

