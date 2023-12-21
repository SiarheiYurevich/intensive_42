package task_1;


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
            array = grow();
        }
        array[size] = element;
        size++;
    }

    private E[] grow() {
        int oldCapacity = array.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1) + 1;
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
            array = grow();
        }
        checkIndex(size, index);
        int destinationStartIndex = index + 1;
        int sizeForCopy = size - index;
        System.arraycopy(array, index, array, destinationStartIndex, sizeForCopy);
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
            throw new IndexOutOfBoundsException("invalid index " + index);
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
        int sourceStartIndex = index + 1;
        int sizeForCopy = size - 1 - index;
        System.arraycopy(array, sourceStartIndex, array, index, sizeForCopy);
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
        array = (E[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    /**
     * Реализует быструю сортировку списка, дефолт по возрастанию.
     *
     * @param comparator - компоратор, на основе которого осуществляется сортировка
     */
    @Override
    public void quickSort(Comparator<E> comparator) {
        sort(array, 0, size - 1, comparator);

    }

    private void sort(E[] array, int leftIndex, int rightIndex, Comparator<E> comparator) {

        if (leftIndex < rightIndex) {
            int[] middleIndex = partition(array, leftIndex, rightIndex, comparator);
            sort(array, leftIndex, middleIndex[0], comparator);
            sort(array, middleIndex[1] + 1, rightIndex, comparator);
        }
    }

    private int[] partition(E[] array, int leftIndex, int rightIndex, Comparator<E> comparator) {
        E pivot = array[leftIndex];
        int j = leftIndex;
        for (int i = leftIndex + 1; i <= rightIndex; i++) {
            if (comparator.compare(array[i], pivot) < 0) {
                E tmp = array[i];
                array[i] = array[j];
                array[j] = tmp;
                j++;
            } else if (comparator.compare(array[i], pivot) > 0) {
                E tmp = array[i];
                array[i] = array[rightIndex];
                array[rightIndex] = tmp;
                rightIndex--;
                i--;
            }
        }
        int[] index = {j, rightIndex};
        return index;
    }


    @Override
    public boolean isSorted(Comparator<E> comparator) {
        if (size < 2) {
            return true;
        }
        for (int i = 1; i < size; i++) {
            if (comparator.compare(array[i - 1], array[i]) > 0) {
                return false;
            }
        }
        return true;
    }


    /**
     * Обрезает список до указанного размера
     *
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
