package gb.spring;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Cart {
    private Map<Product, Integer> items;

    public Cart() {
        items = new ConcurrentHashMap<Product, Integer>();
    }

    public Map<Product, Integer> getItems() {
        return items;
    }

    public void putInto(Product product) {
        if (items.containsKey(product)) {
            items.put(product, items.get(product) + 1);
        } else {
            items.put(product, 1);
        }
    }

    public void remove(Product product) {
        if (items.containsKey(product)) {
            if (items.get(product) > 1) {
                items.put(product, items.get(product) - 1);
            } else {
                items.remove(product);
            }
        }
    }

    public void showItems() {
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            System.out.println(entry.getKey().getName() + " : " + entry.getValue());
        }
    }


}
