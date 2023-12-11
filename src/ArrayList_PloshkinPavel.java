import java.util.Comparator;

/**
 * Класс, реализующий интерфейс IntensiveList, притворяющийся ArrayList-ом
 * @param <E> тип элементов листа
 */
public class ArrayList_PloshkinPavel<E> implements IntensiveList<E>{

    /**
     * Дефолтный размер листа
     */
    private static final int DEFAULT_CAPACITY = 1;

    /**
     * Текущий размер листа
     */
    private int size;

    /**
     * Внутренний массив с элементами
     */
    private E[] objects;

    /**
     * Флаг показывающий сортирован ли лист
     */
    private boolean isSorted;

    /**
     * Конструктор с размером
     * @param capacity размер создаваемого листа
     */
    public ArrayList_PloshkinPavel(int capacity) {
        if (capacity > 0){
            this.objects = (E[]) new Object[capacity];
        }
        else throw new IllegalArgumentException("Размер должен быть >0");
    }

    /**
     * Пустой конструктор создающий лист с дефолтным размером
     */
    public ArrayList_PloshkinPavel(){
        this(DEFAULT_CAPACITY);
    }

    /**
     * @return кол-во элементов в листе
     */
    @Override
    public int size() {
        return objects.length;
    }

    /**
     * Метод для добавления элемента в список
     * @param element элемент, который будет добавлен в список
     */
    @Override
    public void add(E element) {
        E[] newArray = (E[]) new Object[size+1];
        System.arraycopy(objects, 0, newArray, 0, size);

        newArray[size] = element;
        this.size++;
        this.objects = newArray;
    }

    /**
     * Метод для добавления элемента в список по индексу
     * @param index индекс в списке
     * @param element добавляемый элемент
     */
    @Override
    public void add(int index, E element) {
        E[] newArray = (E[]) new Object[size+1];
        System.arraycopy(objects, 0, newArray, 0, index);
        System.arraycopy(objects, index, newArray, index + 1, size - index);

        newArray[index] = element;
        this.objects = newArray;
        this.size++;
    }

    /**
     * Получение элемента списка
     * @param index индекс получаемого элемента
     * @return элемент с указанным индексом
     */
    @Override
    public E get(int index) {
        return objects[index];
    }

    /**
     * Замена элемента по индексу
     * @param index индекс в списке
     * @param element элемент вставляемый в список
     * @return замененный элемент, который был в списке по указанному индексу
     */
    @Override
    public E set(int index, E element) {
        E prev = objects[index];
        objects[index] = element;
        return prev;
    }

    /**
     * Удаление элемента списка
     * @param index индекс удаляемого элемента
     * @return удаленный элемент, который был в списке по указанному индексу
     */
    @Override
    public E remove(int index) {
        E prev = objects[index];
        objects[index] = null;
        System.arraycopy(objects, index + 1,objects, index, size-index - 1);
        return prev;
    }

    /**
     * Очищение списка
     */
    @Override
    public void clear() {
        this.objects = (E[]) new Object[0];
        this.size = 0;
    }

    /**
     * Выполнение быстрой сортировки списка
     * @param comparator используемый компаратор
     */
    @Override
    public void quickSort(Comparator<E> comparator) {
        quickSort(comparator, 0, size-1);
        isSorted = true;
    }

    /**
     * Внутренний метод сортировки списка методом быстрой сортировки
     * @param low начальный индекс массива
     * @param high конечный индекс массива
     * @param comparator используемый компаратор
     */
    private void quickSort(Comparator<E> comparator, int low, int high){
        if (low >= high){
            isSorted = true;
            return;
        }
        E element = objects[high];
        int i = (low-1);

        for (int j = low; j < high; j++){
            if (comparator.compare(element, objects[j])>0){
                i++;
                E temp = objects[i];
                objects[i] = objects[j];
                objects[j] = temp;
            }
        }
        E temp = objects[i+1];
        objects[i+1] = objects[high];
        objects[high] = temp;

        int idx = i+1;
        quickSort(comparator, low, (idx-1));
        quickSort(comparator, (idx+1), high);
    }

    /**
     * Проверка того сортирован ли список
     * @return возвращает boolean значение сортировки
     */
    @Override
    public boolean isSorted() {
        return isSorted;
    }

    /**
     * Отделение части списка
     * @param size размер отделяемого списка
     */
    @Override
    public void split(int size) {
        if (size > this.size || size < 0){
            throw new IllegalArgumentException("Invalid argument");
        }

        E[] newArray = (E[]) new Object[size];
        System.arraycopy(objects, 0, newArray, 0, size);
        objects = newArray;
        this.size = size;
    }
}
