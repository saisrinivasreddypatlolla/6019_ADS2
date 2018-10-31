/*************************************************************************
 *  Compilation:  javac Queue.java
 *  Execution:    java Queue < input.txt
 *  Data files:   http://algs4.cs.princeton.edu/13stacks/tobe.txt  
 *
 *  A generic queue, implemented using a linked list.
 *
 *  % java Queue < tobe.txt 
 *  to be or not to be (2 left on queue)
 *
 *************************************************************************/

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *  The <tt>Queue</tt> class represents a first-in-first-out (FIFO)
 *  queue of generic items.
 *  It supports the usual <em>enqueue</em> and <em>dequeue</em>
 *  operations, along with methods for peeking at the top item,
 *  testing if the queue is empty, and iterating through
 *  the items in FIFO order.
 *  <p>
 *  All queue operations except iteration are constant time.
 *  <p>
 *  For additional documentation, see
 *  <a href="http://algs4.cs.princeton.edu/13stacks">Section 1.3</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 */
/**
 * List of Items in Queue.
 *
 * @param      <Item>  The item
 */
public class Queue<Item> implements Iterable<Item> {
    /**
     * number of elements on queue.
     */
    private int size;
    /**
     * beginning of queue.
     */
    private Node first;
    /**
     * end of queue.
     */
    private Node last;
    /**
     * Class for node.
     */
    private class Node {
        /**
         * item in the node.
         */
        private Item item;
        /**
         * link of next node.
         */
        private Node next;
    }

    /**
     * Constructs the object.
     */
    public Queue() {
        first = null;
        last  = null;
    }

    /**
     * Determines if empty.
     *
     * @return     True if empty, False otherwise.
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * returns size of queue.
     *
     * @return     returns size of queue.
     */
    public int size() {
        return size;     
    }

    /**
     * returns first element in the node.
     *
     * @return     returns first element in the node.
     */
    public Item peek() {
        if (isEmpty()) 
            throw new RuntimeException("Queue underflow");
        return first.item;
    }

    /**
     * adds item in tha queue.
     * Time complexity is O(1)
     *
     * @param      item  The item
     */
    public void enqueue(Item item) {
        Node x = new Node();
        x.item = item;
        if (isEmpty()) { first = x;     last = x; }
        else           { last.next = x; last = x; }
        size++;
    }

    /**
     * method removes the item from queue.
     * Time complexity is O(1).
     *
     * @return     returns item which is deleted.
     */
    public Item dequeue() {
        if (isEmpty()) throw new RuntimeException("Queue underflow");
        Item item = first.item;
        first = first.next;
        size--;
        if (isEmpty()) last = null;
        return item;
    }

    /**
     * Returns a string representation of the object.
     * Time complexity of this method is O(N)
     * N is number of items.
     *
     * @return     String representation of the object.
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this)
            s.append(item + " ");
        return s.toString();
    } 
 

    /**
     * returns the iterator to the queue  through.
     * the FIFO order.
     * Time complexity is O(1).
     *
     * @return     returns the iterator to the queue
     */
    public Iterator<Item> iterator()  {
        return new ListIterator();  
    }

    /**
     * Class for list iterator.
     */
    private class ListIterator implements Iterator<Item> {
        /**
         * current node.
         */
        private Node current = first;
        /**
         * Determines if it has next.
         *
         * @return     True if has next, False otherwise.
         */
        public boolean hasNext() {
            return current != null;
        }
        /**
         * removes item.
         */
        public void remove() {
            throw new UnsupportedOperationException();
        }
        /**
         * this method will return the item of
         * that current node.
         * Time complexity is O(1).
         *
         * @return     returns the current node item.
         */
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next; 
            return item;
        }
    }
}
