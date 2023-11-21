import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor


public class ShopService {
    private ProductRepo productRepo = new ProductRepo();
    @Getter
    private OrderRepo orderRepo = new OrderMapRepo();

    public Order addOrder(List<String> productIds) throws ProductNotFoundException {
        List<Product> products = new ArrayList<>();
        for (String productId : productIds) {
            Optional<Product> productToOrder = productRepo.getProductById(productId);
            if (productToOrder.isEmpty()) {
                System.out.println("Product mit der Id: " + productId + " konnte nicht bestellt werden!");
                throw new ProductNotFoundException("Product mit der Id: " + productId + " nicht gefunden.");
            }
            products.add(productToOrder.get());
        }

        Order newOrder = new Order(OrderStatus.PROCESSING, UUID.randomUUID().toString(), products, LocalDateTime.now());

        return orderRepo.addOrder(newOrder);
    }

    public List<Order> getOrdersByStatus(OrderStatus status) {
        return orderRepo.getOrders().stream()
                .filter(order -> order.status() == status)
                .collect(Collectors.toList());
    }

    public Order updateOrder(String orderId, OrderStatus newStatus) {
        Optional<Order> existingOrder = Optional.ofNullable(orderRepo.getOrderById(orderId));

        if (existingOrder.isPresent()) {
            Order updatedOrder = existingOrder.get().withStatus(newStatus);
            orderRepo.updateOrder(updatedOrder);
            return updatedOrder;
        } else {
            throw new IllegalArgumentException("Order mit der ID: " + orderId + " nicht gefunden.");
        }
    }

}
