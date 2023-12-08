
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

/**
 *Implementation of the ArrayList collection with dynamic
 *array extension. It has methods for adding to
 *the collection is both at the end and at the specified deletion and cleanup index. There is a possibility
 * Quick sorting. It is not thread-safe.
 */

public class ArrayList_NikitaNazarjev<E> implements IntensiveList<E>, Iterable<E> {

    private static final int INIT_CAPACITY = 15;

    private E[] objects;
    private int size;

    private boolean isSorted;

    /**
     * The constructor initializes an array of objects, if the initial length is not specified, sets it to 0
     */
    public ArrayList_NikitaNazarjev() {
        isSorted = false;
        objects = (E[]) new Object[INIT_CAPACITY];
        size = 0;
    }


    /**
     * a constructor that allows you to specify the initial length of the array to save time for regrouping
     * @param size Initialization size array
     */
    public ArrayList_NikitaNazarjev(int size) {
        if (size <= 0) {
            throw new IndexOutOfBoundsException("Index is less than zero");
        }
        isSorted = false;
        objects = (E[]) new Object[size];
    }

    /**
     * the method allows you to add an element to a collection,
     * it only works with the elements specified in the generics examples of creating an object
     * @param element  the item to be added to the collection
     */
    @Override
    public void add(E element) {
        if (objects.length == size) {
            resize();
        }
        objects[size] = element;
        isSorted = false;
        size++;
    }

    /**
     *the method allows you to add an element to the collection,
     * while the elements to the right of it will be shifted by one position
     * @param index  indicates which position the element will be added to
     * @param element  the item to be added to the collection
     */
    @Override
    @SuppressWarnings("unchecked")
    public void add(int index, E element) {
        E[] saveArray = Arrays.copyOf(objects, objects.length);
        checkIndex(index, size);
        if (index == size) {
            this.add(element);
            return;
        } else {
            objects = (E[]) new Object[objects.length + 1];
            for (int i = 0; i <= saveArray.length; i++) {
                if (i < index) {
                    objects[i] = saveArray[i];
                } else if (i == index) {
                    objects[i] = element;
                } else {
                    objects[i] = saveArray[i - 1];
                }
            }
        }
        isSorted = false;
        size++;
    }

    /**
     * @return - returns the number of items in the collection
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * allows you to get a collection item by index
     * @param index  indicates which position the element will be added to
     * @return  a collection item
     */
    @Override
    public E get(int index) {
        return objects[index];
    }

    /**
     * adds an element to the collection,
     * at the position specified by the index.
     * The element under this index is removed from the collection and returned in the method
     * @param index indicates which position the element will be added to
     * @param element the item to be added to the collection
     * @return an item deleted from the collection
     */
    @Override
    public E set(int index, E element) {
        E saveValue = objects[index];
        objects[index] = element;
        isSorted = false;
        return saveValue;
    }


    /**
     * clears the collection of all items.
     */
    @Override
    @SuppressWarnings("unchecked")
    public void clear() {
        objects = (E[]) new Object[INIT_CAPACITY];
        size = 0;
        isSorted = false;
    }

    /**
     * Sorts the default collection in ascending order
     * @param comparator - comporator this object
     */
    public void quickSort(Comparator comparator){
        sortForWrapper(objects,0,size()-1,comparator);
        isSorted = true;
    }

    /**
     * An auxiliary method for sorting a collection
     * @param sortArr  the internal array to be sorted
     * @param low  the minimum index in the array should be 0
     * @param high  the maximum index in the array
     * @param comparator comporator this object
     */
    @SuppressWarnings("unchecked")
    private void sortForWrapper(E[] sortArr, int low, int high, Comparator comparator) {

        if (sortArr.length == 0 || low >= high) return;

        int middle = low + (high - low) / 2;

        E border = sortArr[middle];

        int i = low, j = high;
        while (i <= j) {

            while (comparator.compare(sortArr[i], border) < 0) i++;
            while (comparator.compare(sortArr[j], border) > 0) j--;

            if (i <= j) {
                E swap = sortArr[i];
                sortArr[i] = sortArr[j];
                sortArr[j] = swap;
                i++;
                j--;
            }
        }
        if (low < j) sortForWrapper(sortArr, low, j, comparator);
        if (high > i) sortForWrapper(sortArr, i, high, comparator);
    }

    /**
     * shows whether the array is sorted or not, the value is set to true, after the quickSort method
     * @return returns a Boolean value depending on the sorting of the collection
     */
    @Override
    public boolean isSorted() {
        return isSorted;
    }

    /**
     * allows you to crop the collection from 0 to the selected index
     * @param size the number of items that will remain in the collection
     */
    @Override
    public void split(int size) {
        checkIndex(size, this.size);
        this.size = size;
    }

    /**
     * an auxiliary method that recreates our internal array when it is filled
     */
    private void resize() {
        objects = Arrays.copyOf(objects, objects.length + (objects.length / 2));
    }

    /**
     * an auxiliary method that checks whether the index is specified correctly
     * @param index the index specified by the user
     * @param size the size of our collection
     */
    private void checkIndex(int index, int size) {
        if (index > size) {
            throw new IndexOutOfBoundsException("Index is longer than the length of the array");
        } else if (index < 0) {
            throw new IndexOutOfBoundsException("Index is less than zero");
        }
    }


    /**
     * deletes an item from the collection at the specified index
     * @param index the index specified by the user
     * @return deleted object
     */
    @Override
    public E remove(int index) {
        checkIndex(index, size - 1);
        E save = objects[index];
        for (int i = index; i < objects.length - 1; i++) {
            objects[i] = objects[i + 1];
        }
        size--;
        isSorted = false;
        return save;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArrayList_NikitaNazarjev<?> that)) return false;

        for (int i = 0; i < this.size(); i++) {
            if (!this.get(i).equals(that.get(i))) {
                return false;
            }
        }
        if (size() != that.size()) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(objects);
        result = 31 * result + size;
        return result;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator() {
            int index = 0;

            @Override
            public boolean hasNext() {
                return objects[index] == null ? false : true;
            }

            @Override
            public E next() {
                return objects[index++];
            }
            @Override
            public void remove() {
                ArrayList_NikitaNazarjev.this.remove(index--);
            }
        };

    }
}
