package arraylist.impl;

import arraylist.IntensiveList;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Класс ArrayList (списка), реализующий интерфейс {@link IntensiveList}
 * @param <E> тип элементов, используемых в списке
 */
public class ArrayList_SlavaSles<E> implements IntensiveList<E> {
    private static final int DEFAULT_ARRAY_SIZE = 10;
    private static final float GROW_FACTOR = 1.5F;
    private int size;
    private E[] array;
    private boolean isSorted;

    /**
     * Конструктор списка нулевой длины
     */
    public ArrayList_SlavaSles() {
        this(0);
    }

    /**
     * Конструктор списка с базовым массивом определенной длины
     * @param initialSize количество элементов в базовом массиве
     */
    @SuppressWarnings("unchecked")
    public ArrayList_SlavaSles(int initialSize) {
        if (initialSize > 0) {
            array = (E[]) new Object[initialSize];
        } else if (initialSize == 0) {
            array = getEmptyList();
        } else {
            throw new IllegalArgumentException("Illegal initial size!");
        }
        size = 0;
        isSorted = false;
    }

    @SuppressWarnings("unchecked")
    private E[] getEmptyList() {
        return  (E[]) new Object[0];
    }

    /**
     * Функция, возвращающая размер списка
     * @return возвращает размер списка
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Метод, добавляющий новый элемент в конец списка
     * @param element элемент, добавляемый в список
     */
    @Override
    public void add(E element) {
        add(size, element);
    }

    /**
     * Метод, добавляющий новый элемент в произвольное место в списке. При этом все значения, расположенные правее
     * нового элемента в списке, сдвигаются вправо на одну позицию
     * @param index номер элемента в списке, начиная с нуля
     * @param element элемент, добавляемый в список
     * @throws IndexOutOfBoundsException Исключение выбрасывается при превышении index размера списка
     */
    @Override
    @SuppressWarnings("unchecked")
    public void add(int index, E element) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        if (size == 0) {
            array = (E[]) new Object[DEFAULT_ARRAY_SIZE];
        }
        checkSizeAndGrowArray();
        if (index < size) {
            for (int i = size; i >= index; i--) {
                array[i + 1] = array[i];
            }
        }
        array[index] = element;
        size++;
        isSorted = false;
    }

    private void checkSizeAndGrowArray() {
        if (size == array.length) {
            array = Arrays.copyOf(array, (int) (array.length * GROW_FACTOR));
        }
    }

    /**
     * Функция, возвращающая элемент списка с указанным индексом
     * @param index номер элемента в списке, начиная с нуля
     * @return возвращает элемент списка с указанным индексом
     * @throws IndexOutOfBoundsException Исключение выбрасывается, если index больше либо равен размеру списка
     */
    @Override
    public E get(int index) {
        checkIndex(index);
        return array[index];
    }

    /**
     * Функция, изменяющая значение элемента с указанным индексом
     * @param index номер элемента в списке, начиная с нуля
     * @param element элемент, заменяющий старое значение в списке
     * @return возвращает предыдущее (старое) значение элемента
     */
    @Override
    public E set(int index, E element) {
        checkIndex(index);
        E previousValue = array[index];
        array[index] = element;
        return previousValue;
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds for length " + size);
        }
    }

    /**
     * Функция, удаляющая элемент с указанным индексом. При этом все значения, расположенные правее этого элемента,
     * сдвигаются влево на одну позицию
     * @param index номер элемента в списке, начиная с нуля
     * @return возвращает значение удаленного элемента
     * @throws IndexOutOfBoundsException Исключение выбрасывается, если index больше либо равен размеру списка
     */
    @Override
    public E remove(int index) {
        checkIndex(index);
        E removedValue = array[index];
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        size--;
        array[size] = null;
        return removedValue;
    }

    /**
     * Метод, очищающий (создающий пустой) список
     */
    @Override
    public void clear() {
        array = getEmptyList();
        size = 0;
    }

    /**
     * Метод, выполняющий быструю сортировку списка
     * @param comparator функция для сравнения элементов списка
     */
    @Override
    public void quickSort(Comparator<E> comparator) {
//        split(size);
//        Arrays.sort(array, comparator);
        if (size >= 1) {
            quickSortAlgorithm(array, 0, size - 1, comparator);
        }
        isSorted = true;
    }

    private void quickSortAlgorithm(E[] array, int from, int to, Comparator<E> comparator) {
        if (from >= to) return;
        int i = from;
        int j = to;
        E pivot = array[(i + j) / 2];
        do {
            while (comparator.compare(array[i], pivot) < 0) {
                i++;
            }
            while (comparator.compare(array[j], pivot) > 0) {
                j--;
            }
            if (i <= j) {
                if (i < j) {
                    E tmp = array[i];
                    array[i] = array[j];
                    array[j] = tmp;
                }
                i++;
                j--;
            }
        } while (i <= j);
        if (i < to) {
            quickSortAlgorithm(array, i, to, comparator);
        }
        if (from < j) {
            quickSortAlgorithm(array, from, j, comparator);
        }
    }


    /**
     * Функция, указывающая отсортирован ли список
     * @return возвращает состояние списка: true - отсортирован, false - в произвольном порядке
     */
    @Override
    public boolean isSorted() {
        return isSorted;
    }

    /**
     * Метод, сокращающий список до заданной длины. Элементы, находящиеся за пределами указанной длины удаляются
     * @param size новое значение длины списка
     * @throws IndexOutOfBoundsException Исключение выбрасывается, если size больше размера списка
     */
    @Override
    public void split(int size) {
        if (size > this.size || size < 0) {
            throw new IndexOutOfBoundsException("Size " + size + " out of bounds for length " + this.size);
        }
        array = Arrays.copyOf(array, size);
        this.size = size;
    }
}
