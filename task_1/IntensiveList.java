package task_1;

import java.util.Comparator;



public interface IntensiveList<E> {
    int size();
    void add(E element);
    void add(int index, E element);
    E get(int index);
    E set(int index, E element);
    E remove(int index);

    //удаляем все элементы, capacity приводим к дефолтному
    void clear();

    //реализуем быструю сортировку, дефолт по возрастанию
    void quickSort(Comparator<E> comparator);
    boolean isSorted(Comparator<E> comparator);

    //обрезаем список до указанного размера
    void split(int size);
}
