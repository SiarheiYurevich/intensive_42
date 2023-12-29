package context.impl;

import annotation.IntensiveComponent_YaroslavRulev;
import context.IntensiveContext_YaroslavRulev;
import service.FactoryService;
import service.InjectionService;
import service.SearchService;
import service.impl.FactoryServiceImpl;
import service.impl.InjectServiceImpl;
import service.impl.SearchServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс IntensiveContextImpl_YaroslavRulev является реализацией интерфейса IntensiveContext_YaroslavRulev.
 * Он представляет контекст, который управляет внедрением зависимостей и созданием объектов для фреймворка Intensive.
 */
public class IntensiveContextImpl_YaroslavRulev implements IntensiveContext_YaroslavRulev {
    private final Map<String, Object> BMap = new HashMap<>();
    private final InjectionService injectionService;
    private final SearchService searchService;
    private final FactoryService factoryService;
    /**
     * Конструктор нового экземпляра IntensiveContextImpl_YaroslavRulev с указанными пакетами для сканирования классов компонентов.
     */

    public IntensiveContextImpl_YaroslavRulev(String packages) {
        injectionService = new InjectServiceImpl();
        searchService = new SearchServiceImpl(packages);
        factoryService = new FactoryServiceImpl();
        scanBean();

    }

    @Override
    public <T> T getObject(Class<T> type) {
        Object obj = BMap.get(type.getName());
        injectBean(obj);

        return (T) obj;

    }
    /**
     * Сканирует классы компонентов и создает экземпляры соответствующих объектов.
     */
    private void scanBean() {
        try {
            List<Class<?>> classes = searchService.getClasses();
            for (Class<?> selectedClass : classes) {
                if (selectedClass.isAnnotationPresent(IntensiveComponent_YaroslavRulev.class)) {
                    Object bean = factoryService.createInstance(selectedClass);
                    BMap.put(selectedClass.getName(), bean);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);

        }
    }
    /**
     * Внедряет необходимые зависимости в указанный объект.
     * @param object объект, в который будут внедрены зависимости.
     */
    private void injectBean(Object object) {
        injectionService.injectDependency(object, BMap);
    }

}
