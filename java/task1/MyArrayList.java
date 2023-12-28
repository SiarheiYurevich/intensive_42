package task1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;



/**
 * Кастомная реализация динамического массива.
 */
public class MyArrayList<E> implements IntensiveList<E> {

    private final int DEFAULT_CAPACITY = 10;
    private E[] storage;

    private int size;

    @SuppressWarnings("unchecked")
    public MyArrayList(int capacity) {
        if (capacity >= 0) {
            this.storage = (E[]) new Object[capacity];
        } else {
            this.storage = (E[]) new Object[DEFAULT_CAPACITY];
        }
    }

    @SuppressWarnings("unchecked")
    public MyArrayList() {
        storage = (E[]) new Object[DEFAULT_CAPACITY];
    }

    /**
     * Вернуть количество элементов в списке.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Добавить элемент в конец списка. Если количество элементов в списке
     * сравнялось с длиной внутреннего хранилища, увеличить размер хранилища.
     * @param element элемент для добавления в список.
     */
    @Override
    public void add(E element) {
        if (storage.length == size) {
            storage = grow();
        }
        storage[size] = element;
        ++size;
    }

    /**
     * Добавить элемент в список по индексу со сдвигом вправо элементов большего индекса.
     * Если количество элементов в списке сравнялось с длиной внутреннего хранилища,
     * увеличить размер хранилища.
     * @param index индекс внедряемого элемента.
     * @param element элемент для добавления в список.
     */
    @Override
    public void add(int index, E element) {
        Objects.checkIndex(index, size);
        if (storage.length == size) {
            storage = grow();
        }
        System.arraycopy(storage, index, storage, index + 1, size - index);
        storage[index] = element;
        ++size;
    }


