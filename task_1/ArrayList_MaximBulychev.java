package task_1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;


/**
 * Реализация динамически расширяющегося списка на основе массива.
 * Включает в себя методы для вставки, получения и удаления элементов, а также
 * проверки наличия элемента, сортировки с помощью заданного компаратора и проверки
 * отсортированности. Данная реализация не является потокобезопасной.
 * Список имеет вместимость ({@code capacity}), которая является размером внутреннего массива
 * и размер ({@code size}), являющийся числом хранимых элементов в списке.
 *
 * @author Максим Булычев
 * @param <E> тип элементов, хранимых в ArrayList_MaximBulychev
 */
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
     * @param initialCapacity начальная вместимость внутреннего массива.
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
     * @return количество элементов в ArrayList_MaximBulychev
     */
    public int size() {
        return size;
    }

    /**
     * Вставляет переданный элемент в конец ArrayList_MaximBulychev.
     *
     * @param element элемент, который необходимо вставить.
     */
    public void add(E element) {
        checkCapacityAndResize();
        array[size] = element;
        size++;
    }

    /**
     * Вставляет переданный элемент в ArrayList_MaximBulychev на указанную позицию.
     *
     * @param index   позиция, на которую необходимо вставить элемент.
     * @param element элемент, который необходимо вставить.
     * @throws IndexOutOfBoundsException -
     */
    public void add(int index, E element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Индекс " + index +
                    " вне допустимого диапазона: 0 .. " + size);
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
     * @param index индекс элемента, который необходимо получить.
     * @return ссылка на найденный элемент.
     * @throws IndexOutOfBoundsException (см. {@link ArrayList_MaximBulychev#checkIndex(int)}).
     */
    public E get(int index) {
        checkIndex(index);
        return array[index];
    }

    /**
     * Заменяет элемент в ArrayList_MaximBulychev на указанной позиции на новый переданный элемент.
     *
     * @param index   позиция, на которой необходимо произвести замену элемента.
     * @param element элемент, который необходимо вставить.
     * @return - ссылка на удалённый элемент.
     * @throws IndexOutOfBoundsException (см. {@link ArrayList_MaximBulychev#checkIndex(int)}).
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
     * @param index индекс элемента, который необходимо удалить.
     * @return ссылка на удалённый элемент.
     * @throws IndexOutOfBoundsException (см. {@link ArrayList_MaximBulychev#checkIndex(int)}).
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
     * Проверяет, содержится ли переданный элемент в ArrayList_MaximBulychev.
     *
     * @param element элемент, наличие которого проверяется.
     * @return {@code true}, если элемент содержится, иначе {@code false}.
     */
    public boolean contains(E element) {
        boolean result = false;
        for (int i = 0; i < size && !result; i++) {
            if (Objects.equals(array[i], element)) {
                result = true;
            }
        }
        return result;
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
     * используя переданный компаратор.
     *
     * @param comparator компаратор, который будет использоваться для сравнения элементов.
     */
    public void quickSort(Comparator<E> comparator) {
        quickSortRecursive(comparator, 0, size - 1);
    }

    /**
     * Проверяет, отсортированы ли элементы в ArrayList_MaximBulychev в порядке возрастания,
     * используя переданный {@code Comparator}
     *
     * @param comparator компаратор, который будет использоваться для сравнения элементов.
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
     * @param size новая вместимость массива.
     * @throws IndexOutOfBoundsException если новая вместимость меньше 0.
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
     * Проверяет данный список на эквивалентность переданному. Возвращает {@code true} только в том случае,
     * если класс переданного объекта тоже является реализацией инерфейса {@code IntensiveList},
     * оба списка имеют одинаковый размер и все элементы в них попарно эквивалентны друг другу.
     *
     * @param o объект, с которым необходимо сравнить данный список.
     * @return {@code true}, если списки эквивалентны, иначе {@code false}.
     */
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;
        if (o instanceof IntensiveList<?> that) {
            if (size != that.size())
                return false;
            for (int i = 0; i < size; i++) {
                if (!Objects.equals(this.get(i), that.get(i)))
                    return false;
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Возвращает значение хэш-кода данного списка. Значение зависит от размера списка
     * и значения хэш-кода каждого хранящегося в нём элемента.
     * @return значение хэш-кода данного списка.
     */
    public int hashCode() {
        int result = Objects.hash(size);
        result = 31 * result + Arrays.hashCode(array);
        return result;
    }

    /**
     * Проверяет указанный индекс на вхождение в диапазон массива.
     *
     * @param index проверяемый индекс
     * @throws IndexOutOfBoundsException -
     */
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Индекс " + index +
                    " вне пределов массива: 0 .. " + (size - 1));
        }
    }

    /**
     * Проверяет, не заполнен ли массив и если заполнен,
     * увеличивает его вместимость до базового значения 10 или в 2 раза.
     */
    private void checkCapacityAndResize() {
        if (size == capacity) {
            // если произойдёт переполнение int
            if (capacity * 2 < capacity)
                changeInnerArrayCapacity(Integer.MAX_VALUE);
            else
                changeInnerArrayCapacity(Math.max(capacity * 2, 10));
        }
    }

    /**
     * Устанавливает вместимость внутреннего массива на указанное значение.
     * Если новая вместимость меньше количества элементов, хранящихся в массиве,
     * не поместившиеся элементы теряются.
     *
     * @param newCapacity новое значение вместимости внутреннего массива.
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

    /**
     * Рекурсивный метод сортировки внутреннего массива. Сравнение элементов производится
     * по правилам переданного компаратора.
     *
     * @param comparator переданный компаратор.
     * @param left       минимальный индекс подмассива.
     * @param right      максимальный индекс подмассива.
     */
    private void quickSortRecursive(Comparator<E> comparator, int left, int right) {
        if (right - left < 2) {
            manualSort(comparator, left, right);
            return;
        }
        int middle = (left + right) / 2;
        E pivot = getPivotAndSwap(comparator, left, middle, right);
        int divPoint = partition(comparator, left, right, pivot);
        quickSortRecursive(comparator, left, divPoint - 1);
        quickSortRecursive(comparator, divPoint + 1, right);
    }

    /**
     * Сортирует подмассив, содержащий не более двух элементов по правилам
     * переданного компаратора.
     *
     * @param left  минимальный индекс подмассива.
     * @param right максимальный индекс подмассива.
     */
    private void manualSort(Comparator<E> comparator, int left, int right) {
        if (left < right) {
            if (comparator.compare(array[left], array[right]) > 0) {
                swap(left, right);
            }
        }
    }

    /**
     * Метод производит разделение подмассива, ограниченного переданными индексами
     * на 2 подмассива. Левый подмассив содержит элементы, меньшие опорного,
     * а правый - большие или равные опорному.
     * Сравнение производится по правилам переданного компаратора.
     *
     * @param left     минимальный индекс подмассива.
     * @param right    максимальный индекс подмассива.
     * @param pivot опорный элемент.
     * @return минимальный индекс элемента большего либо равного опорному (точка разделения).
     */
    private int partition(Comparator<E> comparator, int left, int right, E pivot) {
        int leftPoint = left;
        int rightPoint = right;
        while (true) {
            while (leftPoint <= right && comparator.compare(array[leftPoint], pivot) < 0) leftPoint++;
            while (rightPoint >= left && comparator.compare(array[rightPoint], pivot) >= 0) rightPoint--;
            if (leftPoint < rightPoint)
                swap(leftPoint, rightPoint);
            else
                break;
        }
        return leftPoint;
    }

    /**
     * Выбирает опорный элемент.
     * Берутся 3 элемента подмассива: с минимальным, максимальным и средним индексом.
     * Элементы меняются местами таким образом, чтобы находиться в порядке возрастания
     * по правилам переданного компаратора, после чего возвращается элемент, находящийся
     * на средней позиции (он же имеет среднее значение).
     *
     * @param comparator переданный компаратор.
     * @param left       минимальный индекс подмассива.
     * @param middle     средний индекс подмассива.
     * @param right      максимальный индекс подмассива.
     * @return выбранный опорный элемент.
     */
    private E getPivotAndSwap(Comparator<E> comparator, int left, int middle, int right) {
        if (comparator.compare(array[left], array[middle]) > 0) {
            swap(left, middle);
        }
        if (comparator.compare(array[left], array[right]) > 0) {
            swap(left, right);
        }
        if (comparator.compare(array[middle], array[right]) > 0) {
            swap(middle, right);
        }
        return array[middle];
    }

    /**
     * Меняет два элемента с указанными индексами местами.
     *
     * @param a индекс первого элемента.
     * @param b индекс второго элемента.
     */
    private void swap(int a, int b) {
        E temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
}
