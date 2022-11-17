package Workshop.smartArray;

import java.util.function.Consumer;

public class SmartArray {

    public static final int INITIAL_CAPACITY = 4;
    public static final int INITIAL_SIZE = 0;
    public static final int SHRINK_THRESHOLD = 4;

    private int[] data;
    private int size;
    private int capacity;

    public SmartArray() {
        this.size = INITIAL_SIZE;
        this.capacity = INITIAL_CAPACITY;
        this.data = new int[INITIAL_CAPACITY];
    }

    public void add(int element) {
        this.ensureCapacity();
        this.data[size] = element;
        this.size++;

    }

    private void resize() {
        //add capacity in to array
        this.capacity = this.capacity * 2;
        int temp[] = new int[capacity];
        for (int i = 0; i < this.size; i++) {
            temp[i] = this.data[i];
        }
        this.data = temp;
    }

    public int size() {
        return this.size;
    }

    public int get(int index) {
        this.checkIndex(index);
        return this.data[index];
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            String errorMassage = String.format("Index %d is out of bounds for our SmartArray", index);
            throw new IndexOutOfBoundsException(errorMassage);
        }
    }

    public int remove(int index) {
        int numberToRemove = this.get(index);
        this.shiftLeft(index);
        this.size--;
        if (this.capacity / this.size > SHRINK_THRESHOLD) {
            this.shrink();
        }
        return numberToRemove;
    }

    public void shrink() {
        int temp[] = new int[this.capacity];
        for (int i = 0; i < this.size; i++) {
            temp[i] = this.data[i];
        }
        this.data = temp;
    }

    private void shiftLeft(int index) {
        for (int i = index + 1; i < this.size; i++) {
            this.data[i - 1] = this.data[i];
        }
        this.data[size - 1] = 0;
    }

    public boolean contains(int element) {
        for (int i = 0; i < this.size; i++) {
            if (this.data[i] == element) {
                return true;
            }
        }
        return false;
    }

    public void add(int index, int element) {
        this.checkIndex(index);
        this.ensureCapacity();
        this.size++;
        this.shiftRight(index);
        this.data[index] = element;
    }

    private void shiftRight(int index) {
        for (int i = this.size - 1; i > index; i--) {
            this.data[i] = this.data[i - 1];

        }
    }

    private void ensureCapacity() {
        if (this.size == this.capacity) {
            this.resize();
        }
    }

    public void forEach(Consumer<Integer> consumer){
        for (int i = 0; i < this.size; i++) {
            consumer.accept(this.data[i]);
        }
    }
}
