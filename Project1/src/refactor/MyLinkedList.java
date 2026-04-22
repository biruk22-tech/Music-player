package refactor;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * A circular singly linked list implementation that extends StaticEndlessLinkedList.
 *
 *
 * @param <T> the type of elements held in this collection
 */
public class MyLinkedList<T> extends StaticEndlessLinkedList<T> {
    /** The last node in the list, which links back to the head. */
    StaticEndlessLinkedList.Node<T> tail;

    /**
     * Constructs an empty circular linked list.
     */
    public MyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /**
     * Adds an element to the front of the list.
     * @param data the element to add.
     * @throws IllegalArgumentException if data is null.
     */
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

    /**
     * Adds an element to the end of the list.
     * @param data the element to add.
     * @throws IllegalArgumentException if data is null.
     */
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

    /**
     * Removes and returns the first element of the list.
     * @return the data from the removed node.
     * @throws NoSuchElementException if the list is empty.
     */
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

    /**
     * Removes and returns the last element of the list.
     * @return the data from the removed node.
     * @throws NoSuchElementException if the list is empty.
     */
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
                for (curr = this.head; curr.getNext() != this.tail; curr = curr.getNext()) {
                }
                curr.setNext(this.head);
                this.tail = curr;
            }

            --this.size;
            return data;
        }
    }

    /**
     * Removes the first occurrence of the specified value.
     * @param data the value to be removed.
     * @return the index of the removed element.
     * @throws IllegalArgumentException if data is null.
     * @throws NoSuchElementException if the list is empty or value not found.
     */
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

    /**
     * Retrieves the element at the specified position.
     * @param index the index of the element to return.
     * @return the element at the specified index.
     * @throws IndexOutOfBoundsException if index is out of range.
     */
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

    /**
     * Reverses the order of the nodes in the circular list in-place.
     */
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
            this.tail = temp; // Ensure tail reference is updated
        }
    }

    /**
     * Returns an iterator over the elements in this list.
     * @return an Iterator.
     */
    public Iterator<T> iterator() {
        return new MyLinkedListIterator();
    }

    /**
     * Iterator implementation for MyLinkedList.
     * Traverses the list starting from head.
     */
    private class MyLinkedListIterator implements Iterator<T> {
        private StaticEndlessLinkedList.Node<T> curr;
        private int indexCounter;

        /**
         * Initializes the iterator at the head of the list.
         */
        public MyLinkedListIterator() {
            Objects.requireNonNull(MyLinkedList.this);
            this.curr = MyLinkedList.this.head;
            this.indexCounter = 0;
        }

        /**
         * Returns true if the list is not empty.
         * @return true if there is at least one element.
         */
        public boolean hasNext() {
            return MyLinkedList.this.size > 0;
        }

        /**
         * Returns the next element in the circular traversal.
         * @return the next element.
         * @throws NoSuchElementException if the list is empty.
         */
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