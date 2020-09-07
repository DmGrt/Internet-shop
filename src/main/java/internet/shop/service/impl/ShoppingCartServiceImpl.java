package internet.shop.service.impl;

import internet.shop.dao.ShoppingCartDao;
import internet.shop.lib.Inject;
import internet.shop.lib.Service;
import internet.shop.model.Product;
import internet.shop.model.ShoppingCart;
import internet.shop.service.ShoppingCartService;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Inject
    private ShoppingCartDao shoppingCartDao;

    @Override
    public ShoppingCart create(ShoppingCart shoppingCart) {
        return shoppingCartDao.create(shoppingCart);
    }

    @Override
    public ShoppingCart addProduct(ShoppingCart shoppingCart, Product product) {
        shoppingCartDao.get(shoppingCart.getId())
                .get()
                .getProducts()
                .add(product);
        shoppingCartDao.update(shoppingCart);
        return shoppingCart;
    }

    @Override
    public ShoppingCart deleteProduct(ShoppingCart shoppingCart, Product product) {
        shoppingCartDao.get(shoppingCart.getId())
                .get()
                .getProducts()
                .remove(product);
        shoppingCartDao.update(shoppingCart);
        return shoppingCart;
    }

    @Override
    public void clear(ShoppingCart shoppingCart) {
        shoppingCartDao.get(shoppingCart.getId())
                .get()
                .getProducts()
                .clear();
        shoppingCartDao.update(shoppingCart);
    }

    @Override
    public ShoppingCart getByUserId(Long userId) {
        return shoppingCartDao.getAll().stream()
                .filter(shoppingCart -> shoppingCart.getUserId().equals(userId))
                .findFirst()
                .get();
    }

    @Override
    public boolean delete(Long id) {
        shoppingCartDao.delete(id);
        return true;
    }
}
