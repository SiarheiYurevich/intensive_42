package task_1.intensive_list_interface;

import java.util.Comparator;

/**
 * Интерфейс списка
 *
 * @param <E> тип элементов, хранимых в {@code IntensiveList}
 * @author Александр Змеев
 */
public interface IntensiveList<E> {

    /**
     * Возвращает количество элементов, хранящихся в {@code IntensiveList}
     *
     * @return количество элементов
     */
    int size();

    /**
     * Производит вставку элемента в конец {@code IntensiveList}.
     *
     * @param element элемент, который необходимо вставить
     */
    void add(E element);

    /**
     * Производит вставку элемента в {@code IntensiveList} по указанному индексу.
     *
     * @param index   позиция в {@code IntensiveList}, в которую вставляется элемент
     * @param element элемент, который необходимо вставить
     */
    void add(int index, E element);

    /**
     * Возвращает элемент {@code IntensiveList} по указанному индексу.
     *
     * @param index позиция в {@code IntensiveList}, элемент которой необходимо получить
     * @return ссылка элемента по указанному индексу
     */
    E get(int index);

    /**
     * Заменяет элемент {@code IntensiveList} по указанному индексу на элемент, переданный в аргументе.
     *
     * @param index   позиция в {@code IntensiveList}, элемент которой необходимо заменить
     * @param element элемент для замены
     * @return ссылка на старый элемент по указанному индексу
     */
    E set(int index, E element);

    /**
     * Удаляет элемент {@code IntensiveList} по указанному индексу.
     *
     * @param index позиция в {@code IntensiveList}, элемент которой необходимо удалить
     * @return ссылка на удаленный элемент по указанному индексу
     */
    E remove(int index);

    /**
     * Удаляет все элементы из списка.
     */
    void clear();

    /**
     * Производит быструю сортировку {@code IntensiveList}.
     * Сравнение элементов производится по правилам, которые задаются объектом класса {@code Comparator}.
     *
     * @param comparator компаратор, задающий правила сортировки
     */
    void quickSort(Comparator<E> comparator);

    /**
     * Возвращает состояние упорядоченности элементов в {@code IntensiveList},
     * сравнивая элементы в естественном порядке.
     *
     * @return true - список отсортирован, false - список не отсортирован
     */
    boolean isSorted();

    /**
     * Возвращает состояние упорядоченности элементов в {@code IntensiveList} по
     * правилам сравнения, которые заданы объектом класса {@code Comparator}.
     *
     * @param comparator компаратор, задающий правила сортировки
     * @return true - {@code IntensiveList} отсортирован,
     * false - {@code IntensiveList} не отсортирован
     */
    boolean isSorted(Comparator<E> comparator);

    /**
     * Обрезает {@code IntensiveList} до указанного размера.
     *
     * @param size новый размер массива
     */
    void split(int size);
}