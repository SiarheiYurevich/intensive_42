package task_1.intensive_list_implementations;

import task_1.intensive_list_interface.IntensiveList;

import java.util.Comparator;

/**
 * Реализация {@code IntensiveList} на основе
 * динамически расширяющегося массива.
 *
 * @param <E> тип элементов, хранимых в {@code ArrayList_AleksandrZmeyev}
 * @author Александр Змеев
 */
public class ArrayList_AleksandrZmeyev<E> implements IntensiveList<E> {

    /**
     * Вместимость {@code data} по умолчанию
     */
    private static final int DEFAULT_CAPACITY = 10;
    /**
     * Множитель увеличения вместимости {@code data} при его переполнении
     */
    private static final int INCREASE_MULTIPLIER = 2;
    /**
     * Массив данных
     */
    private E[] data;
    /**
     * Размер {@code data}
     */
    private int size = 0;

    /**
     * Стандартный конструктор {@code ArrayList_AleksandrZmeyev}
     * с вместимостью {@code data} по умолчанию
     */
    @SuppressWarnings("unchecked")
    public ArrayList_AleksandrZmeyev() {
        data = (E[]) new Object[DEFAULT_CAPACITY];
    }

    /**
     * Конструктор {@code ArrayList_AleksandrZmeyev},
     * позволяющий задать начальную вместимость
     *
     * @param capacity вместимость {@code data}
     * @throws IllegalArgumentException вместимость задана некорректно
     */
    @SuppressWarnings("unchecked")
    public ArrayList_AleksandrZmeyev(int capacity) {
        if (capacity > 0) {
            data = (E[]) new Object[capacity];
        } else if (capacity == 0) {
            data = (E[]) new Object[DEFAULT_CAPACITY];
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + capacity);
        }
    }

    /**
     * Возвращает количество элементов в {@code data}
     *
     * @return количество элементов
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Производит вставку элемента в конец {@code data}.
     * При переполнении {@code data} его вместимость увеличивается.
     *
     * @param element элемент, который необходимо вставить
     */
    @Override
    public void add(E element) {
        checkCapacity();
        data[size] = element;
        size++;
    }

    /**
     * Производит вставку элемента по указанному индексу.
     * При переполнении {@code data} его вместимость увеличивается.
     *
     * @param index   позиция в массиве, в которую вставляется элемент
     * @param element элемент, который необходимо вставить
     * @throws IndexOutOfBoundsException некорректный индекс
     * ({@link ArrayList_AleksandrZmeyev#checkIndex(int)})
     */
    @Override
    public void add(int index, E element) {
        if (index != size) {
            checkIndex(index);
        }
        checkCapacity();

        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
        }
        data[index] = element;
        size++;
    }

    /**
     * Возвращает элемент {@code data} по указанному индексу.
     *
     * @param index позиция в массиве, элемент которой необходимо получить
     * @return ссылка элемента по указанному индексу
     * @throws IndexOutOfBoundsException некорректный индекс
     * ({@link ArrayList_AleksandrZmeyev#checkIndex(int)})
     */
    @Override
    public E get(int index) {
        checkIndex(index);
        return data[index];
    }

    /**
     * Заменяет элемент {@code data} по указанному индексу на элемент, переданный в аргументе.
     *
     * @param index   позиция в массиве, элемент которой необходимо заменить
     * @param element элемент для замены
     * @return ссылка на старый элемент по указанному индексу
     * @throws IndexOutOfBoundsException некорректный индекс
     * ({@link ArrayList_AleksandrZmeyev#checkIndex(int)})
     */
    @Override
    public E set(int index, E element) {
        checkIndex(index);
        E oldValue = data[index];
        data[index] = element;
        return oldValue;
    }

    /**
     * Удаляет элемент из {@code data} по указанному индексу.
     *
     * @param index позиция в массиве, элемент которой необходимо удалить
     * @return ссылка на удаленный элемент по указанному индексу
     * @throws IndexOutOfBoundsException некорректный индекс
     * ({@link ArrayList_AleksandrZmeyev#checkIndex(int)})
     */
    @Override
    public E remove(int index) {
        checkIndex(index);
        E oldValue = data[index];
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        data[size - 1] = null;
        size--;
        return oldValue;
    }

    /**
     * Заменяет значения всех элементов {@code data} на {@code null}.
     * Вместимость {@code data} остается прежней.
     */
    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            data[i] = null;
        }
        size = 0;
    }

    /**
     * Обрезает {@code data} до указанного размера {@code size}.
     * Вместимость {@code data} сокращается до указанного размера.
     *
     * @param size новый размер массива
     * @throws IllegalArgumentException некорректный размер массива
     */
    @Override
    public void split(int size) {
        if (size < 0 || size >= this.size) {
            throw new IllegalArgumentException("Invalid size: " + size);
        }
        data = copyOf(data, size, size);
    }

    /**
     * Производит быструю сортировку {@code data}.
     * Сравнение элементов производится по правилам, которые задаются
     * объектом класса {@code Comparator}.
     *
     * @param comparator компаратор, задающий правила сортировки
     */
    @Override
    public void quickSort(Comparator<E> comparator) {
        quickSort(data, 0, size - 1, comparator);
    }

    /**
     * Возвращает состояние упорядоченности элементов в {@code data},
     * сравнивая элементы в естественном порядке.
     *
     * @return true - список отсортирован, false - список не отсортирован
     */
    @Override
    @SuppressWarnings("unchecked")
    public boolean isSorted() {
        return isSorted((Comparator<E>) Comparator.naturalOrder());
    }

    /**
     * Возвращает состояние упорядоченности элементов в {@code data} по
     * правилам сравнения, которые задаются объектом класса {@code Comparator}.
     *
     * @param comparator компаратор, задающий правила сортировки
     * @return {@code true} - список отсортирован, {@code false} - список не отсортирован
     */
    @Override
    public boolean isSorted(Comparator<E> comparator) {
        for (int i = 1; i < size; i++) {
            if (comparator.compare(data[i - 1], data[i]) > 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Возвращает элементы {@code data} в виде {@code String}
     *
     * @return элементы массива в виде {@code String}
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            stringBuilder.append(data[i]);
            if (i < size - 1) {
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    /**
     * Реализует алгоритм быстрой сортировки.
     * Сравнение элементов производится по правилам, которые задаются
     * объектом класса {@code Comparator}.
     *
     * @param data       массив элементов
     * @param low        нижний индекс подмассива
     * @param high       верхний индекс подмассива
     * @param comparator компаратор, задающий правила сортировки
     */
    private void quickSort(E[] data, int low, int high, Comparator<E> comparator) {
        if (data.length == 0 || low >= high) return;

        int middle = low + (high - low) / 2;
        E midElement = data[middle];

        int i = low, j = high;
        while (i <= j) {
            while (comparator.compare(data[i], midElement) < 0) {
                i++;
            }
            while (comparator.compare(data[j], midElement) > 0) {
                j--;
            }

            if (i <= j) {
                E buffer = data[i];
                data[i] = data[j];
                data[j] = buffer;
                i++;
                j--;
            }
        }

        if (low < j) {
            quickSort(data, low, j, comparator);
        }
        if (high > i) {
            quickSort(data, i, high, comparator);
        }
    }

    /**
     * Производит проверку {@code index} на приналдежность диапазону
     * возможных значений индекса элемента массива.
     *
     * @param index индекс массива
     * @throws IndexOutOfBoundsException некорректный {@code index}
     */
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index +
                    ", Size: " + size);
        }
    }

    /**
     * Проверяет {@code data} на переполнение.
     * В случае переполнения вместимость массива увеличивается
     * ({@link ArrayList_AleksandrZmeyev#increaseCapacity()}).
     */
    private void checkCapacity() {
        if (size == data.length) {
            increaseCapacity();
        }
    }

    /**
     * Увеличивает вместимость {@code data} в
     * {@code INCREASE_MULTIPLIER} раз.
     */
    private void increaseCapacity() {
        if (data.length > Integer.MAX_VALUE / INCREASE_MULTIPLIER) {
            data = copyOf(data, size, Integer.MAX_VALUE);
        } else {
            data = copyOf(data, size, data.length * INCREASE_MULTIPLIER);
        }
    }

    /**
     * Создает массив указанной вместимости и копирует
     * в него указанный размер данных из массива, переданного в параметре
     *
     * @param data       массив с данными для копирования
     * @param sizeToCopy размер массива для копирования
     * @param capacity   вместимость созданного массива
     * @return ссылка на созданный массив
     * ({@link ArrayList_AleksandrZmeyev#increaseCapacity()}).
     */
    @SuppressWarnings("unchecked")
    private E[] copyOf(E[] data, int sizeToCopy, int capacity) {
        E[] newData = (E[]) new Object[capacity];
        for (int i = 0; i < sizeToCopy; i++) {
            newData[i] = data[i];
        }
        return newData;
    }
}
