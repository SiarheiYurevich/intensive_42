package arrayList;

import java.util.Comparator;

/**
 * Реализация динамического списка, похожего на ArrayList, но не потокобезопасного
 * @param <E> тип элементов в списке
 */
public class ArrayList_GalinaSamokhina<E> implements IntensiveList<E> {
    public static final int DEFAULT_CAPACITY = 10;
    private int size;
    private Object[] elements;

    /**
     * Конструктор по умолчанию. Создает список с начальной емкостью DEFAULT_CAPACITY
     */
    public ArrayList_GalinaSamokhina() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    /**
     * Возвращает количество элементов в списке
     * @return количество элементов в списке
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Добавляет элемент в конец списка
     * Если список заполнен -> емкость увеличивается
     * @param element элемент для добавления
     */
    @Override
    public void add(E element) {
        if (elements.length == size) {
            increaseCapacity();
        }
        elements[size++] = element;
    }

    /**
     * Добавляет элемент под заданным индексом
     * От заданного индекса до последнего элементы копируются и передвигаются вперед на 1
     * После перемещения элементов -> элемент под заданным индксоим заменяется на новый
     * Если список заполнен -> емкость увеличивается
     * Если заданный индекс выходит за границы списка -> выброс Exception
     * @param index индекс для добавления нового элемента
     * @param element новый элемент для встваки
     */
    @Override
    public void add(int index, E element) {
        if (index < 0 && index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + " out of bounds of capacity");
        }
        if (size == elements.length) {
            increaseCapacity();
        }

        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;
        size++;
    }

    /**
     * Возвращает элемент под заданным индексом
     * Если заданный индекс выходит за границы списка -> выброс Exception
     * @param index индекс возвращаемого элемента
     * @return элемент под заданным индексом
     */
    @Override
    public E get(int index) {
        if (index < 0 && index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + " out of bounds of capacity");
        }
        return (E) elements[index];
    }

    /**
     * Заменяет элемент под заданным индексом на новый элемент
     * Заменяемый элемент удаляется
     * Если заданный индекс выходит за границы списка -> выброс Exception
     * @param index индекс заменяемого элемента на новый
     * @param element новый элемент для замены элемента в списке
     */
    @Override
    public void set(int index, E element) {
        if (index < 0 && index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + " out of bounds of capacity");
        }
        elements[index] = element;
    }

    /**
     * Удаляет элемент под заданным индексом в списке
     * От заданного индекса +1 до последнего элементы копируются и передвигаются назад на 1
     * После перемещения элементов -> последнему элементу присваивается null
     * Если заданный индекс выходит за границы списка -> выброс Exception
     * @param index
     */
    @Override
    public void remove(int index) {
        if (index < 0 && index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + " out of bounds of capacity");
        }
            int numMoved = size - index - 1;
            if (numMoved > 0) {
                System.arraycopy(elements, index + 1, elements, index, numMoved);
            }
            elements[--size] = null;
    }

    /**
     * Удаляет все элементы списка
     * Приводит емкость к дефолтному размеру
     */
    @Override
    public void clear() {
        elements = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    /**
     * Реализует быструю сортировку, дефолт по возврастанию
     * @param comparator Comparator используемый для сортировки
     */
    @Override
    public void quickSort(Comparator comparator) {
        if (elements != null && elements.length != 0) {
            sort((E[]) elements, 0, size() - 1, comparator);
        }
    }

    /**
     * Проверяет отсортирован список или нет
     * @return true список отсортирован, false не отсортирован
     */
    @Override
    public boolean isSorted() {
        for (int i = 1; i < size; ++i) {
            if (((Comparable) elements[i - 1]).compareTo(elements[i]) > 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Обрезает список до указанного размера
     * Элементы, находящиеся под индексами не входящих в обрезанный список, удаляются
     * Если заданный индекс выходит за границы списка -> выброс Exception
     * @param size новый желаемый размер списка
     */
    @Override
    public void split(int size) {
        if (size < 0 && size > this.size) {
            throw new IllegalArgumentException("Invalid size: " + size);
        }
            this.size = size;
        }

    private void increaseCapacity() {
        int newCapacity = (int) (size * 1.5) + 1;

        Object[] tempArray = elements;
        elements = new Object[newCapacity];
        System.arraycopy(tempArray, 0, elements, 0, size);
    }

    private void sort(E[] array, int low, int high, Comparator comparator) {
        int i = low;
        int j = high;
        E pivot = array[low + (high - low) / 2];

        while (i <= j) {
            while (comparator.compare(array[i], pivot) < 0) {
                ++i;
            }

            while (comparator.compare(array[j], pivot) > 0) {
                --j;
            }

            if (i <= j) {
                E temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                ++i;
                --j;
            }
        }

        if (low < j) {
            sort(array, low, j, comparator);
        }

        if (i < high) {
            sort(array, i, high, comparator);
        }
    }
}