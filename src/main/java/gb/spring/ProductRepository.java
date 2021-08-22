package gb.spring;


import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ProductRepository {
    private List<Product> items = new CopyOnWriteArrayList<>();

    public void showProducts() {
        for (int i = 0; i < items.size(); i++) {
            System.out.println(items.get(i).toString());
        }
    }

    public void showProducts(int id) {
        for (Product item : items) {
            if (item.getId() == id) {
                System.out.println(item.toString());
            }
        }
    }

    public Product getProducts(int id) {
        for (Product item : items) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    public List<Product> getProducts() {
        return items;
    }


    public void putProduct(Product product) {
        if (!items.contains(product)) {
            items.add(product);
        }
    }

}
