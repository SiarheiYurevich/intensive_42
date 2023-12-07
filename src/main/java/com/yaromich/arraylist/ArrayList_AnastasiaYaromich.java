package com.yaromich.arraylist;

import java.util.Comparator;

/**
 * Resizable-array implementation.
 * @param <E> - type of elements in list
 * @author AnastasiaYaromich
 */
public class ArrayList_AnastasiaYaromich<E> implements IntensiveList<E> {

    /**
     * Size for array which used to store the elements by default.
     * Capacity will grow automatically when element are added to list.
     */
    private final int DEFAULT_CAPACITY = 10;

    /**
     * Array into which elements of the ArrayList_AnastasiaYaromich
     * are stored.
     */
    private Object[] data;
    /**
     * Size(number of elements ArrayList_AnastasiaYaromich contains)
     */
    private int size;
    /**
     * Keep an information about elements contains in
     * ArrayList_AnastasiaYaromich are sorted or not.
     */
    private boolean isSorted;

    /**
     * Construct an empty ArrayList_AnastasiaYaromich with the
     * capacity by default.
     */
    public ArrayList_AnastasiaYaromich() {
        this.data = new Object[DEFAULT_CAPACITY];
    }

    /**
     * Construct an empty ArrayList_AnastasiaYaromich with the
     * capacity specified capacity.
     * Param: capacity - capacity of the ArrayList_AnastasiaYaromich.
     * Throws: IllegalArgumentException – if the capacity is negative.
     */
    public ArrayList_AnastasiaYaromich(int capacity) {
        if (capacity > 0) {
            this.data = new Object[capacity];
        } else if (capacity == 0) {
            this.data = new Object[DEFAULT_CAPACITY];
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + capacity);
        }
    }

    /**
     *
     * @return the number of elements in ArrayList_AnastasiaYaromich.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Adds element to the end of ArrayList_AnastasiaYaromich.
     * @param element which need to be added to the ArrayList_AnastasiaYaromich.
     */
    @Override
    public void add(E element) {
        add(element, data);
    }

    /**
     * This method check if number of elements of the
     * ArrayList_AnastasiaYaromich(size) equal to current capacity.
     * If they are equals, array(data) into which elements of the
     * ArrayList_AnastasiaYaromich are stored grows.
     * And then new element adds to the end of the array.
     * @param element which need to be added to the ArrayList_AnastasiaYaromich.
     * @param data array into which elements of the ArrayList_AnastasiaYaromich
     * are stored.
     */
    private void add(E element, Object[] data) {
        if (size == data.length) {
            data = growArray();
        }
        data[size] = element;
        size++;
    }

    /**
     * Increase the capacity of the ArrayList_AnastasiaYaromich.
     * @return array in which capacity are grown (oldCapacity * 1.5).
     */
    private Object[] growArray() {
        int oldCapacity = data.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        data = copyOf(data, newCapacity);
        return data;
    }

    /**
     * Create array with new capacity and copy all elements in
     * this array.
     * @param data - array with old capacity. It's necessary to
     * copy all the elements from these array to new array with
     * new capacity.
     * @param capacity - the new capacity of the array.
     * @return - new Array with new capacity.
     */
    private Object[] copyOf(Object[] data, int capacity) {
        Object[] newArray = new Object[capacity];
        for (int i = 0; i < size; i++) {
            newArray[i] = data[i];
        }
        return newArray;
    }

    /**
     * Insert the element at the specified position.
     * Shifts the elements from these position to
     * the right(adds one to indices).
     * @param index - specified position in which element
     * to be inserted.
     * @param element - element to be inserted.
     */
    @Override
    public void add(int index, E element) {
        checkIndex(index);
        if (size == data.length) {
            data = growArray();
        }
        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        size++;
        data[index] = element;
    }

    /**
     * Returns the element at the specified position.
     * @param index - position of the element to return.
     * @return element at the specified position.
     */
    @SuppressWarnings("unchecked")
    @Override
    public E get(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", size: " + size);
        }
        return (E) data[index];
    }

    /**
     * Check is specified position is correct.
     * @param index - position of the element.
     */
    private void checkIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    /**
     * Replaces the element at the specified position
     * with the specified element.
     * @param index - index of the element to replace
     * @param element - element to be stored at the specified position
     * @return the element previously at the specified position
     */
    @Override
    public E set(int index, E element) {
        checkIndex(index);
        E previous = get(index);
        data[index] = element;
        return previous;
    }

    /**
     * Remover the element at the specified position.
     * Shifts any subsequent elements to the left
     * (subtracts one from their indices).
     * @param index - index of element to be removed.
     * @return the element that was removed from the list
     */
    @SuppressWarnings("unchecked")
    @Override
    public E remove(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", size: " + size);
        }
        E previous = (E) data[index];
        doRemove(data, index);
        return previous;
    }

    /**
     * Shifts any subsequent elements to the left
     * (subtracts one from their indices).
     * Decrease size of array.
     * @param data - array in which element removes.
     * @param index - index of element to be removed.
     */
    private void doRemove(Object[] data, int index) {
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        data[size - 1] = null;
        size--;
    }

    /**
     * Replace all the elements
     * of the ArrayList_AnastasiaYaromich to null.
     * Change capacity to default.
     */
    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            data[i] = null;
        }
        data = new Object[DEFAULT_CAPACITY];
    }

    /**
     * Sort all the elements of the ArrayList_AnastasiaYaromich
     * according to the order induced by the specified Comparator.
     * @param comparator - comparator used to compare elements.
     */
    @Override
    public void quickSort(Comparator<E> comparator) {
        quickSort(comparator, 0, size - 1, data);
    }

    /**
     * This algorithm select a pivot element and
     * divide array into two subarrays.
     * Elements that are smaller than the pivot,
     * and elements that are greater than the pivot.
     * And then recursively apply sorting to these
     * subarrays.It's continues until one element remains,
     * which is then sorted.
     * @param comparator- comparator used to compare elements.
     * @param low - start index in array.
     * @param high - end index in array.
     * @param data - array which are sorted.
     */
    @SuppressWarnings("unchecked")
    void quickSort(Comparator<E> comparator, int low, int high, Object[] data) {
        if(data.length == 0) {
            return;
        }
        E[] elementData = (E[]) data;

        if(low < high) {
            E pivot = elementData[high];
            int i = (low - 1);

            for (int j = low; j < high; j++) {
                if(comparator.compare(pivot, elementData[j]) > 0) {
                    i++;

                    E temp = elementData[i];
                    elementData[i] = elementData[j];
                    elementData[j] = temp;
                }
            }
            E temp = elementData[i + 1];
            elementData[i + 1] = elementData[high];
            elementData[high] = temp;

            int pi = i + 1;

            quickSort(comparator, low, (pi - 1), elementData);
            quickSort(comparator, (pi + 1), high, elementData);
        }
        this.data = elementData;
        isSorted = true;
    }

    /**
     * Returns the information about is elements
     * are sorted or not.
     * @return true if elements are sorted and
     * false if they are not.
     */
    @Override
    public boolean isSorted() {
        return isSorted;
    }

    /**
     *
     * Cuts the elements in ArrayList_AnastasiaYaromich to
     * specified size.
     * @param size - specified size.
     */
    @Override
    public void split(int size) {
        if(size > this.size || size < 0) {
            throw new IndexOutOfBoundsException("Size: " + size);
        }
        if(size < this.size) {
            Object[] newArray = new Object[data.length];
            for(int i = 0; i < size; i++) {
                newArray[i] = data[i];
            }
            this.data = newArray;
            this.size = size;
        }
    }

    /**
     * Calls a toString() method for ArrayList_AnastasiaYaromich,
     * which return string representation of it's the elements.
     */
    public void display() {
        System.out.println(this);
    }

    /**
     * Returns a string representation of the elements in
     * ArrayList_AnastasiaYaromich.
     * @return string representation of the elements.
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            builder.append(data[i]);
            if(i < size - 1) {
                builder.append(", ");
            }
        }
        builder.append("]");
        return builder.toString();
    }
}
