package task1;

import java.util.Arrays;
import java.util.Comparator;

public class ArrayList_TimurAgeev<E> implements IntensiveList<E> {
    private E[] array;
    private int size;
    private final static int DEFAULT_CAPACITY = 10;
    private final static double CAPACITY_MULTIPLIER = 1.5;

    @SuppressWarnings("unchecked")
    public ArrayList_TimurAgeev() {
        array = (E[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(E element) {
        if (size >= array.length) {
            increaseCapacity();
        }

        array[size] = element;
        size++;
    }

    @Override
    public void add(int index, E element) {
        checkIndex(index);

        if (size >= array.length - 1) {
            increaseCapacity();
        }

        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = element;
        size++;
    }

    @Override
    public E get(int index) {
        checkIndex(index);

        return array[index];
    }

    @Override
    public E set(int index, E element) {
        checkIndex(index);

        E replaceableElement = array[index];
        array[index] = element;

        return replaceableElement;
    }

    @Override
    public E remove(int index) {
        checkIndex(index);

        E removedElement = array[index];

        System.arraycopy(array, index + 1, array, index + 1, size - index - 1);
        size--;
        array[size] = null;

        return removedElement;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void clear() {
        array = (E[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public void quickSort(Comparator<E> comparator) {
        if (isSorted(comparator)) {
            return;
        }

        sortArray(array, 0, size - 1, comparator);
    }

    @Override
    public boolean isSorted(Comparator<E> comparator) {
        if (size <= 1) {
            return true;
        }

        for (int i = 1; i < size; i++) {
            if (comparator.compare(array[i - 1], array[i]) > 0) {
                return false;
            }
        }

        return true;
    }

    @Override
    public void split(int size) {
        if (size < 0) {
            throw new IndexOutOfBoundsException();
        }

        System.arraycopy(array, 0, array, size, 0);
        this.size = size;
    }

    /**
     * Пересоздает массив с длинной равной 1.5 длины существующего массива и копирует в него все элементы из существующего.
     */
    private void increaseCapacity() {
        array = Arrays.copyOf(array, (int) (size * CAPACITY_MULTIPLIER));
    }

    /**
     * Проверяет валидность "index" и в случае если он не валиден выбрасывает {@link IndexOutOfBoundsException}.
     *
     * @param index index, подлежащий проверке
     */
    private void checkIndex(int index) {
        if (index > size) {
            throw new IndexOutOfBoundsException("index can't be more than size. index = " + index + ", size = " + size);
        }

        if (index < 0) {
            throw new IndexOutOfBoundsException("index can't be 0");
        }
    }

    /**
     * производит быструю сортировку по возрастанию используя рекурсию.
     *
     * @param array      переданный массив, который подлежит сортировке.
     * @param left       индекс начала отсчета с начала массива.
     * @param right      индекс начала отсчета с конца массива.
     * @param comparator переданный компаратор, по которому происходит сравнение объектов
     */
    private void sortArray(E[] array, int left, int right, Comparator<E> comparator) {
        if (array.length == 0 || left >= right) {
            return;
        }

        int i = left;
        int j = right;

        E supportElement = array[left];

        while (i <= j) {
            while (comparator.compare(array[i], supportElement) < 0) {
                i++;
            }

            while (comparator.compare(array[j], supportElement) > 0) {
                j--;
            }

            if (i <= j) {
                E temp = array[i];
                array[i] = array[j];
                array[j] = temp;

                i++;
                j--;
            }
        }

        if (j > left) {
            sortArray(array, left, j, comparator);
        }

        if (i < right) {
            sortArray(array, i, right, comparator);
        }
    }
}
