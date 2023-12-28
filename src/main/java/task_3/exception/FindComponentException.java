package task_3.exception;

/**
 * Класс исключения, выбрасываемого при отсутствии запрашиваемого пакета/класса, данных о запрашиваемом классе.
 */
public class FindComponentException extends IntensiveContextException {

    /**
     * Конструктор класса.
     * @param exception исключение, выброшенное методами других классов
     */
    public FindComponentException(Exception exception) {
        super("Ошибка поиска компонентов контекста: не найдены пакет/файлы или данные о классе", exception);
    }
}
