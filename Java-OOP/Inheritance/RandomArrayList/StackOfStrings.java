package Lab_03.RandomArrayList;

import java.util.ArrayDeque;

public class StackOfStrings {

    private ArrayDeque<String> data;

    public StackOfStrings() {
        this.data = new ArrayDeque<>();
    }

    public void push (String element){
        data.push(element);
    }

    public String pop(){
       return this.data.pop();
    }

    public String peek(){
        return this.data.peek();
    }

    public boolean isEmpty() {
        return this.data.isEmpty();
    }

}
