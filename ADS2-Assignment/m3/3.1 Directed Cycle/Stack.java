/*************************************************************************
 *  Compilation:  javac Stack.java
 *  Execution:    java Stack < input.txt
 *
 *  A generic stack, implemented using a linked list. Each stack
 *  element is of type Item.
 *
 *  % more tobe.txt
 *  to be or not to - be - - that - - - is
 *
 *  % java Stack < tobe.txt
 *  to be not that or be (2 left on stack)
 *
 *************************************************************************/

import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 *  The <tt>Stack</tt> class represents a last-in-first-out
 *  (LIFO) stack of generic items.
 *  It supports the usual <em>push</em> and <em>pop</em>
 *  operations, along with methods
 *  for peeking at the top item, testing if the stack is
 *  empty, and iterating through
 *  the items in LIFO order.
 *  <p>
 *  All stack operations except iteration are constant time.
 *  <p>
 *  For additional documentation, see
 *  <a href="/algs4/13stacks">Section 1.3</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick
 *  and Kevin Wayne.
 */
/**
 * List of items.
 *
 * @param      <Item>  The item
 */
public class Stack<Item> implements Iterable<Item> {
    /**
     * size of the stack.
     */
    private int size;
    /**
     * top of stack.
     */
    private Node first;

    /**
     * Class for node.
     */
    private class Node {
        /**
         * item in that node.
         */
        private Item item;
        /**
         * address of the next node.
         */
        private Node next;
    }

    /**
     * Create an empty stack.
     */
    public Stack() {
        first = null;
        size = 0;
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
     * returns size of stack.
     *
     * @return     returns size of stack.
     */
    public int size() {
        return size;
    }

    /**
     * this method adds the items to the stack.
     * Time complexity of this O(1)
     *
     * @param      item  The item
     */
    public void push(final Item item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        size++;
    }

    /**
     * removes the last element in the stack.
     * Time complexity of this method is O(1).
     *
     * @return     returns the item which is deleted.
     */
    public Item pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack underflow");
        }
        Item item = first.item;        // save item to return
        first = first.next;            // delete first node
        size--;
        return item;                   // return the saved item
    }


    /**
     * returns last item in stack.
     * Time complexity of this method is O(1).
     *
     * @return     returns last item in stack.
     */
    public Item peek() {
        if (isEmpty()) {
            throw new RuntimeException("Stack underflow");
        }
        return first.item;
    }

    /**
     * Returns a string representation of the object.
     * Time complexity of this method is O(N).
     * N is number of items in the stack.
     *
     * @return     String representation of the object.
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this) {
            s.append(item + " ");
        }
        return s.toString();
    }


    /**
     * Return an iterator to the stack that iterates
     * through the items in LIFO order.
     * Time complexity of this method is O(1).
     *
     *
     * @return     Return an iterator to the stack
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

