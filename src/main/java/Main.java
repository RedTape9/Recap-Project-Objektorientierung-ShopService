import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ProductRepo productRepo = new ProductRepo();
        productRepo.addProduct(new Product("2", "Birne"));
        productRepo.addProduct(new Product("3", "Banane"));
        productRepo.addProduct(new Product("4", "Kiwi"));
        List<Product> products = productRepo.getProducts();
        OrderListRepo orderRepo = new OrderListRepo();
        orderRepo.addOrder(new Order(OrderStatus.PROCESSING, "1", products, LocalDateTime.now()));

        ShopService shopService = new ShopService(productRepo, orderRepo);

        Order order = orderRepo.getOrderById("1");
        System.out.println(order);

        shopService.getOrdersByStatus(OrderStatus.PROCESSING).forEach(System.out::println);


    }
}
