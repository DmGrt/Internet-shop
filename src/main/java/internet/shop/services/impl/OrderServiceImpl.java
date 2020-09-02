package internet.shop.services.impl;

import internet.shop.dao.interfaces.OrderDao;
import internet.shop.lib.Inject;
import internet.shop.lib.Service;
import internet.shop.models.Order;
import internet.shop.services.interfaces.OrderService;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    @Inject
    private OrderDao orderDao;

    @Override
    public Order create(Order order) {
        return orderDao.create(order);
    }

    @Override
    public Optional<Order> getById(Long orderId) {
        return orderDao.getById(orderId);
    }

    @Override
    public Order update(Order order) {
        return orderDao.update(order);
    }

    @Override
    public boolean deleteById(Long orderId) {
        return orderDao.deleteById(orderId);
    }

    @Override
    public boolean delete(Order order) {
        return orderDao.delete(order);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderDao.getAllOrders();
    }
}
