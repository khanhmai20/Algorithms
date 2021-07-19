/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int size;

    public Deque() {
        first = null;
        last = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (isEmpty()) {
            first = new Node(item);
            last = first;
        } else {
            Node oldFirst = first;
            first = new Node(item);
            first.next = oldFirst;
            oldFirst.prev = first;
            oldFirst = null;
        }
        size++;
    }

    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (isEmpty()) {
            last = new Node(item);
            first = last;
        } else {
            Node hold = new Node(item);
            last.next = hold;
            hold.prev = last;
            last = last.next;
            hold = null;
        }
        size++;
    }

    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Node hold = first;
        if (size == 1) {
            first = null;
            last = null;
        } else {
            first = first.next;
            first.prev = null;
            hold.next = null;
        }
        size--;
        return hold.item;
    }

    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Node hold = last;
        if (size == 1) {
            first = null;
            last = null;
        } else {
            last = last.prev;
            last.next = null;
            hold.prev = null;
        }
        size--;
        return hold.item;
    }

    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private class Node {
        Item item;
        Node next;
        Node prev;

        public Node(Item something) {
            item = something;
            next = null;
            prev = null;
        }
    }

    public static void main(String[] args) {
        Deque<String> deque = new Deque<>();

        deque.addFirst("Hllo");
        deque.addLast("Bao");
        deque.addFirst("conac");
        deque.addLast("Khan");
        StdOut.println(deque.size());

        for (String s : deque) {
            StdOut.println(s);
        }

        StdOut.println(deque.removeLast());
        StdOut.println(deque.size());

        StdOut.println(deque.removeFirst());
        StdOut.println(deque.size());

        StdOut.println(deque.removeLast());
        StdOut.println(deque.size());

        StdOut.println(deque.removeLast());
        StdOut.println(deque.size());
    }
}
