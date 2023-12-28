package task_3.objectDefinitionScanners;
import task_3.annotations.IntensiveComponent_AleksandrZmeyev;
import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Класс, реализующий методы сканер. Основная задача класса -
 * сканирование пакетов на наличие классов, помеченных аннотацией
 * {@link task_3.annotations.IntensiveComponent_AleksandrZmeyev}.
 */
public class ClassPathObjectDefinitionScanner implements ObjectDefinitionScanner {

    /**
     * Сканер класса {@link Reflections}. Необходим для сканирования пакетов.
     */
    private Reflections scanner;

    /**
     * Хэш таблица, в которой интерфейсу сопоставлена реализация.
     * Необходима для упрощения поиска реализации по интерфейсу.
     */
    private Map<Class, Class> implementationByInterface = new HashMap<>();

    /**
     * Коллекция классов, имеющих аннотацию {@link IntensiveComponent_AleksandrZmeyev}.
     */
    private Set<Class<?>> objectDefinitions;

    /**
     * Конструктор {@link ClassPathObjectDefinitionScanner}.
     * Инициализирует {@code scanner} и задает корневой пакет
     * для поиска классов.
     *
     * @param packageToScan имя пакета
     */
    public ClassPathObjectDefinitionScanner(String packageToScan) {
        this.scanner = new Reflections(packageToScan);
        objectDefinitions = scanner.getTypesAnnotatedWith(IntensiveComponent_AleksandrZmeyev.class)
                .stream().filter(objectDefinition -> !objectDefinition.isInterface())
                .collect(Collectors.toSet());
    }

    /**
     * Геттер для поля {@code scanner}.
     */
    public Reflections getScanner() {
        return scanner;
    }

    /**
     * Геттер для поля {@code objectDefinitions}.
     */
    public Set<Class<?>> getObjectDefinitions() {
        return objectDefinitions;
    }

    /**
     * Метод возвращает класс, который реализует
     * передающийся в параметре {@code infce} интерфейс.
     *
     * @param infce интерфейс, реализацию которого необходимо найти
     * @return {@code Class}, который является реализацией {@code infce}
     */
    @SuppressWarnings("unchecked")
    public <T> Class<? extends T> getImplementationClass(Class<T> infce) {
        return implementationByInterface.computeIfAbsent(infce, implClass -> {

            Set<Class<? extends T>> classes = scanner.getSubTypesOf(infce);
            if (classes.size() != 1) {
                throw new RuntimeException(infce + " has 0 or more than 1 implementations");
            }
            implClass = classes.iterator().next();
            if (objectDefinitions.contains(implClass)) {
                return classes.iterator().next();
            } else {
                return null;
            }
        });
    }
}
