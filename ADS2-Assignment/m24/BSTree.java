import java.util.NoSuchElementException;
import java.util.Scanner;
class BSTree<Key extends Comparable<Key>, Value> {
	private Key[] keys;
  private Value[] values;
  private int[] size;
  private int[] leftLinks;
  private int[] rightLinks;

  public BSTree(int sizes) {

    keys = (Key[]) new Comparable[sizes];
    values = (Value[]) new Object[sizes];
    size = new int[sizes];
    leftLinks = new int[sizes];
    rightLinks = new int[sizes];

    for (int i = 0; i < sizes; i++) {
      leftLinks[i] = -1;
      rightLinks[i] = -1;
    }
  }

  public int size() {
    return size[0];
  }

  public int size(int index) {
    if (index == -1) {
      return 0;
    }
    return size[index];
  }

  public Value get(Key key) {
    return get(0, key);
  }

  private Value get(int index, Key key) {
    if (index == -1 || keys[index] == null) {
      throw new NegativeArraySizeException();
    }

    int compare = key.compareTo(keys[index]);
    if (compare < 0) {
      return get(leftLinks[index], key);
    } else if (compare > 0) {
      return get(rightLinks[index], key);
    } else {
      return values[index];
    }
  }

  public void put(Key key, Value value) {
    if (size() == keys.length) {
      System.out.println("Tree is full");
      return;
    }

    put(0, key, value);
  }

  private int put(int index, Key key, Value value) {
    if (index == -1 || keys[index] == null) {
      int nextElementIndex = size();

      keys[nextElementIndex] = key;
      values[nextElementIndex] = value;
      size[nextElementIndex] = 1;

      return nextElementIndex;
    }

    int compare = key.compareTo(keys[index]);

    if (compare < 0) {
      leftLinks[index] = put(leftLinks[index], key, value);
    } else if (compare > 0) {
      rightLinks[index] = put(rightLinks[index], key, value);
    } else {
      values[index] = value;
    }

    size[index] = size(leftLinks[index]) + 1 + size(rightLinks[index]);
    return index;
  }

  public void delete(Key key) {
    int rootIndex = delete(0, key);

    if (rootIndex == -1) {
      eraseKeyData(0);
      return;
    }

    //Update root
    if (rootIndex != 0) {
      copyDataFromOtherKey(0, rootIndex);
      eraseKeyData(rootIndex);
    }
  }

  private void eraseKeyData(int index) {
    keys[index] = null;
    values[index] = null;
    size[index] = 0;
    leftLinks[index] = -1;
    rightLinks[index] = -1;
  }

  private void copyDataFromOtherKey(int indexToCopyTo, int indexToCopyFrom) {
    keys[indexToCopyTo] = keys[indexToCopyFrom];
    values[indexToCopyTo] = values[indexToCopyFrom];
    size[indexToCopyTo] = size[indexToCopyFrom];
    leftLinks[indexToCopyTo] = leftLinks[indexToCopyFrom];
    rightLinks[indexToCopyTo] = rightLinks[indexToCopyFrom];
  }

  private int delete(int index, Key key) {
    if (index == -1 || keys[index] == null) {
      return -1;
    }

    int compare = key.compareTo(keys[index]);
    if (compare < 0) {
      int leftIndex = delete(leftLinks[index], key);
      leftLinks[index] = leftIndex;
    } else if (compare > 0) {
      int rightIndex = delete(rightLinks[index], key);
      rightLinks[index] = rightIndex;
    } else {
      keys[index] = null;
      values[index] = null;
      size[index] = 0;

      if (leftLinks[index] == -1) {
        int rightLinkIndex = rightLinks[index];
        rightLinks[index] = -1;

        return rightLinkIndex;
      } else if (rightLinks[index] == -1) {
        int leftLinkIndex = leftLinks[index];
        leftLinks[index] = -1;

        return leftLinkIndex;
      } else {
        int promotedIndex = min(rightLinks[index]);
        rightLinks[promotedIndex] = deleteMin(rightLinks[index], false);
        leftLinks[promotedIndex] = leftLinks[index];

        rightLinks[index] = -1;
        leftLinks[index] = -1;

        index = promotedIndex;
      }
    }

    size[index] = size(leftLinks[index]) + 1 + size(rightLinks[index]);
    return index;
  }

  public void deleteMin() {
    int rootIndex = deleteMin(0, true);

    if (rootIndex == -1) {
      eraseKeyData(0);
      return;
    }

    //Update root
    if (rootIndex != 0) {
      copyDataFromOtherKey(0, rootIndex);
      eraseKeyData(rootIndex);
    }
  }

  private int deleteMin(int index, boolean setKeyNull) {
    if (index == -1 || keys[index] == null) {
      return -1;
    }

    if (leftLinks[index] == -1) {
      int rightKeyLink = rightLinks[index];
      if (setKeyNull) {
        eraseKeyData(index);
      }

      return rightKeyLink;
    }

    int leftIndex = deleteMin(leftLinks[index], setKeyNull);
    leftLinks[index] = leftIndex;

    size[index] = size(leftLinks[index]) + 1 + size(rightLinks[index]);
    return index;
  }

  public Key min() {
    if (size() == 0) {
      throw new NullPointerException("Empty binary search tree.");
    }

    int minIndex = min(0);
    return keys[minIndex];
  }

  private int min(int index) {
    if (leftLinks[index] == -1) {
      return index;
    }
    return min(leftLinks[index]);
  }

}