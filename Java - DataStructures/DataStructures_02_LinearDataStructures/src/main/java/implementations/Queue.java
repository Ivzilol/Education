package implementations;

import interfaces.AbstractQueue;

import java.util.Iterator;

public class Queue<E> implements AbstractQueue<E> {

    private Node<E> head;
    private int size;
    private static class Node<E> {
        private E value;
        private Node<E> next;

        public Node(E element) {
            this.value = element;
        }
    }

    public Queue() {
        this.head = null;
        this.size = 0;
    }
    @Override
    public void offer(E element) {
        Node<E> toInsert = new Node<>(element);
        if (this.isEmpty()) {
            this.head = toInsert;
        } else  {
            Node<E> currentElement = this.head;
            while (currentElement.next != null) {
                currentElement = currentElement.next;
            }
            currentElement.next = toInsert;
        }
        this.size++;
    }

    @Override
    public E poll() {
        emptyQueue();
        Node<E> firstElement = this.head;
        this.head = firstElement.next;
        this.size--;

        return firstElement.value;
    }



    @Override
    public E peek() {
        emptyQueue();
        return this.head.value;
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
            private Node<E>  currentElement = head;
            @Override
            public boolean hasNext() {
                return currentElement != null;
            }

            @Override
            public E next() {
                E value = currentElement.value;
                currentElement = currentElement.next;
                return value;
            }
        };
    }

    private void emptyQueue() {
        if (this.isEmpty()) {
            throw new IllegalStateException();
        }
    }
}
