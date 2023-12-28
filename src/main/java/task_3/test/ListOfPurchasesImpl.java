package src.main.java.task_3.test;

import src.main.java.task_3.annotation.InjectComponent_StanislavFedin;
import src.main.java.task_3.annotation.IntensiveComponent_StanislavFedin;

@IntensiveComponent_StanislavFedin
public class ListOfPurchasesImpl implements ListOfPurchases {
    private final String nomenclature;

    @InjectComponent_StanislavFedin(productName = "Tomat")
    private Product tomat;

    @InjectComponent_StanislavFedin(productName = "Apple")
    private Product apple;

    public ListOfPurchasesImpl() {
        this.nomenclature = "What I want to buy: ";
    }

    @Override
    public void listOfPurchases() {
        System.out.println(nomenclature);
        System.out.println("1. " + apple.getProductName());
        System.out.println("2. " + tomat.getProductName());
    }

    public void setProduct(Object product) {
        if (product instanceof Product) {
            if (((Product) product).getClass().getSimpleName().equals(Tomat.class.getSimpleName()))
                this.tomat = (Product) product;

            if (((Product) product).getClass().getSimpleName().equals(Apple.class.getSimpleName()))
                this.apple = (Product) product;
        }
    }
}
