public class Product {

    private int id;
    private String title;
    private double cost;


    public Product(int id, String title, double cost) {
        this.id = id;
        this.title = title;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "<p>Product{" +
                "<b>id=" + id +
                ", title='" + title + '\'' +
                ", cost=" + String.format("%.2f",cost) +
                "</b>}</p>";
    }

}
