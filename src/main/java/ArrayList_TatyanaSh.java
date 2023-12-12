package src.java;


import java.util.Comparator;


/**
 * Реализация интерфейса IntensiveList.
 *
 * @param <E>
 * @Author Tatyana Sharova
 */

public class ArrayList_TatyanaSh<E> implements IntensiveList<E> {
    /**
     * Начальная емкость по умолчанию.
     */
    private static final int DEFAULT_CAPACITY = 10;
    private E[] array;
    /**
     * Количество элементов, которые содержит ArrayList_TatyanaSh.
     */
    private int size;
    private boolean isSorted = false;

    /**
     * Создает пустой список с начальной емкостью десять.
     */
    @SuppressWarnings("unchecked")
    public ArrayList_TatyanaSh() {
        this.array = (E[]) new Object[DEFAULT_CAPACITY];
    }

    /**
     * @return возвращает количество элементов в списке.
     */
    @Override
    public int size() {
        return size;
    }

    public int length() {
        return array.length;
    }

    /**
     * Добавляет элемент в список, при необходимости увеличивает емкость.
     *
     * @param element – элемент, который должен быть вставлен
     */
    @Override
    public void add(E element) {

        if (size == array.length) {
          array =  grow();
        }
        array[size] = element;
        size++;
    }

    private E[] grow() {
        int oldCapacity = array.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        return copyOf(array, newCapacity);
    }

    /**
     * Вставляет указанный элемент в указанную позицию в этом списке.
     * Сдвигает элемент, находящийся в данный момент в этой позиции (если таковой имеется),
     * и все последующие элементы вправо (добавляет единицу к их индексам). При необходимости увеличивает емкость.
     * Бросает:
     * IndexOutOfBoundsException – если индекс находится вне диапазона (index < 0 || index > size())
     *
     * @param index   - индекс, по которому должен быть вставлен указанный элемент
     * @param element – элемент, который должен быть вставлен
     */

    @Override
    public void add(int index, E element) {
        if (size == array.length) {
           array =  grow();
        }
        checkIndex(size, index);
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = element;
        size++;
    }

    private E[] copyOf(E[] arr, int capacity) {
        E[] newArray = (E[]) new Object[capacity];
        if (size < capacity) {
            System.arraycopy(arr, 0, newArray, 0, size);
        }
        return newArray;
    }

    /**
     * Возвращает элемент в указанной позиции в этом списке.
     *
     * @param index – индекс возвращаемого элемента
     * @return возвращает элемент, находящийся в указанной позиции в этом списке
     */

    @Override
    public E get(int index) {
        checkIndex(size, index);
        return array[index];
    }

    /**
     * Заменяет элемент в указанной позиции в этом списке указанным элементом.
     * Бросает IndexOutOfBoundsException – если индекс находится вне диапазона (index < 0 || index >= size())
     *
     * @param index   – индекс элемента для замены
     * @param element – элемент, который будет сохранен в указанной позиции
     * @return возвращает элемент, ранее находившийся в указанном положении
     */
    @Override
    public E set(int index, E element) {
        checkIndex(size, index);
        E oldElement = array[index];
        array[index] = element;
        return oldElement;
    }

    private void checkIndex(int size, int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Удаляет элемент в указанной позиции в этом списке. Сдвигает все последующие элементы влево (вычитает единицу из их индексов).
     * IndexOutOfBoundsException – если индекс находится вне диапазона (index < 0 || index >= size())
     *
     * @param index– индекс удаляемого элемента
     * @return возвращается элемент, который был удален из списка
     */
    @Override
    public E remove(int index) {
        checkIndex(size, index);
        E removedElement = array[index];
        System.arraycopy(array, index + 1, array, index, size - 1 - index);
        array[size - 1] = null;
        size--;
        return removedElement;
    }

    /**
     * Удаляет все элементы из этого списка. Сapacity приводится к дефолтному значению.
     */
    @Override
    public void clear() {

        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        array = (E[])new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    /**
     * Реализует быструю сортировку списка, дефолт по возрастанию.
     * @param comparator - компоратор, на основе которого осуществляется сортировка
     */
    @Override
    public void quickSort(Comparator<E> comparator) {
        sort(array, 0, size - 1, comparator);
        isSorted = true;
    }

    private void sort(E[] arr, int left, int right, Comparator<E> c) {

        if (left < right) {
            int[] m = partition(arr, left, right, c);
            sort(arr, left, m[0], c);
            sort(arr, m[1] + 1, right, c);
        }
    }

    private int[] partition(E[] arr, int l, int r, Comparator<E> c) {
        E pivot = arr[l];
        int j = l;
        for (int i = l + 1; i <= r; i++) {
            if (c.compare(arr[i], pivot) < 0) {
                E tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
                j++;
            } else if (c.compare(arr[i], pivot) > 0) {
                E tmp = arr[i];
                arr[i] = arr[r];
                arr[r] = tmp;
                r--;
                i--;
            }
        }
        int[] index = {j, r};
        return index;
    }


    @Override
    public boolean isSorted() {
        return isSorted;
    }

    /**
     * Обрезает список до указанного размера
     * @param size - требуемый размер списка
     */
    @Override
    public void split(int size) {
        checkIndex(this.size, size);
        int newCapacity = size + (size >> 1);
        E[] splitedArray = copyOf(array, newCapacity);
        System.arraycopy(array, 0, splitedArray, 0, size);
        array = splitedArray;
        this.size = size;
    }

    public void print() {
        if (size == 0) {
            System.out.println("[]");
        } else {
            System.out.println(this);
        }
    }

    public String toString() {
        StringBuilder builder = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            builder.append(array[i]);
            if (i < (size - 1)) {
                builder.append(", ");
            } else {
                builder.append("]");
            }

        }
        return builder.toString();
    }
}
