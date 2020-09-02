package internet.shop.dao.impl;

import internet.shop.dao.interfaces.ShoppingCartDao;
import internet.shop.db.Storage;
import internet.shop.lib.Dao;
import internet.shop.models.ShoppingCart;
import java.util.List;
import java.util.Optional;

@Dao
public class ShoppingCartDaoImpl implements ShoppingCartDao {
    @Override
    public ShoppingCart create(ShoppingCart shoppingCart) {
        Storage.addShoppingCart(shoppingCart);
        return shoppingCart;
    }

    @Override
    public Optional<ShoppingCart> getById(Long shoppingCartId) {
        return Storage.shoppingCarts.stream()
                .filter(x -> x.getId().equals(shoppingCartId))
                .findFirst();
    }

    @Override
    public ShoppingCart update(ShoppingCart shoppingCart) {
        for (int i = 0; i < Storage.shoppingCarts.size(); i++) {
            if (Storage.shoppingCarts.get(i).getId().equals(shoppingCart.getId())) {
                ShoppingCart oldCart = Storage.shoppingCarts.get(i);
                Storage.shoppingCarts.set(i, shoppingCart);
                return oldCart;
            }
        }
        return null;
    }

    @Override
    public boolean deleteById(Long shoppingCartId) {
        return Storage.products.removeIf(x -> x.getId().equals(shoppingCartId));
    }

    @Override
    public boolean delete(ShoppingCart product) {
        return deleteById(product.getId());
    }

    @Override
    public List<ShoppingCart> getAllShoppingCarts() {
        return Storage.shoppingCarts;
    }
}
