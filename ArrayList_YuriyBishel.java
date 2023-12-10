package com.yuriybishel.list;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Реализация интерфейса IntensiveList с использованием массива.
 * @param <E> тип элементов, хранящихся в списке
 */
public class ArrayList_YuriyBishel<E> implements IntensiveList<E> {


    E[] array;
    int size;
    boolean isSorted = false;

    public ArrayList_YuriyBishel(E[] array) {
        this.array = array;
        this.size = array.length;
    }

    /**
     * Возвращает текущий размер списка
     *
     * @return размер списка, количество элементов в нем
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Добавляет элемент в конец списка
     *
     * @param element добавляемый элемент
     */
    @Override
    public void add(E element) {

        E[] res = (E[]) Array.newInstance(array.getClass().getComponentType(), size + 1);
        System.arraycopy(this.array, 0, res, 0, size);

        res[size] = element;
        this.size++;
        this.array = res;
    }

    /**
     * Вставляет элемент по указанному индесу,
     * сдвигая остальные элементы вправо.
     *
     * @param index индекс для вставки элемента
     * @param element вставляемый элемент
     */
    @Override
    public void add(int index, E element) {

        E[] res = (E[]) Array.newInstance(array.getClass().getComponentType(), size + 1);
        System.arraycopy(this.array, 0, res, 0, index);
        System.arraycopy(this.array, index, res, index + 1, size - index);

        res[index] = element;
        this.array = res;
        this.size++;
    }

    /**
     * Получение объекта из списка по индексу
     * @param index индекс искомого объекта
     *
     * @return искомый объект
     */
    @Override
    public E get(int index) {
        return array[index];
    }

    /**
     * Заменяет элемент в списке по указанному индексу
     *
     * @param index индекс, по которому следует произвести замену элемента
     * @param element новый элемент, который будет помещен в список
     *
     * @return предыдущее значение элемента на указанной позиции
     */
    @Override
    public E set(int index, E element) {
        E oldValue = array[index];
        array[index] = element;
        return oldValue;
    }

    /**
     * Удаляет элемент из списка по указанному индексу
     * @param index индекс элоемента, который следует удалить
     *
     * @return удаленное значение элемента
     */
    @Override
    public E remove(int index) {
        E oldValue = array[index];
        E[] res = (E[]) Array.newInstance(array.getClass().getComponentType(), size - 1);
        System.arraycopy(array, 0, res, 0, index);
        System.arraycopy(array, index + 1, res, index, size - index - 1);

        this.array = res;
        this.size--;
        return oldValue;
    }

    /**
     * Удаляет все элементы из списка, устанавливая его в пустое состояние.
     */
    @Override
    public void clear() {
        this.array = (E[]) Array.newInstance(array.getClass().getComponentType(), 0);
        this.size = 0;
    }

    /**
     * Выполняет сортировку списка с использованием алгоритма быстрой сортировки.
     * @param comparator компаратор для сравнения элементов при сортировке
     */
    @Override
    public void quickSort(Comparator<E> comparator) {

        if (size < 2) {
            return;
        }
        quickSort(0, size - 1, comparator);
        isSorted = true;
    }

    /**
     * Рекурсивный метод для сортировки части списка методом быстрой сортировки.
     * @param low индекс начала сортируемой части
     * @param high индекс конца сортируемой части
     * @param comparator компаратор для расвнения элементов при сортировке
     */
    private void quickSort(int low, int high, Comparator<E> comparator) {
        if (low >= high) {
            return;
        }
        int pivotIndex = partition(low, high, comparator);
        quickSort(low, pivotIndex, comparator);
        quickSort(pivotIndex + 1, high, comparator);
    }

    /**
     * Выполняет разделение части списка для быстрой сортировки.
     * @param low индекс начала разделения
     * @param high индекс конца разделения
     * @param comparator компаратор для сравнения элементов при сортировке
     *
     * @return индекс опороного элемента после разделения
     */
    private int partition(int low, int high, Comparator<E> comparator) {
        E pivot = array[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (comparator.compare(array[j], pivot) <= 0) {
                i++;
                swap(i, j);
            }
        }
        swap(i + 1, high);
        return i + 1;
    }

    /**
     * Обменивает значения двух элементов в списке по их индексам.
     * @param i индекс первого элемента
     * @param j индекс второго элемента
     */
    private void swap(int i, int j) {
        E temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /**
     * Проверяет, отсортирован ли текущий список
     *
     * @return {@code true}, если список отсортирован, иначе {@code false}
     */
    @Override
    public boolean isSorted() {
        return isSorted;
    }

    /**
     * Разделяет текущий список на новый список указанного размера.
     * @param size размер нового списка после разделения
     */
    @Override
    public void split(int size) {

        if (size < 0 || size > this.size) {
            throw new IllegalArgumentException("Invalid size");
        }

        E[] newArray = (E[]) Array.newInstance(array.getClass().getComponentType(), size);

        System.arraycopy(this.array, 0, newArray, 0, size);

        this.array = newArray;
        this.size = size;
    }

    @Override
    public String toString() {
        return "ArrayList_YuriyBishel{" +
                "array=" + Arrays.toString(array) +
                ", size=" + size +
                '}';
    }
}
