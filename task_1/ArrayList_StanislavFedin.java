package task_1;

import java.util.Comparator;

/**
 * Реализация task_1.IntensiveList<E> идейно похожая на ArrayList<E>
 * @param <E> - тип элементов хранящихся в массиве
 * @author Stanislav Fedin
 */
public class ArrayList_StanislavFedin<E> implements IntensiveList<E>{

    private static final int DEFAULT_CAPACITY = 10;
    private Object[] arr;
    private int arrSize = 0;

    private boolean isSorted = false;

    /**
     * Создает пустой task_1.ArrayList_StanislavFedin
     * с вместимостью по умолчанию(10).
     */
    public ArrayList_StanislavFedin () {
        this.arr = new Object[DEFAULT_CAPACITY];
    }

    /**
     * Создает пустой task_1.ArrayList_StanislavFedin
     * с указанной в качестве аргумента вместимостью.
     * @param initialCapacity - вместимость массива
     * @throws IllegalArgumentException - если вместимость меньше 1.
     */
    public ArrayList_StanislavFedin (int initialCapacity) {
        if (initialCapacity < 1) throw new IllegalArgumentException("Initial capacity cannot be less than 1");
        else this.arr = new Object[initialCapacity];
    }

    /**
     *
     * @return количество элементов в массиве.
     */
    @Override
    public int size() {
        return this.arrSize;
    }

    /**
     *
     * @param element Добавляет элемент в конец массива.
     */
    @Override
    public void add(E element) {
        if (this.arrSize == this.arr.length) increaseCapacity();
        this.arr[this.arrSize] = element;
        this.arrSize++;
        this.isSorted = false;
    }

    /**
     * Добавляет элемент в указанное место в массиве.
     * В массиве не происходит никаких сдвигов влево после добавления элемента.
     * @throws IllegalArgumentException - если индекс меньше нуля или больше вместимости массива.
     */
    @Override
    public void add(int index, E element) {
        if (this.arrSize == this.arr.length) increaseCapacity();
        checkBounds(index);
        for (int i = this.size(); i > index; i--) {
            this.arr[i] = this.arr[i - 1];
        }
        this.arr[index] = element;
        this.arrSize++;
        this.isSorted = false;
    }

    /**
     *
     * @return значение по индексу
     * @throws IllegalArgumentException - индекс меньше нуля или больше вместимости.
     */
    @Override
    public E get(int index) {
        checkBounds(index);
        return (E) this.arr[index];
    }

    /**
     * Вставляет по указанному индексу элемент перезаписывая
     * ячейку и возвращает замененный элемент.
     * @return элемент, который был заменен
     * @throws IllegalArgumentException - индекс меньше нуля или больше вместимости.
     */
    @Override
    public E set(int index, E element) {
        checkBounds(index);
        E oldValue = get(index);
        this.arr[index] = element;
        this.isSorted = false;
        return oldValue;
    }

    /**
     *
     * @return удаляет элемент по указанному индексу и возвращает его.
     * После удаления все элементы массива, стоящие справа, сдвигаются влево на 1.
     */
    @Override
    public E remove(int index) {
        checkBounds(index);

        if (index == size() - 1) {
            E oldValue = get(index);
            this.arr[index] = null;
            this.arrSize--;
            return oldValue;
        }

        E oldValue = get(index);

        for (int i = index; i < size() - 1; i++) {
            this.arr[i] = this.arr[i+1];
        }
        this.arr[size() - 1] = null;
        this.arrSize--;

        return oldValue;
    }

    /**
     * Удаляет все элементы массива.
     * Затем приводит вместительность массива к значению по умолчанию.
     */
    @Override
    public void clear() {
        for (int i = 0; i < size(); i++) this.arr[i] = null;
        this.arrSize = 0;
        this.arr = new Object[DEFAULT_CAPACITY];
    }

    /**
     * Сортирует элементы массива с помощью алгоритма быстрой сортировки.
     * @param comparator - инструмент для сравнения двух объектов.
     */
    @Override
    public void quickSort(Comparator<E> comparator) {
        quickSortImpl(0, this.arrSize-1, comparator);
        this.isSorted = true;
    }

    /**
     *
     * @return состояние отсоритированности массива.
     */
    @Override
    public boolean isSorted() {
        return isSorted;
    }

    /**
     * Обрезает массив до указанного размера.
     * Вместимость также сокращается до указанного размера.
     */
    @Override
    public void split(int size) {
        if (size < 1) {
            throw new IllegalArgumentException("Illegal size: " + size);
        }

        if (size < size()) {
            Object[] newArr = new Object[size];
            this.arrSize = 0;
            for (int i = 0; i < size; i++) {
                newArr[i] = this.arr[i];
                this.arrSize++;
            }
            this.arr = newArr;
        }
    }

    private void checkBounds(int index) {
        if (index >= this.arr.length || index < 0) throw new IllegalArgumentException("Incorrect index value");
    }

    private void quickSortImpl(int begin, int end, Comparator<E> comparator) {
        if (size() == 0) return;
        if (begin >= end) return;

        int middle = begin + (begin - end) / 2;
        E midElement = get(middle);

        int i = begin;
        int j = end;

        while (i <= j) {
            while (comparator.compare(get(i), midElement) < 0) i++;
            while (comparator.compare(get(j), midElement) > 0) j--;

            if (i <= j) {
                E tmp = get(i);
                this.arr[i] = get(j);
                this.arr[j] = tmp;
                i++;
                j--;
            }
        }

        if (begin < j) quickSortImpl(begin, j, comparator);
        if (end > i) quickSortImpl(i, end, comparator);
    }

    private void increaseCapacity() {
        Object[] newArray = new Object[this.arr.length + (this.arr.length / 2)];
        copyOf(newArray);
    }

    private void copyOf(Object[] newArray) {
        for (int i = 0; i < this.arrSize; i++) {
            newArray[i] = this.arr[i];
        }
        this.arr = newArray;
    }
}
