package Workshop.customStack;

public class CustomStack {
    public static final int INITIAL_CAPACITY = 4;
    public static final int INITIAL_SIZE = 0;
    public static final int SHRINK_THRESHOLD = 4;

    private int[] data;
    private int size;
    private int capacity;

    public CustomStack() {
        this.data = new int[INITIAL_CAPACITY];
        this.capacity = INITIAL_CAPACITY;
        this.size = 0;

    }

    //push -> add element in end
    public void push(int element) {
        this.ensureCapacity();
        this.data[this.size] = element;
        this.size++;
    }

    private void ensureCapacity() {
        if (this.size == this.capacity) {
            this.resize();
        }
    }

    private void resize() {
        this.capacity = this.capacity * 2;
        int temp[] = new int[capacity];
        for (int i = 0; i < this.size; i++) {
            temp[i] = this.data[i];
        }
        this.data = temp;
    }

    //pop -> remove and returns element
    public int pop() {
        this.ensureStackNotEmpty();
        this.size--;
        int itemToRemove = this.data[size];
        this.data[size] = 0;
        return itemToRemove;
    }

    private void ensureStackNotEmpty() {
        if (this.size == 0) {
            throw new IndexOutOfBoundsException("Our CustomStack is empty");
        }
    }

    //peek -> returns element form end
    public int peek() {
        return this.data[size = 1];
    }
}