    /**
     * Получить элемент списка по его индексу.
     * @param index индекс искомого элемента.
     * @return элемент списка.
     */
    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        return storage[index];
    }

    /**
     * Назначить новый элемент в списке по индексу.
     * @param index индекс старого элемента.
     * @param element новый элемент.
     * @return элемент, ранее находящийся по заданному индексу.
     */
    @Override
    public E set(int index, E element) {
        Objects.checkIndex(index, size);
        E oldValue = storage[index];
        storage[index] = element;
        return oldValue;
    }

    /**
     * Удалить элемент по заданному индексу. После удаления элементы
     * внутреннего массива, находящиеся после удаленного, сдвигаются влево.
     * @param index индекс удаляемого элемента.
     * @return удаленный элемент.
     */
    @Override
    public E remove(int index) {
        Objects.checkIndex(index, size);
        int newSize = size - 1;
        E oldValue = storage[index];

        if (newSize > index) {
            System.arraycopy(storage, index + 1, storage, index, newSize - index);
        }
        size--;
        return oldValue;
    }

    /**
     * Очистить хранилище. После очистки размеру хранилища
     * устанавливается значение по умолчанию.
     */
    @SuppressWarnings("unchecked")
    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
        storage = (E[]) new Object[DEFAULT_CAPACITY];
    }

    /**
     * Сортировка списка по правилам, установленным заданным
     * Comparator'ом.
     * @param comparator Comparator для сортировки.
     */
    @Override
    public void quickSort(Comparator<E> comparator) {
        E[] arrayWithoutNull = trimNullValues(storage);
        int lowIndex = 0;
        int highIndex = arrayWithoutNull.length - 1;
        utilQuickSort(arrayWithoutNull, comparator, lowIndex, highIndex);
        storage = fillWithNull(arrayWithoutNull);
    }

    /**
     * Проверить, отсортирован ли список. Отсортированным считается список,
     * не содержащий null-полей в пределах от 0 до size, а также соответствующий
     * правилам, установленным заданным Comparator'ом.
     * @param comparator Comparator, задающий правила сортировки.
     * @return список отсортирован / не отсортирован
     */
    @Override
    public boolean isSorted(Comparator<E> comparator) {
        for(int i = 0; i < storage.length - 1; i++) {
            if(i == size-1 && storage[i + 1] == null) {
                return true;
            }
            if(i < size && storage[i + 1] == null) {
                return false;
            }
            if(comparator.compare(storage[i], storage[i+1]) > 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Сократить размер списка до указанного размера. Сокращение
     * производится с начального элемента.
     * @param size новый размер списка.
     */
    @SuppressWarnings("unchecked")
    @Override
    public void split(int size) {
        if(size >= 0 && size <= this.size) {
            E[] newStorage = (E[]) new Object[DEFAULT_CAPACITY + size];
            System.arraycopy(storage, 0, newStorage, 0, size);
            storage = newStorage;
            this.size = size;
        }
    }

    /**
     * Увеличить размер внутреннего хранилища при достижении равенства
     * его длины и количества элементов.
     * @return новый массив длины oldLength * 1.5
     */
    @SuppressWarnings("unchecked")
    private E[] grow() {
        int oldLength = storage.length;
        int newLength = (int) (oldLength * 1.5);

        if (oldLength > 0) {
            storage = Arrays.copyOf(storage, newLength);
        } else {
            storage = (E[]) new Object[DEFAULT_CAPACITY];
        }
        return storage;
    }


    private void utilQuickSort(E[] array, Comparator<E> comparator, int lowIndex, int highIndex) {
        if (lowIndex >= highIndex) {
            return;
        }

//        int index = new Random().nextInt(highIndex - lowIndex) + lowIndex;
        int index = highIndex;

        E pivot = array[index];
        int leftPointer = doPartitioning(array, comparator, lowIndex, highIndex, pivot);

        utilQuickSort(array, comparator, lowIndex, leftPointer - 1);
        utilQuickSort(array, comparator, leftPointer + 1, highIndex);

    }

    private int doPartitioning(E[] array, Comparator<E> comparator, int lowIndex, int highIndex, E pivot) {
        int leftPointer = lowIndex;
        int rightPointer = highIndex;

        while (leftPointer < rightPointer) {

            while (comparator.compare(array[leftPointer], pivot) <= 0 &&
                   leftPointer < rightPointer) {

                leftPointer++;
            }
            while (comparator.compare(array[rightPointer], pivot) >= 0 &&
                   leftPointer < rightPointer) {

                rightPointer--;
            }

            swap(array, leftPointer, rightPointer);
        }
        swap(array, leftPointer, highIndex);
        return leftPointer;
    }

    private void swap(E[] array, int index1, int index2) {
        E temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    @SuppressWarnings("unchecked")
    private E[] trimNullValues(E[] array) {
        E[] newArray = (E[]) new Object[size];
        int notNullElements = 0;
        for(int i = 0, j = 0; i < array.length; i++) {
            if(array[i] != null) {
                newArray[j] = array[i];
                j++;
                notNullElements = j;
            }
        }
        E[] result = (E[]) new Object[notNullElements];
        for(int i = 0, j = 0; i < newArray.length; i++) {
            if(newArray[i] != null) {
                result[j] = newArray[i];
                j++;
            }
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    private E[] fillWithNull(E[] array) {
        Object[] newStorage = new Object[storage.length];
        for(int i = 0; i < newStorage.length; i++) {
            if(i < array.length) {
                newStorage[i] = array[i];
            } else {
                newStorage[i] = null;
            }
        }
        return (E[]) newStorage;
    }

    public E[] getStorage() {
        return storage;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MyArrayList{");
        if(size == 0) {
            sb.append("}");
            return sb.toString();
        }
        for(int i = 0; i < size - 1; i++) {
            sb.append(storage[i]).append(", ");
        }
        sb.append(storage[size - 1]).append("}");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MyArrayList<E> that = (MyArrayList<E>) o;

        if(this.size != that.size) return false;

        for (int i = 0; i < size; i++) {
            if(!this.storage[i].equals(that.storage[i])) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = DEFAULT_CAPACITY;
        result = 31 * result + Arrays.hashCode(storage);
        result = 31 * result + size;
        return result;
    }
}
