package task_3.service;

import java.lang.reflect.InvocationTargetException;

/**
 * Интерфейс, создающий экземпляры классов и внедряющий в них зависимости.
 * @author Slava Sles
 * @version 1.0
 */
public interface InjectionService {

    /**
     * Метод, создающий экземпляры классов и внедряющий в них зависимости.
     * @throws InvocationTargetException выбрасывается при возникновении ошибки вызова метода/конструктора у класса
     * @throws NoSuchMethodException выбрасывается при отсутствии запрашиваемого метода у класса
     * @throws InstantiationException выбрасывается при возникновении ошибки при создании экземпляра класса
     * @throws IllegalAccessException выбрасывается при возникновении ошибки получения доступа к классу
     */
    void injectDependencies() throws InvocationTargetException, NoSuchMethodException, InstantiationException,
            IllegalAccessException;
}
