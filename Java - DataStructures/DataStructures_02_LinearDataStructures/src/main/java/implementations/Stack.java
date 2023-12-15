package implementations;

import interfaces.AbstractStack;

import java.util.Iterator;

public class Stack<E> implements AbstractStack<E> {

    private Node<E> topElement;
    private int size;
    private static class Node<E> {
        private E value;
        private Node<E> next;

        Node(E element) {
            this.value = element;
        }
    }

    public Stack() {
        this.topElement = null;
        this.size = 0;
    }

    @Override
    public void push(E element) {
        Node<E> toInsert = new Node<>(element);
        toInsert.next = this.topElement;
        this.topElement = toInsert;
        this.size++;
    }

    @Override
    public E pop() {
        emptyStack();
        Node<E> tmp = this.topElement;
        this.topElement = tmp.next;
        this.size--;
        return tmp.value;
    }

    @Override
    public E peek() {
        emptyStack();
        return this.topElement.value;
    }


    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node<E> currentElement = topElement;
            @Override
            public boolean hasNext() {
                return this.currentElement != null;
            }

            @Override
            public E next() {
                E value = this.currentElement.value;
                this.currentElement = this.currentElement.next;
                return value;
            }
        };
    }

    private void emptyStack() {
        if (this.isEmpty()) {
            throw new IllegalStateException();
        }
    }
}
