package internet.shop.dao.interfaces;

import internet.shop.models.Order;
import java.util.List;
import java.util.Optional;

public interface OrderDao {
    Order create(Order order);

    Optional<Order> getById(Long orderId);

    Order update(Order order);

    boolean deleteById(Long orderId);

    boolean delete(Order order);

    List<Order> getAllOrders();
}
