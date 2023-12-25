package org.example.context;

public interface IntensiveContext_TimurAgeev {
    /**
     * Возвращает экземпляр переданного класса
     * Если экземпляр класса уже создавался, то возвращает ранее созданный
     *
     * @param type тип класса, экземпляр которого нужно создать
     * @return созданный объект
     */
    <T> T getObject(Class<T> type);
}
