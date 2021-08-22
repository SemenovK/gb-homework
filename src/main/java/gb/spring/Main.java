package gb.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        final ApplicationContext context = new AnnotationConfigApplicationContext("gb.spring");
        ProductRepository productRepository = context.getBean(ProductRepository.class);
        Cart cart = context.getBean(Cart.class);
        final Scanner scanner = new Scanner(System.in);

        while (true) {
            productRepository.showProducts();
            System.out.println("Введите команду:");
            final String str = scanner.nextLine().toUpperCase();

            if (Commands.QUIT.toString().equals(str)) {
                return;
            } else if (Commands.NEW_CART.toString().equals(str)) {
                cart = context.getBean(Cart.class);
            } else if (Commands.SHOW_CART.toString().equals(str)) {
                cart.showItems();
            } else if (str.startsWith(Commands.PUT.toString())) {
                int id = Integer.parseInt(str.replaceAll("[\\D]", ""));
                if (id != 0) {
                    cart.putInto(productRepository.getProducts(id));
                }
            } else if (str.startsWith(Commands.REMOVE.toString())) {
                int id = Integer.parseInt(str.replaceAll("[\\D]", ""));
                if (id != 0) {
                    cart.remove(productRepository.getProducts(id));
                }
            }

        }
    }
}
