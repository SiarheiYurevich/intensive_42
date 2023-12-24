package task_1;

import java.util.Arrays;
import java.util.Comparator;

public class ArrayList_KonstantinZverev<E> implements IntensiveList<E> {
    private static final int DEFAULT_CAPACITY = 10;
    public static final double CAPACITY_INCREASE = 1.5;
    private Object[] list;

    private int size;

    public ArrayList_KonstantinZverev() {
        list = new Object[DEFAULT_CAPACITY];
    }

    /**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Appends the specified element to the end of this list (optional
     * operation).
     *
     * @param element element to be appended to this list
     */
    @Override
    public void add(E element) {
        add(size, element);
    }

    /**
     * Inserts the specified element at the specified position in this list
     * (optional operation).  Shifts the element currently at that position
     * (if any) and any subsequent elements to the right (adds one to their
     * indices).
     *
     * @param index   index at which the specified element is to be inserted
     * @param element element to be inserted
     * @throws IndexOutOfBoundsException if the index is out of range
     *                                   ({@code index < 0 || index > size()})
     */
    @Override
    public void add(int index, E element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

        if (size == list.length) {
            increaseCapacity();
        }

        for (int i = size; i > index; i--) {
            list[i] = list[i - 1];
        }

        list[index] = element;
        size++;
    }

    /**
     * Returns element in the specified position in the list
     *
     * @param index index of the element
     * @return element in the specified position
     * @throws IndexOutOfBoundsException if the index is out of range
     *                                   ({@code index < 0 || index > size()})
     */
    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        return elementOfType(index);
    }

    /**
     * Replaces element in the specified position to new value
     *
     * @param index   position of element to replace
     * @param element new value of element
     * @return value of replaced element
     * @throws IndexOutOfBoundsException if the index is out of range
     *                                   ({@code index < 0 || index > size()})
     */
    @Override
    public E set(int index, E element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        E elementToReplace = elementOfType(index);
        list[index] = element;
        return elementToReplace;
    }

    /**
     * Removes element with specified index
     *
     * @param index index of element to be removed from this list
     * @return removed element
     * @throws IndexOutOfBoundsException if the index is out of range
     *                                   ({@code index < 0 || index > size()})
     */
    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        E elementToRemove = elementOfType(index);

        for (int i = index; i < size - 1; i++) {
            list[i] = list[i + 1];
        }
        list[size - 1] = null;

        size--;
        return elementToRemove;
    }

    /**
     * Clears all elements from the list
     */
    @Override
    public void clear() {
        list = new Object[list.length];
        size = 0;
    }

    /**
     * Sorts list with quick sort method.
     *
     * @param comparator to compare elements
     */
    @Override
    public void quickSort(Comparator<E> comparator) {
        quickSort(0, size - 1, comparator);
    }

    /**
     * Returns true if list is in state of ascending or descending order
     *
     * @param comparator to compare elements
     * @return true if elements are ordered and false otherwise
     */
    @Override
    public boolean isSorted(Comparator<E> comparator) {
        boolean isDesc = true;
        boolean isAsc = true;
        for (int i = 1; i < size; i++) {
            isAsc = isAsc && comparator.compare(elementOfType(i - 1), elementOfType(i)) >= 0;
            isDesc = isDesc && comparator.compare(elementOfType(i - 1), elementOfType(i)) <= 0;
        }
        return isAsc || isDesc;
    }

    /**
     * Truncate list to specified length
     *
     * @param size new length of list
     * @throws IllegalArgumentException if new size less than zero or greater than list size
     */
    @Override
    public void split(int size) {
        if (size < 0 || size > this.size) {
            throw new IllegalArgumentException("Size of splited array must be between 0 and list size");
        }

        list = Arrays.copyOf(list, size);
        this.size = size;
    }

    @SuppressWarnings("unchecked")
    private E elementOfType(int index) {
        return (E) list[index];
    }

    private void swap(int index1, int index2) {
        Object tmp = list[index2];
        list[index2] = list[index1];
        list[index1] = tmp;
    }

    private void quickSort(int beginIndex, int endIndex, Comparator<E> comparator) {
        if (beginIndex == endIndex) {
            return;
        }

        int largeLeftIndex = beginIndex;
        int smallRightIndex = endIndex - 1;
        while (largeLeftIndex <= smallRightIndex) {
            while (largeLeftIndex <= endIndex && comparator.compare(elementOfType(largeLeftIndex), elementOfType(endIndex)) < 0) {
                largeLeftIndex++;
            }
            while (smallRightIndex >= beginIndex && comparator.compare(elementOfType(smallRightIndex), elementOfType(endIndex)) > 0) {
                smallRightIndex--;
            }
            if (largeLeftIndex < smallRightIndex) {
                swap(largeLeftIndex, smallRightIndex);
            }
        }
        swap(largeLeftIndex, endIndex);
        quickSort(beginIndex, Math.max(beginIndex, largeLeftIndex - 1), comparator);
        quickSort(Math.min(endIndex, largeLeftIndex + 1), endIndex, comparator);
    }

    private void increaseCapacity() {
        int newCapacity = (int) Math.ceil(list.length * CAPACITY_INCREASE);
        list = Arrays.copyOf(list, newCapacity);
    }
}
