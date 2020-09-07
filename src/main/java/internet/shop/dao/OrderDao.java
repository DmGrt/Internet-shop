package internet.shop.dao;

import internet.shop.db.Storage;
import internet.shop.model.Order;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public interface OrderDao {
    Order create(Order order);

    Optional<Order> get(Long id);

    List<Order> getAll();

    Order update(Order order);

    boolean delete(Long id);

    List<Order> getUserOrders(Long userId);
}
