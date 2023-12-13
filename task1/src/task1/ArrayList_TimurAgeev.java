package task1;

import java.util.Arrays;
import java.util.Comparator;

public class ArrayList_TimurAgeev<E> implements IntensiveList<E> {
    private E[] array;
    /**
     * размер списка
     */
    private int size;
    /**
     * длина массива по умолчанию
     */
    private final static int DEFAULT_CAPACITY = 10;

    @SuppressWarnings("unchecked")
    public ArrayList_TimurAgeev() {
        array = (E[]) new Object[DEFAULT_CAPACITY];
    }

    /**
     * @return Возвращает текущий размер листа
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Если размер листа равен или больше длине массива,
     * то создается новый массив с теми же данными и длиной массива в 1.5 раза больше текущего,
     * Вставляет переданный элемент в конец списка и увеличивает размер массива на 1
     *
     * @param element элемент, который необходимо вставить
     */
    @Override
    public void add(E element) {
        if (size >= array.length) {
            increaseCapacity();
        }

        array[size] = element;
        size++;
    }

    /**
     * Проверяет валидность index и в случае если он не валиден выбрасывает {@link IndexOutOfBoundsException}.
     * Если размер листа равен или больше длине массива,
     * то создается новый массив с теми же данными и длиной массива в 1.5 раза больше текущего,
     * Пересоздает массив с теми же элементами, но начиная с index копирует количество элементов равных size - index и вставляет их начиная с индекса равного index + 1.
     * тем самым вставляет переданный элемент по указанному индексу
     * увеличивает размер на 1
     *
     * @param index   индекс, по которому нужно вставить элемент
     * @param element элемент, который необходимо вставить по указанному индексу
     */
    @Override
    public void add(int index, E element) {
        checkIndex(index);

        if (size >= array.length - 1) {
            increaseCapacity();
        }

        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = element;
        size++;
    }

    /**
     * Проверяет валидность index и в случае если он не валиден выбрасывает {@link IndexOutOfBoundsException}.
     * Возвращает элемент списка по переданному индексу.
     *
     * @param index - индекс, по которому нужно найти элемент
     * @return элемент по указанному индексу
     */
    @Override
    public E get(int index) {
        checkIndex(index);

        return array[index];
    }


    /**
     * Проверяет валидность "index" и в случае если он не валиден выбрасывает {@link IndexOutOfBoundsException}.
     * Записывает переданный элемент по указанному индексу.
     *
     * @param index   индекс по которому будет перезаписан элемент
     * @param element элемент, который будет записан по переданному индексу.
     * @return возвращает элемент, который был перезаписан
     */
    @Override
    public E set(int index, E element) {
        checkIndex(index);

        E replaceableElement = array[index];
        array[index] = element;

        return replaceableElement;
    }

    /**
     * Проверяет валидность index и в случае если он не валиден выбрасывает {@link IndexOutOfBoundsException}
     * Пересоздает массив с теми же элементами, но начиная с index + 1 копирует количество элементов равных size - index - 1 и вставляет их начиная с индекса равного index + 1.
     * устанавливает последнему элементу списка значение null.
     * Тем самым удаляет элемент по указанному индексу
     * уменьшает размер списка на 1
     *
     * @param index индекс, по которму будет удален элемент.
     * @return возвращает удаленный элемент.
     */
    @Override
    public E remove(int index) {
        checkIndex(index);

        E removedElement = array[index];

        System.arraycopy(array, index + 1, array, index + 1, size - index - 1);
        size--;
        array[size] = null;

        return removedElement;
    }

    /**
     * Пересоздает массив с null значениями, размером DEFAULT_CAPACITY = 10 и устанавливает размер 0.
     */
    @SuppressWarnings("unchecked")
    @Override
    public void clear() {
        array = (E[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    /**
     * Поверяет не отсортирован ли список. Если список отсортирован - работа метода прекращается. Если список не остортирован -
     * производит быструю сортировку по возрастанию используя переданный компаратор.
     *
     * @param comparator переданный компаратор, по которому происходит сравнение объектов
     */
    @Override
    public void quickSort(Comparator<E> comparator) {
        if (isSorted(comparator)) {
            return;
        }

        sortArray(array, 0, size - 1, comparator);
    }


    /**
     * Поверяет не отсортирован ли список.
     * Если размер списка <= 1, проверка не проводится, считается что список отсортирован.
     *
     * @param comparator переданный компаратор, по которому происходит сравнение объектов
     * @return true, если список отсортирован; иначе -  false
     */
    @Override
    public boolean isSorted(Comparator<E> comparator) {
        if (size <= 1) {
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
     * Создается копия массива в который копируются значения с текущего массива начиная с 0-го индекса элемента, заканчивая индексом элемента равным size.
     * устанавливает размер списка переданному значению size
     *
     * @param size устанавливаемый размер списка
     */
    @Override
    public void split(int size) {
        if (size < 0) {
            throw new IndexOutOfBoundsException();
        }

        System.arraycopy(array, 0, array, size, 0);
        this.size = size;
    }

    /**
     * Пересоздает массив с длинной равной 1.5 длины существующего массива и копирует в него все элементы из существующего.
     */
    private void increaseCapacity() {
        array = Arrays.copyOf(array, (int) (size * 1.5));
    }

    /**
     * Проверяет валидность "index" и в случае если он не валиден выбрасывает {@link IndexOutOfBoundsException}.
     *
     * @param index index, подлежащий проверке
     */
    private void checkIndex(int index) {
        if (index > size) {
            throw new IndexOutOfBoundsException("index can't be more than size. index = " + index + ", size = " + size);
        }

        if (index < 0) {
            throw new IndexOutOfBoundsException("index can't be 0");
        }
    }

    /**
     * производит быструю сортировку по возрастанию используя рекурсию.
     *
     * @param array      переданный массив, который подлежит сортировке.
     * @param left       индекс начала отсчета с начала массива.
     * @param right      индекс начала отсчета с конца массива.
     * @param comparator переданный компаратор, по которому происходит сравнение объектов
     */
    private void sortArray(E[] array, int left, int right, Comparator<E> comparator) {
        if (array.length == 0 || left >= right) {
            return;
        }

        int i = left;
        int j = right;

        E supportElement = array[left];

        while (i <= j) {
            while (comparator.compare(array[i], supportElement) < 0) {
                i++;
            }

            while (comparator.compare(array[j], supportElement) > 0) {
                j--;
            }

            if (i <= j) {
                E temp = array[i];
                array[i] = array[j];
                array[j] = temp;

                i++;
                j--;
            }
        }

        if (j > left) {
            sortArray(array, left, j, comparator);
        }

        if (i < right) {
            sortArray(array, i, right, comparator);
        }
    }
}
