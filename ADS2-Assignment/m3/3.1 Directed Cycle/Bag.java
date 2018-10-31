/*************************************************************************
 *  Compilation:  javac Bag.java
 *  Execution:    java Bag < input.txt
 *
 *  A generic bag or multiset, implemented using a linked list.
 *
 *************************************************************************/

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *  The <tt>Bag</tt> class represents a bag (or multiset) of
 *  generic items. It supports insertion and iterating over the
 *  items in arbitrary order.
 *  <p>
 *  The <em>add</em>, <em>isEmpty</em>, and <em>size</em>  operation
 *  take constant time. Iteration takes time
 *  proportional to the number of items.
 *  <p>
 *  For additional documentation, see
 *  <a href="http://algs4.cs.princeton.edu/13stacks">Section 1.3</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 */
/**
 * Class for bag.
 *
 * @param      <Item>  The item
 */
public class Bag<Item> implements Iterable<Item> {
    /**
     * number of elements in bag.
     */
    private int count;
    /**
     * beginning of bag.
     */
    private Node first;

    /**
     * Class for node.
     */
    private class Node {
        /**
         * element in the node.
         */
        private Item item;
        /**
         * next node address.
         */
        private Node next;
    }

    /**
      * Create an empty stack.
      */
    public Bag() {
        first = null;
        count = 0;
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
     * returns the size of stack.
     *
     * @return     returns the size of stack
     */
    public int size() {
        return count;
    }

    /**
     * this method adds node to the bag.
     * Time complexity is O(1).
     *
     * @param      item  The item
     */
    public void add(final Item item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        count++;
    }


    /**
     * Return an iterator that iterates over the
     * items in the bag.
     * Time complexity of this method is O(1)
     * it returns elements in the bag.
     *
     *
     * @return     Return an iterator that iterates
     *             over the items in the bag.
     */
    public Iterator<Item> iterator()  {
        return new ListIterator();
    }
    /**
     * Class for list iterator.
     */
    private class ListIterator implements Iterator<Item> {
        /**
         * this is the currnt node.
         */
        private Node current = first;
        /**
         * Determines if it has next.
         *
         * @return     True if has next,
         *             False otherwise.
         */
        public boolean hasNext() {
            return current != null;
        }
        /**
         * this method removes elements.
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
