package refactor;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class MyLinkedList<T> extends StaticEndlessLinkedList<T> {
    StaticEndlessLinkedList.Node<T> tail;

    public MyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public void addFirst(T data) throws IllegalArgumentException {
        if (data == null) {
            throw new IllegalArgumentException("Data is null");
        } else {
            if (this.size == 0) {
                this.head = new StaticEndlessLinkedList.Node(data);
                this.tail = this.head;
                this.tail.setNext(this.head);
            } else {
                StaticEndlessLinkedList.Node<T> newNode = new StaticEndlessLinkedList.Node(data);
                newNode.setNext(this.head);
                this.head = newNode;
                this.tail.setNext(newNode);
            }

            ++this.size;
        }
    }

    public void addLast(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is null");
        } else {
            if (this.head == null) {
                this.head = new StaticEndlessLinkedList.Node(data);
                this.tail = this.head;
                this.tail.setNext(this.head);
            } else {
                StaticEndlessLinkedList.Node<T> newNode = new StaticEndlessLinkedList.Node(data);
                this.tail.setNext(newNode);
                this.tail = newNode;
                this.tail.setNext(this.head);
            }

            ++this.size;
        }
    }

    public T removeFirst() {
        if (this.size == 0) {
            throw new NoSuchElementException("List is empty");
        } else {
            T data = (T)this.head.getData();
            if (this.size == 1) {
                this.head = null;
                this.tail = null;
            } else {
                this.head = this.head.getNext();
                this.tail.setNext(this.head);
            }

            --this.size;
            return data;
        }
    }

    public T removeLast() {
        if (this.size == 0) {
            throw new NoSuchElementException("List is empty");
        } else {
            T data = (T)this.tail.getData();
            if (this.size == 1) {
                this.head = null;
                this.tail = null;
            } else {
                StaticEndlessLinkedList.Node<T> curr;
                for(curr = this.head; curr.getNext() != this.tail; curr = curr.getNext()) {
                }

                curr.setNext(this.head);
                this.tail = curr;
            }

            --this.size;
            return data;
        }
    }

    public int removeValue(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is null");
        } else if (this.size == 0) {
            throw new NoSuchElementException("List is empty");
        } else if (this.head.getData().equals(data)) {
            this.removeFirst();
            return 0;
        } else {
            StaticEndlessLinkedList.Node<T> curr = this.head;

            int index;
            for(index = 1; !curr.getNext().getData().equals(data) && curr.getNext() != this.head; ++index) {
                curr = curr.getNext();
            }

            if (!curr.getNext().getData().equals(data)) {
                throw new NoSuchElementException("Data is not contained within the list.");
            } else {
                if (curr.getNext() == this.tail) {
                    this.removeLast();
                } else {
                    curr.setNext(curr.getNext().getNext());
                    --this.size;
                }

                return index;
            }
        }
    }

    public T get(int index) {
        if (index >= 0 && index < this.size) {
            StaticEndlessLinkedList.Node<T> curr = this.head;

            for(int i = 0; i < index; ++i) {
                curr = curr.getNext();
            }

            return (T)curr.getData();
        } else {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.size);
        }
    }

    public void reverse() {
        if (this.size > 1) {
            StaticEndlessLinkedList.Node<T> curr = this.head;
            StaticEndlessLinkedList.Node<T> prev = this.tail;

            for(int i = 0; i < this.size; ++i) {
                StaticEndlessLinkedList.Node<T> next = curr.getNext();
                curr.setNext(prev);
                prev = curr;
                curr = next;
            }

            StaticEndlessLinkedList.Node<T> temp = this.head;
            this.head = prev;
        }
    }

    public Iterator<T> iterator() {
        return new MyLinkedListIterator();
    }

    private class MyLinkedListIterator implements Iterator<T> {
        private StaticEndlessLinkedList.Node<T> curr;
        private int indexCounter;

        public MyLinkedListIterator() {
            Objects.requireNonNull(MyLinkedList.this);
            super();
            this.curr = MyLinkedList.this.head;
            this.indexCounter = 0;
        }

        public boolean hasNext() {
            return MyLinkedList.this.size > 0;
        }

        public T next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException("List is empty");
            } else {
                T data = (T)this.curr.getData();
                this.curr = this.curr.getNext();
                ++this.indexCounter;
                return data;
            }
        }
    }
}