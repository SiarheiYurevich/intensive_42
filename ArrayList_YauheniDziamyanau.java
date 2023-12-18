package com.dziamyanau.task1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
/**
 * Реализация интерфейса IntensiveList, создание коллекци похожей на ArrayList.
 * @param <E> тип элементов, хранящихся в списке
 */
public class ArrayList_YauheniDziamyanau <E> implements IntensiveList<E> {

    private Object[] arr;
    private final int CAPACITY= 10;
    private int size=0;
    private boolean isSorted=false;
    /**
     * Дефолтный конструктор, создает массив с дефолтным размером
     */
    public ArrayList_YauheniDziamyanau() {
        this.arr=new Object[CAPACITY];

    }
    /**
     * Конструктор, создает массив исходя из его длины, при этом создает его немного больше
     * чтобы мы могли добавать немного элементов в него без увеличения массива
     */
    public ArrayList_YauheniDziamyanau(int capacity) {
        if(capacity<0){
            throw new IllegalArgumentException("Неверный аргумент конструктора");}

        else if(capacity<=7){
            this.arr= new Object[CAPACITY];
        }


        else this.arr = new Object[(int) (capacity*1.5)];

    }
    /**
     * Возвращает размер нашей коллекции
     */
    @Override
    public int size() {
        return size;
    }
    /**
     * Добавляет элемент в конец списка
     */
    @Override
    public void add(E element) {

        if (size* 1.5 > arr.length) {
            riseArray();
        }
        for(int i=0; i<arr.length; i++) {

            if (arr[i] == null) {
                arr[i] = element;
                break;
            }
        }
        size++;

    }
    /**
     * Увеличивает наш массив arr
     */

    private void riseArray() {
      Object[] newArr= new Object[(int) (arr.length*1.5)];

      for(int i=0; i<this.arr.length; i++){
      newArr[i]=arr[i];}

       this.arr= newArr;
        }




    @Override
    public void add(int index, E element) {

        if (size* 1.5 > arr.length) {
            riseArray();
        }
        Object[] newArr= new Object[arr.length];
        for(int i=0; i<arr.length-1;i++){
            newArr[i+1]=arr[i];}


        int oldSize=size();
        split(index);


        for(int n=0; n<arr.length;n++){
            newArr[n]=arr[n];}


        newArr[index]=element;
        arr=newArr;
        this.size=oldSize;

        size++;
    }



    @Override
    public E get(int index) {
        if(index<0 && index>=size){
            throw new IllegalArgumentException("Неверный аргумент в методе get");
        }
        return (E) this.arr[index];
    }

    @Override
    public E set(int index, E element) {
        if(index<0){
            throw new IllegalArgumentException("Неверный аргумент в методе set");
        }
        this.arr[index]=element;
        size++;

        return (E) this.arr[index];
    }
    /**
     *Удаляет элемент массива по индексу
     */

    @Override
    public E remove(int index) {
        E returnElement= (E) this.arr[index];
        if(index==size-1) {
            arr[index] = null;
        }

        else {
            for (int i = index; i < size - 1; i++) {
                arr[i] = arr[i + 1];
            }
        }

        size--;
        if(size==0)
         arr=new Object[CAPACITY];
        else if (arr.length/size > 1.5) {
            reduceArray();
        }

        return returnElement;
    }
    /**
     * Уменьшает наш массив arr
     */

    private void reduceArray(){
        Object[] newArr;

        if(arr.length/1.5<10){
            newArr=new Object[CAPACITY];
        }


        else {
            newArr = new Object[(int) (arr.length / 1.5)];

            for (int i = 0; i < newArr.length; i++) {
                newArr[i] = arr[i];
            }
        }



        this.arr= newArr;
    }


    /**
     * Очищает наш массив и приводит в первоначальный размер
     */

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
                arr[i] = null;
            }
            arr = new Object[CAPACITY];
        size=0;
        }

    @Override
    public void quickSort(Comparator<E> comparator) {
        quickSort(comparator, arr, 0, size-1);
        isSorted=true;

    }

    /**
     * Быстрая сортировка массива
     */

    private void quickSort(Comparator<E> comparator, Object[] source, int leftBorder, int rightBorder) {
        int leftMarker = leftBorder;
        int rightMarker = rightBorder;
        E middle = (E) source[(leftBorder + rightBorder) / 2];

        while (leftMarker <= rightMarker) {
            while (comparator.compare((E) source[leftMarker], middle) < 0) {
                leftMarker++;
            }
            while (comparator.compare((E) source[rightMarker], middle) > 0) {
                rightMarker--;
            }

            if (leftMarker <= rightMarker) {
                if (leftMarker < rightMarker) {
                    Object tmp = source[leftMarker];
                    source[leftMarker] = source[rightMarker];
                    source[rightMarker] = tmp;
                }

                leftMarker++;
                rightMarker--;
            }
        }

        if (leftMarker < rightBorder) {
            quickSort(comparator, source, leftMarker, rightBorder);
        }
        if (leftBorder < rightMarker) {
            quickSort(comparator, source, leftBorder, rightMarker);
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("[");


        for(int i=0; i<size;i++){
            stringBuilder.append(arr[i]+" ");
        }

        stringBuilder.append("]");

        return stringBuilder.toString();
    }

    /**
     * проверяет отсортирован ли массив
     */

    public boolean isSorted(Comparator<E> comparator) {
        for (int i = 0; i < size - 1; i++) {
            if (comparator.compare((E) arr[i], (E) arr[i + 1]) > 0) {
                return false;
            }
        }

        return true;

    }
    /**
     * Получаем массив заданно длины От начала до x - заданная длина
     */
    @Override
    public void split(int newSize) {
        if(newSize< 0 || newSize > this.size ) {
            throw new RuntimeException("Неверный размер");
        }


        if(newSize < this.size) {
            Object[] newArr = new Object[newSize];
            for(int i = 0; i < newSize; i++) {
                newArr[i] = arr[i];
            }


            this.arr=newArr;
            this.size=newSize;
        }

    }
}
