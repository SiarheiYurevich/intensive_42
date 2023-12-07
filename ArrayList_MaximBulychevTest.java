import java.util.ArrayList;

public class ArrayList_MaximBulychevTest {
    public static void main(String[] args) {
        ArrayList_MaximBulychev<String> a = new ArrayList_MaximBulychev<>();
        ArrayList<String> ab = new ArrayList<>();
        a.add("Апельсины");
        a.add("Бананы");
        a.add("Лимоны");
        a.add("Грейпфруты");
        a.add("Гранаты");
        a.add("Яблоки");
        a.add("Персики");
        a.add("Мандарины");
        a.add("Груши");
        a.add("Манго");
        System.out.println(a + " Размер: " + a.size());
        a.add(4, "Помидоры");
        System.out.println(a + " Размер: " + a.size());
        String lemons = a.set(2, "Нектарины");
        System.out.println(a + " Размер: " + a.size());
        System.out.println(lemons);
        String tomatoes = a.remove(4);
        System.out.println(a + " Размер: " + a.size());
        System.out.println(tomatoes);
        a.add(null);
        System.out.println(a + " Размер: " + a.size());
        a.remove(10);
        System.out.println(a + " Размер: " + a.size());
        System.out.println(a + " Размер: " + a.size());
        a.quickSort((o1, o2) -> o1.compareTo(o2));
        System.out.println(a + " Размер: " + a.size());
    }
}
