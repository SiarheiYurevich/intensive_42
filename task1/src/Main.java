
public class Main {
    public static void main(String[] args) {
        ArrayList_TimurAgeev<Integer> list = new ArrayList_TimurAgeev<>();

        list.add(3);
        list.add(5);
        list.add(2);
        list.add(4);
        list.add(1);
        list.add(3);
        list.add(5);
        list.add(2);
        list.add(4);
        list.add(1);

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        list.add(2, 199);

        System.out.println("||||||||||||||||||||||||||||||");


        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}