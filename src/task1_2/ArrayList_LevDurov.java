package task1_2;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

/**
 * Реализация собственного динамического списка (аналог ArrayList, не потокобезопасный).
 *
 * @param <E> Тип элементов списка.
 * @author Лев Дуров
 * @see IntensiveList
 */
public class ArrayList_LevDurov<E> implements IntensiveList<E> {

    /**
     * Емкость списка по умолчанию
     */
    final static private int DEFAULT_CAPACITY = 10;
    /**
     * Массив, в котором хранятся элементы списка.
     */
    private Object[] array;
    /**
     * Количество элементов в списке.
     */
    private int size;

    /**
     * Создает пустой список с емкостью по умолчанию.
     */
    public ArrayList_LevDurov() {
        size = 0;
        this.array = new Object[DEFAULT_CAPACITY];
    }

    /**
     * Создает пустой список с указанной емкостью.
     *
     * @param capacity Емкость списка.
     */
    public ArrayList_LevDurov(int capacity) {
        size = 0;
        this.array = new Object[capacity];
    }

    /**
     * @return Количество элементов в списке.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Добавляет элемент в конец списка.
     *
     * @param element Элемент для добавления в список.
     */
    @Override
    public void add(E element) {
        if (size == array.length)
            grow();

        array[size] = element;
        size++;
    }

    /**
     * Увеличивает емкость списка.
     */
    private void grow() {
        int newCapacity = array.length * 2;
        Object[] newArray = new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newArray[i] = array[i];
        }
        array = newArray;
    }

    /**
     * Добавляет элемент в список на определенную позицию.
     *
     * @param index   Индекс для вставки элемента.
     * @param element Элемент для добавления в список.
     */
    @Override
    public void add(int index, E element) {
        if (size == array.length)
            grow();
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = element;
        size++;
    }

    /**
     * Возвращает элемент списка с заданным индексом.
     *
     * @param index Индекс элемента.
     * @return Элемент списка.
     */
    @Override
    public E get(int index) {
        return (E) array[index];
    }

    /**
     * Устанавливает значение элементу списка с заданным индексом.
     *
     * @param index   Индекс списка.
     * @param element Новый элемент для вставки.
     * @return Возвращает старое значение ячейки.
     */
    @Override
    public E set(int index, E element) {
        E oldElem = (E) array[index];
        array[index] = element;
        return oldElem;
    }

    /**
     * Удаляет элемент с заданным индексом из списка.
     *
     * @param index Индекс удаляемого элемента.
     * @return Возвращает удаленный элемент.
     */
    @Override
    public E remove(int index) {
        E remElem = (E) array[index];
        size--;
        System.arraycopy(array, index + 1, array, index, size - index);
        array[size] = null;

        return remElem;
    }

    /**
     * Удаляет все элементы из списка.
     */
    @Override
    public void clear() {
        array = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    /**
     * Сортирует список по возрастанию элементов, используя алгоритм быстрой сортировки.
     *
     * @param comparator Содержит функцию сравнения элементов коллекции.
     */
    @Override
    public void quickSort(Comparator<E> comparator) {
        quickSortRecurs(comparator, 0, size - 1);
    }

    private void quickSortRecurs(Comparator<E> comparator, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(comparator, begin, end);

            quickSortRecurs(comparator, begin, partitionIndex-1);
            quickSortRecurs(comparator, partitionIndex+1, end);
        }
    }

    private int partition(Comparator<E> comparator, int begin, int end) {
        Object pivot = array[end];
        int i = (begin-1);

        for (int j = begin; j < end; j++) {
            if (comparator.compare((E) pivot, (E) array[j]) >= 0) {
                i++;

                Object swapTemp = array[i];
                array[i] = array[j];
                array[j] = swapTemp;
            }
        }

        Object swapTemp = array[i+1];
        array[i+1] = array[end];
        array[end] = swapTemp;

        return i+1;
    }


    /**
     * Проверяет, отсортирован ли список.
     *
     * @return Если список отсортирован по возрастанию возвращает true, иначе - false.
     */
    @Override
    public boolean isSorted(Comparator<E> comparator) {
        if (size < 2)
            return true;

        for (int i = 0; i < size - 1; i++) {
            if (comparator.compare((E) array[i], (E) array[i + 1]) > 0)
                return false;
        }

        return true;
    }

    /**
     * Обрезает список до заданного размера, если текуцщий размер списка больше заданного.
     *
     * @param size Максимальный размер списка после обрезания.
     */
    @Override
    public void split(int size) {
        if (array.length > size) {
            Object[] newArray = new Object[size];
            for (int i = 0; i < size; i++) {
                newArray[i] = array[i];
            }
            array = newArray;
        }
    }

    @Override
    public String toString() {
        Object[] newArray = new Object[size];
        System.arraycopy(array, 0, newArray, 0, size);
        return "array=" + Arrays.toString(newArray) +
                ", size=" + size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArrayList_LevDurov<?> that = (ArrayList_LevDurov<?>) o;
        return Arrays.equals(array, that.array);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size);
        result = 31 * result + Arrays.hashCode(array);
        return result;
    }
}
