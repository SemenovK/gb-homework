import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@WebServlet(name = "ProductListServlet" , urlPatterns = "/products")
public class ProductListServlet extends HttpServlet {
    private List<Product> productList;

    @Override
    public void init() throws ServletException {
        productList = new ArrayList<>();

        Random rand = new Random();
        for (int i = 0; i < 10; i++) {
            productList.add(new Product(i,"Product - "+i,  rand.nextDouble()*100+1));
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        for (Product product : productList) {
            resp.getWriter().write(product.toString());
        }

    }
}
