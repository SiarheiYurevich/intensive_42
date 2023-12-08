package arraylist.impl;

import arraylist.IntensiveList;

import java.util.Arrays;
import java.util.Comparator;

public class ArrayList_SlavaSlesObj<E> implements IntensiveList<E> {

    public static final Object[] EMPTY_LIST = {};
    private static final int DEFAULT_ARRAY_SIZE = 10;
    private static final float GROW_FACTOR = 1.5F;
    private int size;
    private Object[] array;
    private boolean isSorted;

    public ArrayList_SlavaSlesObj() {
        this(0);
    }

    public ArrayList_SlavaSlesObj(int initialSize) {
        if (initialSize > 0) {
            array = new Object[initialSize];
        } else if (initialSize == 0) {
            array = EMPTY_LIST;
        } else {
            throw new IllegalArgumentException("Illegal initial size!");
        }
        size = 0;
        isSorted = false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(Object element) {
        if (size == 0) {
            array = new Object[DEFAULT_ARRAY_SIZE];
        }
        checkSizeAndGrowArray();
        array[size] = element;
        size++;
        isSorted = false;
    }

    private void checkSizeAndGrowArray() {
        if (size == array.length) {
            array = Arrays.copyOf(array, (int) (array.length * GROW_FACTOR));
        }
    }

    @Override
    public void add(int index, Object element) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        } else if (index == size) {
            add(element);
        } else {
            checkSizeAndGrowArray();
            for (int i = size; i >= index; i--) {
                array[i + 1] = array[i];
            }
            array[index] = element;
            size++;
            isSorted = false;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public E get(int index) {
        checkIndex(index);
        return (E) array[index];
    }

    @Override
    @SuppressWarnings("unchecked")
    public E set(int index, Object element) {
        checkIndex(index);
        Object previousValue = array[index];
        array[index] = element;
        return (E) previousValue;
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds for length " + size);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public E remove(int index) {
        checkIndex(index);
        Object removedValue = array[index];
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        size--;
        array[size] = null;
        return (E) removedValue;
    }

    @Override
    public void clear() {
        array = EMPTY_LIST;
        size = 0;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void quickSort(Comparator<E> comparator) {
        split(size);
        E[] typedArray = (E[]) new Object[size];
        for (int i = 0; i < size; i++) {
            typedArray[i] = (E) array[i];
        }
        Arrays.sort(typedArray, comparator);
        array = typedArray;
        isSorted = true;
    }

    @Override
    public boolean isSorted() {
        return isSorted;
    }

    @Override
    public void split(int size) {
        if (size > this.size || size < 0) {
            throw new IndexOutOfBoundsException("Size " + size + " out of bounds for length " + this.size);
        }
        array = Arrays.copyOf(array, size);
        this.size = size;
    }
}
