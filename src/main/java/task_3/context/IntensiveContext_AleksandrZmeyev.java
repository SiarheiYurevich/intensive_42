package task_3.context;

import task_3.objectFactories.ObjectFactory;
import task_3.objectDefinitionScanners.ClassPathObjectDefinitionScanner;
import task_3.objectDefinitionScanners.ObjectDefinitionScanner;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс, реализующий интерфейс контекста.
 * Управляет созданием объектов.
 *
 * @author Aleksandr Zmeyev
 */
public class IntensiveContext_AleksandrZmeyev implements ApplicationContext {


    /**
     * Поле фабрики объектов {@link ObjectFactory},
     * которая занимается созданием объектов в контексте
     */
    private ObjectFactory objectFactory;

    /**
     * Хэш таблица для хранения объектов контекста.
     */
    private Map<Class, Object> objectMap = new HashMap<>();

    /**
     * Сканер, позволяющий найти классы, помеченные аннотацией
     * {@link task_3.annotations.IntensiveComponent_AleksandrZmeyev}.
     */
    private ObjectDefinitionScanner objectDefinitionScanner;

    /**
     * Конструктор класса {@link IntensiveContext_AleksandrZmeyev}
     * Создает {@link ObjectDefinitionScanner}
     * и {@link ObjectFactory}.
     *
     * @param packageName имя пакета, который необходимо просканировать
     *                    на наличие классов, помеченных
     *                    {@link task_3.annotations.IntensiveComponent_AleksandrZmeyev}
     */
    public IntensiveContext_AleksandrZmeyev(String packageName) {
        this.objectDefinitionScanner = new ClassPathObjectDefinitionScanner(packageName);
        this.objectFactory = new ObjectFactory(this.objectDefinitionScanner);
        objectFactory.setContext(this);
    }

    /**
     * Метод позволяет получить объект {@link ObjectDefinitionScanner},
     * основная задача которого - сканирование пакетов
     * на наличие классов, помеченных аннотацией
     * {@link task_3.annotations.IntensiveComponent_AleksandrZmeyev}.
     *
     * @return объект ObjectDefinitionScanner
     */
    public ObjectDefinitionScanner getObjectDefinitionScanner() {
        return objectDefinitionScanner;
    }

    /**
     * Метод возвращает объект, класс которого помечен аннотацией
     * {@link task_3.annotations.IntensiveComponent_AleksandrZmeyev}.
     * Если объект находится в {@code objectMap}, то он возвращается
     * из неё, если такого не существует, то он создается с помощью
     * {@link ObjectFactory} и помещается в {@code objectMap}.
     *
     * @param type класс или интерфейс, из которого необходимо создать объект
     * @return объект на основе класса, указанного в параметре
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T> T getObject(Class<T> type) {

        if (objectMap.containsKey(type)) {
            return (T) objectMap.get(type);
        }

        Class<? extends T> implClass = type;
        if (type.isInterface()) {
            implClass = objectDefinitionScanner.getImplementationClass(type);
        }

        T object = objectFactory.getObject(implClass);

        objectMap.put(type, object);

        return object;
    }
}
