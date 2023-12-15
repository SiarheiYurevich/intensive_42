package util.CustomCollection;

import java.util.Arrays;
import java.util.Comparator;

public class CustomArrayList<E> implements IntensiveList<E> {
    private Object[] elementData;
    private static final int DEFAULT_CAPACITY = 10;
    private int size = 0;
    private boolean isSorted;

    /**
     * Конструктор для создания списка с начальной емкостью по умолчанию.
     */
    public CustomArrayList() {
        this.elementData = new Object[DEFAULT_CAPACITY];
    }

    /**
     * Конструктор для создания списка с заданной начальной емкостью.
     *
     * @param capacity начальный размер внутреннего массива
     */

    public CustomArrayList(int capacity) {
        this.elementData = capacity > 0 ? new Object[capacity] : new Object[DEFAULT_CAPACITY];
    }

    /**
     * Возвращает количество элементов в списке.
     *
     * @return текущий размер списка
     */

    @Override
    public int size() {
        return size;
    }

    /**
     * Добавляет элемент в конец списка.
     * Если внутренний массив достиг максимального размера, то его емкость увеличивается.
     *
     * @param element элемент, который нужно добавить в список
     */

    @Override
    public void add(E element) {
        if (size == elementData.length) {
            ensureCapacity();
        }
        elementData[size] = element;
        size++;
    }

    /**
     * Вставляет элемент на указанную позицию в списке.
     * Элементы справа от указанной позиции сдвигаются на одну позицию вправо.
     * Если внутренний массив достиг максимального размера, его емкость увеличивается.
     *
     * @param index индекс, на который нужно добавить элемент.
     * @param element элемент для вставки в список.
     * @throws IndexOutOfBoundsException если указанный индекс вне диапазона [0, size]
     */
    @Override
    public void add(int index, E element) {
        checkIndexForAdd(index);
        if (size == elementData.length) {
            ensureCapacity();
        }

        for (int i = size; i > index; i--) {
            elementData[i] = elementData[i - 1];
        }
        elementData[index] = element;
        size++;
    }
    /**
     * Метод возвращает элемент списка по указанному индексу.
     *
     * @param index индекс элемента, который нужно вернуть
     * @return элемент списка по указанному индексу
     * @throws IndexOutOfBoundsException если индекс выходит за пределы списка
     */

    @Override
    public E get(int index) {
        checkIndex(index);
        return (E) elementData[index];
    }
    /**
     * Метод заменяет элемент списка по указанному индексу на новый элемент.
     *
     * @param index   индекс элемента, который нужно заменить
     * @param element новый элемент, который нужно установить на указанном индексе
     * @return старый элемент списка по указанному индексу
     * @throws IndexOutOfBoundsException если индекс выходит за пределы списка
     */

    @Override
    public E set(int index, E element) {
        checkIndex(index);
        E oldValue = (E) elementData[index];
        elementData[index] = element;
        return oldValue;
    }
    /**
     * Метод удаляет элемент списка по указанному индексу.
     *
     * @param index индекс элемента, который нужно удалить
     * @return удаленный элемент списка по указанному индексу
     * @throws IndexOutOfBoundsException если индекс выходит за пределы списка
     */
    @Override
    public E remove(int index) {
        checkIndex(index);
        E oldValue = (E) elementData[index];

        for (int i = index; i < size - 1; i++) {
            elementData[i] = elementData[i + 1];
        }
        elementData[--size] = null;
        return oldValue;
    }
    /**
     * Метод очищает список, устанавливая все элементы в null и сбрасывая размер и флаг сортировки.
     */
    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elementData[i] = null;
        }
        size = 0;
        isSorted = false;
    }
    /**
     * Метод выполняет быструю сортировку списка с помощью заданного компаратора.
     *
     * @param comparator компаратор, используемый для сравнения элементов списка
     */
    @Override
    public void quickSort(Comparator<E> comparator) {
        quickSort(0, size - 1, comparator);
    }

    /**
     * Метод проверяет, отсортирован ли список.
     *
     * @return true, если список отсортирован, в противном случае - false
     */

    @Override
    public boolean isSorted() {
        return isSorted;
    }
    /**
     * Обрезвет  список до указаного размера.
     *
     * @param size размер нового массива
     */
    @Override
    public void split(int size) {
        Object[] arr = new Object[size];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = elementData[i];
        }
        elementData = arr;

    }

    /**
     * Производит проверку индекса.
     *
     * @param index индекс для проверки
     * @throws IndexOutOfBoundsException если индекс выходит за пределы списка
     */


    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }
    /**
     * Производит проверку индекса для добавления элемента.
     *
     * @param index индекс для проверки
     * @throws IndexOutOfBoundsException если индекс выходит за пределы списка
     */

    private void checkIndexForAdd(int index) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }
    /**
     * Обеспечивает достаточную емкость списка.
     */
    private void ensureCapacity() {

        int newCapacity = elementData.length + (elementData.length / 2);
        Object[] newData = new Object[newCapacity];

        for (int i = 0; i < elementData.length; i++) {
            newData[i] = elementData[i];
        }
        elementData = newData;
    }
    /**
     * Быстрая сортировка списка.
     *
     * @param left       левая граница сортировки
     * @param right      правая граница сортировки
     * @param comparator компаратор для определения порядка элементов
     */
    private void quickSort(int left, int right, Comparator<E> comparator) {
        if (left < right) {
            int pivotIndex = partition(left, right, comparator);
            quickSort(left, pivotIndex - 1, comparator);
            quickSort(pivotIndex, right, comparator);
        }
        isSorted = true;
    }
    /**
     * Выполняет разделение списка на две части.
     *
     * @param left       левая граница разделения
     * @param right      правая граница разделения
     * @param comparator компаратор для определения порядка элементов
     * @return индекс опорного элемента
     */
    private int partition(int left, int right, Comparator<E> comparator) {
        E pivot = (E) elementData[left + (right - left) / 2];
        int i = left;
        int j = right;
        while (i <= j) {
            while (comparator.compare((E) elementData[i], pivot) < 0) {
                i++;
            }
            while (comparator.compare((E) elementData[j], pivot) > 0) {
                j--;
            }
            if (i <= j) {
                E temp = (E) elementData[i];
                elementData[i] = elementData[j];
                elementData[j] = temp;
                i++;
                j--;
            }
        }
        return i;
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(elementData, size));
    }
}