/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] array;
    private int size;

    public RandomizedQueue() {
        size = 0;
        array = (Item[]) new Object[1];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        if (size == array.length) resize(2*array.length);
        array[size++] = item;
    }

    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        int index = StdRandom.uniform(size);
        int last = size-1;
        Item item = array[index];
        array[index] = array[last];
        array[last] = null;
        size--;
        if (size > 0 && size == array.length/4) resize(array.length/2);
        return item;
    }

    private void resize(int length) {
        Item[] copy = (Item[]) new Object[length];

        for (int i = 0; i < size; i++) {
            copy[i] = array[i];
        }
        array = copy;
    }

    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        int index = StdRandom.uniform(size);
        Item item = array[index];
        return item;
    }

    public Iterator<Item> iterator() {
        return new RandomIterator();
    }

    private class RandomIterator implements Iterator<Item> {
        private int i = size;

        public boolean hasNext() {
            return i > 0;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            int index = StdRandom.uniform(i);
            Item item = array[index];
            array[index] = array[i-1];
            i--;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        RandomizedQueue<String> queue = new RandomizedQueue<>();
        queue.enqueue("Hello World");
        queue.enqueue("Khanh Mai");
        queue.enqueue("Bao Mai");
        queue.enqueue("Binh Mai");
        queue.enqueue("Ly Dang");

        for (String s : queue) {
            StdOut.println(s);
        }
        StdOut.println(queue.dequeue());
    }
}
