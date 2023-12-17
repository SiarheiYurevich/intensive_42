package MyArrList;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Реализация списка ArrayList_AlexandrTelezhkin, который реализует IntensiveList и Comparator.
 *
 * @param <E> Тип элементов списка, ограниченный интерфейсом Comparable
 */
public class ArrayList_AlexandrTelezhkin<E extends Comparable<E>>
        implements IntensiveList<E>, Comparator<E> {
    private Object[] elements;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;
    private static final double ADDITIONAL_LOAD_FACTOR = 1.5;

    /**
     * Конструктор по умолчанию. Инициализирует массив с заданной начальной ёмкостью.
     */
    public ArrayList_AlexandrTelezhkin() {
        this.elements = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    /**
     * Возвращает текущий размер списка.
     *
     * @return Размер списка
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Добавляет элемент в конец списка.
     *
     * @param element Элемент для добавления
     */
    @Override
    public void add(E element) {
        capacityNeedsToBeIncreased();
        elements[size] = element;
        size++;
    }

    /**
     * Добавляет элемент по указанному индексу, сдвигая остальные элементы.
     *
     * @param index   Индекс для добавления элемента
     * @param element Элемент для добавления
     */
    @Override
    public void add(int index, E element) {
        checkIndex(index);
        capacityNeedsToBeIncreased();
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;
        size++;
    }

    /**
     * Проверяет, нужно ли увеличивать ёмкость списка, и увеличивает её при необходимости.
     */
    private void capacityNeedsToBeIncreased() {
        if (size == elements.length) {
            int newCapacity = (int) (elements.length * ADDITIONAL_LOAD_FACTOR);
            elements = Arrays.copyOf(elements, newCapacity);
        }
    }

    /**
     * Проверяет корректность индекса.
     *
     * @param index Проверяемый индекс
     * @throws IndexOutOfBoundsException Если индекс выходит за границы размера списка
     */
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Индекс: " + index + ", за пределами границ размера: " + size);
        }
    }

    /**
     * Возвращает элемент по указанному индексу.
     *
     * @param index Индекс элемента
     * @return Элемент по указанному индексу
     */
    @Override
    public E get(int index) {
        checkIndex(index);
        return (E) elements[index];
    }


    @Override
    public E set(int index, E element) {
        checkIndex(index);
        return (E) (elements[index] = element);
    }

    @Override
    public E remove(int index) {
        checkIndex(index);

        E removedElement = (E) elements[index];
        int startingPosition = (1 + index);
        int copyTheRequiredNumberOfElements = (size - index - 1);

        System.arraycopy(elements, startingPosition, elements, index, copyTheRequiredNumberOfElements);

        elements[--size] = null;
        size--;

        return removedElement;
    }

    @Override
    public void clear() {
        this.elements = new Object[size];
    }

    /**
     * Быстрая сортировка списка с использованием предоставленного компаратора.
     *
     * @param comparator Компаратор для сравнения элементов списка
     */
    @Override
    public void quickSort(Comparator<E> comparator) {
        if (isSorted() || size > 1) {
            quickSortHelper(comparator, 0, size);
        }
    }

    private void quickSortHelper(Comparator<E> comparator, int low, int high) {
        if (low < high) {
            int partitionIndex = partition(comparator, low, high);
            quickSortHelper(comparator, low, partitionIndex - 1);
            quickSortHelper(comparator, partitionIndex + 1, high);
        }
    }

    private int partition(Comparator<E> comparator, int low, int high) {
        E pivot = (E) elements[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (comparator.compare((E) elements[j], pivot) <= 0) {
                i++;
                swap(i, j);
            }
        }

        swap(i + 1, high);
        return i + 1;
    }

    private void swap(int i, int j) {
        E temp = (E) elements[i];
        elements[i] = elements[j];
        elements[j] = temp;
    }

    /**
     * Проверяет, отсортирован ли список в порядке возрастания.
     *
     * @return true, если список отсортирован, иначе false
     */
    @Override
    public boolean isSorted() {
        for (int i = 1; i < size; i++) {
            E firstElement = (E) elements[i - 1];
            E secondElement = (E) elements[i];
                if (firstElement.compareTo(secondElement) > 0) {
                    return false;
                }
        }
        return true;
    }

    /**
     * Обрезает список до указанного размера, отбрасывая лишние элементы.
     *
     * @param size Новый размер списка после обрезки
     * @throws IndexOutOfBoundsException Если новый размер выходит за границы текущего размера списка
     */
    @Override
    public void split(int size) {
        checkIndex(size);
        this.size = size;
        elements = Arrays.copyOf(elements, size);
    }

    /**
     * Сравнивает два элемента используя компаратор.
     *
     * @param element1 Первый элемент для сравнения
     * @param element2 Второй элемент для сравнения
     * @return Результат сравнения элементов
     */
    @Override
    public int compare(E element1, E element2) {
        return element1.compareTo(element2);
    }

    /**
     * Возвращает строковое представление списка.
     *
     * @return Строковое представление списка
     */
    @Override
    public String toString() {
        return "ArrayList_AlexandrTelezhkin{" +
                "elements=" + Arrays.toString(elements) +
                ", size=" + size +
                '}';
    }
}