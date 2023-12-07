import java.util.Comparator;


public class ArrayList_MaximBulychev<E> implements IntensiveList<E> {

    private E[] array;
    private final int DEFAULT_CAPACITY = 10;
    private int size;
    private int capacity;

    /**
     * Стандартный конструктор класса ArrayList_MaximBulychev.
     * Инициализирует внутренний массив, задаёт стандартную вместимость равную 10.
     */
    @SuppressWarnings("unchecked")
    public ArrayList_MaximBulychev() {
        array = (E[]) new Object[DEFAULT_CAPACITY];
        capacity = DEFAULT_CAPACITY;
        size = 0;
    }

    /**
     * Конструктор класса ArrayList_MaximBulychev, позволяющий задать начальную
     * вместимость внутреннего массива.
     *
     * @param initialCapacity - начальная вместимость внутреннего массива.
     */
    @SuppressWarnings("unchecked")
    public ArrayList_MaximBulychev(int initialCapacity) {
        array = (E[]) new Object[DEFAULT_CAPACITY];
        capacity = initialCapacity;
        size = 0;
    }

    /**
     * Возвращает количество элементов, содержащихся в ArrayList_MaximBulychev.
     *
     * @return number of elements stored in ArrayList_MaximBulychev
     */
    public int size() {
        return size;
    }

    /**
     * Вставляет переданный элемент в конец ArrayList_MaximBulychev.
     *
     * @param element - элемент, который необходимо вставить.
     */
    public void add(E element) {
        checkCapacityAndResize();
        array[size] = element;
        size++;
    }

    /**
     * Вставляет переданный элемент в ArrayList_MaximBulychev на указанную позицию.
     *
     * @param index   - позиция, на которую необходимо вставить элемент.
     * @param element - элемент, который необходимо вставить.
     * @throws IndexOutOfBoundsException -
     */
    public void add(int index, E element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Индекс " + index + " вне допустимого диапазона: 0 .. " + size);
        }
        if (index == size) {
            add(element);
        } else {
            checkCapacityAndResize();
            for (int i = size - 1; i >= index; i--) {
                array[i + 1] = array[i];
            }
            array[index] = element;
            size++;
        }
    }

    /**
     * Возвращает элемент ArrayList_MaximBulychev по указанной позиции.
     *
     * @param index - индекс элемента, который необходимо получить.
     * @return ссылка на найденный элемент.
     * @throws IndexOutOfBoundsException -
     */
    public E get(int index) {
        checkIndex(index);
        return array[index];
    }

    /**
     * Заменяет элемент в ArrayList_MaximBulychev на указанной позиции на новый переданный элемент.
     *
     * @param index   - позиция, на которой необходимо произвести замену элемента.
     * @param element - элемент, который необходимо вставить.
     * @return - ссылка на удалённый элемент.
     * @throws IndexOutOfBoundsException -
     */
    public E set(int index, E element) {
        checkIndex(index);
        E oldElement = array[index];
        array[index] = element;
        return oldElement;
    }

    /**
     * Удаляет элемент с указанным индексом из ArrayList_MaximBulychev
     *
     * @param index - индекс элемента, который необходимо удалить.
     * @return ссылка на удалённый элемент.
     */
    public E remove(int index) {
        checkIndex(index);
        E oldElement = array[index];
        for (int i = index + 1; i < size; i++) {
            array[i - 1] = array[i];
        }
        array[size - 1] = null;
        size--;
        return oldElement;
    }

    /**
     * Удаляет все элементы из ArrayList_MaximBulychev,
     * вместимость приводит к стандартному значению.
     */
    @SuppressWarnings("unchecked")
    public void clear() {
        array = (E[]) new Object[DEFAULT_CAPACITY];
        capacity = DEFAULT_CAPACITY;
        size = 0;
    }

    /**
     * Сортирует элементы ArrayList_MaximBulychev в порядке возрастания,
     * используя переданный {@code Comparator}
     *
     * @param comparator - компаратор, который будет использоваться для сравнения элементов.
     */
    public void quickSort(Comparator<E> comparator) {
        for (int i = 0; i < size; i++) {
            int min = i;
            for (int j = i + 1; j < size; j++) {
                if (comparator.compare(array[min], array[j]) > 0) {
                    min = j;
                }
            }
            E temp = array[i];
            array[i] = array[min];
            array[min] = temp;
        }
        //todo Быструю сортировку, а не перестановкой.
    }

    /**
     * Проверяет, отсортированы ли элементы в ArrayList_MaximBulychev в порядке возрастания,
     * используя переданный {@code Comparator}
     *
     * @param comparator - компаратор, который будет использоваться для сравнения элементов.
     * @return {@code true}, если список отсортирован, иначе {@code false}.
     **/
    public boolean isSorted(Comparator<E> comparator) {
        boolean result = true;
        for (int i = 1; i < size; i++) {
            if (comparator.compare(get(i - 1), get(i)) > 0) {
                result = false;
                break;
            }
        }
        return result;
    }

    /**
     * Уменьшает вместимость внутреннего массива до указанного значения.
     * Элементы, которые оказались за пределами, не сохраняются.
     *
     * @param size - новая вместимость массива.
     * @throws IndexOutOfBoundsException - если новая меньше 0
     */
    public void split(int size) {
        if (size < 0) {
            throw new IndexOutOfBoundsException("Неверный размер массива: " + size);
        }
        changeInnerArrayCapacity(size);
    }

    /**
     * Возвращает содержимое ArrayList_MaximBulychev как строку.
     *
     * @return строка элементов.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        if (size > 0) {
            for (int i = 0; i < size - 1; i++) {
                sb.append(array[i]).append(", ");
            }
            sb.append(array[size - 1]);
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * Проверяет указанный индекс на вхождение в диапазон массива.
     *
     * @param index - проверяемый индекс
     * @throws IndexOutOfBoundsException -
     */
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Индекс " + index + " вне пределов массива: 0 .. " + (size - 1));
        }
    }

    /**
     * Проверяет, не заполнен ли массив и если заполнен,
     * увеличивает его вместимость до базового значения 10 или в 2 раза.
     */
    private void checkCapacityAndResize() {
        if (size == capacity) {
            changeInnerArrayCapacity(Math.max(capacity * 2, 10));
        }
    }

    /**
     * Устанавливает вместимость внутреннего массива на указанное значение.
     * Если новая вместимость меньше, не вместившиеся элементы теряются.
     *
     * @param newCapacity - новое значение вместимости внутреннего массива.
     */
    @SuppressWarnings("unchecked")
    private void changeInnerArrayCapacity(int newCapacity) {
        E[] newArray = (E[]) new Object[newCapacity];
        for (int i = 0; i < newCapacity && i < capacity; i++) {
            newArray[i] = array[i];
        }
        capacity = newCapacity;
        size = Math.min(size, newCapacity);
        array = newArray;
    }
}
