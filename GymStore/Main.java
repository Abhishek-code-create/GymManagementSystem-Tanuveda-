import controller.ProductController;
import dao.ProductDAO;
import view.ProductView;

public class Main {
    public static void main(String[] args) {
        ProductDAO dao = new ProductDAO();
        ProductController controller = new ProductController(dao, null);
        ProductView view = new ProductView(controller);
        controller = new ProductController(dao, view);
        controller.showProducts();
    }
}
