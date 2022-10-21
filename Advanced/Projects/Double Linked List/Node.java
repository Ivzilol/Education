package Workshop_02;

//everyone element in LinkedList
public class Node {
    public int currentValue;
    public Node next;
    public Node prev;

    public Node (int currentValue){
        this.currentValue = currentValue;
    }
    @Override
    public String toString(){
        return "" + this.currentValue;
    }
}

