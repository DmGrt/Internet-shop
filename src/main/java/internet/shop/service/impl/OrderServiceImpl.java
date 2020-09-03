package internet.shop.service.impl;

import internet.shop.dao.daointerface.OrderDao;
import internet.shop.db.Storage;
import internet.shop.lib.Service;
import internet.shop.model.Order;
import internet.shop.model.ShoppingCart;
import internet.shop.service.serviceinterface.OrderService;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao;
    @Override
    public Order completeOrder(ShoppingCart shoppingCart) {
        return null; //TODO: impl pls
    }

    @Override
    public List<Order> getUserOrders(Long userId) {
        return Storage.orders.stream()
                .filter(order -> order.getUserId().equals(userId))
                .collect(Collectors.toList());
    }

    @Override
    public Order get(Long id) {
        return orderDao.get(id).orElseThrow();
    }

    @Override
    public List<Order> getAll() {
        return orderDao.getAll();
    }

    @Override
    public boolean delete(Long id) {
        return orderDao.delete(id);
    }
}
