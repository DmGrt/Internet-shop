package internet.shop.dao.impl;

import internet.shop.dao.interfaces.OrderDao;
import internet.shop.db.Storage;
import internet.shop.lib.Dao;
import internet.shop.models.Order;
import java.util.List;
import java.util.Optional;

@Dao
public class OrderDaoImpl implements OrderDao {

    @Override
    public Order create(Order order) {
        Storage.addOrder(order);
        return order;
    }

    @Override
    public Optional<Order> getById(Long orderId) {
        return Storage.orders.stream()
                .filter(x -> x.getId().equals(orderId))
                .findFirst();
    }

    @Override
    public Order update(Order order) {
        for (int i = 0; i < Storage.orders.size(); i++) {
            if (Storage.orders.get(i).getId().equals(order.getId())) {
                Order oldOrder = Storage.orders.get(i);
                Storage.orders.set(i, order);
                return oldOrder;
            }
        }
        return null;
    }

    @Override
    public boolean deleteById(Long orderId) {
        return Storage.orders.removeIf(x -> x.getId().equals(orderId));
    }

    @Override
    public boolean delete(Order order) {
        return deleteById(order.getId());
    }

    @Override
    public List<Order> getAllOrders() {
        return Storage.orders;
    }
}
