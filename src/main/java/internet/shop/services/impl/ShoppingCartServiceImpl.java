package internet.shop.services.impl;

import internet.shop.dao.interfaces.ShoppingCartDao;
import internet.shop.lib.Inject;
import internet.shop.lib.Service;
import internet.shop.models.ShoppingCart;
import internet.shop.services.interfaces.ShoppingCartService;
import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Inject
    private ShoppingCartDao shoppingCartDao;

    @Override
    public ShoppingCart create(ShoppingCart shoppingCart) {
        return shoppingCartDao.create(shoppingCart);
    }

    @Override
    public Optional<ShoppingCart> getById(Long shoppingCartId) {
        return shoppingCartDao.getById(shoppingCartId);
    }

    @Override
    public ShoppingCart update(ShoppingCart shoppingCart) {
        return shoppingCartDao.update(shoppingCart);
    }

    @Override
    public boolean deleteById(Long shoppingCartId) {
        return shoppingCartDao.deleteById(shoppingCartId);
    }

    @Override
    public boolean delete(ShoppingCart shoppingCart) {
        return shoppingCartDao.delete(shoppingCart);
    }

    @Override
    public List<ShoppingCart> getAllShoppingCarts() {
        return shoppingCartDao.getAllShoppingCarts();
    }
}
