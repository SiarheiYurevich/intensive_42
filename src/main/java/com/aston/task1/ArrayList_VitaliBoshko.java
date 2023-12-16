package com.aston.task1;

import java.util.Comparator;

/**
 * Class implementing structure of data for keeping elements the specified of types
 *
 * @param <E> the type of elements in the list
 * @author Vitali Boshko
 */
@SuppressWarnings(value = {"unchecked"})
public class ArrayList_VitaliBoshko<E> implements IntensiveList<E> {
    private E[] array;
    private final int DEFAULT_CAPACITY = 10;
    private final int INITIAL_SIZE = 0;
    private int size = INITIAL_SIZE;
    private final double ARRAY_LENGTH_INCREASE_FACTOR = 1.5;

    /**
     * Creates the empty list with default capacity
     */
    public ArrayList_VitaliBoshko() {
        this.array = (E[]) new Object[DEFAULT_CAPACITY];
    }

    /**
     * Creates the empty list with specified capacity
     *
     * @param capacity initial capacity of the list
     */
    public ArrayList_VitaliBoshko(Integer capacity) {
        this.array = (E[]) new Object[capacity];
    }

    /**
     * The method returns size specified the list
     *
     * @return size the list
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * The method adds new element to the list
     *
     * @param element to be added to the list
     */
    @Override
    public boolean add(E element) {
        if (array.length == size) {
            resize((int) (size * ARRAY_LENGTH_INCREASE_FACTOR));
        }

        array[size] = element;
        size++;
        return true;
    }

    private void resize(int newCapacity) {
        Object[] newArray = new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newArray[i] = array[i];
        }

        array = (E[]) newArray;
    }

    /**
     * The method adds new element to the list to specified position
     *
     * @param index   specified position for adding a new element
     * @param element to be added to the list
     */
    @Override
    public void add(int index, E element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index -" + index + ", is not in range");
        }

        if (array.length == size) {
            resize((int) (size * ARRAY_LENGTH_INCREASE_FACTOR));
        }

        for (int i = size - 1; i >= index; i--) {
            array[i + 1] = array[i];
        }

        array[index] = element;
        size++;
    }

    /**
     * The method for getting element by index
     *
     * @param index of the requested element
     * @return the requested element of the list
     */
    @Override
    public E get(int index) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("Index -" + index + ", is not in range");
        }
        return array[index];
    }

    /**
     * The method for set new element on existing index
     *
     * @param index   index of the element to replace
     * @param element element which replace to specified position
     * @return element at specified position which was before replacement
     */
    @Override
    public E set(int index, E element) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("Index -" + index + ", is not in range");
        }

        E oldElement = array[index];
        array[index] = element;

        return oldElement;
    }


    /**
     * The method removes the specified element from the list
     *
     * @param index of element that needs to remove
     * @return the element removed from the list
     */
    @Override
    public E remove(int index) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("Index -" + index + ", is not in range");
        }

        E removeElement = array[index];
        for (int i = index; i < size - 1; i++) {
            array[index] = array[index + 1];
        }

        size--;
        array[size] = null;

        return removeElement;
    }

    /**
     * The method clears the list and returns the list to default capacity
     */
    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        array = (E[]) new Object[DEFAULT_CAPACITY];
        size = INITIAL_SIZE;
    }

    /**
     * The method sorts the list by specified condition
     *
     * @param comparator object that specifies a condition for comparing elements in a list
     */
    @Override
    public void quickSort(Comparator<E> comparator) {
        recursionQuickSort(0, size - 1, comparator);
    }

    private void recursionQuickSort(int left, int right, Comparator<E> comparator) {
        if ((right - left) > 0) {
            E supportElement = array[right];
            int supportElementPosition = detPosition(left, right, supportElement, comparator);

            recursionQuickSort(left, supportElementPosition - 1, comparator);
            recursionQuickSort(supportElementPosition + 1, right, comparator);
        }
    }

    private int detPosition(int leftBorderPos, int rightBorderPos, E supportElement, Comparator<E> comparator) {
        int leftPos = leftBorderPos - 1;
        int rightPos = rightBorderPos;

        while (true) {
            while (comparator.compare(array[++leftPos], supportElement) < 0) ;
            while (rightPos > 0 && comparator.compare(array[--rightPos], supportElement) > 0) ;

            if (leftPos >= rightPos) break;
            else exchange(leftPos, rightPos);
        }
        exchange(leftPos, rightBorderPos);

        return leftPos;
    }

    private void exchange(int i, int j) {
        E temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /**
     * The Method checks is sorted list or not
     *
     * @param comparator object that specifies a condition for comparing elements in a list
     * @return true if the list is sorted
     */
    @Override
    public boolean isSorted(Comparator<E> comparator) {
        for (int i = 0; i < size - 1; i++) {
            if (comparator.compare(array[i], array[i + 1]) > 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * The method reduces the list size to the specified value
     *
     * @param size of the list after split
     */
    @Override
    public void split(int size) {
        if (size < 0 || size > this.size){
            throw new IndexOutOfBoundsException("Size -" + size + ", cannot be applied");
        }

        Object[] reduceArray = new Object[size];
        for (int i = 0; i < size; i++) {
            reduceArray[i] = array[i];
        }

        this.size = size;
        array = (E[]) reduceArray;
    }

    /**
     * The method use for string display of list data
     *
     * @return string display of list data
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (E e : array) {
            stringBuilder.append(e.toString()).append(", ");
        }
        return stringBuilder.toString();
    }
}
